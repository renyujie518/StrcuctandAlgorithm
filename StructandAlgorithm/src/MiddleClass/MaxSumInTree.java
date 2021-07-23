package MiddleClass;

import Tree.TreeNode;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName MaxSumInTree.java
 * @Description 二叉树每个结点都有一个int型权值， 给定一棵二叉树，
 * 要求计算出从根结点到 叶结点的所有路径中， 权值和最大的值为多少。
 *
 * 注意，这里是根节点出发
 *
 * 涉及到二叉树的路径问题  适合用左神的关于树的动态规划  详见IsBalenceTree.java中的总结
 * @createTime 2021年07月22日 16:13:00
 */
public class MaxSumInTree {
    //法一  逻辑思考
    //全局变量  只在到达叶节点的时候可能跟新
    public static int maxSum;
    public static int maxPathWithoutRecursion(TreeNode head) {
        process1(head, 0);
        return maxSum;
    }

    //pre的含义是从上往下，从根节点出发到当前节点的"上方"节点所产生的路径和 注意不包括curr,
    public static void process1(TreeNode curr, int pre) {
        if (curr.left == null && curr.right == null) {   //处于叶节点的位置
            maxSum = Math.max(maxSum, pre + curr.val);
        }
        //非叶节点，如果有左树，递归，状态跟新，当前节点变为左节点，路径和变为 已有值+本身值
        if (curr.left != null) {
            process1(curr.left, pre + curr.val);
        }
        if (curr.right != null) {
            process1(curr.right, pre + curr.val);
        }
        //递归完成，maxSum必然是最大的
    }

}
