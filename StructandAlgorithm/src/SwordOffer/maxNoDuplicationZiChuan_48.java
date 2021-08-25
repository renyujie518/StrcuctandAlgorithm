package SwordOffer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName maxNoDuplicationZiChuan_48.java
 * @Description 最长不含重复字符的子字符串
 * 输入一个字符串（只包含 a~z 的字符），求其最长不含重复字符的子字符串的长度。例如对于 arabcacfr，
 * 最长不含重复字符的子字符串为 acfr，长度为 4。
 *
 * dp[j] 代表以字符 s[j] 为结尾的 “最长不重复子字符串” 的长度
 * 转移方程： 固定右边界 j ，设字符 s[j] 左边距离最近的相同字符为 s[i] ，即 s[i] = s[j] 。
 *
 * 当 i < 0 ，即 s[j] 左边无相同字符，则dp[j]=dp[j−1]+1 ；
 * 当 dp[j−1]<j−i ，说明字符 s[i]在子字符串 dp[j-1] 区间之外 ，则dp[j]=dp[j−1]+1 ；
 * 当 dp[j−1]≥j−i ，说明字符 s[i]在子字符串dp[j−1] 区间之中 ，则 dp[j] 的左边界由 s[i]决定，即 dp[j]=j−i ；
 * 当 i < 0时，由于dp[j−1]≤j 恒成立，因而dp[j−1]<j−i 恒成立，因此分支 1. 和 2. 可被合并。
 *
 * 左边界 i获取方式： 遍历到 s[j]时，初始化索引 i=j−1 ，向左遍历搜索第一个满足s[i]=s[j] 的字符即可 。
 *
 * 方法2：
 * 思路很简单，就是滑动窗口
 *  * 1、判断 当前字符 在 当前子串 中是否出现。
 *  * 2、如果没有出现，则将当前的字符添加到子串中；
 *  * 3、如果出现了，则判断 当前子串 与 历史最长串 的大小，再去掉包含子串中从 0 到 重复字符所在位置（含该位置）之间的字符，
 *  *      再将当前的字符添加到子串中。
 *  * 4、循环结束后还需要再判断一次 当前子串 与 历史最长串 的大小，因为如果从来都没有重复过的话，再循环中不会更新max。
 *  *
 *  * 我们维护一个窗口，
 *  * 每当当前数字 在之前遍历时出现过，就更新 窗口左边界
 *  * 每遍历一个字符，就计算一次 当前结果


 * @createTime 2021年08月25日 21:53:00
 */
public class maxNoDuplicationZiChuan_48 {
    public static int lengthOfLongestSubstring1(String s) {
        if (s.isEmpty()) {
            return 0;
        }
        if (s.length() == 1) {
            return 1;
        }
        //dp[j]用于存储以j为结尾的不重复字符串的长度
        int[] dp = new int[s.length()];
        dp[0]=1;
        int max = 0;
        for (int j = 1; j < s.length(); j++) {
            //设置一个i,从j-1开始，向前遍历。如果找到一个与j相同的字符，那么就停止索引（i--,因此i停留在该字符的前一位）
            int i=j-1;
            while (i >= 0 && s.charAt(i) != s.charAt(j)) {
                i--;
            }
            if (dp[j - 1] < j - i) {//说明s[i]在最短字符串之外
                dp[j] = dp[j - 1] + 1;
            }
            else if (dp[j-1]>=j-i){//说明s[i]在最短字符串之内
                dp[j] = j - i;
            }
            max=Math.max(dp[j],max);
        }
        return max;
    }
    public static int lengthOfLongestSubstring2(String s) {
        if (s == null || s.length() <= 0) {
            return 0;
        }

        int leftIndex = -1;
        int result = 0;
        Map<Character, Integer> leftIndexMap = new HashMap<Character, Integer>();
        char[] charArray = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            if (leftIndexMap.containsKey(charArray[i])) {   // 当前数字 在之前遍历时出现过，就更新 窗口左边界
                leftIndex = Math.max(leftIndex, leftIndexMap.get(charArray[i]));
            }
            leftIndexMap.put(charArray[i], i);

            result = Math.max(result, i - leftIndex);   // 计算 当前结果
        }
        return result;
    }
}
