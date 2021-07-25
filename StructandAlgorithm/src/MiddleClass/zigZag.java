package MiddleClass;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName zigZag.java
 * @Description 用zigzag的方式打印矩阵， 比如如下的矩阵
 * 0 1 2 3
 * 4 5 6 7
 * 8 9 10 11
 * 打印顺序为： 0 1 4 8 5 2 3 6 9 1 0 7 1 1
 * <p>
 * 其实就是斜着来
 * 左神的例子写的太精简  刷题的目的就是做出题  网上的笨办法学习了也可以
 * 主要思想：
 * 由观察可以得知，一共有两种走法，向右上方走和向左下方走。
 * 向右上方走 ↗  实际上等于 x -= 1, y += 1   减少列 增加行
 * 向左下方走 ↙  实际上等于 x+= 1, y -= 1  增加行 减少列
 * <p>
 * 当向右上方走的时候，有两种情况会造成碰壁，因而需要转弯，
 * CASE 1: 碰到上方的壁 (x无法再 - 1),但还没碰到右方的壁（y可以 +1）
 * 在这种情况下，下一步的坐标为 y += 1
 * CASE 2: 碰到右方的壁 (y无法再 +1)
 * 在这种情况下, 下一步的坐标为 x += 1
 * <p>
 * 向左下方走同理：
 * CASE 1: 碰到左方的壁 (y无法再 -1), 但还没有碰到下方的壁(x可以 +1)
 * 在这种情况下,下一步的坐标为 x += 1
 * CSSE 2: 碰到下方的壁 (x无法再 +1)
 * 在这种情况下,下一步的坐标为 y += 1
 * <p>
 * 链接：https://leetcode-cn.com/problems/diagonal-traverse/solution/zui-zhi-bai-de-xie-fa-by-meng-zhi-hen-n/
 * <p>
 * <p>
 * 这里采取了更为精简的一种思考方式：
 * 每次循环把一个方向的数据放入答案数组中，到达边界，改变方向；
 * https://leetcode-cn.com/problems/diagonal-traverse/solution/xiao-bai-kan-guo-lai-zui-zhi-bai-yi-li-jie-ban-ben/
 * 1.确定循环次数
 * 对于m*n矩阵，所有方向应该有m+n-1次
 * 2.方向变更
 * 这个问题也比较简单，对于遍历次数对2取余即可
 * 3.右上方向
 * m，n代表横纵坐标，右上方向的移动为 m--，n++
 * 根据这两个条件的话，在正常范围内的条件为m >= 0 && n < columnLength，
 * 只要横坐标不减到负数，纵坐标不大于列数，往数组添加元素的动作就可以继续下去，
 * 3.1越界处理
 * 在右上方向的尽头有这么两种情况
 * 1.n<columnLength 碰到上方的壁
 * 这种情况m++即可
 * 2.n>=columnLength 碰到右方的壁
 * 这种呢m需要加2，n--即可（这里为什么是2，因为在程序debug的时候可以看到，在进那个小if之前，m先-过了，弥补，所以+2）
 * <p>
 * 左下同理
 * <p>
 * 左神的方法：
 * 一开始A，B都停留在左上角的位置  之后两个点每次同时走一步  A往右走 走到头向下走  B往下走，走到头向右走
 * 每次打印这条斜线   分方向（bool类型决定  交替打印）
 */
public class zigZag {
    public static int[] zigzagMatrix(int[][] matrix) {
        if (matrix.length == 0) {
            return new int[0];
        }
        //注意，这里没有减1，理由是下面判断边界的时候都是<  这里看做边界即可
        int rowLength = matrix.length;
        int colLength = matrix[0].length;
        int[] result = new int[rowLength * colLength];
        int count = rowLength + colLength - 1;
        //行的索引
        int m = 0;
        //列的索引
        int n = 0;
        int resultIndex = 0;
        for (int i = 0; i < count; i++) {  //这个i代表的矩阵中元素的位置
            if (i % 2 == 0) {//如果i的位置属于偶数位 是右上的方向
                while (m >= 0 && n < colLength) {
                    result[resultIndex] = matrix[m][n];
                    resultIndex++;
                    m--;
                    n++;
                }
                if (n < colLength) {//越界处理,n是一直++的，碰到上方的壁
                    m++;
                } else {//越界处理, 碰到右方的壁
                    m = m + 2;
                    n--;
                }
            } else {//剩余的一种情况就是左下的方向
                while (n >= 0 && m < rowLength) {
                    result[resultIndex] = matrix[m][n];
                    resultIndex++;
                    n--;
                    m++;
                }
                if (m < colLength) {//越界处理, m是一直++的，碰到下方的壁
                    n++;
                } else {//越界处理, 碰到左方的壁
                    n = n + 2;
                    m--;
                }
            }
        }
        return result;
    }

    public static void printMatrixZigZagWithZuo(int[][] matrix) {
        //开始的时候 A，B都在左上角
        int Arow = 0;
        int Acol = 0;
        int Brow = 0;
        int Bcol = 0;
        //规定边界
        int endRow = matrix.length - 1;
        int endCol = matrix[0].length - 1;
        boolean fromUp = false;
        while (Arow != endRow + 1) {
            //只要不越界  越界的条件就是超过行边界1次
            //首先要把最左上角的点打印出来  要不会少一个点
            turnPrint(matrix, Arow, Acol, Brow, Bcol, fromUp);
            //A的行怎么变 A的列到了列边界 A的行才+1，否则不变
            Arow = Acol == endCol ? Arow + 1 : Arow;
            //A的列怎么变 A的列到了列边界 A的列才不变，否则一直+1
             Acol = Acol == endCol ? Acol : Acol + 1;
            //以上保证了A走到不能往右了，再往下走

            //B的列怎么变 B的行到了行边界 才开始+1，否则不变
            Bcol = Brow == endRow ? Bcol + 1 : Bcol;
            //B的行怎么变 B的行到了行边界 才不变，否则一直+1
            Brow = Brow == endRow ? Brow : Brow + 1;
            //以上保证了B走到不能往下了，再往右走

            //每完成一步，方向变一下
            fromUp = !fromUp;
        }
        System.out.println();
    }

    //按照斜线打印函数，带方向
    public static void turnPrint(int[][] matrix, int Arow, int Acol, int Brow, int Bcol, Boolean turn) {
        if (turn) {
            //沿着左下方向打  以B为终点
            while (Arow != Brow + 1) {
                System.out.println(matrix[Arow++][Acol--] + " ");
            }
        } else {
            //沿着右上方向打  以A为终点
            while (Brow != Arow - 1) {
                System.out.println(matrix[Brow--][Bcol++] + " ");
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[] reuslt = zigzagMatrix(matrix);
        for (int i = 0; i < reuslt.length; i++) {
            System.out.println(reuslt[i]);
        }
        System.out.println("=======");
        printMatrixZigZagWithZuo(matrix);
    }

}
