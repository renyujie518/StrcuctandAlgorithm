package facing;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName sortArrrayFindNum.java
 * @Description
 * 统计一个数字在排序数组中出现的次数。
 *
 * 一看到排序，首先想到二分
 * @createTime 2021年08月30日 17:46:00
 */
public class sortArrayFindNum {
    public int sortArrayFindNum(int[] nums, int k) {
        int n = nums.length;
        int l = 0, r = n - 1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] <= k) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        //这时候l= mid = r, 而且  nums[mid] = k
        //要找出现的次数 比如k = 3   3 3 3 4
        int cishu = 0;
        while (r > 0 && nums[r] == k && r-- > 0) {
            cishu++;
        }
        return cishu;
    }
}
