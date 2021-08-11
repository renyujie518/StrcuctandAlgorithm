package MiddleClass;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName ziJuZhenMaxhe.java
 * @Description 给定一个整型矩阵， 返回子矩阵的最大累计和。
 * 思路：
 * 之前做过子数组最大累加和 想着利用一下，即每行每行考虑
 * 比如一个3行4列的矩阵  暴力的话依次要 计算 0，0-1，0-1-2，1，1-2，2 这么多行数组合
 * 可以利用矩阵压缩的技巧 0行和1行累加就是考虑的0-1矩阵下的最大累加和，在在这个基础上加上2行，就是0-1-2这个大矩阵的累加和
 * @createTime 2021年08月10日 22:07:00
 */
public class ziJuZhenMaxhe {
    public static int ziJuZhenMaxheWithZS(int[][] m) {
        if (m == null || m.length == 0 || m[0].length == 0) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        int cur = 0;
        int[] s = null;//用作考虑行的
        for (int i = 0; i != m.length; i++) {//开始的行号
            //这里开始就退化为"子数组最大累加和"
            s = new int[m[0].length];
            for (int j = i; j != m.length; j++) {//结束的行号  i~j行是我讨论的范围
                cur = 0;
                for (int k = 0; k != s.length; k++) {
                    s[k] += m[j][k];  //当前j行k列  压缩矩阵
                    cur += s[k];
                    max = Math.max(max, cur);
                    cur = cur < 0 ? 0 : cur;
                }
            }
        }
        return max;
    }

}
