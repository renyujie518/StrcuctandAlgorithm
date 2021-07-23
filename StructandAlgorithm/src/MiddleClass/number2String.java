package MiddleClass;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName number2String.java
 * @Description 将给定的数转换为字符串
 * 原则如下： 1 对应 a， 2对应b， …. . 26对应z，
 * 例如12258 可以转换为"abbeh", " aveh", " abyh", " lbeh"和" lyh"， 个数为5，
 * 编写一个函数， 给出可以转换的不同字符串的个数。
 *
 * 思路：从左往右尝试模型
 * 1. i位置的数字是0  i位置出发往后无效
 * 2. i位置的数字!=0,考虑F(i+1)
 * 3. F(i)(i+1) <=26  考虑F(i+2)
 * @createTime 2021年07月22日 10:51:00
 */
public class number2String {
    //首先写暴力递归的，str[index...]能走出多少种有效的字符串
    public static int process(char[] str, int index) {
        //走到底了，说明是有一条可行的路
        if (index == str.length) {
            return 1;
        }
        //开头不能是0
        if (str[index] == '0') {
            return 0;
        }
        //走到这里还没return,说明index及index+1还有数字,要满足不以0位开头
        //这时候做一个决定，让index处自己做决定(str[index]自己做一一次转换)，那么process中新的起点是index+1
        int result = process(str, index + 1);
        //这时候要考虑边界：除了index之外，后续有没有字符串了(index位于倒数第二，那么index+1就到头了，直接return)
        if (index == str.length - 1) {
            return result;
        }
        //如果还是没有return,说明上述条件都符合，index+1还没有越界，这时候要考虑条件3
        if (((str[index] - '0') * 10 + (str[index + 1] - '0')) <= 26) {
            //把index+2的情况累加到result上
            result += process(str, index + 2);
        }
        return result;
    }

    //给定的数转为字符串，返回转换 的可能性次数
    public static int number2String(int num) {
        if (num < 1) {
            return 0;
        }
        return process(String.valueOf(num).toCharArray(), 0);
    }


    public static int number2StringDP(int num) {
        if (num < 1) {
            return 0;
        }
        char[] str = String.valueOf(num).toCharArray();
        //为什么+1：  把所有尝试过程的可能装下 index == str.length，返回1，即index可以取到str.length，
        int[] dp = new int[str.length + 1];
        dp[str.length] = 1;
        //初值定义后从右往左推,如果最后一个字符（length-1）不为0，代表可以转化，否则不能转化。对应思路1
        dp[str.length - 1] = str[str.length - 1] == '0' ? 0 : 1;
        //以上是定义初值，从右往左考虑的，所以定义的是最右边的两个str.length   str.length-1
        //下面开始填满dp[],从右往左（即考虑思路2，3）去塞满dp,但是注意的一点是，依据题意，还是左边的取值依赖右边的
        for (int i = str.length - 2; i >= 0; i--) {
            if (str[i] == '0') {
                dp[i] = 0;
            } else {
                dp[i] = dp[i + 1] + (((str[i] - '0') * 10 + str[i + 1] - '0') <= 26 ? dp[i + 2] : 0);
            }
        }
        //从右往左推，第一个是答案
        return dp[0];
    }

    public static void main(String[] args) {
        int test = 12258;
        System.out.println(number2String(test));
        System.out.println(number2StringDP(test));
    }


}
