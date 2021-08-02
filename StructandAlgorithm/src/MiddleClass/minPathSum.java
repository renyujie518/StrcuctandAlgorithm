package MiddleClass;

import static util.generateRandomMatrix.generateRandomMatrix;
import static util.printMatrix.printMatrix;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName minPathSum.java
 * @Description
 * 给你一个二维数组matrix， 其中每个数都是正数， 要求从左上角走到右下角。每 一步只能向右或者向下， 沿途经过的数字要累加起来。
 * 最后请返回最小的路径和。
 *
 * 这道题以前在学习DP的时候做过 本次再做一遍  主要是采用空间压缩的技巧 将二维数组转化为一维数组
 * @createTime 2021年07月28日 15:05:00
 */
public class minPathSum {
    //普通的dp解法
    public static int minPathSumDP1(int[][] matrix) {
        //dp[i][j]  表示从（0，0）到（i，j）的最小路径和  最终返回的结果就是最右下角
        int m = matrix.length;
        int n = matrix[0].length;
        int dp[][] = new int[m][n];
        //状态转移
        for (int i = 0; i < m; i++) {//从左往右
            for (int j = 0; j < n; j++) {//从上到下
                //首先处理第一行  到达第一行只能往右走  所以累计路径只能该位置的值+左边的
                if (i == 0 && j != 0) {
                    dp[i][j] = matrix[i][j] + dp[i][j - 1];
                } else if (i != 0 && j == 0) {//处理第一列  到达第一列只能往下走  所以累计路径只能该位置的值+上边的
                    dp[i][j] = matrix[i][j] + dp[i-1][j];
                } else if (i != 0 && j != 0) {//其余情况  不管是从左边到达，还是从上边到达（i,j）这个点，取最小+该位置
                    dp[i][j] = matrix[i][j] + Math.min(dp[i][j - 1], dp[i - 1][j]);
                }

            }
        }
        return dp[m - 1][n - 1];
    }

    //压缩空间的dp解法  一维的数组就可以（适用于当前状态和临近的点有关）
    public static int minPathSumDP2(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        //dp[i] 表示从 (0, 0) 到达第 i - 1 行的最短路径值
        int dp[] = new int[n];
        // 状态转移
        for (int i = 0; i < m ; i++) {
            for (int j = 0; j < n ; j++) {
                if (i == 0 && j != 0) {////首先处理第一行 ,只与左边的值相关
                    dp[j] = matrix[i][j] + dp[j - 1];
                } else if (i != 0 && j == 0) {//第一列，只与上的值相关
                    dp[j] = matrix[i][j] + dp[j];
                } else if (i != 0 && j != 0) {
                    dp[j] = matrix[i][j] + Math.min(dp[j], dp[j - 1]);
                }
            }
        }

        // 返回结果
        return dp[n - 1];
    }

    public static void main(String[] args) {
        int[][] m = generateRandomMatrix(3, 4);
      /*  int[][] m = { { 1, 3, 5, 9 }, { 8, 1, 3, 4 }, { 5, 0, 6, 1 },
                { 8, 8, 4, 0 } };*/
        printMatrix(m);
        System.out.println(minPathSumDP1(m));
        System.out.println(minPathSumDP2(m));

    }
}
