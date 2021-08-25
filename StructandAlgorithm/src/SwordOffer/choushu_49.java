package SwordOffer;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName choushu_49.java
 * @Description 丑数

 * 把只包含因子 2、3 和 5 的数称作丑数（Ugly Number）。例如 6、8 都是丑数，但 14 不是，因为它包含因子 7。
 * 习惯上我们把 1 当做是第一个丑数。求按从小到大的顺序的第 N 个丑数。
 *
 * 定义数组dp，其中dp[i] 表示第 i个丑数，第 n个丑数即为dp[n]
 * 由于最小的丑数是 1，因此 dp[1]=1
 * 定义三个指针 p2,p3,p5表示下一个丑数是当前指针指向的丑数乘以对应的质因数。初始时，三个指针的值都是 1。
 *
 * 当 2≤i≤n 时，令 dp[i]=min(dp[p2]*2,dp[p3]*3,dp[p5]*5)
 * 然后分别比较 是否相等，如果相等则将对应的指针加 11。


 * @createTime 2021年08月25日 23:00:00
 */
public class choushu_49 {
    public int nthUglyNumber(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        int p2 = 1, p3 = 1, p5 = 1;
        for (int i = 2; i <= n; i++) {
            int num2 = dp[p2] * 2;
            int num3 = dp[p3] * 3;
            int num5 = dp[p5] * 5;
            dp[i] = Math.min(Math.min(num2, num3), num5);
            if (dp[i] == num2) {
                p2++;
            }
            if (dp[i] == num3) {
                p3++;
            }
            if (dp[i] == num5) {
                p5++;
            }
        }
        return dp[n];
    }
}
