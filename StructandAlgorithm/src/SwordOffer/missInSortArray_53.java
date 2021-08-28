package SwordOffer;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName missInSortArray_53.java
 * @Description   0～n-1中缺失的数字
一个长度为n-1的递增排序数组中的所有数字都是唯一的，并且每个数字都在范围0～n-1之内。
在范围0～n-1内的n个数字中有且只有一个数字不在该数组中，请找出这个数字。
输入: [0,1,2,3,4,5,6,7,9]
输出: 8


排序数组中的搜索问题，首先想到 二分法 解决。
根据题意，数组可以按照以下规则划分为两部分。
左子数组： nums[i] = i ；
右子数组： nums[i] != i
缺失的数字等于 “右子数组的首位元素” 对应的索引；因此考虑使用二分法查找 “右子数组的首位元素” 。
。
 * @createTime 2021年08月27日 16:12:00
 */
public class missInSortArray_53 {
    public static int missInSortArray(int[] nums) {
        int i = 0;
        int j = nums.length - 1;
        while(i <= j) {
            int m = i + (j - i) / 2;
            if(nums[m] == m){
                i = m + 1;
            }
            else{
                j = m - 1;
            }
        }
       // 变量 i 和 j 分别指向 “右子数组的首位元素” 和 “左子数组的末位元素” 。因此返回 i 即可
        return i;
    }
}
