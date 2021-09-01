package SwordOffer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName sumAsSXuLie_57.java
 * @Description
 * 输出所有和为 S 的连续正数序列。
 * 例如和为 100 的连续序列有：
 * [9, 10, 11, 12, 13, 14, 15, 16]
 * [18, 19, 20, 21, 22]。
 *
 * 滑动窗口(左闭右开)
 * 当窗口的和小于 target 的时候，窗口的和需要增加，所以要扩大窗口，窗口的右边界向右移动
 * 当窗口的和大于 target 的时候，窗口的和需要减少，所以要缩小窗口，窗口的左边界向右移动
 * 当窗口的和恰好等于 target 的时候，我们需要记录此时的结果。设此时的窗口为 [i,j)，
 * 那么我们已经找到了一个 ii开头的序列，也是唯一一个 i 开头的序列，接下来需要找 i+1 开头的序列，所以窗口的左边界要向右移动
 * @createTime 2021年08月27日 21:11:00
 */
public class sumAsSXuLie_57 {
    public int[][] sumAsSXuLie(int target) {
        int i = 1; // 滑动窗口的左边界
        int j = 1; // 滑动窗口的右边界
        int sum = 0; // 滑动窗口中数字的和
        List<int[]> res = new ArrayList<>();

        while (i <= target / 2) {
            if (sum < target) {
                // 右边界向右移动
                sum += j;
                j++;
            } else if (sum > target) {
                // 左边界向右移动
                sum -= i;
                i++;
            } else {
                // 记录结果
                int[] arr = new int[j-i];
                for (int k = i; k < j; k++) {
                    arr[k-i] = k;
                }
                res.add(arr);
                // 左边界向右移动
                sum -= i;
                i++;
            }
        }

        return res.toArray(new int[res.size()][]);
    }



}
