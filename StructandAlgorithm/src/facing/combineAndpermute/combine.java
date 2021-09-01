package facing.combineAndpermute;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName combine.java
 * @Description 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
 * 你可以按 任何顺序 返回答案。(答案中不能有重复)
 * 输入：n = 4, k = 2
 * 输出：
 * [
 *   [2,4],
 *   [3,4],
 *   [2,3],
 *   [1,2],
 *   [1,3],
 *   [1,4],
 * ]
 *回溯
 * 组合问题，相对于排列问题而言，不计较一个组合内元素的顺序性（即 [1, 2, 3] 与 [1, 3, 2] 认为是同一个组合），
 * 因此很多时候需要按某种顺序展开搜索，这样才能做到不重不漏。
 * https://leetcode-cn.com/problems/combinations/solution/hui-su-suan-fa-jian-zhi-python-dai-ma-java-dai-ma-/
 * @createTime 2021年09月01日 22:35:00
 */
public class combine {
    List<List<Integer>> result = new ArrayList<>();
    LinkedList<Integer> path = new LinkedList<>();

    //一个范围内取数，不重复，结果也不重复
    public List<List<Integer>> combine(int n, int k) {
        backtrace(n, k, 1);
        return result;
    }
    // startIndex 用来记录本层递归的中，集合从哪里开始遍历
    private void backtrace(int n, int k, int startIndex){
        if (path.size() == k) {
            result.add(new ArrayList<>(path));
            return;
        }
        /**
         * 剪枝优化过程如下：
         * 已经选择的元素个数：path.size();
         * 还需要的元素个数为: k - path.size();
         * 在集合n中至少要从该起始位置 : n - (k - path.size()) + 1，开始遍历
         * 为什么有个+1呢，因为包括起始位置，我们要是一个左闭的集合。
         *
         * 举个例子，n = 4，k = 3， 目前已经选取的元素为0（path.size为0），n - (k - 0) + 1 即 4 - ( 3 - 0) + 1 = 2。
         * 从2开始搜索都是合理的，可以是组合[2, 3, 4]。
         */
        for (int i = startIndex; i <= n - (k - path.size()) + 1; i++) {
            path.add(i);
            backtrace(n, k, i + 1);
            path.removeLast();
        }
    }
 
}
