package MiddleClass;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName ZeroLeftOneStringNumber.java
 * @Description
 * 字符串只由'0'和'1'两种字符构成， 当字符串长度为1时， 所有可能的字符串为"0"、 " 1 " ；
 * 当字符串长度为2时， 所有可能的字符串为"00"、 "01 " 、 " 1 0"、 " 1 1 " ；
 * 当字符串长度为3时， 所有可能的字符串为"000"、 "001 " 、 "010"、 "011" 、"100"、"101" 、"110"、"111 " . . .
 * 如果某一个字符串中， 只要是出现'0'的位置， 左边就靠着'1'，（1的左边无所谓） 这样的字符串叫作 达标字符串。
 * 给定一个正数N， 返回所有长度为N的字符串中， 达标字符串的数量。
 * 比如， N=3， 返回3， 因为只有"101"、 " 110"、 "111"达标。
 *
 * 转化思路 比如n =8 0位置一定是1  这时候再考虑n=7的情况 这是一个递推的问题
 * 假设在n=7时第一个位置填0  第二个位置必须填1  所以f(i) = f(i-1)+f(i-2)  斐波那契数列问题
 *
 * 发现递推公式严格  用斐波那契数列套路
 * @createTime 2021年08月02日 15:36:00
 */
public class ZeroLeftOneStringNumber {

    //求一个矩阵的p次方
    public static int[][] matrixPower(int[][] matrix, int p) {
        int[][] result = new int[matrix.length][matrix[0].length];
        //首先构造一个单位矩阵
        for (int i = 0; i < result.length; i++) {
            result[i][i] = 1;
        }
        int[][] temp = matrix;
        for (; p != 0; p = p >> 1) {
            if ((p & 1) != 0) {
                result = muliMarix(result, temp);
            }
            temp = muliMarix(temp, temp);
        }
        return result;
    }
    //两矩阵相乘
    public static int[][] muliMarix(int[][] m1, int[][] m2) {
        int[][] res = new int[m1.length][m2[0].length];
        for (int i = 0; i < m1.length; i++) {
            for (int j = 0; j < m2[0].length; j++) {
                for (int k = 0; k < m2.length; k++) {
                    res[i][j] = m1[i][k] * m2[k][j];
                }
            }
        }
        return res;
    }

    //以斐波那契数列为例 严格递推式 |F(3) F(2)| = |F(2) F(1)| * 一个状态矩阵  用前几项求出的状态矩阵是{ { 1, 1 }, { 1, 0 } }
    //以此推出 |F(N) F(N-1)| = |F(2) F(1)| *   { { 1, 1 }, { 1, 0 } }的n-2次方
    public static int Feibonaqi(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return n;
        }
        int[][] base = {{1, 1}, {1, 0}};
        int[][] xishu = matrixPower(base, n - 2);
        return 2 * xishu[0][0] + xishu[1][0];

    }

}
