package SwordOffer;

import java.util.HashSet;
import java.util.Set;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName shunZi_61.java
 * @Description 五张牌，其中大小鬼为癞子，牌面为 0。判断这五张牌是否能组成顺子。
 * 1 0  3 4 5  true
 * 1 2 4 6 7   false
 *
 * 遍历五张牌，遇到大小王（即 0 ）直接跳过。
 * 判别重复： 利用 Set 实现遍历判重， Set 的查找方法的时间复杂度为 O(1)
 * 获取最大 / 最小的牌 最大牌 - 最小牌 < 5 则可构成顺子
 * @createTime 2021年08月28日 17:39:00
 */
public class isShunZi_61 {
    public boolean isShunZi(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int max = 0, min = 14;
        for(int num : nums) {
            if(num == 0){
                continue; // 跳过大小王
            }
            max = Math.max(max, num); // 最大牌
            min = Math.min(min, num); // 最小牌
            if (set.contains(num)) {
                return false;//有重复肯定不是顺子
            }
            set.add(num);
        }
        return max - min < 5; // 最大牌 - 最小牌 < 5 则可构成顺子
    }
}
