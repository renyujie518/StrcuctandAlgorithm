package facing;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName zhuanQiang.java
 * @Description
 * 找出怎样画才能使这条线 穿过的砖块数量最少 ，并且返回 穿过的砖块数量 。
 * https://leetcode-cn.com/problems/brick-wall/
 *
 * 思路：
 * 对于任意一条垂线，其穿过的砖块数量加上从边缘（缝隙）经过的砖块数量之和是一个定值，即砖墙的高度。
 * 因此，问题可以转换成求「垂线穿过的砖块缝隙数量的最大值」，用砖墙的高度减去该最大值即为答案。
 * 我们只需要统计每行砖块中除了最右侧的砖块以外的其他砖块的右边缘即可。
 *
 * 具体地，我们遍历砖墙的每一行，对于当前行，我们从左到右地扫描每一块砖，
 * 使用一个map记录当前砖的右侧边缘到砖墙的左边缘的距离，
 * 将除了最右侧的砖块以外的其他砖块的右边缘到砖墙的左边缘的距离加入到哈希表中。
 * 最后我们遍历该哈希表，找到出现次数最多的砖块边缘，这就是垂线经过的砖块边缘，(找到缝隙最多的那条竖线)
 * 而该垂线经过的砖块数量即为砖墙的高度减去该垂线经过的砖块边缘的数量。
 * @createTime 2021年09月14日 21:24:00
 */
public class zhuanQiang {
    public int leastBricks(List<List<Integer>> wall) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (List<Integer> width : wall) {
            int n = width.size();
            int zhuanGeShu = 0;
            for (int i = 0; i < n-1; i++) {//排除除了最右侧的砖块（防止把最左右两个竖线加进去）
                zhuanGeShu = zhuanGeShu + map.get(i);
                map.put(zhuanGeShu, map.getOrDefault(zhuanGeShu, 0) + 1);
                //key = 到左边界的距离和(相当于砖块的个数)    value  距离每次累加1
            }
        }
        int maxLength = 0;
        for (Map.Entry<Integer, Integer> sum : map.entrySet()) {
            maxLength = Math.max(maxLength, sum.getValue());
        }
        return wall.size() - maxLength;

    }
}
