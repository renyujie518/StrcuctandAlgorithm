package util;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName printArray.java
 * @Description TODO
 * @createTime 2021年08月11日 17:07:00
 */
public class printArray {
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
