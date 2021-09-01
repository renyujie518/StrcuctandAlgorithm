package facing.combineAndpermute;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName combinAsTarget.java
 * @Description
 * 给定一个数组candidates和一个目标数target，找出candidates中所有可以使数字和为target的组合。
 * candidates中的每个数字在每个组合中只能使用一次。
 * 注意：解集不能包含重复的组合。
 *
 * 输入: candidates =[10,1,2,7,6,1,5], target =8,
 * 输出:
 * [
 * [1,1,6],
 * [1,2,5],
 * [1,7],
 * [2,6]
 * ]
 *
 * 这种找组合  首先想到回溯（有点像之前美团的那道找书架空隙的）
 *
 * @createTime 2021年09月01日 12:10:00
 */
//有重复元素的数组中组合出不重复的的解
public class combinaSumAsTarget1 {
    List<List<Integer>> res = new ArrayList<>();
    //储存单次的返回结果
    LinkedList<Integer> track = new LinkedList<>();

    public List<List<Integer>> combinaSumAsTarget1(int[] candidates, int target) {
        Arrays.sort(candidates);//排序后对candidates中的重复元素容易做判断
        backtrace(0, target, 0, candidates);
        return res;
    }

    // curSum 表示当前的节点的总和
    //start 控制的起始的数字，这个是为了防止结果变量 res 中出现类似于 [1,2] [2,1] 重复的情况。
    public void backtrace(int curSum, int target, int start, int[] candidates) {
        //basecase  当前节点的和 curSum 等于目标值 targe
        if (curSum == target) {
            res.add(new ArrayList<>(track));
            return;
        }
        //终止条件是<=而不是<，因为比如targrt = 6  也要把[1,2,3]中的3包含进去
        for (int i = start; i < candidates.length && curSum + candidates[i] <= target; i++) {
            //由于 candidates排过序了，但是有可能有重复元素比如【1，1，2，3，4，4...】
            //去重的核心思路就是，重复的元素我只消费1次，比如我用过1，就不再用下一个1，这样结果中就不会有重复的组合
            //考虑当前元素i，如果和前一个i-1位置同，该位置i我就不要了,continue会跳出上面这个for,但是i++
            if (i > start && candidates[i] == candidates[i - 1]) {
                continue;
            }
            //做选择
            curSum = curSum + candidates[i];
            track.add(candidates[i]);
            //下一层递归（上面先去过重了，直接用下一个元素尝试即可）
            backtrace(curSum, target, i + 1, candidates);
            //回溯  撤销选择
            curSum = curSum - candidates[i];
            track.removeLast();
        }
    }


}
