package SwordOffer;

import java.util.Arrays;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName tiaoTaijie_10.java
 * @Description
 * 一只青蛙一次可以跳上 1 级台阶，也可以跳上 2 级... 它也可以跳上 n 级。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。
 * @createTime 2021年08月17日 21:56:00
 */
public class tiaoTaijie_10 {


    public static int JumpFloorII(int target) {
        int[] dp = new int[target];
        Arrays.fill(dp, 1);
        for (int i = 1; i < target; i++)
            for (int j = 0; j < i; j++)
                dp[i] += dp[j];
        return dp[target - 1];
    }
}
