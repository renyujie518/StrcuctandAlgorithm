package facing.combineAndpermute;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName combinaSumAsTarget2.java
 * @Description
 * 给定一个无重复元素的正整数数组candidates和一个正整数target，
 * 找出candidates中所有可以使数字和为目标数target的唯一组合。
 * candidates中的数字可以无限制重复被选取。如果至少一个所选数字数量不同，则两种组合是唯一的。
 * 题目中给出了数组中元素的范围是 [1,200]（数组中的数字可以无限制重复被取，这里注意如果有 0 会导致死循环）
 *
 * 输入: candidates = [2,3,5], target = 8
 * 输出: [[2,2,2,2],[2,3,3],[3,5]]
 *
 * 组合总和也是组合类题目中的常见题目，经典的回溯算法
 * @createTime 2021年09月01日 16:41:00
 */
public class combinaSumAsTarget2 {
    List<List<Integer>> res = new ArrayList<>();    // 用于存储结果
    LinkedList<Integer> track = new LinkedList<>();   // 存储每次遍历的正确结果

    //无重复元素的数组中组合出不重复的的解，但是元素可以重复使用
    public List<List<Integer>> combinaSumAsTarget2(int[] candidates, int target) {
        Arrays.sort(candidates);      // 先进行排序
        backtrack(0, target, 0, candidates);
        return res;
    }
    public void backtrack(int curSum, int target, int start, int[] candidates) {
        //base case
        if (curSum == target) {
            res.add(new ArrayList<>(track));
            return;
        }
        // 如果 curSum + candidates[i] > target 就终止遍历(剪枝)
        for (int i = start; i < candidates.length && curSum + candidates[i] <= target; i++) {
            //做选择
            curSum += candidates[i];
            track.add(candidates[i]);
            // 递归  注意  这里是与combinaSumAsTarget1最大的不同： 可以重复，所以从 i 开始遍历子树，而不是必须r+1，
            //也不用去重了，本身题目就是无重复的candidates，排序后直接往后用就行
            backtrack(curSum, target, i, candidates);
            // 回溯，撤销选择
            curSum -= candidates[i];
            track.removeLast();
        }
    }

}
