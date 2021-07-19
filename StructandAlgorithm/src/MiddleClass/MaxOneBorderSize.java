package MiddleClass;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName MaxOneBorderSize.java
 * @Description
 * 给定一个N*N的矩阵matrix，只有0和1两种值，返回边框全是1的最大正方形的边长长度。
 * 例如:
 * 01111
 * 01001
 * 01001
 * 01111
 * 01011
 * 其中边框全是1的最大正方形的大小为4*4，所以返回4。
 * 如果用普通枚举，将会得到O(N^3)级别
 * @createTime 2021年03月31日 12:22:00
 */
public class MaxOneBorderSize {
    //这里采用辅助矩阵的方法
    //right代表包括自己在内 右方有多少个连续的1（从右下角生成，从右往左，从下往上）
    //down代表包括自己在内 下方有多少个连续的1（从右下角生成，从下往上，从右往左）
    //具体的思路是在A（i，j）这个点上去查询right,down这两个表，看其连续为1的数量，
    //此时跳到B看down表里连续1的数量和C看right表里连续1的数量
    /*
    A     B


    C     D
     */

    public static  void setBorderMap(int[][] map,int[][] right,int[][] down){
        int row = map.length;
        int column = map[0].length;
        if (map[row-1][column-1] ==1){   //设定最右下角的初始值
            right[row - 1][column - 1] = 1;
            down[row - 1][column - 1] = 1;
        }
        for (int i =row-2;i !=-1;i--){ //生成down，从下往上，i可以为0但不能为-1
            if (map[i][column-1] ==1){
                down[i][column - 1] = down[i + 1][column - 1] + 1;//down[i + 1][column - 1]是最右边的那列往上走
                right[i][column - 1] = 1;//因为right是从右往左的，若最右边的那列为1，相当于给right的最后一列赋初值
            }
        }
        for (int i = column-2;i !=-1;i--){//生成right，从右往左，i可以为0但不能为-1
            if (map[row-1][i] == 1){
                right[row - 1][i] = right[row - 1][i + 1] + 1;
                down[row - 1][i] = 1;//因为down是从下往上的，若最下边的一行为1，相当于给down的最后一行赋初值
            }
        }
        //最普遍的情况
        for (int i = row-2;i!=-1;i--){
            for (int j = column-2; j !=-1 ; j--) {
                if (map[i][j]==1){
                    right[i][j] = right[i][j+1]+1;//（i，j）这个点上为1，在right上代表其左边的值为1
                    down[i][j] = down[i+1][j]+1;//（i，j）这个点上为1，在down上代表其上边的值为1
                }
            }
        }
    }
    //判断ABC三个点组成的正方形的边长有没有超过指定的size,其实就是在验证size大小的正方形边框是否全为1
    //举个例子  现在A(4,5) size =3 取right(4,5)上的值（右方有多少个连续的1）是否>=3 取down(4,5)上的值（下方有多少个连续的1）是否>=3
    //再跳到B（4,8）看它下方的连续为1的点够不够3个 即down(4,8)
    //再跳到C（7,5）看它右方的连续为1的点够不够3个 即right(7,5)
    //一旦满足，就返回true
    public static  boolean hasSizeOfBorder(int size,int[][] right,int[][] down){
        for (int i = 0;i!= right.length-size+1;i++){
            for (int j = 0; j != right[0].length - size + 1; j++) {
                if (right[i][j]>=size && down[i][j]>=size && right[i+size-1][j]>=size && down[i][j+size-1]>=size ){
                    return true;
                }
            }
        }
        return false;
    }
    public static int getMaxSize(int[][] m){
        int[][] right = new int[m.length][m[0].length];
        int[][] down = new int[m.length][m[0].length];
        //找到down right
        setBorderMap(m, right, down);
        for (int size = Math.min(m.length,m[0].length);size!=0;size--){ //现在是要在给定map（是个矩形）的范围内尝试各种的size
            if (hasSizeOfBorder(size,right,down)){ //一旦发现满足ABC三个点组成的正方形的边长有没有超过指定的size，就返回
                return size;//由于size是逐渐减小的，所以返回的是最大size
            }
        }
        return 0;//什么都没找到就返回0
    }
    public static int[][] generateRandom01Matrix(int rowSize, int colSize) {
        int[][] res = new int[rowSize][colSize];
        for (int i = 0; i != rowSize; i++) {
            for (int j = 0; j != colSize; j++) {
                res[i][j] = (int) (Math.random() * 2);
            }
        }
        return res;
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
        int[][] matrix = generateRandom01Matrix(7, 8);
        printMatrix(matrix);
        System.out.println(getMaxSize(matrix));
    }
}