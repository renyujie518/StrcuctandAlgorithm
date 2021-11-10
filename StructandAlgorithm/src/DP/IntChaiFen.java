package DP;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName IntChaiFen.java
 * @Description
 * 给定一个正整数 n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。 返回你可以获得的最大乘积。
 * 示例 1: 输入: 2 输出: 1 解释: 2 = 1 + 1, 1 × 1 = 1。
 * 示例 2: 输入: 10 输出: 36 解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36。
 * 说明: 你可以假设 n 不小于 2 且不大于 58
 *
 * dp[i]：分拆数字i，可以得到的最大乘积为dp[i]。
 * dp[i] = max({dp[i], (i - j) * j, dp[i - j] * j});
 * 从dp[i]的定义来说，拆分数字2，得到的最大乘积是1 所以dp[2] =1
 *
 * @createTime 2021年09月02日 16:43:00
 */
public class IntChaiFen {
    public int IntChaiFen(int n) {
        //dp[i]为正整数i拆分结果的最大乘积
        int[] dp = new int[n + 1];
        dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                //j*(i-j)代表把i拆分为j和i-j两个数相乘
                //j*dp[i-j]代表把i拆分成j和继续把(i-j)这个数拆分，取(i-j)拆分结果中的最大乘积与j相乘
                dp[i] = Math.max(dp[i], Math.max(j * (i - j), j * dp[i - j]));
            }
        }
        return dp[n];
    }
}
