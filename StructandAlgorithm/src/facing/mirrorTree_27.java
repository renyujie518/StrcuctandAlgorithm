package facing;

import Tree.TreeNode;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName mirrorTree_27.java
 * @Description T请完成一个函数，输入一个二叉树，该函数输出它的镜像。
 * ，递归地对树进行遍历，并从叶子节点先开始翻转得到镜像。如果当前遍历到的节点 root 的左右两棵子树都已经翻转得到镜像，
 * 那么我们只需要交换两棵子树的位置，即可得到以 \textit{root}root 为根节点的整棵子树的镜像。

 * @createTime 2021年08月19日 21:53:00
 */
public class mirrorTree_27 {
    public static void mirrorWithRecorsion(TreeNode root) {
        if (root == null)
            return;
        swap(root);
        mirrorWithRecorsion(root.left);
        mirrorWithRecorsion(root.right);
    }

    private static void swap(TreeNode root) {
        TreeNode t = root.left;
        root.left = root.right;
        root.right = t;
    }

}
