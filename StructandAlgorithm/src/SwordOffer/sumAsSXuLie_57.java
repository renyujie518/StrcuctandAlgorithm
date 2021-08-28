package SwordOffer;

import java.util.ArrayList;

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
 * 滑动窗口
 * 设连续正整数序列的左边界 i和右边界 j ，则可构建滑动窗口从左向右滑动。
 * 循环中，每轮判断滑动窗口内元素和与目标值 target的大小关系，若相等则记录结果，
 * 若大于 target 则移动左边界 i （以减小窗口内的元素和），若小于 target 则移动右边界 j（以增大窗口内的元素和）。
 *
 *         //l是窗口左边界，r是窗口右边界，窗口中的值一定是连续值。
 *         //这里有个技巧： a+a+1=target，那么a=(target-1)/2，同理，右边界r的最大值为(target+1)/2
 *         //等差和公式是 target = ( 首项+末项 ) * len / 2 , 所以(target*2) mod len == 0 是当前len有效的必要条件，
 *         //所以过滤掉（target*2）%len!=0的len 是第一剪枝
 * @createTime 2021年08月27日 21:11:00
 */
public class sumAsSXuLie_57 {
    public static int[][] sumAsSXuLie(int target) {
        ArrayList<int[]> res = new ArrayList<>();
        int left = 1;
        int right = 2;
        int sum = 3;//初始窗口大小为1+2=3
        while(left <= target / 2){
            //右
            if(sum > target){
                //注意这里的顺序，先减去原先的left，代表把原先的left的值囊括进来，再改变边界值
                sum -= left;
                left++;
            }
            else{//sum <= target  包含了等于的情况
                if(sum == target){
                    res.add(help(left, right));
                }

                //右边界右移会使得sum变大，先移后改sum
                right++;
                sum += right;
            }
        }
        return res.toArray(new int[0][]);


    }

    private static int[] help(int l, int h) {
        int[] res = new int[h - l + 1];
        for (int i = l; i <= h; i++) {
            res[i - l] = i;
        }
        return res;
    }


}
