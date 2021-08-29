package facing.meituan;
import java.util.HashSet;
import java.util.Scanner;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName Q1.java
 * @Description
 * 题目描述
 * 小团每天都会走过一条路去上课。这条路旁种有丁香树，从左向右排成一排并编号为1…n。又是一年一度的丁香季，所有丁香都开花了，第i棵丁香树的芳香值为ai，小团要从第一棵丁香树走到最后一棵。当走到第i棵丁香树时，如果这棵丁香树的芳香值比之前经过的i-1棵丁香树中棵的芳香值高，她的满意度就要加上那x棵丁香树的不同的芳香值个数。
 * 小团知道了这n程丁香树的芳香值，她想知道走过这n棵工香树后自己的满音度是多少。
 *
 * 输入描述
 * 第一行一个正整数n，为丁香树数目:
 * 第二行n个致ai第i个数代表第i棵丁香树的芳香度。对于10%的数据，n<=100;
 *
 * 输入描述
 * 16
 * 1122233331
 *
 * 输出描述
 * 一行一个整数，表示小团的满意度，
 * 提示
 * 当走到第34。5棵丁香树时，因为它们的芳香值为2，大于前两棵丁香树的芳香值1，
 * 因为只有一种芳香值，所以经过3、4、5 中的每棵树都会产生1的满意度，总满意度累计为 3；
 * 当走到第67，8，9棵丁香树时，因为它们的芳香值为3，大于前面两种芳香值12，
 * 所以经过6，7，8，9中的每棵树都会产生2的满意度，总满意度累计为 8;
 * 综上，一共会产生11点满意度。
 *
 * @createTime 2021年08月29日 10:12:00
 */
public class manyidu {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        //开始判断
        HashSet<Integer> memo = new HashSet<>();
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            memo.add(nums[i]);
            for (Integer i1 : memo) {
                if (i1<nums[i]){
                    res++;
                }
            }
        }
        System.out.println(res);
    }
}
