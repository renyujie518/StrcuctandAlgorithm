package SwordOffer;

import Tree.TreeNode;

import java.util.ArrayList;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName FindPath_34.java
 * @Description
 * 输入一颗二叉树和一个整数，打印出二叉树中结点值的和为输入整数的所有路径。
 * 路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。
 * @createTime 2021年08月21日 19:00:00
 */
public class FindPathInTree_34 {
    private static ArrayList<ArrayList<Integer>> result = new ArrayList<>();

    public static ArrayList<ArrayList<Integer>> FindPathInTree(TreeNode root, int target) {
        backtrace(root, target, new ArrayList<>());
        return result;
    }

    public static void backtrace(TreeNode node, int target, ArrayList<Integer> path) {
        if (node == null) {
            return;
        }
        path.add(node.val);
        target = target - node.val;
        if (target == 0 && node.left == null && node.right == null) {
            //如果走到底的同时恰好target用光了（路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径）说明找到一条
            result.add(new ArrayList<>(path));
        } else {
            backtrace(node.left, target, path);
            backtrace(node.right, target, path);
        }
        //最后别忘了回溯还要退一步  即把path.add的末尾的val删除
        path.remove(path.size() - 1);


    }
}
