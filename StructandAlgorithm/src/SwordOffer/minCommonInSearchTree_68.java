package SwordOffer;

import Tree.TreeNode;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName minCommonInTree_68.java
 * @Description 二叉搜索树(（对于每个子树，左边的小，右边的大）)的最近公共祖先
 * 近公共祖先的定义： 设节点 root为节点 p,q 的某公共祖先，若其左子节点 root.left和右子节点 root.right 都不是 p,q 的公共祖先，
 * 则称 rootroot 是 “最近的公共祖先” 。
 * 根据以上定义，若 root 是 p,q 的 最近公共祖先 ，则只可能为以下情况之一：
 *
 * p 和 q在 root的子树中，且分列 root 的 异侧（即分别在左、右子树中）；
 * p = root，且 q 在 root的左或右子树中；
 * q=root，且 pp在 root 的左或右子树中；
 *
 *
 * 给定条件：① 树为 二叉搜索树 ，② 树的所有节点的值都是 唯一 的。
 * 根据以上条件，可方便地判断 p,q 与 root的子树关系，即：
 *
 * 若 root.val<p.val ，则 p 在 root右子树 中；
 * 若root.val>p.val ，则 p 在 root 左子树 中；
 * 若 root.val=p.val ，则 p 和 root 指向 同一节点 。


 * @createTime 2021年08月28日 18:29:00
 */
public class minCommonInSearchTree_68 {
    public TreeNode minCommonInSearchTree(TreeNode root, TreeNode p, TreeNode q) {
        if(root.val < p.val && root.val < q.val){
            //当 p,q 都在 root 的 右子树 中，则开启递归 root.right 并返回；
            return minCommonInSearchTree(root.right, p, q);
        }

        if(root.val > p.val && root.val > q.val){
            //当p,q 都在 root 的 左子树 中，则开启递归 root.left 并返回；
            return minCommonInSearchTree(root.left, p, q);
        }

        return root;
    }

}
