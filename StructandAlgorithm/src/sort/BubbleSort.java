package sort;


import util.bijiaoqi_arrays_sort;

import java.util.Arrays;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName bubbleSort.java
 * @Description 冒泡排序
 * @createTime 2021年02月08日 19:24:00
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] arrs = {3,4,5,123,45,13,435,765,12,1,2,3,345,56,78,9,3};
        int[] bubblesort = bubblesort(arrs);
        for (int arr : bubblesort) {
            System.out.println(arr);
        }


    }


    public static int[] bubblesort(int[] arrs){
        if (arrs ==null || arrs.length<2){
            return arrs;
        }
        for (int i =arrs.length-1;i>0;i--) {
            for (int j = 0; j < i; j++) {
                if (arrs[j]>arrs[j+1]){
                    swap(arrs,j,j+1);
                }
            }
        }
       return arrs;
    }

    public  static  void swap(int[] arrs,int i,int j){
        arrs[i] = arrs[i]^arrs[j];
        arrs[j] = arrs[i]^arrs[j];
        arrs[i] = arrs[i]^arrs[j];
    }

}
