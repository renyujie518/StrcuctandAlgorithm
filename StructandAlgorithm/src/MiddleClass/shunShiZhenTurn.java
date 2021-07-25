package MiddleClass;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName shunShiZhenTurn.java
 * @Description
 * 给定一个正方形矩阵， 只用有限几个变量，
 * 实现矩阵中每个位置的数顺时针转动 90度，
 * 比如如下的矩阵
 * 0 1 2 3
 * 4 5 6 7
 * 8 9 10 11
 * 12 13 14 15
 * 矩阵应该被调整为：
 * 12 8 4 0
 * 13 9 5 1
 * 14 10 6 2
 * 15 11 7 3
 *
 * 思路：
 * 大致与之前顺时针旋转打印的那个一样  把最外围的框搞定 设置一个func规定如何旋转 在两个点往对角线走  剥洋葱的感觉
 *每个框必是正方形  比如4x4  可以分成3组（n-1，也是Bcol-Acol+1个）围绕框的中心点形成3个正方形，每组还是4个
 * @createTime 2021年07月24日 22:09:00
 */
public class shunShiZhenTurn {
    public static void shunShiZhenTurn(int[][] matrix) {
        int Arow = 0;
        int Acol = 0;
        int Brow = matrix.length - 1;
        int Bcol = matrix[0].length - 1;
        //直到越界停止（两者不能形成一个框  即不能在一条直线上）,每次A点向右下方移动  B点向左上方移动
        while (Arow <Brow) {
            rotate(matrix, Arow++, Acol++, Brow--, Bcol--);
        }
    }

    private static void rotate(int[][] matrix, int Arow, int Acol, int Brow, int Bcol) {
        int zushu = Bcol-Acol;
        int temp = 0;
        for (int i = 0; i < zushu; i++) {
            //谁由谁给谁
            temp = matrix[Arow][Acol + i];
            matrix[Arow][Acol + i] = matrix[Brow-i][Acol];
            matrix[Brow - i][Acol] = matrix[Brow][Bcol - i];
            matrix[Brow][Bcol - i] = matrix[Arow + i][Bcol];
            matrix[Arow + i][Bcol] = temp;
        }
    }

    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i != matrix.length; i++) {
            for (int j = 0; j != matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{0,1,2,3},{4,5,6,7},{8,9,10,11},{11,12,13,14}};
        printMatrix(matrix);
        shunShiZhenTurn(matrix);
        System.out.println("=========");
        printMatrix(matrix);

    }
}
