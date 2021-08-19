package SwordOffer;

import Tree.TreeNode;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName isDuiChenTree.java
 * @Description  判断两棵树是不是对称二叉树
 * 终止条件：
 * 当 LL 和 RR 同时越过叶节点： 此树从顶至底的节点都对称，因此返回 true ；
 * 当 LL 或 RR 中只有一个越过叶节点： 此树不对称，因此返回 false ；
 * 当节点 LL 值 \ 节点 RR 值： 此树不对称，因此返回 false；
 * 递推工作：
 * 判断两节点 L.leftL.left 和 R.rightR.right 是否对称，即 recur(L.left, R.right) ；
 * 判断两节点 L.rightL.right 和 R.leftR.left 是否对称，即 recur(L.right, R.left) ；
 * 返回值： 两对节点都对称时，才是对称树，因此用与逻辑符 && 连接。
 * @createTime 2021年08月19日 22:07:00
 */
public class isDuiChenTree_28 {
    public static boolean isDuiChenTree(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isDuiChenTreeWithDG(root.left, root.right);

    }

    public static boolean isDuiChenTreeWithDG(TreeNode t1, TreeNode t2){
        if (t1 == null & t2 == null) {
            return true;
        }
        if (t1 == null || t2 == null) {
            return false;
        }
        if (t1.val != t2.val){
            return false;
        }
        return isDuiChenTreeWithDG(t1.left, t2.right) && isDuiChenTreeWithDG(t1.right, t1.left);


    }
}
