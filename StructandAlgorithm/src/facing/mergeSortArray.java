package facing;

import java.util.Arrays;

/**
 * @author renyujie
 * @version 1.0.0
 * @ClassName mergeSortArray.java
 * @Description
 * 给定两个有序整数数组 nums1 和 nums2，将 nums2 合并到 nums1 中，使得 num1 成为一个有序数组。
 * 说明:
 * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n。
 * 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
 * 示例:
 * 输入:
 * nums1 = [1,2,3,0,0,0], m = 3
 * nums2 = [2,5,6], n = 3
 * 输出: [1,2,2,3,5,6]
 * @createTime 2021年08月16日 16:13:00
 */
public class mergeSortArray {
    //首先是笨办法  就暴力解  从小到大的放
    public static int[] heBingShengXu1(int[] A, int[] B) {
        int aLength = A.length;
        int bLength = B.length;
        //复制下备用,不要改变原数组
        int[] copyA = Arrays.copyOf(A, aLength + bLength);
        int i = 0;
        int j = 0;
        int k = 0;

        //边界情况 如果数组B为空直接返回数组A
        if(B==null){
            return A;
        }
        //边界情况 如果数组A为空直接把数组B复制入数组A中
        if (A == null) {
            for (int b = 0; b < bLength; b++) {
                copyA[b] = B[b];
            }
            return copyA;
        }

        for (; i < aLength && j < bLength; k++) {  //谁小放谁
            if (A[i] <=  B[j]) {
                copyA[k] = A[i];
                i++;
            } else {
                copyA[k] = B[j];
                j++;
            }
        }

        //终止边界
        if (i == aLength) {//A数组先到头,因为B是有序的，直接放
            while (j < bLength) {
                copyA[k] = B[j];
                j++;
                k++;
            }

        }

        if (j == bLength) {//B数组先到头,因为A是有序的，直接放
            while (i < aLength) {
                copyA[k] = A[i];
                i++;
                k++;
            }

        }
        //题目要求返回A
        return copyA;
    }

    //双指针解法  从后往前  倒着来 谁大放谁
    public static int[] heBingShengXu2(int[] A, int[] B) {
        int aLength = A.length;
        int bLength = B.length;
        //复制下备用,不要改变原数组
        int[] copyA = Arrays.copyOf(A, aLength + bLength);

        //边界情况 如果数组B为空直接返回数组A
        if(B==null){
            return A;
        }
        //边界情况 如果数组A为空直接把数组B复制入数组A中
        if (A == null) {
            for (int b = 0; b < bLength; b++) {
                copyA[b] = B[b];
            }
            return copyA;
        }


        int[] result = new int[aLength + bLength];
        int aPoint = aLength-1;
        int bPoint = bLength-1;
        int index = aLength+bLength-1;

        while (aPoint>=0 && bPoint>=0){
            if(A[aPoint]>=B[bPoint]){
                result[index] = A[aPoint];
                aPoint--;
            }else {
                result[index] = B[bPoint];
                bPoint--;
            }
            index--;
        }
        while (bPoint>=0){//A数组先到头,因为B是有序的，直接放
            result[index] = B[bPoint];
            bPoint--;
            index--;
        }
        while (aPoint>=0){//B数组先到头,因为A是有序的，直接放
            result[index] = A[aPoint];
            aPoint--;
            index--;
        }
        return result;
    }



    //test
    public static void main(String[] args) {
        int[] A = {1, 2, 3};
        int[] B = {2, 5, 6};
        System.out.println("暴力解: ");
        int[] result1 = heBingShengXu1(A, B);
        for (int i = 0; i < result1.length; i++) {
            System.out.print(result1[i]);
        }
        System.out.println();

        System.out.println("双指针: ");
        int[] result2 = heBingShengXu2(A, B);
        for (int i = 0; i < result2.length; i++) {
            System.out.print(result2[i]);
        }

    }

}
