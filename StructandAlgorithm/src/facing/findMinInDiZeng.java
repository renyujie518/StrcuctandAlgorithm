package facing;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName findMinInDiZeng.java
 * @Description
 * 给定一个严格递增循环整数数组，从里面找出最小的元素，使用的算法越快越好。
 * 特别地，最小的元素可能出现在数组中间。比如：50, 52, 63, 90, 3, 8, 15, 44。
 *
 * 这道题就是二分而已了
 * @createTime 2021年08月20日 14:06:00
 */
public class findMinInDiZeng {
    public static  int findMinInDiZeng(int[] num, int begin, int end){

        //当数组只有一个元素时，num[begin] == num[end] 直接返回
        //当数组第一位小于最后一位时，第一位即为最小，因为数组循环递增
        if(num[begin] <= num[end]){
            return num[begin];
        }
        //数组只有两位时，例如2,1这时候直接范围最后一位,如果是1,2则上边的If语句已经做出判断，这里不用考虑
        if(end - begin == 1){
            return num[end];
        }
        //算出中间位
        int middle = (begin + end) / 2;
        //如果中间位小于左边的，那么中间位便是最小值，因为数组循环递增
        if(num[middle] < num[middle - 1]){
            return num[middle];
        }
        //当中间位小于第一位时，中间位左边为循环递增，右边为严格递增，最小值一定在循环递增处
        //反之，左边为循环递增，最小值一定在循环递增处
        if(num[middle] > num[begin]){
            return findMinInDiZeng(num,middle+1,end);
        }else{
            return findMinInDiZeng(num,begin, middle-1);
        }


    }

    public static void main(String[] args) {
        int[] a = new int[]{3,4,5,2};
        System.out.println(findMinInDiZeng(a, 0, 3));

    }

}
