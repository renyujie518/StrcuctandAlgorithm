package sort;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName SelectSort.java
 * @Description 选择排序
 * @createTime 2021年02月08日 19:47:00
 */
public class SelectSort {
    public static void main(String[] args) {
        int[] arrs = {3,4,5,123,45,13,435,765,12,1,2,3,345,56,78,9,3};
        selectsort(arrs);
    }



    public static void selectsort(int[] arrs){
        if (arrs ==null || arrs.length<2){
            return;
        }
        for (int i = 0; i < arrs.length - 1; i++) {
            int minIndex =i;
            for (int j= i+1;j<arrs.length;j++){
                minIndex = arrs[j]<arrs[minIndex] ? j : minIndex;
            }
            swap(arrs, i, minIndex);
        }
        for (int arr : arrs) {
            System.out.print(arr);
        }

    }

    public static void swap(int[] arrs,int i,int j){
        int temp = arrs[i];
        arrs[i]= arrs[j];
        arrs[j] = temp;

    }
}
