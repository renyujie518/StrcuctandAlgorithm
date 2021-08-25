package SwordOffer;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName num2Str_46.java
 * @Description 把数字翻译成字符串
 * 给定一个数字，按照如下规则翻译成字符串：1 翻译成“a”，2 翻译成“b”... 26 翻译成“z”。
 * 一个数字有多种翻译可能，例如 12258 一共有 5 种，分别是 abbeh，lbeh，aveh，abyh，lyh。
 * 实现一个函数，用来计算一个数字有多少种不同的翻译方法。
 * <p>
 * 动态规划：dp[i]代表以 xi为结尾的数字的翻译方案数量。
 * 转移方程： 若 xi和xi-1组成的两位数字可以被翻译，则 dp[i] = dp[i - 1] + dp[i - 2]；否则dp[i]=dp[i−1]
 * <p>
 * 但是得注意区间  当 xi-1=0 时，组成的两位数是无法被翻译的 例如 00, 01, 02，因此区间为 [10, 25]。
 * dp[2]=dp[1]+dp[0]=2    dp[2]代表总共两位数字的翻译方式  有2种组合  而dp[0]无数字的翻译方法就肯定是1，所以dp[1] = 1
 * @createTime 2021年08月25日 14:45:00
 */
public class num2Str_46 {
    public static int num2StrWithDP(int num) {
        String str = String.valueOf(num);
        int[] dp = new int[str.length() + 1];
        dp[0] = dp[1] = 1;
        for (int i = 2; i <= str.length(); i++) {
            String strTemp = str.substring(i - 2, i);  //substring左闭右开  所以是【i-2,i-1】 取出i位置的前两位
            //翻译时,翻译到第i位时有两种可能，仅采用第i位数字进行翻译或者结合i-1位进行翻译
            if (strTemp.compareTo("10") >= 0 && strTemp.compareTo("25") <= 0) {
                dp[i] = dp[i - 1] + dp[i - 2];
            } else {
                dp[i] = dp[i - 1];
            }
        }
        return dp[dp.length - 1];
    }

}
