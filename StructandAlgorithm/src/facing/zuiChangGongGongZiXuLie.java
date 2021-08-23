package facing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName problem1.java
 * @Description 最长公共子序列
 * 输入
 * abcfbc abfcab
 * programming contest
 * abcd mnp
 * 输出
 * 4
 * 2
 * 0
 * @createTime 2021年08月20日 15:47:00
 */
public class zuiChangGongGongZiXuLie {
    public static void main(String[] args) throws IOException {
        //最长公共子序列问题
        //思路  初步设计使用动态规划求解
//        //利用inputStreamBuffer去读
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        String[] data1 = br.readLine().trim().split("");
//        String[] data2 = br.readLine().trim().split("");
//        System.out.println(data1.toString());
////        System.out.println(data[0]);
//        System.out.println(ziXulie(data[0].toCharArray(),data[1].toCharArray()));

        Scanner sc = new Scanner(System.in);
        String str1;
        String str2;
        while (sc.hasNext()) {
            str1 = sc.next();
            str2 = sc.next();
            System.out.println( ziXulie(str1.toCharArray(), str2.toCharArray()));
        }
    }

    public static int  ziXulie(char[] str1,char[] str2){
        int len1 = str1.length;
        int len2 = str2.length;
        //dp[i][j]：str1[0,i-1]与str2[0,j-1]的最长公共子序列长度
        //dp[i][j]的定义不是 str1[0,i] 和 str2[0,j] ，是为了方便当 i = 0 或者 j = 0 的时候，
        //dp[i][j]表示的为空字符串和非空字符串的匹配，这样 dp[i][j]可以初始化为 0.
        int dp[][] = new int[len1+1][len2+1];
        //初始化
        for(int i = 0;i <=len1;i++){
            for(int j = 0;j<=len2;j++){
                //basecase
                if(i== 0 || j ==0){//i =0 时，dp[0][j] 表示的是str1中取空字符串 跟 text2的最长公共子序列，结果肯定为 0,j的时候同理
                    dp[i][j] = 0;
                }else if( str1[i-1] == str2[j-1]){
                    //当 text1[i - 1] == text2[j - 1] 时，说明两个子字符串的最后一位相等，所以最长公共子序列又增加了 1，
                    //所以 dp[i][j] = dp[i - 1][j - 1] + 1；
                    //举个例子，比如对于 ac 和 bc 而言，他们的最长公共子序列的长度等于 a和 b的最长公共子序列长度 0 + 1 = 1。。
                    dp[i][j] = dp[i-1][j-1]+1;
                }else {
                    //当 str1[i - 1] != str2[j - 1]时，说明两个子字符串的最后一位不相等，
                    //那么此时的状态 dp[i][j] 应该是 dp[i - 1][j] 和 dp[i][j - 1] 的最大值。
                    //举个例子，比如对于 ace和 bc而言，他们的最长公共子序列的长度等于 :
                    //① ace 和 b 的最长公共子序列长度0 与 ② ac 和 bc的最长公共子序列长度1 的最大值，即 1。
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        //要返回的是最长公共子序列，肯定是考虑最全字母的
        return dp[str1.length][str2.length];

    }
}
