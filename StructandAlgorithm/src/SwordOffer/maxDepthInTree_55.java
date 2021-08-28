package SwordOffer;

import Tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName maxDepthInTree_55.java
 * @Description 二叉树的深度
 * 求树的深度需要遍历树的所有节点
 * 此树的深度 等于 左子树的深度 与 右子树的深度 中的 最大值 +1
 * @createTime 2021年08月27日 16:46:00
 */
public class maxDepthInTree_55 {
    public int maxDepthWithDG(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(maxDepthWithDG(root.left), maxDepthWithDG(root.right)) + 1;
    }

    //BFS 一层层遍历，统计一下总共有多少层
    public int maxDepthWithBFS(TreeNode root) {
        if (root == null) {
            return 0;
        }
        //创建一个队列
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int count = 0;
        while (!queue.isEmpty()) {
            //每一层的个数
            int size = queue.size();
            while (size-- > 0) {
                TreeNode curr = queue.poll();
                if (curr.left != null) {
                    queue.add(curr.left);
                }
                if (curr.right != null) {
                    queue.add(curr.right);
                }
            }
            //每while一次，即完成一层的遍历
            count++;
        }
        return count;
    }

    //可以使用两个栈，一个记录节点的stack栈，一个记录节点所在层数的level栈，
    //stack中每个节点在level中都会有一个对应的值，并且他们是同时出栈，同时入栈

    public int maxDepthWithDFS(TreeNode root) {
        if (root == null) {
            return 0;
        }
        //stack记录的是节点
        //level记录的是节点在第几层
        //是同时入栈同时出栈，并且level记录的是节点在第几层
        Stack<TreeNode> stack = new Stack<>();
        Stack<Integer> level = new Stack<>();
        //他们是同时出栈，同时入栈
        stack.push(root);
        level.push(1);
        int max = 0;
        while (!stack.isEmpty()) {
            //stack中的元素和level中的元素同时出栈
            TreeNode node = stack.pop();
            int temp = level.pop();
            //取出来立马比较
            max = Math.max(temp, max);
            if (node.left != null) {
                //同时入栈
                stack.push(node.left);
                level.push(temp + 1);
            }
            if (node.right != null) {
                //同时入栈
                stack.push(node.right);
                level.push(temp + 1);
            }

        }
        return max;

    }


}
