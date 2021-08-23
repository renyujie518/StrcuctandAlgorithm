package SwordOffer;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName minK_40.java
 * @Description 给定一个数组，找出其中最小的K个数。例如数组元素是4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4。
 * <p>
 * 思路1：
 * 快速排序的 partition() 方法，会返回一个整数 j 使得 a[l..j-1] 小于等于 a[j]，且 a[j+1..h] 大于等于 a[j]，
 * 此时 a[j] 就是数组的第 j 大元素。可以利用这个特性找出数组的第 K 个元素，这种找第 K 个元素的算法称为快速选择算法。
 * <p>
 * 思路2：
 * 应该使用大顶堆来维护最小堆，而不能直接创建一个小顶堆并设置一个大小，企图让小顶堆中的元素都是最小元素。
 * 维护一个大小为 K 的最小堆过程如下：在添加一个元素之后，如果大顶堆的大小大于 K，那么需要将大顶堆的堆顶元素去除。
 * @createTime 2021年08月21日 23:29:00
 */
public class minK_40 {
    public static ArrayList<Integer> minKWithPartition(int[] nums, int k) {
        ArrayList<Integer> result = new ArrayList<>();
        if (k > nums.length || k <= 0){
            return result;
        }
        //indKthSmallest 会改变数组，使得前 k 个数都是最小的 k 个数
        findKthSmallest(nums, k - 1);
        for (int i = 0; i < k; i++){
            result.add(nums[i]);
        }
        return result;
    }

    public static void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }


    public static void findKthSmallest(int[] nums, int k) {
        int l = 0;
        int h = nums.length - 1;
        while (l < h) {
            int j = partation(nums, l, h);
            if (j == k) {
                break;//j 就是第 k 小的数，直接返退出
            }
            if (j > k) {//第 k小的数在 j的左侧
                h = j - 1;
            } else {
                l = j + 1;
            }
        }
    }

    public static int partation(int[] nums,int l,int h){
        int p = nums[l];
        int i = l;
        int j = h + 1;
        while (true) {
            while (i != h && nums[++i] < p) ;
            while (j != l && nums[--j] > p) ;
            if (i >= j)
                break;
            swap(nums, i, j);
        }
        swap(nums, l, j);
        return j;
    }

    public static ArrayList<Integer> minKWithDui(int[] nums, int k) {
        if (k > nums.length || k <= 0) {
            return new ArrayList<>();
        }
        //创建大根堆
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(((o1, o2) -> o2 - o1));
        for (int num : nums) {
            maxHeap.add(num);
            if (maxHeap.size() > k) {//堆顶最大，个数超过k，得剔除
                maxHeap.poll();
            }
        }
        //构造函数决定了你放进是个整形的堆，返回也是整形
        return new ArrayList<>(maxHeap);
    }
}
