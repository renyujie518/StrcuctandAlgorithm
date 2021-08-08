package facing;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName xiaYiGePaiLie.java
 * @Description
 * “下一个排列”的定义是：给定数字序列的字典序中下一个更大的排列。
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 *
 * 我们可以将该问题形式化地描述为：给定若干个数字，将其组合为一个整数。
 * 何将这些数字重新排列，以得到下一个更大的整数。如 123 下一个更大的数为 132。如果没有更大的整数，则输出最小的整数。
 *
 * 以 1,2,3,4,5,6 为例，其排列依次为：
 * 123456
 * 123465
 * 123546
 * ...
 * 654321
 * 可以看到有这样的关系：123456 < 123465 < 123546 < ... < 654321。
 *
 * 以数字序列 [1,2,3][1,2,3] 为例，其排列按照字典序依次为：
 * [1,2,3]
 * [1,3,2]
 * [2,1,3]
 * [2,3,1]
 * [3,1,2]
 * [3,2,1]
 *特别的，最大的排列 [3,2,1] 的下一个排列为最小的排列 [1,2,3]
 *
 * 思路：
 * 1、[1,2,3,6,5,4]下一个排列是[1,2,4,3,5,6]完全题解题意后就好写了
 * 2、从后往前找到不是递增的那个数上述是3，然后再从后往前找到第一个比3大的数，交换这两个数的位置后[1,2,4,6,5,3]
 * 3、已近知道6,5,3从后往前是递增的，所以要逆序一下
 * 4、注意边界判断
 * @createTime 2021年08月04日 19:24:00
 */
public class xiaYiGePaiLie {
    public static void nextPermutation(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int i = nums.length - 2;//以倒数第二个开始
        while (i >= 0 && nums[i + 1] <= nums[i]) {//直到找到"从左往右较大的那个数"
            i--;
        }
        //这时会i指向从前往后的那个较大的数
        if (i > 0) {
            int j = nums.length - 1;
            while (j >= 0 && nums[j] <= nums[i]) {//从后往前找到第一个i位置还大的数
                j--;
            }
            swap(nums, i, j);
        }
        //此时这两个数交换完之间的数是递减的（大数被交换到前面去了）
        reverse(nums, i + 1, nums.length - 1);
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void reverse(int[] nums, int i, int j) {
        while (i < j) {
            swap(nums, i++, j--);
        }
    }
}
