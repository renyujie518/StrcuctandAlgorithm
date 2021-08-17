package SwordOffer;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName minNumberInRotateArray_11.java
 * @Description
 * 旋转数组的最小数字
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
 * 输入一个非递减排序的数组的做一次旋转（已经是旋转，破坏过递增，末尾是个小值），输出旋转数组的最小元素。（从最后面几个元素找）
 *
 * 思路：
 * 将旋转数组对半分可以得到一个包含最小元素的新旋转数组，以及一个非递减排序的数组。
 * 新的旋转数组的数组元素是原数组的一半，从而将问题规模减少了一半，（一半是递增，一半是旋转的，破坏了递增的）
 * 这种折半性质的算法的时间复杂度为 O(logN)（为了方便，这里将 log2N 写为 logN）。
 * 此时问题的关键在于确定对半分得到的两个数组哪一个是旋转数组，哪一个是非递减数组。
 * 我们很容易知道非递减数组的第一个元素一定小于等于最后一个元素。
 * 通过修改二分查找算法进行求解作者：CyC2018

 * （l 代表 low，m 代表 mid，h 代表 high）：
 * 当 nums[m] <= nums[h] 时，表示 [m, h] 区间内的数组是非递减数组，[l, m] 区间内的数组是旋转数组，此时令 h = m；
 * 否则 [m + 1, h] 区间内的数组是旋转数组，令 l = m + 1。
 * @createTime 2021年08月17日 21:57:00
 */
public class minNumberInRotateArray_11 {
    public static int minNumberInRotateArray1(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        //二分法
        int low = 0;
        int high = nums.length - 1;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (nums[high] > nums[mid]) {  //如果中间的<后面的  是递增的  要找的值肯定不在里面,最小值在[low, mid] 区间的旋转数组里
                high = mid;
            } else {//中间的>后面的 非递增的  宗旨就是逼近那个旋转数组
                low = mid + 1;
            }
        }
        //循环完毕后 依据旋转数组的定义 即使被甩到后面  之前的递增性是不变的，所以low位置就是最小值
        return nums[low];
    }

    /**
     * 如果数组元素允许重复，会出现一个特殊的情况：nums[l] == nums[m] == nums[h]，
     * 此时无法确定解在哪个区间，需要切换到顺序查找
     * 例如对于数组 {1,1,1,0,1}，l、m 和 h 指向的数都为 1，此时无法知道最小数字 0 在哪个区间。

     */
    public static int minNumberInRotateArray2(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        //二分法
        int low = 0;
        int high = nums.length - 1;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (nums[low] == nums[mid] && nums[mid] == nums[high]) {
                return help(nums, low, high);
            } else if (nums[high] > nums[mid]) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return nums[low];
    }

    //顺序查找
    public static int help(int[] nums,int low,int high){
        for (int i = low; i < high; i++) {
            if (nums[i] > nums[i + 1]) {
                return nums[i + 1];
            }
        }
        //没有上面的异常情况，就按照非递增的特性，返回low
        return nums[low];

    }


}
