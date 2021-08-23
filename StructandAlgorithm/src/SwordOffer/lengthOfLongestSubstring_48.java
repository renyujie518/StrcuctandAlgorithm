package SwordOffer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName lengthOfLongestSubstring_48.java
 * @Description
 * 请从字符串中找出一个最长的不包含重复字符的子字符串，计算该最长子字符串的长度。
 * 示1:
 *
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3
 * 思路很简单，就是滑动窗口
 * 1、判断 当前字符 在 当前子串 中是否出现。
 * 2、如果没有出现，则将当前的字符添加到子串中；
 * 3、如果出现了，则判断 当前子串 与 历史最长串 的大小，再去掉包含子串中从 0 到 重复字符所在位置（含该位置）之间的字符，
 *      再将当前的字符添加到子串中。
 * 4、循环结束后还需要再判断一次 当前子串 与 历史最长串 的大小，因为如果从来都没有重复过的话，再循环中不会更新max。
 *
 * 我们维护一个窗口，
 * 每当当前数字 在之前遍历时出现过，就更新 窗口左边界
 * 每遍历一个字符，就计算一次 当前结果

 * @createTime 2021年08月20日 14:17:00
 */
public class lengthOfLongestSubstring_48 {
    public int lengthOfLongestSubstring(String s) {
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
