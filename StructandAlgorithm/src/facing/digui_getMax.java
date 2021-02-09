package facing;

import util.bijiaoqi_arrays_sort;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName digui_getMax.java
 * @Description 递归寻找最大数
 * @createTime 2021年02月08日 21:35:00
 */
public class digui_getMax {
    public static void main(String[] args) {
        int[] arrs = {3,4,5,123,45,13,435,765,12,1,2,3,345,56,78,9,3};
        int result = process(arrs, 0, arrs.length-1);
        System.out.println(result);

    }

    public static  int process(int[] arrs,int L,int R){
        if (L==R){
            return arrs[L];
        }

        int mid = L+((R-L)>>1);
        int leftMax = process(arrs, L, mid);
        int rightMax = process(arrs, mid+1, R);
        return Math.max(leftMax, rightMax);
    }
}
