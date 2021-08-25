package SwordOffer;

import java.util.HashMap;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName firstUniqChar_50.java
 * @Description 第一个只出现一次的字符
 * 在字符串 s 中找出第一个只出现一次的字符。如果没有，返回一个单空格。 s 只包含小写字母。
 *
 * 示例:
 * s = "abaccdeff"
 * 返回 "b"
 *
 * s = ""
 * 返回 " "

我们可以对字符串进行两次遍历。

在第一次遍历时，我们使用哈希映射统计出字符串中每个字符出现的次数。
在第二次遍历时，我们只要遍历到了一个只出现一次的字符，那么就返回该字符，否则在遍历结束后返回空格。

 * @createTime 2021年08月25日 23:06:00
 */
public class firstUniqChar_50 {
    public static char firstUniqChar(String s) {
        HashMap<Character, Integer> map = new HashMap<>();//key = 字符串  value = 次数
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        for (int i = 0; i < s.length(); ++i) {
            if (map.get(s.charAt(i)) == 1) {
                return s.charAt(i);
            }
        }
        return ' ';
    }
}
