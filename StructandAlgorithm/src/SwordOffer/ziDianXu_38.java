package SwordOffer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName ziDianXu_38.java
 * @Description
 * 输入一个字符串，按字典序打印出该字符串中字符的所有排列
 * 。例如输入字符串 abc，则打印出由字符 a, b, c 所能排列出来的所有字符串 abc, acb, bac, bca, cab 和 cba。
 *  *保证在填每一个空位的时候重复字符只会被填入一次
 *  我们首先对原字符串排序，保证相同的字符都相邻，
 *  在递归函数中，我们限制每次填入的字符一定是这个字符所在重复字符集合中 从左往右第一个未被填入的字符
 * @createTime 2021年08月21日 22:35:00
 */
public class ziDianXu_38 {
    private static ArrayList<String> result = new ArrayList<>();
    public static ArrayList<String> ziDianXu(String str) {
        if (str.length() == 0) {
            return result;
        }
        char[] chars = str.toCharArray();
        Arrays.sort(chars);
        tracrback(chars, new boolean[chars.length], new StringBuilder());
        return result;
    }

    public static void tracrback(char[] chars, boolean[] hasUsed, StringBuilder s) {
        if (s.length() == chars.length) {
            result.add(s.toString());
            return;
        }
        for (int i = 0; i < chars.length; i++) {
            if (hasUsed[i]) {
                continue;
            }
            if (i != 0 && chars[i] == chars[i - 1] && !hasUsed[i - 1]) {//保证不重复（从左往右第一个未被填入的字符）
                continue;
            }
            hasUsed[i] = true;
            s.append(chars[i]);
            //回溯
            tracrback(chars, hasUsed, s);
            s.deleteCharAt(s.length() - 1);
            hasUsed[i] = false;
        }

    }
}
