package SwordOffer;

import Tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName PrintTreeFromTopToBottom_32.java
 * @Description 层序遍历树
 * 从上往下打印出二叉树的每个节点，同层节点从左至右打印。
 *
 * 使用队列来进行层次遍历。
 * 不需要使用两个队列分别存储当前层的节点和下一层的节点，因为在开始遍历一层的节点时，当前队列中的节点数就是当前层的节点数，
 * 只要控制遍历这么多节点数，就能保证这次遍历的都是当前层的节点。
 * @createTime 2021年08月21日 16:17:00
 */
public class PrintTreeFromTopToBottom_32 {
    public static ArrayList<Integer> PrintTreeFromTopToBottom(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        ArrayList<Integer> result = new ArrayList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                TreeNode curr = queue.poll();
                if (curr == null) {
                    continue;
                }
                result.add(curr.val);
                if(curr.left != null)
                    queue.add(curr.left);
                if(curr.right != null)
                    queue.add(curr.right);
                size--;

            }
        }
        return result;
    }
}
