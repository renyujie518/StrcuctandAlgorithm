package Tree;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName UniqueBST.java
 * @Description 给定一个非负整数n，代表二叉树的节点个数。返回能形成多少种不同的二叉树结构
 * @createTime 2021年03月31日 15:25:00
 */
public class UniqueBST {
    public static  int process(int n){
        if (n<0){
            return 0;
        }
        if (n == 0){
            return 1; //空树
        }
        if (n==1){
            return 1;//只有一个节点，只能一种结构
        }
        if (n==2){
            return 2;//两个节点，要么连在一起，要么各自为树
        }
        int res = 0;
        for (int leftNum = 0;leftNum<=n-1;leftNum++){  //leftNum 给左树分配的节点数
            int leftways = process(leftNum);
            int rightWays = process(n - 1 - leftNum); //-1是因为有个头结点
            res += leftways * rightWays;  //注意 是乘
        }
        return res;
    }

    public static int numTreesDp(int n) {
        if (n<2){
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[0] = 1;  //basecase 空树
        for (int i = 1; i < n + 1; i++) { //节点数为i的情况
            for (int j = 0; j <= i - 1; j++) {  //左侧节点个数为j,j最多不能超过总计点数i再减1（头结点），右侧节点个数i-j-1
                dp[i] += dp[j] * dp[i - j - 1];
            }
        }

        return dp[n];//返回节点数在n的时候的情况
    }
}
