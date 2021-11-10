package facing.didi;

import java.util.HashMap;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName noDuplicationZuiChang.java
 * @Description 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 * 示例 1:
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 *
 * 示例 3:
 * 输入: s = "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是"wke"，所以其长度为 3。
 * 请注意，你的答案必须是 子串 的长度，"pwke"是一个子序列，不是子串。
 *
 * 滑动窗口

 * @createTime 2021年09月17日 14:17:00
 */
public class noDuplicationZuiChang {
    public int lengthOfLongestSubstring(String s) {
        //定义返回结果
        int res = 0;
        //定义窗口左边界坐标
        int start = 0;
        //key = 对应字符   value = 所在的位置
        HashMap<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (map.containsKey(c)){
                //start右移到前一个重复字符的下一个坐标，若该坐标小于start则不移动，防止start左移
                start = Math.max(map.get(c) + 1, start);
            }
            map.put(c, i);
            res = Math.max(res, i - start + 1);
        }
        return res;
    }
}
