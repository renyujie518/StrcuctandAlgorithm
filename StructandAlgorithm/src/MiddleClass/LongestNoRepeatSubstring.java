package MiddleClass;

import java.util.HashMap;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName LongestNoRepeatSubstring.java
 * @Description
 * 在一个字符串中找到没有重复字符子串中最长的长度。
 * 例如：
 * abcabcbb没有重复字符的最长子串是abc， 长度为3
 * bbbbb， 答案是b， 长度为1
 * pwwkew， 答案是wke， 长度是3 要求： 答案必须是子串， "pwke" 是一个子字符序列但不是一个子字符串。
 *
 * 思路：
 * 看到子串和子数组的问题  就想每个位置结尾的情况  比如本题 以i位置为结尾没有重复字符子串中最长的长度
 * 瓶颈1：  i位置字符上一次出现的位置
 * 瓶颈2： 以i-1位置为结尾没有重复字符子串中最长的长度
 * 取瓶颈中最近的（取较小的）
 * @createTime 2021年08月13日 21:30:00
 */
public class LongestNoRepeatSubstring {
    public static int maxUnique1(String str) {
        if (str == null || str.equals("")) {
            return 0;
        }
        char[] data = str.toCharArray();
        //用map代替hashMap 字符的范围是0~255  其中数组的index是具体的字母比如a,b,c..，值是对应在str中的位置i
        int[] map = new int[256];
        for (int i = 0; i < 256; i++) {
            map[i] = -1;
        }
        int len = 0;
        int pre = -1;
        int curr = 0;
        for (int i = 0; i < data.length; i++) {
            //以i位置的字符是m1,i-1位置字符为n1,在i的左边某个位置再次出现m2=m1，在i-1的左边某个位置再次出现n2=n1
            //这里首先max取的是  n2的位置，m2的位置
            //为什么是max  这两个值都是位置，而且都在i左边，从左到右看，那个值最大是不是离i越近
            pre = Math.max(pre, map[data[i]]);
            curr = i - pre;//i在pre的右边  答案要求的是个数
            len = Math.max(len, curr);
            map[data[i]] = i;//map更新
        }
        return len;
    }

    //滑动窗口版本  用一个之指针记录滑动窗口的起点 判断滑动窗口内是否包含当前值
    //如果包含，就将头指针移动到当前指针位置处
    //注意：通过HashMap判断的注意滑动窗口的起始位置
    public static int maxUnique2(String s) {
        int left = 0;
        if (s.length() == 0) {
            return 0;
        }
        int max = 0;
        HashMap<Character, Integer> map = new HashMap<>();  //key = 字符  value= 对应的位置
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (map.containsKey(ch)) {//说明已经存在
                if (map.get(ch) + 1 > left) {//只找窗口内的（右窗口默认就是i）
                    left = map.get(ch) + 1;//更新左窗口至重复值的右边1位
                }
            }
            map.put(ch, i);//每到一处更新map
            //选最大值  按照上述的贪心策略 max即【left~i】之间的距离  i-left+1
            if (max < i - left + 1) {
                max = i - left + 1;//max每次跟新比他大的
            }
        }
        return max;
    }



    // for test
    public static String getRandomString(int len) {
        char[] str = new char[len];
        int base = 'a';
        int range = 'z' - 'a' + 1;
        for (int i = 0; i != len; i++) {
            str[i] = (char) ((int) (Math.random() * range) + base);
        }
        return String.valueOf(str);
    }

    public static void main(String[] args) {
        String str1 = getRandomString(5);
        System.out.println(str1);
        System.out.println(maxUnique1(str1));
        System.out.println(maxUnique2(str1));
        System.out.println("+++++++++");
        String str2 = "abcabcbb";
        System.out.println(str2);
        System.out.println(maxUnique1(str2));
        System.out.println(maxUnique2(str2));
    }
}
