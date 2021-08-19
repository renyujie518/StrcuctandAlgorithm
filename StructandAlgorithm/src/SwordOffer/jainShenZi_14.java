package SwordOffer;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName jainShenZi.java
 * @Description 剪绳子问题
 * 把一根绳子剪成多段，并且使得每段的长度乘积最大。
 * n = 2
 * return 1 (2 = 1 + 1)
 *
 * n = 10
 * return 36 (10 = 3 + 3 + 4)
 *
 * 思路：
 * 动态规划  用一个dp数组记录从0到n长度的绳子剪掉后的最大乘积
 * dp[i]表示长度为i的绳子剪成m段后的最大乘积，初始化dp[2] = 1
 * 我们想要求长度为n的绳子剪掉后的最大乘积，可以从前面比n小的绳子转移而来
 * 我们先把绳子剪掉第一段（长度为j），如果只剪掉长度为1，对最后的乘积无任何增益，所以从长度为2开始剪
 * 剪了第一段后，剩下(i - j)长度可以剪也可以不剪。如果不剪的话长度乘积即为j * (i - j)；如果剪的话长度乘积即为j * dp[i - j]
 * 取两者最大值max(j * (i - j), j * dp[i - j])
 *
 * 对所有j不同的情况取最大值，因此最终dp[i]的转移方程为
 * dp[i] = max(dp[i], max(j * (i - j), j * dp[i - j]))

 * 最后返回dp[n]即可

 * @createTime 2021年08月18日 15:48:00
 */
public class jainShenZi_14 {
    public static int cuttingRope1(int n) {
        int[] dp = new int[n + 1];
        //dp[i]表示长度为i的绳子剪成若干段后的最大乘积
        dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            //我们想要求长度为n的绳子剪掉后的最大乘积，可以从前面比n小的绳子转移而来
            //第一段长度j可以取的区间为[2,i)
            for (int j = 2; j < i; j++) {
                dp[i] = Math.max(dp[i], Math.max((j * (i - j)), (j * dp[i - j])));
            }
        }
        return dp[n];
    }

    /**
     剑指 Offer 14- II. 剪绳子 II
     这里加个限定：答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
     这题再用动态规划的话惨不忍睹，还是要看贪心
     */
    //尽可能把绳子分成长度为3的小段，这样乘积最大(贪心)
    public int cuttingRope2(int n) {
        if(n < 4){
            //如果 n == 2，返回1，如果 n == 3，返回2，两个可以合并成n小于4的时候返回n - 1
            return n - 1;
        }
        long res = 1;
        while(n > 4){
            //分成尽可能多的长度为3的小段，每次循环长度n减去3，乘积res乘以3；

            res  = res * 3 % 1000000007;
            n -= 3;
        }
        //最后返回时乘以小于等于4的最后一小段；每次乘法操作后记得取余就行
        return (int) (res * n % 1000000007);
    }

}
