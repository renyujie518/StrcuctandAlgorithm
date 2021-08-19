package SwordOffer;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName shunShiZhenMatrix.java
 * @Description  顺时针打印矩阵
 *    0 1 2 3
 *  * 4 5 6 7
 *  * 8 9 10 11
 *  * 打印顺序为： 0 1 2 3 7 11 10 9 8 4 5 6
 *  * 思路：
 *  * 首先不要往局部上写，这样会很复杂
 *  *
 *  * 考虑左上角和右下角两个点A.B  怎么表示两个点  4个变量就可以  A的行号和列号  B的行号和列号
 *  * 首先实现一个函数func  A，B两点会形成一个框 按照顺时针 把形成的这个框挨个打印出来
 *  *
 *  * 打印完这个框 A点向右下方移动  B点向左上方移动  会形成下一个框 继续打印
 *  * 直到错过去了  即A,B两点在同一行或者同一列  特殊处理  停止
 *  * 实际上处理三种情况：A，B在同一行，A，B在同一列，错开的（执行上面那个func即可）
 * @createTime 2021年08月19日 22:33:00
 */
public class shunShiZhenMatrix_29 {
    public static void shunShiZhenMatrix(int[][] matrix) {
        int Arow = 0;
        int Acol = 0;
        int Brow = matrix.length - 1;
        int Bcol = matrix[0].length - 1;
        //直到越界停止（B不能越过A）,每次A点向右下方移动  B点向左上方移动
        while (Arow <= Brow && Acol <= Bcol) {
            func(matrix, Arow++, Acol++, Brow--, Bcol--);
        }
    }
    //四个变量分别代表A的行号和列号  B的行号和列号
    public static void func(int[][] matrix, int Arow, int Acol, int Brow, int Bcol) {
        if (Arow == Brow) {//这两个点处于一条横线上
            //在一条横线线上 从左到右打印即可
            for (int i = Acol; i <= Bcol; i++) {
                System.out.println(matrix[Arow][i] + " ");
            }
        } else if (Acol == Bcol) {//这两个点处于一条竖线上
            //在一条横线线上 从上到下打印即可
            for (int i = Arow; i <= Brow; i++) {
                System.out.println(matrix[i][Acol] + " ");
            }
        } else {//情况三   顺时针打印
            //这里相当于把B固定 A走动
            int currArow = Arow;
            int currAcol = Acol;
            //向右走  直到列相等  打印滑过的这一行
            while (currAcol != Bcol) {
                System.out.println(matrix[Arow][currAcol]);
                currAcol++;
            }
            //向下走  直到行相等  打印滑过的这一列
            while (currArow != Bcol) {
                System.out.println(matrix[currArow][Bcol]);
                currArow++;
            }
            //向左走  直到列相等  打印滑过的这一行
            while (currAcol != Acol) {
                System.out.println(matrix[Brow][currAcol]);
                currAcol--;
            }
            //向上走  直到行相等  打印滑过的这一列
            while (currArow != Arow) {
                System.out.println(matrix[currArow][Arow]);
                currArow--;
            }
        }
    }



    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        shunShiZhenMatrix(matrix);
    }
}
