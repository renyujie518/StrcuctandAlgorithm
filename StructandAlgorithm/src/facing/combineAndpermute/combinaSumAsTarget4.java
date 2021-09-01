package facing.combineAndpermute;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName combinaSumAsTarget3.java
 * @Description 给你一个由 不同 整数组成的数组 nums ，和一个目标整数 target 。
 * 请你从 nums 中找出并返回总和为 target 的元素组合的个数。
 * 输入：nums = [1,2,3], target = 4
 * 输出：7
 * 解释：
 * 所有可能的组合为：
 * (1, 1, 1, 1)
 * (1, 1, 2)
 * (1, 2, 1)
 * (1, 3)
 * (2, 1, 1)
 * (2, 2)
 * (3, 1)
 * 请注意，顺序不同的序列被视作不同的组合。
 *
 * 如果还是使用combinaSumAsTarget1,2，3的回溯
 * 如果本题要把排列都列出来的话，只能使用回溯算法爆搜。
 *
 * 背包问题  dp
 * dp[i]: 凑成目标正整数为i的排列个数为dp[i]
 * dp[i] += dp[i - nums[j]]
 * dp[i]（考虑nums[j]）可以由 dp[i - nums[j]]（不考虑nums[j]） 推导出来。
 * 因为只要得到nums[j]，排列个数dp[i - nums[j]]，就是dp[i]的一部分。

 *因为递推公式dp[i] += dp[i - nums[j]]的缘故，dp[0]要初始化为1，这样递归其他dp[i]的时候才会有数值基础。
 * 至于dp[0] = 1 有没有意义呢？
 * 其实没有意义
 *
 * @createTime 2021年09月01日 17:01:00
 */
public class combinaSumAsTarget4 {
    public int combinaSumAsTarget4(int[] nums, int target) {
        //dp[i]: 凑成target为i的排列个数为dp[i]
        int[] dp = new int[target + 1];
        // 这个值被其它状态参考，设置为 1 是合理的
        dp[0] = 1;
        for (int i = 1; i <= target; i++) {
            for (int num : nums) {
                if (num <= i) {
                    dp[i] = dp[i] + dp[i - num];
                }
            }
        }
        return dp[target];
    }



}
