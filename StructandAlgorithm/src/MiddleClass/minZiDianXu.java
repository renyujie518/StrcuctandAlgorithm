package MiddleClass;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName minZiDianXu.java
 * @Description
 * 给定一个全是小写字母的字符串str， 删除多余字符， 使得每种字符只保留一个， 并让 最终结果字符串的字典序最小
 * 【举例】 str = "acbc"， 删掉第一个'c'， 得到"abc"， 是所有结果字符串中字典序最小的。
 * str = "dbcacbca"， 删掉第一个'b'、 第一个'c'、 第二个'c'、 第二个'a'， 得到"dabc"(d必须保留，剩1个)， 是所有结果字符串中字典序最小的
 * 思路：
 * 贪心+递归
 * 先建立词频表  找到minACSIndex即阿斯科码最小的字符的位置,minACSIndex左舍弃，右去相应字符,拼接+递归
 *
 * @createTime 2021年08月14日 17:36:00
 */
public class minZiDianXu {
    public static String removeDuplicateLetters(String str) {
        if (str == null || str.length() < 2) {
            return str;
        }
        int[] map = new int[256];
        for (int i = 0; i < str.length(); i++) {
            map[str.charAt(i)]++;   //key:对应的字符  value：出现的次数
        }
        int minACSIndex = 0;
        for (int i = 0; i < str.length(); i++) {
            if (--map[str.charAt(i)] == 0) {
                break;
            } else {
                //遍历过程中找到阿斯科码最小的字符的位置
                minACSIndex = str.charAt(minACSIndex) > str.charAt(i) ? i : minACSIndex;
            }
        }
        //要按照字典序排序，现在ACSI最小的位置找到了，放在首位，后面要拼接+
        //递归调用  剩下在函数中需要考虑的字符有哪些呢 首先minACSIndex左边包括minACSIndex不要（已经考虑过了）
        //举个例子；bbavdfb ACSI最小的是a,只保留vdbfb下次循环，左边的都舍弃
        //为什么可以舍弃，首先词频表中=0时才break,那么能进入下面这个递归的首先minACSIndex所指的字符一定只剩一个
        //而不管原先是1个的还是有重复值的其他字符一定还有，一定在i之后（因为考虑的是0~i）,所以考虑之后的字符就可以了
        //同时注意之前考虑的是0~i位置的a,而且只选择了1次，保不准a有重复，题目要求最终结果中国各字符一次，所以要去掉（替换为""）
        //总结就是minACSIndex左舍弃，右去相应字符
        return String.valueOf(str.charAt(minACSIndex)) +
                removeDuplicateLetters(
                        str.substring(minACSIndex + 1).
                                replaceAll(String.valueOf(str.charAt(minACSIndex)), "")
                );
        //str[minACSIndex] + str[minACSIndex+1...](痛死去掉里面的str.charAt(minACSIndex))
    }


    public static void main(String[] args) {
        String str = "dbcacbca";
        System.out.println(removeDuplicateLetters(str));

    }

}
