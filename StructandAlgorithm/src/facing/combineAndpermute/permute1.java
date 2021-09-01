package facing.combineAndpermute;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName permute1.java
 * @Description 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
 *
 * 示例 1：
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * 与组合不同：排列是有序的，也就是说[1,2] 和[2,1] 是两个集合
 *
 * 注意点：
 * 每层都是从0开始搜索而不是startIndex
 * 需要used数组记录path里都放了哪些元素了
 * @createTime 2021年09月01日 22:47:00
 */
public class permute1 {
    List<List<Integer>> result = new ArrayList<>();// 存放符合条件结果的集合
    LinkedList<Integer> path = new LinkedList<>();// 用来存放符合条件结果
    boolean[] used;//用于判断放过哪些元素

    public List<List<Integer>> permute1(int[] nums) {
        if (nums.length == 0){
            return result;
        }
        used = new boolean[nums.length];
        backtrack(nums);
        return result;
    }

    private void backtrack(int[] nums){
        if (path.size() == nums.length){
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++){
            if (used[i]){//保证结果里没有重复元素
                continue;
            }
            used[i] = true;
            path.add(nums[i]);
            backtrack(nums);
            path.removeLast();
            used[i] = false;
        }
    }

}
