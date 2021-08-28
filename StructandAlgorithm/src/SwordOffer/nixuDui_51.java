package SwordOffer;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName nixuDui_51.java
 * @Description  逆序对
 * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。
 * 暴力解法（超时）
 * 使用两层 for 循环枚举所有的数对，逐一判断是否构成逆序关系。
 *
 * 方法二：分治思想（借助归并排序统计逆序数）
 * 说明：理解这个算法需要对「归并排序」比较熟悉。掌握如果编写递归函数
 * 每一次都一分为二拆分数组的子区间，然后在方法栈弹出的时候，一步一步合并两个有序数组，最后完成排序工作。
 * 而计算逆序数就发生在排序的过程中，利用了「排序」以后数组的有序性。
 *
 * 利用「归并排序」计算逆序对，是非常经典的做法；
 * 关键在于「合并两个有序数组」的步骤，利用数组的部分有序性，一下子计算出一个数之前或者之后元素的逆序的个数；
 * 前面「分」的时候什么都52
 * 不做，「合」的过程中计算「逆序对」的个数；
 * 「排序」的工作是必要的，正是因为「排序」才能在下一轮利用顺序关系加快逆序数的计算，也能避免重复计算；
 * 在代码实现上，只需要在「归并排序」代码的基础上，加上「逆序对」个数的计算，计算公式需要自己在草稿纸上推导思想是「分治算法」，所有的「逆序对」来源于 3 个部分：
 *
 * 左边区间的逆序对；
 * 右边区间的逆序对；
 * 横跨两个区间的逆序对。
 *
 *合并阶段 本质上是 合并两个排序数组 的过程，而每当遇到 左子数组当前元素 > 右子数组当前元素 时，
 * 意味着 「左子数组当前元素 至 末尾元素」（后面的只会越来越大） 与 「右子数组当前元素」 构成了若干 「逆序对」  即（mid -left+1）个
 * 不要重复讨论，而且归并的过程保证了不重复讨论
https://leetcode-cn.com/problems/shu-zu-zhong-de-ni-xu-dui-lcof/solution/jian-zhi-offer-51-shu-zu-zhong-de-ni-xu-pvn2h/
 * @createTime 2021年08月26日 14:39:00
 */
public class nixuDui_51 {

    public static int nixuDui1(int[] nums) {
        int cnt = 0;
        int len = nums.length;
        for (int i = 0; i < len-1; i++) {
            for (int j = i + 1; j < len; j++) {
                if (nums[i] > nums[j]) {//前大于后
                    cnt++;
                }
            }
        }
        return cnt;
    }

    //运用分治的思想
    private static long cnt = 0;
    private static int[] temp; // // 在这里声明辅助数组，而不是在 merge() 递归函数中声明（避免在递归中总是重复传参）
    public static int nixuDui(int[] nums) {
        temp = new int[nums.length];
        mergeSort(nums, 0, nums.length - 1);
        return (int) (cnt % 1000000007);
    }

    public static void mergeSort(int[] nums, int l, int h) {
        if (h - l < 1) {
            return;
        }
        int mid = l + (h - l) / 2;
        mergeSort(nums, l, mid);
        mergeSort(nums, mid + 1, h);
        merge(nums, l, mid, h);
    }

    public static void merge(int[] nums, int l, int mid, int h) {
        int i = l;
        int j = mid + 1;
        int k = l;

        while (i <= mid || j <= h) {
            if (i > mid) {
                temp[k] = nums[j++];
            } else if (j > h) {
                temp[k] = nums[i++];
            } else if (nums[i] < nums[j]) {
                temp[k] = nums[i++];
            } else {
                temp[k] = nums[j++];
                // nums[i] > nums[j]，说明 nums[i...mid] 都大于 nums[j]
                cnt += mid - i + 1;
            }
            k++;
        }
        for (k = l; k <= h; k++) {
            nums[k] = temp[k];
        }
    }
}
