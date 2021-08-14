package MiddleClass;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName num2Chinese.java
 * @Description 阿拉伯数字转汉语
 * 把一个数字用中文表示出来。
 * 数字范围为 [0, 99999]。
 * 为了方便输出， 使用字母替换相应的中文， 万W 千Q 百B 十S 零L。
 * 使用数字取代中 文数字注： 对于 11 应该表示为 一十一(1S1)， 而不是十一(S1)
 * 输入描述： 数字 0 （包含） 到 99999 （包含） 。
 * 输出描述： 用字母替换相应的中文， 万W 千Q 百B 十S 零L
 * 示例1: 输入 12001 输出 1W2QL1
 *
 * 这题的原题就是比如 输入 12001  输出一万两千零一  纯按照汉语翻译  是个纯code问题
 * 但在百位的时候有个问题 比如17 读十七  而117 读一百一十七  多了个"十" 取决于有没有百位
 * 思路：有小及大  先考虑简单的
 * @createTime 2021年08月11日 22:00:00
 */
public class num2Chinese {
    public static String num1To9(int num) {
        if (num < 1 || num > 9) {
            return "";
        }
        String[] names = { "一", "二", "三", "四", "五", "六", "七", "八", "九" };
        return names[num - 1];
    }

    public static String num1To99(int num, boolean haiBai) {
        if (num < 1 || num > 99) {
            return "";
        }
        if (num < 10) {
            return num1To9(num);
        }
        int shi = num / 10;
        if (shi == 1 && (!haiBai)) {
            return "十" + num1To9(num % 10);
        } else {
            return num1To9(shi) + "十" + num1To9(num % 10);
        }
    }
    public static String num1To999(int num) {
        if (num < 1 || num > 999) {
            return "";
        }
        if (num < 100) {
            return num1To99(num, false);
        }
        String res = num1To9(num / 100) + '百';
        int rest = num % 100;
        if (rest == 0) {
            return res;
        } else if (rest >= 10) {//117
            res += num1To99(rest, true);
        } else {//107
            res += "零" + num1To9(rest);
        }
        return res;
    }

    public static String num1To9999(int num) {
        if (num < 1 || num > 9999) {
            return "";
        }
        if (num < 1000) {
            return num1To999(num);
        }
        String res = num1To9(num / 1000) + "千";
        int rest = num % 1000;
        if (rest == 0) {
            return res;
        } else if (rest >= 100) {
            res += num1To999(rest);
        } else {//1007   1070
            res += "零" + num1To99(rest, false);
        }
        return res;
    }

    public static String num1To99999999(int num) {
        if (num < 1 || num > 99999999) {
            return "";
        }
        int wan = num / 10000;
        int rest = num % 10000;
        if (wan == 0) {
            return num1To9999(rest);
        }
        String res = num1To9999(wan) + "万";
        if (rest == 0) {
            return res;
        } else {//10002  10012 10201
            if (rest < 1000) {
                return res + "零" + num1To999(rest);
            } else {
                return res + num1To9999(rest);
            }
        }
    }

    //判断正负和亿
    public static String getNumChiExp(int num) {
        if (num == 0) {
            return "零";
        }
        String res = num < 0 ? "负" : "正";
        int yi = Math.abs(num / 100000000);
        int rest = Math.abs((num % 100000000));
        if (yi == 0) {
            return res + num1To99999999(rest);
        }
        res += num1To9999(yi) + "亿";
        if (rest == 0) {
            return res;
        } else {
            if (rest < 10000000) {
                return res + "零" + num1To99999999(rest);
            } else {
                return res + num1To99999999(rest);
            }
        }
    }


    // for test
    public static int generateRandomNum() {
        boolean isNeg = Math.random() > 0.5 ? false : true;
        int value = (int) (Math.random() * Integer.MIN_VALUE);
        return isNeg ? value : -value;
    }

    public static void main(String[] args) {
        System.out.println(0);
        System.out.println(getNumChiExp(0));

        System.out.println(Integer.MAX_VALUE);
        System.out.println(getNumChiExp(Integer.MAX_VALUE));

        System.out.println(Integer.MIN_VALUE);
        System.out.println(getNumChiExp(Integer.MIN_VALUE));

        int num = generateRandomNum();
        System.out.println(num);
        System.out.println(getNumChiExp(num));

        num = generateRandomNum();
        System.out.println(num);
        System.out.println(getNumChiExp(num));

        num = generateRandomNum();
        System.out.println(num);
        System.out.println(getNumChiExp(num));

        num = generateRandomNum();
        System.out.println(num);
        System.out.println(getNumChiExp(num));

        System.out.println(getNumChiExp(10));
        System.out.println(getNumChiExp(110));
        System.out.println(getNumChiExp(1010));
        System.out.println(getNumChiExp(10010));
        System.out.println(getNumChiExp(1900000000));
        System.out.println(getNumChiExp(1000000010));
        System.out.println(getNumChiExp(1010100010));

    }

}
