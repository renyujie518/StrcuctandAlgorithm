package MiddleClass;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName beibaoBianzhong.java
 * @Description
 * 牛牛准备参加学校组织的春游, 出发前牛牛准备往背包里装入一些零食, 牛牛的背包容 量为w。
 * 牛牛家里一共有n袋零食, 第i袋零食体积为v[i]。 牛牛想知道在总体积不超过背包容量的情况下， 他一共有多少种零食放法(总体积为0也 算一种放法)。
 *
 * 背包问题的变种
 * dp解决 dp[i][j]代表的含义：arr[0...i]上的数每个只用一次 (也可以不用)，累加和正好为j
 * 就两种可能  不用i位置的数 0...i-1的数搞定j      用i位置的数  0...i-1的数搞定j -arr[i]
 *这样这个二维表的最后一行就是 0..i这么多数凑出来j=0,j=1,j=2..有多少种方法  题目是不超过容量 所以累加即可
 * @createTime 2021年08月08日 13:31:00
 */
public class beibaoBianzhong {
    public static int ways(int[] arr, int w) {
        if (arr == null || arr.length == 0 || w < 0) {
            return 0;
        }
        // dp[i][j]代表的含义：arr[0...i]上的数每个只用一次 (也可以不用)，累加和正好为j
        int[][] dp = new int[arr.length][w + 1];
        for (int i = 0; i < arr.length; i++) {
            dp[i][0] = 1;  //相当与背包容量为0  不管有多少零食，题目中说：(背包总体积为0也 算一种放法)。
        }
        //初始化完第一列再初始化第一行（第一行代表的是容量在变，零食只有一个arr[0]）
        for (int j = 1; j <= w; j++) {
            //有可放入和不放入两种,判断的关键点是包的容量是否>=零食,如果大于，对于不同的包来说两种选择，如果不是，只能不放
            dp[0][j] = j >= arr[0] ? 2 : 1;
        }
        //填其他的值
        for (int i = 1; i < arr.length; i++) {
            for (int j = 1; j < w; j++) {
                //是第i个不放入时，前i-1个零食放入背包的状态等于前i-1个，但是w的，即此时的j值是不变的
                dp[i][j] = dp[i - 1][j];
                if (j - arr[i] >= 0) {//因为是贪心，所以假设的前提是能放尽量放
                    //是在第i个放入的情况下，更新状态，依赖于i-1数量的零食体积与w-V[i]情况下的值
                    //同时注意是累加 所以有dp[i][j] +
                    dp[i][j] = dp[i][j] + dp[i - 1][j - arr[i]];
                }
            }
        }

        //看分析，不超过背包容量的情况下一共，在上面加过了,所以返回在w的限定下最右下角
        return dp[arr.length - 1][w];
    }
}
