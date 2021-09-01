package facing.combineAndpermute;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName combinaSumAsTarget3.java
 * @Description
 * 出所有相加之和为n 的k个数的组合。组合中只允许含有 1 -9 的正整数，并且每种组合中不存在重复的数字。
 * 说明：
 * 所有数字都是正整数。
 * 解集不能包含重复的组合。
 * 输入: k = 3, n = 9
 * 输出: [[1,2,6], [1,3,5], [2,3,4]]
 *
 * 回溯算法
 *
 * @createTime 2021年09月01日 17:17:00
 */
public class combinaSumAsTarget3 {
    List<List<Integer>> res = new ArrayList<>();
    LinkedList<Integer> list = new LinkedList<>();

    //无重复元素的数组中组合出不重复的的解，但是元素限定了范围
    //这里为了好看 把目标设为target,把需要几个数组成设为n
    public List<List<Integer>> combinaSumAsTarget3(int n, int target) {
        backtrace(n, target, 1);
        return res;
    }


    private void backtrace(int n, int target, int start) {
        if (list.size() == n || target == 0) {
            res.add(new ArrayList<>(list));
            return;
        }
        //注意这里，因为不能有重复的集合以及集合中不能有重复的数字，所以这里的i不能从0开始，
        // 要从上一个选择之后的下一个值开始 i+1
        for (int i = start; i <= 9; i++) {
            //选择当前值
            list.add(i);
            //递归
            backtrace(n, target - i, i + 1);
            //撤销选择
            list.removeLast();
        }
    }

}
