package SwordOffer;

import Tree.TreeNode;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName inOrderNext_08.java
 * @Description
 * 给定一个二叉树和其中的一个结点，请找出中序遍历顺序的下一个结点并且返回。注意，树中的结点不仅包含左右子结点，同时包含指向父结点的指针。
 * 思路：
 * 中序：左中右
 * ① 如果一个节点的右子树不为空，那么该节点的下一个节点是右子树的最左节点（对于中序）；
 * ② 否则（到叶子节点），向上找第一个左链接指向的树包含该节点的祖先节点
 *
 *
 * 我们知道，二叉树「中序遍历」的遍历顺序为 左-根-右。
 * 可以根据传入节点 pNode 是否有「右儿子」，以及传入节点 pNode 是否为其「父节点」的「左儿子」来进行分情况讨论：
 *
 * 传入节点 pNode 有「右儿子」：根据「中序遍历」的遍历顺序为 左-根-右，可以确定「下一个节点」必然为当前节点的「右子树」中「最靠左的节点」；
 * 传入节点 pNode 没有「右儿子」，这时候需要根据当前节点是否为其「父节点」的「左儿子」来进行分情况讨论：
 * 如果传入节点 pNode 本身是其「父节点」的「左儿子」，那么根据「中序遍历」的遍历顺序为为 左-根-右 可知，下一个节点正是该父节点，直接返回该节点即可；
 * 如果传入节点 pNode 本身是其「父节点」的「右儿子」，那么根据「中序遍历」的遍历顺序为为 左-根-右 可知，其父节点已经被遍历过了，
 * 我们需要递归找到符合 node.equals(node.next.left) 的节点作为答案返回，如果没有则说明当前节点是整颗二叉树最靠右的节点，这时候返回 null 即可。
 *
 * @createTime 2021年08月17日 17:12:00
 */
public class inOrderNext_08 {
    public class TreeLinkNode {

        int val;
        TreeLinkNode left = null;
        TreeLinkNode right = null;
        TreeLinkNode next = null;  //指向父节点

        TreeLinkNode(int val) {
            this.val = val;
        }
    }

    public static TreeLinkNode getNextInOrder(TreeLinkNode pnode) {
        if (pnode.right != null) {
            TreeLinkNode rightNode = pnode.right;
            while (rightNode.left != null) {
                rightNode = rightNode.left;
            }
            return rightNode;
        } else {
            //如果当前节点没有右儿子，则「往上找父节点」，直到出现满足「其左儿子是当前节点」的父节点
            while (pnode.next != null) {
                TreeLinkNode parent = pnode.next;
                if (parent.left == pnode) {  //确认当前节点是父节点的左子节点
                    return parent;
                }
                pnode = parent.next;
            }

        }
        //如果设么都没有
        return null;
    }

}
