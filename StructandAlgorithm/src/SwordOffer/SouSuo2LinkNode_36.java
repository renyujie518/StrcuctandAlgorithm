package SwordOffer;

import Tree.TreeNode;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName SouSuo2LinlNode_36.java
 * @Description 二叉搜索树与双向链表
 * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。要求不能创建任何新的结点，只能调整树中结点指针的指向。
 *
 * 二叉搜索树的中序遍历为 递增序列 。
 * 使用中序遍历访问树的各节点 cur ；并在访问每个节点时构建 cur 和前驱节点 pre 的引用指向；
 * 中序遍历完成后，最后构建头节点和尾节点的引用指向即可。
 * @createTime 2021年08月21日 21:45:00
 */
public class SouSuo2LinkNode_36 {
    private static TreeNode pre = null;//pre用于记录双向链表中位于cur左侧的节点
    private static TreeNode head = null;
    public static TreeNode SouSuo2LinkNode(TreeNode root) {
        if(root == null) return null;
        inOrder(root);
        //中序遍历完成后，head 指向头节点， pre 指向尾节点
        head.left = pre;
        pre.right = head;
        return root;
    }


    public static void inOrder(TreeNode curr) {
        if(curr==null){
            return;
        }
        inOrder(curr.left);
        //pre用于记录双向链表中位于cur左侧的节点，即上一次迭代中的cur,当pre==null时，cur左侧没有节点,即此时cur为双向链表中的头节点
        if(pre==null){
            head = curr;
        } else{
            //反之，pre!=null时，cur左侧存在节点pre，需要进行pre.right=cur的操作。
            pre.right = curr;
        }
        curr.left = pre;//pre是否为null对这句没有影响,且这句放在上面两句if else之前也是可以的。

        pre = curr;//更新 pre = cur ，即节点 cur 是后继节点的 pre
        inOrder(curr.right);//全部迭代完成后，pre指向双向链表中的尾节点

    }
}
