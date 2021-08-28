package SwordOffer;

import Tree.TreeNode;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName searchTreeK_54.java
 * @Description 二叉查找树的第 K 大结点
 * 二叉搜索树(左小右大)的中序遍历（左中右）为 递增序列
 * 二叉搜索树的 中序遍历倒序（右中左） 为 递减序列
 *  “二叉搜索树第 k 大的节点” 可转化为求 “此树的中序遍历倒序的第 k个节点”。
 * @createTime 2021年08月27日 16:26:00
 */
public class searchTreeK_54 {
    private  int res;
    private  int k;

    public int searchTreeK(TreeNode root, int k) {
        this.k = k;
        dfs(root);
        return res;
    }

    public void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        //中序遍历倒序（右中左）
        dfs(root.right);
        if (k == 0) {//先设置终止条件，因为后面会--k
            return;
        }
        //判断第k个节点可以想象为使用，我每使用个节点，就-1，减完了就是第k个
        //为什么先--，很明显，上面的right已经先用过了
        if (--k == 0) {
            res = root.val;
        }
        dfs(root.right);
    }
}
