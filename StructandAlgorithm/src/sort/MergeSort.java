package sort;

import util.bijiaoqi_arrays_sort;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName MergeSort.java
 * @Description 归并排序 左边有序 右边有序，再让整体有序
 * @createTime 2021年02月09日 14:37:00
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] arrs = {3,4,5,123,45,13,435,765,12,1,2,3,345,56,78,9,3};
        int[] mergesort = mergesort(arrs);
        for (int arr : mergesort ) {
            System.out.println(arr);
        }

        //test
        bijiaoqi_arrays_sort.test("sort.MergeSort");
    }

    public static void process(int[] arrs, int L, int R){
        if (L==R){
            return;
        }
        int mid = L+((R-L)>>1);
        process(arrs, L, mid); //左排
        process(arrs, mid+1, R); //右排
        merge(arrs, L, mid, R); //合起来牌
    }

    public static void merge(int[] arrs,int L,int mid,int R){
        int[] temp = new int[R-L+1];//创建一个临时和原数组大小一样的临时数组
        int i= 0;
        int p1 = L;
        int p2 = mid+1;
        while (p1<=mid && p2<=R){
            temp[i++] = arrs[p1]<= arrs[p2] ? arrs[p1++] :arrs[p2++]; //赋值完再移动指针
        }
        while (p1<=mid){
            temp[i++] = arrs[p1++];//p2越界，触发这个while,把左半部分都放到临时
        }
        while (p2<=R){
            temp[i++] = arrs[p2++];//p1越界，触发这个while,把右半部分都放到临时
        }
        for (int j = 0; j < temp.length; j++) {
            arrs[L + j] = temp[j];//排序完再把临时的还给arrs
        }
    }

    public static int[] mergesort(int[] arrs){
        if (arrs == null || arrs.length <2){
            return arrs;
        }
        process(arrs,0, arrs.length-1);
        return arrs;
    }
}





