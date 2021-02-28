package sort;

import util.bijiaoqi_arrays_sort;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName RadixSort.java
 * @Description 桶排序
 * 先进先出  按照个十百的顺序依次进桶出桶
 * 但以下程序在出入桶的过程作了优化  是利用该位的数字出现的频数  从个位开始，个位数<= i的个数，再从右往左按照个数的值放到帮助数组里，个数--
 * @createTime 2021年02月23日 14:24:00
 */
public class RadixSort {

    public static void main(String[] args) {
        int[] arrs = {4,5,123,45,13,435,765,12,1,2,3,345,56,78,9,3};
        if (arrs == null || arrs.length < 2) {
            return;
        }
        int[] result = radixsort(arrs, 0, arrs.length - 1, maxbits(arrs));
        for (int x : result) {
            System.out.println(x);
        }

        //test
        //bijiaoqi_arrays_sort.test("sort.RadixSort");
    }


    //取出该位置上的数，比如 x =789,d =1 取出个位数字9 d= 2取出十位数字8
    public static int getDigit(int x ,int d){
        x = Math.abs(x);
        return ((x / ((int) Math.pow(10, d - 1))) % 10);
    }

    //获得一个数组中最大值的位数 比如数组中的最大值为499，那么就返回3
    public static int maxbits(int[] arrs){
        int max = Integer.MIN_VALUE;//获取系统中的最小值，防止越界，可以看做保险措施
        for (int i = 0; i < arrs.length; i++) {
            max = Math.max(max, arrs[i]);
        }
        int res = 0;
        while (max != 0){
            res++;
            max /=10;
        }
        return res;
    }

    //适用范围更广，radixSort(arr, 0, arr.length - 1, maxbits(arr));   digit是位数，就看做个十百
    public static int[] radixsort(int[] arrs, int begin, int end, int digit) {
        final int radix = 10;//写死了，就是10进制
        int i = 0;
        int j = 0;
        int[] help = new int[end - begin + 1];//创建一个帮助数组，大小和原数组一样
        for (int d = 1; d <= digit; d++) {
            //这个大循环很关键，依照之前的分析，进出桶的次数和该数组的最大值的位数一样，比如数组中的最大值为499，那么就进出桶3次（最小也为1）
            int[] count = new int[radix];//咱们都写得是十进制的排序，那么桶无非就是0.1.2...9,这里去看TODO里所述的优化，
            // 统计位上出现频数  比如针对d=1,那就是个位，个位数取出来为j，count数组上相应位置+1
            for (i = begin; i <= end ; i++) {
                j = getDigit(arrs[i], d);
                count[j]++;
            }
            for (int k = 1; k <radix ; k++) {
                count[k] = count[k]+count[k-1];//累加  达到一种效果  ，比如个位数<=3的个数有7个
                //此时 count[0] 表示 数组中当前位（d位）是0的数字有多少个
                //count[1] 表示 数组中当前位（d位）是0和1的数字有多少个  依次类推 直到count[9]
            }
            for (i =end;i>=begin;i--){//从右往左
                j = getDigit(arrs[i], d);  //再把相应位上的数取出来,无非就是0-9；
                //！！！以下两句非常关键简洁，出桶，利用help数组。把原始数放到频数-1的位置就是出桶，每放完一次，频数--
                help[count[j] - 1] = arrs[i];
                count[j]--;
            }
            //help数组完成使命，对arrs再做一次规整
            for(i = begin,j = 0;i<=end;i++,j++){  //注意啊，这里的j只是临时变量，用作循环数组help
                arrs[i] = help[j];
            }
        }
        return arrs;
    }

}
