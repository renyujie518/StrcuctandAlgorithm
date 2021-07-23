package MiddleClass;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName isContainsiin2Matrix.java
 * @Description 给定一个元素为非负整数的二维数组matrix，
 *每行和每列都是从小到大有序的。 再给定一个非负整数aim， 请判断aim是否在matrix中。
 *
 * 思路类似于猜价格的游戏，高了，低了这种方式，这样最多走（n+m）次，n,m代表二维矩阵的行数和列数
 * 可以预料到  这个矩阵右下角最大  左上角最小
 * 从右上角开始 如果K比curr小，curr这条列下的都可以不要（每列从小到大排序）可以往左走一步
 *             如果K比curr大，curr这条行左边的都可以不要（每行从小到大排列）可以向下走一步
 *             直到边境还没找到就是没有
 *
 * @createTime 2021年07月22日 17:30:00
 */
public class isContainsIn2Matrix {
    public static boolean isContainsIn2Matrix(int[][] matrix, int K) {
        //从右上角  往左下角走
        int row = 0;
        int col = matrix[0].length - 1;
        while (row < matrix.length && col > -1) {//边界范围内
            if (matrix[row][col] == K) {
                return true;
            } else if (matrix[row][col] > K) {//宗旨的方向是往左下角走
                col--;
            } else {
                row++;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][] { { 0, 1, 2, 3, 4, 5, 6 },// 0
                { 10, 12, 13, 15, 16, 17, 18 },// 1
                { 23, 24, 25, 26, 27, 28, 29 },// 2
                { 44, 45, 46, 47, 48, 49, 50 },// 3
                { 65, 66, 67, 68, 69, 70, 71 },// 4
                { 96, 97, 98, 99, 100, 111, 122 },// 5
                { 166, 176, 186, 187, 190, 195, 200 },// 6
                { 233, 243, 321, 341, 356, 370, 380 } // 7
        };
        //int K = 233;
        int K = 22;
        System.out.println(isContainsIn2Matrix(matrix, K));
    }

}
