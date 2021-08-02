package MiddleClass;

import java.util.HashMap;
import java.util.Map;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName zhuangShui.java
 * @Description
 * 给定一个数组arr， 已知其中所有的值都是非负的， 将这个数组看作一个容器， 请返回容器能装多少水
 * 比如， arr = {3， 1 ， 2， 5， 2， 4} ， 根据值画出的直方图就是容器形状，
 * 该容 器可以装下5格水（在空缺位置装水）
 * 再比如， arr = {4， 5， 1 ， 3， 2} ， 该容器可以装下2格水
 *
 * 思路：
 * 关心自己位置的上方能装多少水
 * 问题将转换为 : i位置的水 = min{max(0...i-1)，max(i+1....N-1)} - arr[i]  如果这个差为负  那就是0
 * 总结就是  i位置的水 = max{min{max(0...i-1)，max(i+1....N-1)} - arr[i],0}
 *
 * 这里还有一种优化的解决方案  用有限几个变量实现  因为整体上类似于一个一维数组，所以联想到双指针
 * 有rightMax(右侧的最大值)，leftMax,L,R(移动的右指针)
 * 两个指针侧，哪一侧的Max较小，优先结算这一侧的水量优先结算（如果两个max一样多，从哪侧结算都可以）
 *
 * @createTime 2021年07月28日 21:50:00
 */
public class jieYuShui {
    //思路一的实现
    public static int getWater1(int[] arr) {
        if (arr == null || arr.length < 3) {
            return 0;
        }
        int result = 0;
        //数组的两端的这两个位置上是不可能存下水的
        for (int i = 1; i < arr.length - 1; i++) {
            int leftMax = 0;
            int rightMax = 0;
            //首先找到0...i-1位置上相对于i位置的最大值，每个i位置上都会找一次
            for (int l = 0; l < i; l++) {
                leftMax = Math.max(arr[l], leftMax);
            }
            //在找到i+1....N-1位置上相对于i位置的最大值
            for (int r = i + 1; r < arr.length; r++) {
                rightMax = Math.max(arr[r], rightMax);
            }
            //累加，记住短板效应  最小是瓶颈
            result += Math.max(Math.min(leftMax, rightMax) - arr[i], 0);
        }
        return result;
    }

    //思路2的实现
    public static int getWater2(int[] arr) {
        if (arr == null || arr.length < 3) {
            return 0;
        }
        int result = 0;
        //左右两边的最大值先初始化为左右两边
        int leftMax = arr[0];
        int rightMax = arr[arr.length - 1];
        //两个指针也一样，数组的两端的这两个位置上是不可能存下水的
        int l = 1;
        int r = arr.length - 1;
        while (l <= r) {
            if (leftMax <= rightMax) {//以两侧max中最小的那一侧开始滑动
                result += Math.max(leftMax - arr[l], 0);
                leftMax = Math.max(leftMax, arr[l++]);
            } else {
                result += Math.max(rightMax - arr[r], 0);
                rightMax = Math.max(rightMax, arr[r--]);
            }
        }
        return result;
    }

    public static int[] generateRandomArray() {
        int[] arr = new int[(int) (Math.random() * 98) + 2];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 200) + 2;
        }
        return arr;
    }

    public static void main(String[] args) {
        //比较器
        for (int i = 0; i < 200; i++) {
            int[] arr = generateRandomArray();
            int result1 = getWater1(arr);
            int result2 = getWater2(arr);
            if (result1 != result2) {
                System.out.println("你写的方法不对");
            }else {
                System.out.println("over");
            }
        }
        //防止死循环
        HashMap<String,String> map = new HashMap<String,String>();

        for(Map.Entry<String,String> entry : map.entrySet()){
            System.out.println(entry.getKey()+" , "+ entry.getValue());
        }
//        int[] arr = new int[]{22,12,14,31,8,11,6,25};
//        int result1 = getWater1(arr);
//        int result2 = getWater2(arr);
//        System.out.println(result1);
//        System.out.println(result2);
    }





}
