package DP;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName DontOut.java
 * @Description Bob的生存概率【题目】给定五个参数n,m,i,j,k。
 * 表示在一个N*M的区域，Bob处在(i,j)点，每次Bob等概率的向上、下、左、右四个方向移动一步，Bob必须走K步。
 * 如果走完之后，Bob还停留在这个区域上，就算Bob存活，否则就算Bob死亡。请求解Bob的生存概率，返回字符串表示分数的方式。
 * @createTime 2021年03月19日 21:33:00
 */
public class DontOut {

    //N*M区域为存活位置，Bob从（row,column）出发，走rest步后，获得的存活次数
    public static long process(int N,int M,int row,int column,int rest){
        if(row<0 || row == N || column<0 || column==M ){
            return 0;  //一旦越界返回0存活
        }
        if (rest == 0){ //没越界但是步数走完了，说明这是一种方案，返回1
            return 1;
        }
        //还没走完，也没越界，继续走(4种走法)
        long live = process(N, M, row - 1, column, rest - 1);
        live += process(N, M, row + 1, column, rest - 1);
        live += process(N, M, row, column - 1, rest - 1);
        live += process(N, M, row, column + 1, rest - 1);
        return live;

    }
    public static long gcd(long m, long n) {
        return n == 0 ? m : gcd(n, m % n);  //递归
    }
    public static String aliveRecursion(int N, int M, int i, int j, int K) {
        long all = (long) Math.pow(4, K); //所有情况 4^k 种
        long live = process(N, M, i, j, K);//存活情况
        long gcd = gcd(all, live);//找到最大公约数 这样可以返回最简表达
        return String.valueOf((live / gcd) + "/" + (all / gcd));
    }

    //可变参数3个 是个体
    private static String aliveDP(int N, int M, int i, int j, int K) {
        int[][][] dp = new int[N + 2][M + 2][K + 1];  //下面的row col都是从1开始的，所以+2
        for (int row = 1; row <= N; row++) {
            for (int col = 1; col <= M; col++) {
                dp[row][col][0] = 1;  //第一层，一步都不走，肯定安全，所以第1层都是1
            }
        }
        for (int rest = 1; rest <= K; rest++) { //步数从1开始（体的高）
            for (int row = 1; row <= N; row++) {
                for (int col = 1; col <= M; col++) {
                    dp[row][col][rest] = dp[row - 1][col][rest - 1];
                    dp[row][col][rest] += dp[row + 1][col][rest - 1];
                    dp[row][col][rest] += dp[row][col - 1][rest - 1];
                    dp[row][col][rest] += dp[row][col + 1][rest - 1];
                }
            }
        }
        long all = (long) Math.pow(4, K);
        long live = dp[i + 1][j + 1][K];
        long gcd = gcd(all, live);
        return String.valueOf((live / gcd) + "/" + (all / gcd));


    }
    public static void main(String[] args) {
        int N = 10;
        int M = 10;
        int i = 3;
        int j = 2;
        int K = 5;
        System.out.println(aliveRecursion(N, M, i, j, K));
        System.out.println(aliveDP(N, M, i, j, K));
    }


}
