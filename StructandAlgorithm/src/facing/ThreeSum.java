package facing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName ThreeSum.java
 * @Description T给你一个包含 n 个整数的数nums，判断nums中是否存在三个元素 a，b，c ，
 * 使得a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
 * 输入：nums = [-1,0,1,2,-1,-4]
 * 输出：[[-1,-1,2],[-1,0,1]]
 *
 * 注意：答案中不可以包含重复的三元组。
 * 暴力法的时间复杂度是O(n^3)。可以先固定一个值，然后寻找后两个值时可采取双指针的方法，
 * 将总的时间复杂度优化到 O(n^2)。
 *
 * 实现的过程中，要注意优化以及去重。
 * 首先我们先对原数组进行排序，这样可以把重复的值集中到一起，便于去重。
 * 确定第一个元素时，如果它已经比 0 大了，那么可以直接跳出循环，因为后面的数字都比它大。
 * 如 [1, 2, 3, 4], i = 0, nums[i] > 0, 这样是不可能产生合法的情况的，直接 break。
 * 确定第一个元素时，如果发现它与它前面的值一样，那么跳过本轮。如 [-1, -1, 0, 1], 在第一轮后，
 * 已经选出了 {-1, 0, 1}, 现在 i = 1，nums[i] == nums[i - 1], 为了避免重复，直接 continue。
 * 接下来利用双指针，left 指向 i + 1, right 指向 nums.length - 1。逐个进行判断，并注意去重。
 *
 *
 * @createTime 2021年03月07日 21:19:00
 */
public class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {// 总时间复杂度：O(n^2)
        List<List<Integer>> ans = new ArrayList<>();
        if (nums == null || nums.length <= 2) return ans; //先排除最不可能的情况

        Arrays.sort(nums); // O(nlogn)  先对数组排序

        for (int i = 0; i < nums.length - 2; i++) { // O(n^2)
            if (nums[i] > 0) break; // 第一个数大于 0，后面的数都比它大，肯定不成立了
            if (i > 0 && nums[i] == nums[i - 1]) continue; // 去掉重复情况 continue; 结束本层的本次循环。
            int target = -nums[i];
            int left = i + 1, right = nums.length - 1;  //双指针
            while (left < right) {
                if (nums[left] + nums[right] == target) {
                    ans.add(new ArrayList<>(Arrays.asList(nums[i], nums[left], nums[right])));

                    // 现在要增加 left，减小 right，但是不能重复，比如: [-2, -1, -1, -1, 3, 3, 3],
                    //i = 0, left = 1, right = 6, [-2, -1, 3] 的答案加入后，需要排除重复的 -1 和 3
                    left++; right--; // 首先无论如何先要进行加减操作

                    //因为是先加（先减）的，所此时left = 2,right =5,为了避免重复操作 ，letf在和左边的1位置比一次
                    //同理,right在和右边的6位置比一次，如果相等，因为已经排好序了，所以左右指针再移动即可
                    while (left < right && nums[left] == nums[left - 1]) left++;
                    while (left < right && nums[right] == nums[right + 1]) right--;
                } else if (nums[left] + nums[right] < target) {  //加起来比一个负数还小，说明不够大（升序）右移
                    left++;
                } else {  //加起来比一个负数还大，说明太大了（升序）左移
                    right--;
                }
            }
        }
        return ans;
    }
}
