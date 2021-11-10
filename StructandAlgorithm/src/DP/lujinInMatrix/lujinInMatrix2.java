package DP.lujinInMatrix;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName lujinInMatrix2.java
 * @Description 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
 * 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
 * 网格中的障碍物和空位置分别用 1 和 0 来表示。
 *
 * dp[i][j] ：表示从（0 ，0）出发，到(i, j) 有dp[i][j]条不同的路径。
 * 有障碍的话，其实就是标记对应的dp = 0
 *
 * 初始化的时候注意一点:
 * 没有障碍的时候，因为从(0, 0)的位置到(i, 0)的路径只有一条，所以都初始化为1
 * 但以第一列为例子，(i, 0) 这条边有了障碍之后，障碍之后（包括障碍）都是走不到的位置了，所以障碍之后的dp[i][0]应该还是初始值0。
 * @createTime 2021年09月02日 16:16:00
 */
public class lujinInMatrix2 {
    public static int uniquePathsWithObstacles(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] dp = new int[n][m];
        dp[0][0] = 1 - matrix[0][0];
        for (int i = 1; i < m; i++) {//初始化第0行,当前没有障碍物，而且从左边能走过来（即左边被初始化过）
            if (matrix[0][i] == 0 && dp[0][i - 1] == 1) {
                dp[0][i] = 1;
            }
        }
        for (int i = 1; i < n; i++) {//初始化第0列,当前没有障碍物，而且从上边能走过来（即上边被初始化过）
            if (matrix[i][0] == 0 && dp[i-1][0] == 1) {
                dp[i][0] = 1;

            }
        }
        //经过上述的初始化，只有能走到的第一行和第一列是1，其余由于new的性质是0
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (matrix[i][j] == 1) {
                    continue;
                }
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[n - 1][m - 1];
    }
}
