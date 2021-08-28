package SwordOffer;

import java.util.List;
import java.util.Map;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName ShaiZi_60.java
 * @Description  n 个骰子的点数
 * 把n个骰子扔在地上，所有骰子朝上一面的点数之和为s。输入n，打印出s的所有可能的值出现的概率。
 * 动态规划
 * 使用一个二维数组 dp 存储点数出现的次数，其中 dp[i][j] 表示前 i 个骰子产生点数(前 n枚骰子的点数和) j 的次数。
 * dp[n][j] 来表示最后一个阶段点数 j出现的次数。
 * 单单看第 n 枚骰子，它的点数可能为 1 , 2, 3, ... , 6，因此投掷完 n 枚骰子后点数 j 出现的次数，
 * 可以由投掷完 n-1 枚骰子后出现的次数之和转化过来
 * 第一阶段的状态：投掷完 11枚骰子后，它的可能点数分别为 1, 2, 3, ... , 6 ，并且每个点数出现的次数都是 1 .
 *
 * https://leetcode-cn.com/problems/nge-tou-zi-de-dian-shu-lcof/solution/nge-tou-zi-de-dian-shu-dong-tai-gui-hua-ji-qi-yo-3/
 * @createTime 2021年08月28日 17:08:00
 */
public class ShaiZi_60 {
    public double[] ShaiZi(int n) {
        //点数和最多是6*n,时刻注意第二维是点数和，dp的值代表的是次数，产生这么多点数和的次数
        int[][] dp = new int[n + 1][6 * n + 1];
        for(int i = 1; i <= 6; i++){
            dp[1][i] = 1;
        }
        for(int i = 2; i <= n; i++){
            //考虑第i个筛子所导致的
            for (int j = i; j <= 6 * i; j++){
                for(int k = 1; k <= 6 && k <= j; k++){
                    dp[i][j] = dp[i][j] + dp[i - 1][j - k];
                }
            }
        }
        double[] ans = new double[6 * n - n + 1];
        for(int i = n; i <= 6 * n; i++){
            //所有点数出现的总次数是 6^n
            ans[i - n] = ((double) dp[n][i]) / (Math.pow(6, n));
        }
        return ans;

    }
}
