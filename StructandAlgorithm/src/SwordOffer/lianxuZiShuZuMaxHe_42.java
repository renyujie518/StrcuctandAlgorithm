package SwordOffer;

import com.sun.javafx.tools.packager.MakeAllParams;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName lianxuZiShuZuMaxHe_42.java
 * @Description 连续子数组的最大和
 * {6, -3, -2, 7, -15, 1, 2, 2}，连续子数组的最大和为 8（从第 0 个开始，到第 3 个为止）。
 *
 * 本题考查的是简单的一维动态规划
 * 设dp[i] = 必须以i位置结尾的子数组最大累加和
 * 当结算必须以i位置结尾时的子数组最大累加和时
 * 如果 dp[i-1] > 0，则表示前方有利可图，将[i-1]拉进来；
 * 否则，前方无利可图，[i-1]位置是拖后腿的，[i]位置应单干
 * 所以：f[i]=max(nums[i],f[i−1]+nums[i])
 *
 * @createTime 2021年08月24日 15:26:00
 */
public class lianxuZiShuZuMaxHe_42 {
    public static int  maxSubArrayWithAdd(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];
        int result = dp[0];
        for (int i = 1; i < n; i++) {
            dp[i] = Math.max(nums[i], nums[i] + dp[i - 1]);
            //本题dp[i] = 必须以i位置结尾的子数组最大累加和，并不一定是dp[num.length-1]位置就是最大的，所以要随时对比
            result = Math.max(result, dp[i]);
        }

        return result;
    }

    // 乘积最大子数组
    /**
     * 仍然是考虑定义 f[i]代表以 nums[i]为结尾的最大值，但存在「负负得正」取得最大值的情况，
     * 最大值和最小值是相互转换的，这一点提示我们可以把这种转换关系设计到「状态转移方程」里去
     *在原始的状态设计后面多加一个维度，减少分类讨论，降低解决问题的难度
     * dp[i][j]：以 nums[i] 结尾的连续子数组的最值，计算最大值还是最小值由 j 来表示，j 就两个值；
     * 当 j = 0 的时候，表示计算的是最小值；
     * 当 j = 1 的时候，表示计算的是最大值。
     * 以下这个链接写的很好，注意多看
     * 链接：https://leetcode-cn.com/problems/maximum-product-subarray/solution/dong-tai-gui-hua-li-jie-wu-hou-xiao-xing-by-liweiw/

     当 nums[i] > 0 时，由于是乘积关系：
     最大值乘以正数依然是最大值；
     最小值乘以同一个正数依然是最小值；
     当 nums[i] < 0 时，依然是由于乘积关系：
     最大值乘以负数变成了最小值；
     最小值乘以同一个负数变成最大值；
     当 nums[i] = 0 的时候，由于 nums[i] 必须被选取，最大值和最小值都变成 00 ，合并到上面任意一种情况均成立。

     但是，还要注意一点，之前状态值的正负也要考虑：例如，在考虑最大值的时候，当 nums[i] > 0 是，如果 dp[i - 1][1] < 0 （之前的状态最大值）
     ，此时 nums[i] 可以另起炉灶（这里依然是连续子数组的最大和的思想）


     */

    public static int  maxSubArrayWithMulty(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return 0;
        }
        //dp[i][0]：以 nums[i] 结尾的连续子数组的最小值
        // dp[i][1]：以 nums[i] 结尾的连续子数组的最大值
        int[][] dp = new int[len][2];
        dp[0][0] = nums[0];
        dp[0][1] = nums[0];

        for (int i = 1; i < len; i++) {
            if (nums[i] > 0) {
                dp[i][0] = Math.min(nums[i], nums[i] * dp[i - 1][0]);
                dp[i][1] = Math.max(nums[i], nums[i] * dp[i - 1][1]);
            } else {//和上面刚好相反，但是min/max还是遵循
                dp[i][0] = Math.min(nums[i], nums[i] * dp[i - 1][1]);
                dp[i][1] = Math.max(nums[i], nums[i] * dp[i - 1][0]);
            }

        }
        // 只关心最大值，需要遍历
        int res = dp[0][1];
        for (int i = 1; i < len; i++) {
            res = Math.max(res, dp[i][1]);//二维只选1的（代表最大值）
        }
        return res;
    }
}
