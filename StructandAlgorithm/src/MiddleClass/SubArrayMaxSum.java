package MiddleClass;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName SubArrayMaxSum.java
 * @Description
 * 子数组最大累加和（剑指offer 42）
 * 为了保证招聘信息的质量问题， 公司为每个职位设计了打分系统， 打分可以为正数， 也 可以为负数，
 * 正数表示用户认可帖子质量， 负数表示用户不认可帖子质量．
 * 打分的分数 根据评价用户的等级大小不定， 比如可以为 -1 分， 10分， 30分， -10分等。
 * 假设数组A 记录了一条帖子所有打分记录， 现在需要找出帖子曾经得到过最高的分数是多少，
 * 用于后续根据最高分数来确认需要对发帖用户做相应的惩罚或奖励．
 * 其中， 最高分的定义为： 用户所有打分记录中， 连续打分数据之和的最大值即认为是帖子曾经获得的最高分。
 * 例 如： 帖子10001010近期的打 分记录为[1,1,-1,-10,11,4,-6,9,20,-10,-2],那么该条帖子曾经到达过的最高分数为 11 +4+(-6)+9+20=38。
 * 请实现一段代码， 输入为帖子近期的打分记录， 输出为当前帖子 得到的最高分数。
 *
 * 思路：
 * curr= curr+arr[i]
 * curr让max变更大了,就更新 max = curr
 * curr<0 curr= 0同时max不跟新 else curr不变
 *
 * 为什么这样做就可以：
 * 假设数组中都是负数（也包括0）题意中会越加负数越小，所以因为有" curr<0 curr= 0同时max不跟新 "这一步，max保证了没有继续变小，符合题意
 * 数组中有正数  只要把尽可能多的非负数加起来即可 即"curr让max变更大了,就更新 max = curr"  一旦curr<0 立马让curr= 0，max也不更新
 * @createTime 2021年08月10日 16:32:00
 */
public class SubArrayMaxSum {
    //首先来暴力解这样 时间复杂度至少会是O(n^2)
    public static int maxSubArrayWithBL(int[] nums) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {//每到一个数i是把这个数当起点的
                int sum = 0;
                for (int k = i; k < j; k++) {//j不断向前走，形成一个区间【i,j】累加和就是这个区间
                    sum = sum + nums[k];
                }
                if (sum > max) {
                    max = sum;
                }
            }
        }
        return max;
    }

    //sum(i,j)代表计算从nums[i]到nums[j]的元素之和，我们要找到最大的 sum(i,j)
    //假如我们要以O(n)的时间复杂度优化算法，就需要进一步压缩计算
    //会形成一个表格
    //具体见：https://leetcode-cn.com/problems/lian-xu-zi-shu-zu-de-zui-da-he-lcof/solution/cong-bao-li-po-jie-dao-dong-tai-gui-hua-yfvkp/
    //dp[3]=dp[2]+nums[3]，否则，dp[3] = 0 + nums[3]。
    public static int maxSubArrayWithDP(int[] arr) {
        //设dp[j]为以j结尾的子数组的最大值
        int[] dp = new int[arr.length];
        //basecase
        dp[0] = arr[0];
        for (int j = 1; j < arr.length; j++) {//注意，这里j要从1开始
            if (dp[j - 1] > 0) {
                dp[j] = dp[j - 1] + arr[j];
            } else {
                dp[j] = arr[j];
            }
        }
        //再求最后一列中最大的
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (dp[i] > max) {
                max = dp[i];
            }
        }
        return max;
    }


    //左神的分析法
    public static int maxSubArrayWithZS(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        int curr = 0;
        for (int i = 0; i < arr.length; i++) {
            curr += arr[i];
            max = Math.max(max, curr);
            curr = curr < 0 ? 0 : curr;
        }
        return max;
    }

    public static void main(String[] args) {
        int[] arr1 = { -2, -3, -5, 0, 1, 2, -1 };
        System.out.println(maxSubArrayWithBL(arr1));
        System.out.println(maxSubArrayWithDP(arr1));
        System.out.println(maxSubArrayWithZS(arr1));
    }



}


