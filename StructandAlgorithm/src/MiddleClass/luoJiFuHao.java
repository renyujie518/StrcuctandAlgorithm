package MiddleClass;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName luoJiFuHao.java
 * @Description
 * 给定一个只由 0(假)、 1 (真) 、 &(逻辑与)、 | (逻辑或)和^(异或)五种字符组成 的字符串express，
 * 再给定一个布尔值 desired。 返回express能有多少种组合方式， 可以达到desired的结果。
 * 【举例】
 *      express="1^0|0|1"， desired=false
 *      只有 1 ^ ( (0 | 0) | 1 ) 和 1 ^ (0| (0 | 1 ) ) 的组合可以得到 false， 返回 2。
 *      express="1"， desired=false
 *      无组合则可以得到false， 返回0
 * @createTime 2021年08月13日 20:34:00
 */
public class luoJiFuHao {
    //观察发现，本题的精髓在于括号位置的组合，但是exp是有要求的：从0开始，偶数位必须是数字，奇数位必须是运算符，而且length必须是奇数
    public static boolean isValid(char[] exp) {
        if ((exp.length & 1) == 0) {
            return false;
        }
        for (int i = 0; i < exp.length; i = i + 2) {  //每隔两个，偶数位必须是数字（0，1）
            if ((exp[i] != '1') && (exp[i] != '0')) {
                return false;
            }
        }
        for (int i = 1; i < exp.length; i = i + 2) {
            if ((exp[i] != '&') && (exp[i] != '|') && (exp[i] != '^')) {
                return false;
            }
        }
        return true;
    }
    public static int luoJiFuHao(String express, boolean desired) {
        if (express == null || express.equals("")) {
            return 0;
        }
        char[] exp = express.toCharArray();
        if (!isValid(exp)) {
            return 0;
        }
        return process(exp, desired, 0, exp.length - 1);
    }

    //exp[L..R]返回期待为desired的组合方法数
    //潜台词：L R一定不要压中逻辑符号。否则无法运算
    public static int process(char[] exp, boolean desired, int L, int R) {
        //basecase
        if (L == R) {//注意啊 ，L R一定不要压中逻辑符号
            if (exp[L] == '1') {
                return desired ? 1 : 0;
            } else {//'0'
                return desired ? 0 : 1;
            }
        }
        //L...R不止一个字符，但两者间一定是奇数个  比如 "1^2|5"
        int res = 0;
        if (desired) {//期待为true
            for (int i = L + 1; i < R; i += 2) {//逻辑符从+1位置起，每隔2个
                switch (exp[i]) {
                    case '&'://两者都为真
                        res += process(exp, true, L, i - 1) * process(exp, true, i + 1, R);
                        break;
                    case '|'://有1个为真就是真
                        res += process(exp, true, L, i - 1) * process(exp, false, i + 1, R);
                        res += process(exp, false, L, i - 1) * process(exp, true, i + 1, R);
                        res += process(exp, true, L, i - 1) * process(exp, true, i + 1, R);
                        break;
                    case '^'://不同是真
                        res += process(exp, true, L, i - 1) * process(exp, false, i + 1, R);
                        res += process(exp, false, L, i - 1) * process(exp, true, i + 1, R);
                        break;
                }
            }
        } else {
            //false
            for (int i = L + 1; i < R; i += 2) {
                switch (exp[i]) {
                    case '&'://有一个是假就是假
                        res += process(exp, false, L, i - 1) * process(exp, true, i + 1, R);
                        res += process(exp, true, L, i - 1) * process(exp, false, i + 1, R);
                        res += process(exp, false, L, i - 1) * process(exp, false, i + 1, R);
                        break;
                    case '|'://两个都为假才是假
                        res += process(exp, false, L, i - 1) * process(exp, false, i + 1, R);
                        break;
                    case '^'://相同为假
                        res += process(exp, true, L, i - 1) * process(exp, true, i + 1, R);
                        res += process(exp, false, L, i - 1) * process(exp, false, i + 1, R);
                        break;
                }
            }
        }
        return res;
    }
    public static void main(String[] args) {
        String express = "1^0|0|1";
        boolean desired = false;
        System.out.println(luoJiFuHao(express, desired));
    }
}
