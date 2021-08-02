package util;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName printMatrix.java
 * @Description 打印一个二维矩阵
 * @createTime 2021年07月28日 16:53:00
 */
public class printMatrix {
    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i != matrix.length; i++) {
            for (int j = 0; j != matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
