package facing;

import java.util.LinkedList;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName SlidingWindowMaxArray.java
 * @Description 滑动窗口
 * 有一个整型数组arr和一个大小为w的窗口从数组的最左边滑到最右边，窗口每次向右边滑一个位置。
 * 例如，数组为[4,3,5,4,3,3,6,7]，
 * 窗口大小为3时:[435]433674
 * [354]3367
 * 43[543]367
 * 435[433]67
 * 4354[336]7
 * 43543[367]
 *如果数组长度为n，窗口大小为w，则一共产生n-w+1个窗口的最大值。
 * 请实现一个函数。输入:整型数组arr，窗口大小为w。
 * 输出:一个长度为n-w+1的数组res，res[i]表示每一种窗口状态下的max
 * 以本题为例，结果应该返回{5,5,5,4,6,7}。
 *
 * 左边界，右边界都可以往右动，但左边界不能超过右边界
 * 如果暴力法，在每个窗口遍历代价有点大，如何以较小的代价获得  双端队列（两边都可以进出）放下标，
 * R(i+1)往右动，但由大到小排着，从尾巴进，任何时候的最大值在双端队列的头部待着，一进入的比之前的还大，就把旧值从尾部弹开（弹开就不要了），直到满足严格单调性
 * L(i-1)往右动，看一眼过期元素（L-1）是不是在队列头,是队列头从头部弹出，否则不动
 *
 * 这样双端队列维持的是 如果目前的窗口不动，L往右动的时候（依次过期）最大值的优先级
 *
 * 本试题是双端队列的阉割版 固定窗口为3而且每次把L，R往右动一下即可
 * @createTime 2021年03月22日 20:33:00
 */
public class SlidingWindowMaxArray {
    public static int[] getMaxWindow(int[] arr, int w) {
        if (arr == null || w < 1 || arr.length < w) {
            return null;
        }
        LinkedList<Integer> qmax = new LinkedList<Integer>();  //双端队列就是个list,里面储存index
        int[] res = new int[arr.length - w + 1]; //如果数组长度为n，窗口大小为w，则一共产生n-w+1个窗口的最大值。
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            while (!qmax.isEmpty() && arr[qmax.peekLast()] <= arr[i]) {  //这里把队列放倒，左边是头，右边是尾
                qmax.pollLast();  //一直从尾部弹，直到队列里的尾部>当前值（保证了队列从左到右严格递增）
            }
            //或者这么理解，在队列不为空的前提下，只要当前值比尾部大（等于也不行），就弹，弹完把最大的，也就是arr[i]放到对尾
            qmax.addLast(i); //满足递增条件后把index从尾巴进
            if (qmax.peekFirst() == i - w) {  //i - w 过期的下标 此时w是3，当前位置往前数3个
                //这里有个细节，i=0开始，比如43[543]367，i=4!!(R=4,L=2),   i-3=1=L-1
                qmax.pollFirst(); //L往右动，看一眼过期元素（L-1）是不是在队列头,是队列头从头部弹出，否则不动s
            }
            if (i >= w - 1) {  //i-w+1=0 代表窗口形成。之后i会增加，所以会越来越大
                res[index++] = arr[qmax.peekFirst()];//任何时候的最大值在双端队列的头部待着
            }
        }
        return res;
    }

    // for test
    public static void printArray(int[] arr) {
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = { 4, 3, 5, 4, 3, 3, 6, 7 };
        int w = 3;
        printArray(getMaxWindow(arr, w));

    }
}
