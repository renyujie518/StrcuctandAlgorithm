package util;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName generateRandomMatrix.java
 * @Description 创建随机值得二维矩阵
 * @createTime 2021年07月28日 16:52:00
 */
public class generateRandomMatrix {
    public static int[][] generateRandomMatrix(int rowSize, int colSize) {
        if (rowSize < 0 || colSize < 0) {
            return null;
        }
        int[][] result = new int[rowSize][colSize];
        for (int i = 0; i != result.length; i++) {
            for (int j = 0; j != result[0].length; j++) {
                result[i][j] = (int) (Math.random() * 10);
            }
        }
        return result;
    }
}
