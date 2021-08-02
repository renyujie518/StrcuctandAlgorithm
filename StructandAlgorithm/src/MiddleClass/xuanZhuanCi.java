package MiddleClass;

import facing.KMP;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName xuanZhuanCi.java
 * @Description
 *
 *如果一个字符串为str， 把字符串str前面任意的部分挪到后面形成的字符串叫 作str的旋转词。
 *  比如str="12345"， str的旋转词有"12345"、 "23451 " 、 "34512"、 "45123"和"51234"。
 *  给定两个字符串a和b， 请判断a和b是否互为旋转 词。
 *  比如： a="cdab"， b="abcd"， 返回true。
 *  a="1ab2"， b="ab12"， 返回false。
 *  a="2ab1"， b="ab12"， 返回true。
 *
 *  思路：
 *  复制一遍紧接着后面 比如abcd复制完就是abcdabcd，那么这个大字符串中每个长度为4的子字符串都是旋转词
 *  怎么判断互为旋转词  可以看是不是大字符串的子串  判断子串  优先想到KMP
 *
 * @createTime 2021年07月29日 16:18:00
 */
public class xuanZhuanCi {
    public static boolean isRotation(String a, String b) {
        if (a == null || b == null || a.length() != b.length()) {
            return false;
        }
        String b2 = b + b;
        //KMP算法解决的问题字符串str1和str2，str1是否包含str2，如果包含  返回str2在str1中开始的位置。 如果不包含返回-1
        return KMP.getIndexOf(b2, a) != -1;
    }

    public static void main(String[] args) {
        String str1 = "cdab";
        String str2 = "abcd";
        System.out.println(isRotation(str1, str2));

    }

}
