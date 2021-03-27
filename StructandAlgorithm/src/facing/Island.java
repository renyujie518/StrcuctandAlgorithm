package facing;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName Island.java
 * @Description
 * 岛问题【题目】
 * 一个矩阵中只有0和1两种值，每个位置都可以和自己的上、下、左、右四个位置相连，
 * 如果有一片1连在一起，这个部分叫做一个岛，
 * 求一个矩阵中有多少个岛?【
 * 举例】
 * 001010
 * 111010
 * 100100
 * 000000
 *
 * @createTime 2021年03月20日 23:07:00
 */
public class Island {
    public static int countIslands(int[][] m) {
        if (m == null || m[0] == null) {
            return 0;
        }
        int N = m.length;  //行
        int M = m[0].length;  //列
        int res = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (m[i][j] == 1) {  //碰见是个1
                    res++;  //先++
                    infect(m, i, j, N, M);//感染过程
                }
            }
        }
        return res;
    }

    //感染过程  只有两个可变参数 就是所到的位置 （i，j）
    public static void infect(int[][] m, int i, int j, int N, int M) {
        if (i < 0 || i >= N || j < 0 || j >= M || m[i][j] != 1) {  //越界或者不是1位置，return 我只关心连成一片的1能传递下去
            //并且连成一片的1都会变成2
            return;
        }
        //没越界，而且当前位置是1.就把1改为2
        m[i][j] = 2;
        //只能上下左右递归
        infect(m, i + 1, j, N, M);
        infect(m, i - 1, j, N, M);
        infect(m, i, j + 1, N, M);
        infect(m, i, j - 1, N, M);
    }

    public static void main(String[] args) {
        int[][] m1 = {  { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 1, 1, 1, 0, 1, 1, 1, 0 },
                { 0, 1, 1, 1, 0, 0, 0, 1, 0 },
                { 0, 1, 1, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 1, 1, 0, 0 },
                { 0, 0, 0, 0, 1, 1, 1, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, };
        System.out.println(countIslands(m1));

        int[][] m2 = {  { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 1, 1, 1, 1, 1, 1, 1, 0 },
                { 0, 1, 1, 1, 0, 0, 0, 1, 0 },
                { 0, 1, 1, 0, 0, 0, 1, 1, 0 },
                { 0, 0, 0, 0, 0, 1, 1, 0, 0 },
                { 0, 0, 0, 0, 1, 1, 1, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, };
        System.out.println(countIslands(m2));

    }
}
