package facing;

import java.util.Stack;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName AllTimesMinToMax.java
 * @Description 数组（都是正数）中累积和与最小值的乘积，假设叫做指标A。给定一个数组，请返回子数组中，指标A最大的值。
 * 利用单调栈
 *
 * 到当前值 必须包含自己同时自己是子数组中最小，这时候再先子数组中累加和最大的那个
 * 实际上这是种对抗思想 自己是子数组中最小，就不愿扩数据，累加和最大，愿意扩数据
 * 比如【5,3,2,1,6,7,8,4】
 * i =5  包含5同时5是子数组中最小：【5】 A=25
 * i= 3  包含3同时3是子数组中最小：【5，3】 【3】 A=3*8=24 不能再往右扩到2，这样3不是最小
 * i= 2  包含2同时2是子数组中最小：【5，3，2】【3，2】【2】 A=10*2=20 不能再往右扩到1，这样2不是最小
 * i= 1  包含1同时1是子数组中最小，累加和最大：【5,3,2,1,6,7,8,4】.... A=36*1=36
 *...
 * 遍历一遍，再找最大值
 * 怎么联想到单调栈：
 * 1位置的3 左边离他最近还比他小的是不能扩的位置
 * 1位置的3 右边离他最近还比他小的是不能扩的位置
 * 那就知道3怎么扩了，中间都能扩到，累加和一定最大，因为正数
 * 所有的值套单调栈一搞
 * @createTime 2021年03月22日 23:09:00
 */
public class AllTimesMinToMax {
    //暴力解
    public static int max1(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                int minNum = Integer.MAX_VALUE;
                int sum = 0;
                for (int k = i; k <= j; k++) {
                    sum += arr[k];
                    minNum = Math.min(minNum, arr[k]);
                }
                max = Math.max(max, minNum * sum);
            }
        }
        return max;
    }

    public static int max2(int[] arr) {
        int size = arr.length;
        int[] sums = new int[size];
        sums[0] = arr[0];
        for (int i = 1; i < size; i++) {
            sums[i] = sums[i - 1] + arr[i];
        }
        int max = Integer.MIN_VALUE;
        Stack<Integer> stack = new Stack<Integer>();
        for (int i = 0; i < size; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                int j = stack.pop();
                max = Math.max(max, (stack.isEmpty() ? sums[i - 1] : (sums[i - 1] - sums[stack.peek()])) * arr[j]);
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int j = stack.pop();
            max = Math.max(max, (stack.isEmpty() ? sums[size - 1] : (sums[size - 1] - sums[stack.peek()])) * arr[j]);
        }
        return max;
    }

    public static int[] gerenareRondomArray() {
        int[] arr = new int[(int) (Math.random() * 20) + 10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 101);
        }
        return arr;
    }

    public static void main(String[] args) {
        int testTimes = 2000000;
        for (int i = 0; i < testTimes; i++) {
            int[] arr = gerenareRondomArray();
            if (max1(arr) != max2(arr)) {
                System.out.println("FUCK!");
                break;
            }
        }
    }
}
