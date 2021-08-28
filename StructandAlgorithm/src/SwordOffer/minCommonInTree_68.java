package SwordOffer;

import Tree.TreeNode;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName minCommonInTree_68.java
 * @Description 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 * @createTime 2021年08月28日 18:36:00
 */
public class minCommonInTree_68 {
    public TreeNode minCommonInTree(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null) {
            return null; // 如果树为空，直接返回null
        }
        if(root == p || root == q) {
            return root; // 如果 p和q中有等于 root的，那么它们的最近公共祖先即为root（一个节点也可以是它自己的祖先）
        }
        // 递归遍历左子树，只要在左子树中找到了p或q，则先找到谁就返回谁
        TreeNode left = minCommonInTree(root.left, p, q);
        // 递归遍历右子树，只要在右子树中找到了p或q，则先找到谁就返回谁
        TreeNode right = minCommonInTree(root.right, p, q);
        if(left == null) {
            return right;
            // 如果在左子树中 p和 q都找不到，则 p和 q一定都在右子树中，右子树中先遍历到的那个就是最近公共祖先（一个节点也可以是它自己的祖先）
        } else if(right == null){
            return left;
            // 否则，如果 left不为空，在左子树中有找到节点（p或q），这时候要再判断一下右子树中的情况，如果在右子树中，p和q都找不到，则 p和q一定都在左子树中，左子树中先遍历到的那个就是最近公共祖先（一个节点也可以是它自己的祖先）
        } else {
            //否则，当 left和 right均不为空时，说明 p、q节点分别在 root异侧, 最近公共祖先即为 root
            return root;
        }
    }
}
