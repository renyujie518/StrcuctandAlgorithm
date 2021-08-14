package MiddleClass;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName jiaJieMi.java
 * @Description
 * 在数据加密、压缩中常需要对特殊的字符串进行编码。
 * 给定的字母表A由26个小写英文字母组成， 即 A={a, b. . . z} 。
 * 该字母表产生的长序字符串是指定字符串中字母从左到右出现的次序与字母在字母表中出现 的次序相同， 且每个字符最多出现1次。
 * 例如， a， b， ab， bc， xyz等字符串是升序字符串。
 * 对字母表A产生 的所有长度不超过6的升序字符串按照字典排列编码如下： a(1) ， b(2) ， c (3) ……， z (26) ， ab(27) ， ac(28) ……
 * 对于任意长度不超过16的升序字符串， 迅速计算出它在上述字典中的编码。
 * 输入描述： 第1行是一个正整数N， 表示接下来共有N行， 在接下来的N行中， 每行给出一个字符串。
 * 输出描述： 输出N行， 每行对应于一个字符串编码。
 * 示例1: 输入 3 a b ab 输出 1 2 27
 * @createTime 2021年08月14日 18:39:00
 */
public class ziDianXuBianMa {
    //首先实现两个功能函数

    //必须以i号字符开头，总程度为len的子序列有多少个
    //比如 a 7 实际上要求 以b开头长度为6的子序列+以c开头长度为6的子序列+...以z开头长度为6的子序列
    public static int f1(int i, int len) {
        int sum = 0;
        if (len == 1) {
            return 1;
        }
        for (int j = i + 1; j <= 26; j++) {
            sum += f1(j, len - 1);
        }
        return sum;
    }

    // (不规定开头)长度为len的字符串有多少个
    public static int f2(int len) {
        int sum = 0;
        for (int i = 1; i <= 26; i++) {
            sum += f1(i, len);
        }
        return sum;
    }


    public static int ziDianXuBianMa(String s) {
        char[] str = s.toCharArray();
        int sum = 0;
        int len = str.length;
        //举例{d,j,v}在哪  规定@（a,x）代表以a开头，共x个的字符的总个数
        //a、b、c、"d"、e、f、g、h、i、"j"、k、l、m、n、o、p、q、r、s、t、u、"v"、w、x、y、z
        //首先{d,j,v}一定排在长度为1，长度为2之后
        for (int i = 1; i <= len - 1; i++) {
            sum += f2(i);
        }
        //第二步：一定在@（a,3）+@(b,3)+@(c,3)之后
        int first = str[0] - 'a' + 1;  //a对应1  b对应2 。。。。 z对应26
        for (int i = 1; i <= first - 1; i++) {
            sum += f1(i, len);
        }
        //第三块 ：以d开头：@(e,2)+@(f,2)+...@(i,2)  以j为第2：@(K,1)+@(l,1)+@(m,1)+....+@(u,1)  以v为结尾：+1
        int pre = first;
        for (int i = 1; i <= len - 1; i++) {
            int curr = str[i] - 'a' + 1;
            for (int j = pre + 1; j <= curr - 1; j++) {
                sum += f1(j, len - i);
            }
            pre = curr;
        }
        return sum + 1;
    }

    public static void main(String[] args) {
        String test = "ab";
        System.out.println(ziDianXuBianMa(test));  //27
    }

}
