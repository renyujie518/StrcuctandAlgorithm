package facing.meituan;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName Q2.java
 * @Description
 * 题目描述
 * 小美在整理她的书。
 * 她有n本书要放，书架上也有n个位面可以放书。
 * 每本书都有一个厚度a，可以放书的每个位置都有一个宽度b。
 * 小美不想让书折环，因此只有在满足ai<=bi的情况下，第i本书才可以放到第j个位置，否则不可以放。小美想知道有多少种放书的方案。
 *
 * 输入描述
 * 第一行一个整数n，表示有n本书，同时有n个位置。
 * 第二行n个整数，表示每本书的厚度ai
 * 第三行n个整数，表示每个可以放书位置的宽度bi，数据保证所有书可以返回书架
 *
 * 输出描述
 * 输出一行一个整数，表示放书方案的种类数，由于这个方案数很大，话对10^9+7取模后输出。
 * 4
 * 1 2 3 4
 * 2 4 3 4
 *
 * 思路：
 * 排序，然后从后往前遍历，找每本书可以放的位置个数，每次遍历可选的个数减去之前已经选的，最后全部乘起来
 * @createTime 2021年08Inte月29日 11:03:00
 */
public class fangShu {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int bookCount = sc.nextInt();
        int[] booksInt = new int[bookCount];
        for (int i = 0; i < bookCount; i++) {
            booksInt[i] = sc.nextInt();
        }
        int[] thick = new int[bookCount];
        for (int i = 0; i < bookCount; i++) {
            thick[i] = sc.nextInt();
        }
        Arrays.sort(booksInt);
        Arrays.sort(thick);
        long res = 1;
        int point = 0;
        for (int i = 0; i < bookCount; i++) {
            if (booksInt[i] > thick[i]) {
                System.out.println(0);
                return;
            }
            while (thick[point] < booksInt[i]) {
                point++;
            }
            //此时的point指向为第一次出现空位>=书厚度的地方
            //实际上就是在排序后一旦发现对应位置的thick[point] >= booksInt[i]
            //那么以空位为思考对象，该位置i - point + 1
            res = res * (i - point + 1);
            res = res % 100000007;
        }
        System.out.println(res);
    }

//    static int res = 0;
//
//    static int countProject(int[] booksInt, int[] thick) {
//        if (thick.length == 0) {
//            return 0;
//        }
//        dfs(booksInt, thick, 0);
//        return res;
//    }
//
//    static void dfs(int[] booksInt, int[] thick, int index) {
//        if (index == booksInt.length) {
//            res++;
//            return;
//        }
//        for (int i = index; i < thick.length; i++) {
//            if (booksInt[index] > thick[i]) {
//                continue;
//            }
//            swap(thick, index, i);
//            dfs(booksInt, thick, index + 1);
//            swap(thick, index, i);
//        }
//
//    }
//
//    static void swap(int[] thisck, int a, int b) {
//        int tmp = thisck[a];
//        thisck[a] = thisck[b];
//        thisck[b] = tmp;
//    }
}
