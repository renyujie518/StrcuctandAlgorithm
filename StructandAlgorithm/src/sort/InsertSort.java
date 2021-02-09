package sort;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName InsertSort.java
 * @Description 插入排序
 * @createTime 2021年02月08日 20:16:00
 */
public class InsertSort {
    public static void main(String[] args) {
        int[] arrs = {3,4,5,123,45,13,435,765,12,1,2,3,345,56,78,9,3};
        inserttsort(arrs);
    }

    public static void inserttsort(int[] arrs){
        if (arrs ==null || arrs.length<2){
            return;
        }
        for (int i = 1; i < arrs.length; i++) {//0~0已经有序，不用排
            for (int j = i-1;j >= 0 && arrs[j]>arrs[j+1];j--){ //j是i的前一个数
                swap(arrs,j,j+1);
            }
        }
        for (int arr : arrs) {
            System.out.println(arr);
        }
    }

    public static void swap(int[] arrs,int i,int j){
        int temp = arrs[i];
        arrs[i]= arrs[j];
        arrs[j] = temp;
    }
}
