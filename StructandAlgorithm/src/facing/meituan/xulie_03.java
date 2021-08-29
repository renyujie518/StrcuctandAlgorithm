package facing.meituan;

import java.util.Scanner;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName xulie_03.java
 * @Description

 * 小美有一个长度为 n 的序列 A，A[i] 表示序列中第 i 个数(1<=i<=n)。
 * 她定义序列中第 i 个数的 prev[i] 值 为前 i-1 个数中比 A[i] 小的最大的值，即满足 1<=j<i 且 A[j]<A[i] 中最大的 A[j]，
 * 若不存在这样的数，则 prev[i] 的值为 0。现在她想要你帮忙计算对于所有的 i，prev[i]*i 之和是多少，即
 ∑prev[i]∗i(1≤i≤n，A[1]为序列第1个数)。

翻译：
输入一个数组ints，根据这个数组生成一个新的数组dp，
i位置的数值是不大于ints[i]的前1~i的最大的那个数，
求新数组的dp[i]*i 之和，i=1 ~n

输入描述
第一行是一个整数 n 表示序列的长度。
接下来一行 n 个数用空格隔开，第 i 个数表示 A[i] 的大小。

输出描述
一行一个整数，表示答案。

样例输入
5
1 6 3 3 8
样例输出
39

 * @createTime 2021年08月28日 23:34:00
 */
public class xulie_03 {
    //暴力法
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] ints = new int[n + 1];
        //tiaojian[]表示每个位置对应的数找到的前i-1个数较小中的最大值
        //至于为什么是n+1  因为在iaojian[0]应该是0，用new初始化的方式解决，所以后面循环中的j是从1 开始
        int[] tiaojian = new int[n+1];
        long res = 0;
        for (int i = 1; i <= n; i++) {
            ints[i] = scanner.nextInt();
        }
        for(int j = 1;j <= n; j++){
            for(int k = 0 ;k < j;k++){
                if(ints[k] < ints[j]){
                    tiaojian[j] = Math.max(tiaojian[j],ints[k]);
                }
            }
            res += tiaojian[j]*j;
        }
        System.out.println(res);
    }
}
