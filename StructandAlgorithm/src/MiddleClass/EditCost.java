package MiddleClass;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName EditCost.java
 * @Description
 * 编辑距离问题
 * 给定两个字符串str1和str2， 再给定三个整数ic、 dc和rc， 分别代表插入、 删 除和替换一个字符的代价，
 * 返回将str1编辑成str2的最小代价。
 * 【举例】
 * str1="abc"， str2="adc"， ic=5， dc=3， rc=2 从"abc"编辑成"adc"， 把'b'替换成'd'是代价最小的， 所以返回2
 * str1="abc"， str2="adc"， ic=5， dc=3， rc=100 从"abc"编辑成"adc"， 先删除'b'， 然后插入'd'是代价最小的， 所以返回8
 * str1="abc"， str2="abc"， ic=5， dc=3， rc=2 不用编辑了， 本来就是一样的字符串， 所以返回0
 * @createTime 2021年08月13日 23:55:00
 */
public class EditCost {

    //dp[i,j] 代表的含义：str1[0~i-1]编辑成str2[0~j-1]的最低代价
    //以str2为目标，具体的值是 主要有4种可能：
    //1： str1[0~i-2]彻底变成str2[0~j-1]再把str1[i-1]删了  dp[i-1][j]+del
    //2： str1[0~i-1]彻底变成str2[0~j-2]再把str2[0~j-1]加上   dp[i][j-1]+add
    //3： str1[0~i-2]彻底变成str2[0~j-2]再把str1[0~i-1】替换成str2[0~j-1】  dp[i-1][j-1]+replace
    //4： 只有str1[i-1】 = str2[ij-1】的时候  只要保证str1[0~i-2]和str2[0~j-2]即可，这时候i-1什么都不做（copy,代价为0） dp[i-1][j-1]
    //综上  i，j只和左边，上面和左上角有关   所以从左到右和从上到下
    public static int minCost(String str1, String str2, int add, int del, int replace) {
        if (str1 == null || str2 == null) {
            return 0;
        }
        char[] ch1 = str1.toCharArray();
        char[] ch2 = str2.toCharArray();
        int row = ch1.length + 1;
        int col = ch2.length + 1;
        int[][] dp = new int[row][col];
        //首先第一行  首先(0,0)位置由于new初始化的时候已经保证了
        //第一行的含义是str1 非null转化成str2 = ""，只能delete,而且str1有几个delete几次
        for (int i = 1; i < row; i++) {
            dp[i][0] = i * del;
        }
        //第一列的含义是str1 = ""转化成非null的str2，只能add,而且str2有几个add几次
        for (int j = 1; j < col; j++) {
            dp[0][j] = j * add;
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                //先考虑可能性3，4
                if (ch1[i - 1] == ch2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {//可能性3  两字符最后一个不相等 需要替换
                    dp[i][j] = dp[i - 1][j - 1] + replace;
                }
                //更普遍的1，2
                dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + del);
                dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + add);

            }
        }
        //直到转换完  最右下角
        return dp[row - 1][col - 1];
    }

    public static void main(String[] args) {
        String str1 = "ab12cd3";
        String str2 = "abcdf";
        System.out.println(minCost(str1, str2, 5, 3, 2));


        str1 = "abcdf";
        str2 = "ab12cd3";
        System.out.println(minCost(str1, str2, 3, 2, 4));

        str1 = "";
        str2 = "ab12cd3";
        System.out.println(minCost(str1, str2, 1, 7, 5));


        str1 = "abcdf";
        str2 = "";
        System.out.println(minCost(str1, str2, 2, 9, 8));


    }


}
