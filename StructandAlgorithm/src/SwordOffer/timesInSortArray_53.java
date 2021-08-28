package SwordOffer;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName timesInSortArray_53.java
 * @Description 数字在排序数组中出现的次数
 * Input:
 * nums = 1, 2, 3, 3, 3, 3, 4, 6
 * K = 3
 *
 * Output:
 * 4
 *
 *
 * 二分法：
 * 排序数组 nums中的所有数字 target形成一个窗口，记窗口的 左 / 右边界 索引分别为 left 和 right ，
 * 分别对应窗口左边 / 右边的首个元素。
 * 本题要求统计数字 target 的出现次数，可转化为：使用二分法分别找到 左边界 left 和 右边界 right
 * 易得数字 target 的数量为right−left−1 。
 * @createTime 2021年08月27日 15:50:00
 */
public class timesInSortArray_53 {
    public int timesInSortArray(int[] nums, int target) {
        //搜索右边界
        int i = 0;
        int j = nums.length - 1;
        while (i <= j) {
            int mid = i + (j - i) / 2;
            if (nums[mid] <= target) {//target在右半侧
                i = mid + 1;
            } else {
                j = mid - 1;
            }
        }
        //这时候i = j ,而且i满足的条件带<=，由于是增加趋势，会一直满足到最右边，所以即使有多个target也是在偏最右侧的
        int right = i;
        // 若数组中无 target ，则提前返回
        if (j >= 0 && nums[j] != target) {
            return 0;
        }
        //搜索左边界
        i = 0;
        j = nums.length - 1;
        while (i <= j) {
            int mid = i + (j - i) / 2;
            if (nums[mid] < target) {
                i = mid + 1;
            } else {
                j = mid - 1;

            }
        }
        //这时候i = j ,而且j满足的条件带>=,由于是j是减小趋势，会一直满足到最左边，所以即使有多个target也是在偏最左侧的
        int left = j;
        return right - left + 1;
    }
}
