package MiddleClass;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName printNumberNoInArray.java
 * @Description
 * 给定一个整数数组A， 长度为n， 有 1 <= A[i] <= n， 且对于[1,n]的整数， 其中部分整数会重复出现而部分不会出现。
 * 实现算法找到[1,n]中所有未出现在A中的整数。
 * 提示： 尝试实现O(n)的时间复杂度和O(1)的空间复杂度 （返回值不计入空间复 杂度） 。
 * 输入描述： 一行数字， 全部为整数， 空格分隔 A0 A1 A2 A3. . . 输出描述： 一行数字， 全部为整数， 空格分隔R0 R1 R2 R3. . .
 * 示例1:
 *      输入 1 3 4 3
 *      输出 2
 * 最容易想到的是hashmap  但要求O(1)的空间复杂度即用有限几个变量
 * 思路：
 * 争取做到i位置上的值是i+1(也可以说i位置的值是i-1)  没做到的位置就知道缺了的数字
 * 注意：
 * 这里题目中说道可能会有重复值，那么重复值会不会太影响呢
 * 不会，因为一个萝卜一个坑，只要这个位置有相应的数保证占住了，重复的值并不影响
 * 关注的只是没做到的位置
 * @createTime 2021年08月12日 18:20:00
 */
public class printNumberNoInArray {
    public static void printNumberNoInArray(int[] arr) {
        //已知arr[0~N-1]上的数字都在【1~n】之间
        if (arr == null || arr.length == 0) {
            return;
        }
        for (int value : arr) {
            process(value, arr);
        }
        //没做到的位置就知道缺了的数字
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != i + 1) {
                System.out.println(i + 1);
            }
        }
    }

    //实际上是交换，把值为i的数放到i-1位置上
    public static void process(int value, int[] arr) {
        while (arr[value - 1] != value) {
            int tmp = arr[value - 1];
            arr[value - 1] = value;
            value = tmp;
        }
    }
    public static void main(String[] args) {
        int[] test = { 3, 2, 3, 5, 6, 1, 6 };
        printNumberNoInArray(test);
    }
}
