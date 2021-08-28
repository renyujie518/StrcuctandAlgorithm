package SwordOffer;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName maxLiRun_63.java
 * @Description 假设把某股票的价格按照时间先后顺序存储在数组中，请问买卖该股票一次可能获得的最大利润是多少？
 * 输入: [7,1,5,3,6,4]
 * 输出: 5
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 *      注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。
 * dp[i]代表以 prices[i]为结尾的子数组的最大利润
 * 由于题目限定 “买卖该股票一次” ，因此前 i 日最大利润 dp[i]等于前 i−1 日最大利润 dp[i-1]和第 i 日卖出的最大利润中的最大值。
 * dp[i]=max(dp[i−1],prices[i]−min(prices[0:i]))
 *
 * 初始状态： dp[0] = 0 ，即首日利润为 0 ；
 * 返回值： dp[n−1] ，其中 n 为 dp 列表长度。
 * https://leetcode-cn.com/problems/gu-piao-de-zui-da-li-run-lcof/solution/mian-shi-ti-63-gu-piao-de-zui-da-li-run-dong-tai-2/


前i日的收益最大值 = Math.max(前i - 1日的收益的最大值，第i日收益的最大值)
第i日收益的最大值 = 第i日价格price - 前(i-1)日 价格的最小值minPrice
因此需要维护一个变量minPrice
状态转移方程 ： dp[i] = Math.max(dp[i-1], price - minPrice)，初始状态为0
状态转移的时机：当minPrice没有更新时进行状态转移

 * @createTime 2021年08月28日 17:51:00
 */
public class maxLiRun_63 {
    public int maxProfit(int[] prices) {
        if (prices.length < 1) {
            return 0;
        }
        int maxProfit = 0;
        int minPrice = Integer.MAX_VALUE;
        for (int price : prices) {
            if (minPrice > price) {
                minPrice = price;
            } else {
                maxProfit = Math.max(maxProfit, price - minPrice);
            }
        }
        return maxProfit;
    }
}
