package SwordOffer;

import java.util.Arrays;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName minNumFromArray_45.java
 * @Description 把数组排成最小的数
 * 输入一个正整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
 * 例如输入数组 {3，32，321}，则打印出这三个数字能排成的最小数字为 321323。
 *
 * 解题思路
 * 可以看成是一个排序问题，在比较两个字符串 S1 和 S2 的大小时，应该比较的是 S1+S2 和 S2+S1 的大小，如果 S1+S2 < S2+S1，
 * 那么应该把 S1 排在前面，否则应该把 S2 排在前面。
 * @createTime 2021年08月24日 17:31:00
 */
public class minNumFromArray_45 {
    public static String minNumFromArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return "";
        }

        int n = nums.length;
        String[] nums2String = new String[n];
        for (int i = 0; i < n; i++) {
            nums2String[i] = nums[i] + "";
        }
        //compareTo用于两个相同数据类型的比较 如果指定的数与参数相等返回0。如果指定的数小于参数返回 -1。如果指定的数大于参数返回 1。
        //如果0说明o1和o2相等，如果返回负值，那么o1和o2会倒序排序，返回正值，那么o1和o2会正序排序
        //最后是以s1+s2为序的升序结果
        Arrays.sort(nums2String, (s1, s2) -> (s1 + s2).compareTo(s2 + s1));
        String ret = "";
        for (String str : nums2String)
            ret += str;
        return ret;
    }
}
