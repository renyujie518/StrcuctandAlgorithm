package DP;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName RobotLocation.java
 * @Description
 * 机器人达到指定位置方法数【题目】假设有排成一行的N个位置，记为1~N，N一定大于或等于2。
 * 开始时机器人在其中的M位置上(M一定是1~N中的一个)，机器人可以往左走或者往右走，如果机器人来到1位置，那么下一步只能往右来到2位置;
 * 如果机器人来到N位置，那么下一步只能往左来到N-1位置（在边界也得走，但不能越界）。规定机器人必须走K步，最终能来到P位置(P也一定是1~N中的一个)的方法有多少种。
 * 给定四个参数N、M、K、P，返回方法数。
 *
 * 【举例】N=5,M=2,K=3,P=3上面的参数代表所有位置为12345。
 * 机器人最开始在2位置上，必须经过3步，最后到达3位置。
 * 走的方法只有如下3种:
 * 1)从2到1，从1到2，从2到3
 * 2)从2到3，从3到2，从2到3
 * 3)从2到3，从3到4，从4到3所以返回方法数3。
 *
 * N=3,M=1,K=3,P=3上面的参数代表所有位置为123。机器人最开始在1位置上，必须经过3步，最后到达3位置。怎么走也不可能，所以返回方法数0。
 * @createTime 2021年03月19日 13:24:00
 */
public class RobotLocation {

    //首先先写暴力递归的版本
    // N : 位置为1 ~ N，固定参数
    // cur : 当前在cur位置，可变参数
    // rest : 还剩res步没有走，可变参数
    // E : 最终目标位置是E，固定参数
    // 该函数的含义：只能在1~N这些位置上移动，当前在cur位置，走完rest步之后，停在P位置的方法数作为返回值返回
    public static int process(int N, int cur, int rest, int E) {
        // 如果没有剩余步数了，当前的cur位置就是最后的位置
        // 如果最后的位置停在P上，那么之前做的移动是有效的
        // 如果最后的位置没在P上，那么之前做的移动是无效的
        if (rest == 0) {
            return cur == E ? 1 : 0;
        }
        //没进上面那个if，就是rest>0，还有路可以走
        //当前的cur位置在1位置上，那么当前这步只能从1走向2
        // 后续的过程就是，来到2位置上，还剩rest-1步要走
        if (cur == 1) {
            return process(N, 2, rest - 1, E);
        }
        // 如果还有rest步要走，而当前的cur位置在N位置上，那么当前这步只能从N走向N-1
        // 后续的过程就是，来到N-1位置上，还剩rest-1步要走
        if (cur == N) {
            return process(N, N - 1, rest - 1, E);
        }
        // 如果还有rest步要走，而当前的cur位置在中间位置上，那么当前这步可以走向左，也可以走向右
        // 走向左之后，后续的过程就是，来到cur-1位置上，还剩rest-1步要走
        // 走向右之后，后续的过程就是，来到cur+1位置上，还剩rest-1步要走
        // 走向左、走向右是截然不同的方法，所以总方法数要都算上
        return process(N, cur + 1, rest - 1, E) + process(N, cur - 1, rest - 1, E);
    }
    public static int waysRecursion(int N, int M, int K, int P) {
        // 参数无效直接返回0
        if (N < 2 || K < 1 || M < 1 || M > N || P < 1 || P > N) {
            return 0;
        }
        // 总共N个位置，从M点出发，还剩K步，返回最终能达到P的方法数
        return process(N, M, K, P);
    }


    //下面改为DP(考虑位置依赖的顺序)
    public static  int waysDP(int N,int M,int K,int P){  //N 位置为1 ~ N  开始位置为M，必须走K步，终点在P
        // 参数无效直接返回0
        if (N < 2 || K < 1 || M < 1 || M > N || P < 1 || P > N) {
            return 0;
        }
        int[][] dp = new int[K + 1][N + 1];//有两个可变参数 走过的步数(范围最多到K+1) ,cur当前位置(范围最多N+1)
        dp[0][P] = 1;//开始的时候步数为为0,终点的位置是P
        for (int i = 1; i <=K ; i++) {//步数从1开始走，最多为K
            for (int j=1;j<=N;j++){  // 当前位置在【1，N】
                if (j==1){ // 当前位置在1
                    dp[i][j] = dp[i-1][2];//只能从1走向2,总步数减小1步（取决于右上角）
                }else if (j==N){// 当前位置在N
                    dp[i][j] = dp[i - 1][N - 1];//只能从N走向N-1,总步数减小1步（取决于左上角）
                }else {//中间位置
                    dp[i][j]= dp[i-1][j-1]+dp[i-1][j+1];//可以向左走（j-1）,也可以向右走（j+1）,但每次剩余的步数都要-1
                }
            }
        }
        return dp[K][M];  //结束的时候步数为为K,开始位置在M
    }
    public static void main(String[] args) {
        System.out.println(waysRecursion(7, 4, 9, 5));
        System.out.println(waysDP(7, 4, 9, 5));

    }

}
