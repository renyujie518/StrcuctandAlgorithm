package facing.meituan;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName zhaoShu_1.java
 * @Description
 * 小美和小团在玩游戏。小美将会给出 n 个大小在 1 到 n 之间（包括 1 和 n）的整数，然后小美会再告诉小团一个整数 k，
 * 小团需要找到一个最小的整数 x 满足以下条件：
 * 整数x的大小在 1 到 n 之间（包括1和n）
 * 在小美给出的 n 个整数中，恰好有 k 个数严格比 x 小

 * 输入描述
 * 第一行是一个数 T，表示有 T 组数据。
 * 对于每组数据：
 * 第一行有两个整数 n 和 k，分别表示小美将会给出 n 个数以及她给出的整数k。
 * 接下来一行有 n 个用空格隔开的正整数，表示小美给出的 n 个正整数。
 *
 *  输出描述
 * 对于每组数据：
 * 如果存在满足要求的数 x，第一行先输出 “YES”（不含引号），第二行输出数 x 的值。
 * 如果不存在满足要求的数 x，输出 “NO”（不含引号）。
 *
 * 样例输入
 * 2
 * 6 6
 * 1 6 6 2 1 3
 * 6 3
 * 1 6 5 2 2 5
 * 样例输出
 * NO
 * YES
 * 3
 * @createTime 2021年08月28日 23:07:00
 */
public class zhaoShu_1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for (; t > 0; t--) {
            int n = sc.nextInt();
            int k = sc.nextInt();
            int[] nums = new int[n];
            for (int i = 0; i < nums.length; i++) {
                nums[i] = sc.nextInt();
            }
            int ans = fun(nums, n, k);
            if (ans == -1) {
                System.out.println("NO");
            } else {
                System.out.println("YES");
                System.out.println(ans);
            }
        }
    }

    //穷举法 恰好有 k 个数严格比 ans 小
    private static int fun (int[] nums, int n, int k) {
        Arrays.sort(nums);
        for (int x = 1; x <= n; x++) {//题目中说整数x的大小在 1 到 n 之间（包括1和n）
            int xiao = 0;
            for (int j = 0; j < nums.length; j++) {
                if(nums[j] < x) xiao++;
            }
            if(xiao == k){
                return x;
            }
        }
        return -1;
    }

}
