package SwordOffer;

import Tree.TreeNode;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName isBalanced_55.java
 * @Description 平衡二叉树
 * 对二叉树做后序遍历（左右中），从底至顶返回子树深度，若判定某子树不是平衡树则 “剪枝” ，直接向上返回。
 * 当节点root 左 / 右子树的深度差 ≤1 ：则返回当前子树的深度，即节点 root 的左 / 右子树的深度最大值 +1
 * 当节点root 左 / 右子树的深度差 >2 ：则返回 -1 ，代表 此子树不是平衡树 。
 *
 * 这个我建议用左神的递归套路：
 * Tree.IsBalenceTree
 * @createTime 2021年08月27日 17:20:00
 */
public class isBalanced_55 {
    public boolean isBalanced(TreeNode root) {
        return dfs(root) == -1 ? false : true;
    }

    //用left，right记录root左右子节点的深度，避免遍历root时对左右节点的深度进行重复计算。
    //考虑到需要同时记录各个节点的深度和其是否符合平衡性要求，这里的返回值设为int,
    //用一个特殊值-1来表示出现不平衡的节点的情况，而不是一般采用的boolean
    public int dfs(TreeNode root){
        //用后序遍历的方式遍历二叉树的每个节点（从底至顶）,先左子树，再右子树，最后根节点，
        if (root == null) {
            return 0;//叶节点，因此返回高度 0；
        }
        int left = dfs(root.left);
        if (left == -1) {
            return -1;//剪枝，开始向上返回，之后的迭代不再进行
        }
        int right = dfs(root.right);
        if(right==-1){
            return -1;
        }
        return Math.abs(right - left) < 2 ? Math.max(left, right) + 1 : -1;


    }
}
