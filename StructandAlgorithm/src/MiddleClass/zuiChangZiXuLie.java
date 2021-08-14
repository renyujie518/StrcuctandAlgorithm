package MiddleClass;

import java.util.Arrays;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName zuiChangZiXuLie.java
 * @Description 最长递增子序列问题  返回长度
 * 输入：nums = [10,9,2,5,3,7,101,18]
 * 解释：最长递增子序列是 [2,3,7,101] 返回长度即4
 *
 * dp[i]  子序列以arr[i]为结尾的最长长度
 * 思路：在arr中查比该位置小的对应的dp值，选对应的dp值最大的再+1
 * 举个例子  arr[3,1,2,6,3,4,0]
 * 对于3，开始，basecase,dp[1]
 * 对于1，找在arr中比1小的，没有，就是自己  dp[1,1]
 * 对于2 ，找在arr中比2的，找到1，再加上自己 dp[1,1,2]
 * 对于6，arr中比6的小的，找到3，1，2，去其中对应dp最大的即2对应的2，再加上自己,dp[1,1,2,3]
 * 对于3.arr中比3的小的,找到1，2，去其中对应dp最大的即2对应的2，再加上自己,dp[1,1,2,3，3]
 * 对于4，arr中比4的小的，找到3，1，2，3，去其中对应dp最大的即3对应的3，再加上自己,dp[1,1,2,3,3,4]
 * 对于0，arr中比0的小的,没有，就是自己 dp[1,1,2,3,3,4,1]
 * 最后代表是4个 以arr[index= 5]结尾的是最长的  但复杂度是O(n^2)
 *
 * https://leetcode-cn.com/problems/longest-increasing-subsequence/solution/dong-tai-gui-hua-er-fen-cha-zhao-tan-xin-suan-fa-p/
 * @createTime 2021年08月11日 23:21:00
 */
public class zuiChangZiXuLie {
    public static int zuiChangZiXuLie(int[] nums) {
        int len = nums.length;
        if (len < 2) {
            return len;
        }
        int[] dp = new int[len];
        //初始化全填为1（省去了加1的过程，同时赋值basecase=1）
        Arrays.fill(dp, 1);
        //观察上面的举例  i = 0位置一定是1 也很好理解，子序列只有开头只有自己，长度本来就是1
        for (int i = 1; i < len; i++) {//考虑当前i
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {//j是以前的位置
                    //在arr中查以前j比该位置i小的对应的dp值dp[j]，选对应的dp值最大的再+1(这个1代表要把j算进去)
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        //在dp中找最大值
        int res = 0;
        for (int i = 0; i < len; i++) {
            res = Math.max(res, dp[i]);
        }
        return res;
    }
    public static void main(String[] args) {
        int[] test = {3, 1, 2, 6, 3, 4, 0};
        System.out.println(zuiChangZiXuLie(test));
    }
}
