package MiddleClass;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName MaxABSBetweenLeftAndRight.java
 * @Description
 * 给定一个数组arr长度为N， 你可以把任意长度大于0且小于N的前缀作为左部分， 剩下的 作为右部分。
 * 但是每种划分下都有左部分的最大值和右部分的最大值， 请返回最大的， 左部分最大值减去右部分最大值的绝对值。
 *
 * 首先找到全局最大值  max
 * 只有两种可能：
 * max在左边  leftMax = max,目标就是寻找右半部分中最大值尽量小，但右半部分要有东西，从概率上讲如果只把arr[N-1]划为右部分最大值尽量小的可能最优
 * 此时答案就是  max - arr[N-1]
 *
 * 同理，max在右边的答案就是  max - arr[0]
 *
 * 综上，先遍历找最大值，然后max-min{arr[N-1],arr[0]}
 *
 * 这道题我感觉实际出的几率小，是类似于一个业务分析的问题，转化思路
 * @createTime 2021年07月29日 11:06:00
 */
public class MaxABSBetweenLeftAndRight {
    //暴力求解
    public static int maxABS1(int[] arr) {
        int result = Integer.MIN_VALUE;
        int maxLeft = 0;
        int maxRight = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            //寻找左边
            maxLeft = Integer.MIN_VALUE;
            for (int j = 0; j <= i; j++) {
                maxLeft = Math.max(arr[j], maxLeft);
            }
            //寻找右边
            maxRight = Integer.MIN_VALUE;
            for (int j = i + 1; j < arr.length; j++) {
                maxRight = Math.max(arr[j], maxRight);
            }

            //每个位置都求一次result
            result = Math.max(Math.abs(maxLeft - maxRight), result);

        }
        return result;
    }

    public static int maxABS2(int[] arr) {
        int max = Integer.MIN_VALUE;
        //先找出全局最大值
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(arr[i], max);
        }
        //最大值减去两端较小的一个
        return max - Math.min(arr[0], arr[arr.length - 1]);
    }
    public static int[] generateRandomArray(int length) {
        int[] arr = new int[length];
        for (int i = 0; i != arr.length; i++) {
            arr[i] = (int) (Math.random() * 1000) - 499;
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] arr = generateRandomArray(200);
        System.out.println(maxABS1(arr));
        System.out.println(maxABS2(arr));
    }
}
