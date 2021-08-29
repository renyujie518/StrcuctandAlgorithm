package facing.meituan;

import java.util.Scanner;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName minOption_04.java
 * @Description
 * 小美给了小团一个长度为 n（n为偶数）的序列 A，序列中的数都是介于 [1,100000] 的整数。
 * 小团想把这个序列变得漂亮后再送回给小美。小美觉得一个序列是漂亮的当且仅当这个序列的前一半和后一半是一样的，
 * 即对于 1<=i<=n/2 都满足 A[i]==A[i+n/2]。
 * 小团可以按进行以下操作任意次：
 * 选择两个介于 [1, 100000] 之间的数 x 和 y，然后将序列 A 中所有值为 x 的数替换为 y。
 * 注意，每次操作都会在上一次操作后得到的序列上进行。小团想知道他最少需要操作多少次可以把序列变成漂亮的。
 *
 * 输入描述
 * 第一行是一个整数 n，表示序列的长度。数据保证 n 为偶数。
 * 第二行有 n 个用空格隔开的整数，第 i 个数表示 A[i] 的值。数据保证 1<=A[i]<=100000。
 *
 * 输出描述
 * 输出小团需要的最少操作次数。
 *
 * 输入样例
 * 10
 * 4 2 1 5 2 10 2 1 5 8
 * 输出样例
 * 2
 * ：将一个数组左半和右半变成一样的操作数。
 * @createTime 2021年08月28日 23:51:00
 */
public class minOption_04 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] ints1 = new int[n/2];
        int[] ints2 = new int[n/2];
        int res = 0;
        for(int i = 0;i < n;i ++){
            if(i < n/2){
                ints1[i] = scanner.nextInt();
            }else{
                ints2[i - n/2] = scanner.nextInt();
            }
        }
        for(int i = 0;i < n/2;i ++){
            if (ints1[i] != ints2[i]){
                //不一样就直接替换
                res ++;
            }
        }
        System.out.println(res);
    }
}
