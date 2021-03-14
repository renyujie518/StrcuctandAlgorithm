package Tree;

import javax.swing.table.TableRowSorter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName getSuccessorNode.java
 * @Description 寻找候机节点 就是 中序遍历中的 的下一个 节点
 *                           1
 *  *  *                /        \
 *  *  *              2           3
 *  *  *            /   \       /   \
 *  *  *           4     5     6     7
 *  *  *         /  \   /  \  /  \  /  \
 *  *  *        n   n  n   n n   n n   n     （n代表null）
 *  中序（左 中 右）    4 2 5 1 6 3 7
 *  比如5的后续节点是1
 *
 *  本体结合搜索二叉树  对于每个子树，左边的小，右边的大 中序遍历（左中右）是绝对升序（不能有相等） 就是
 *  利用这个有序性
 *
 *  解题思路
 * 1.p存在右子树，那么p的后继就是p.right子树的最左节点
 * 2.p不存在右子树，那么p的后继就是p所在子树的最近左孩子的父节点
 *
 * @createTime 2021年03月06日 18:57:00
 */
public class getSuccessorNode {
    public static void main(String[] args) {
        TreeNode head1 = new TreeNode(5);
        head1.left = new TreeNode(3);
        head1.left.left = new TreeNode(2);
        head1.left.right = new TreeNode(4);
        head1.left.left.left = new TreeNode(1);
        head1.right = new TreeNode(7);
        head1.right.left = new TreeNode(6);
        head1.right.right = new TreeNode(8);
        util.PrintTree.printTree(head1);
        head1.right.right = new TreeNode(7);
        System.out.println("针对该搜索二叉树正常的中序遍历应该是 12345678");
        System.out.println("1的后继节点： "+inorderSucessor(head1,head1.left.left.left).val);
        System.out.println("5的后继节点： "+inorderSucessor(head1,head1).val);
        System.out.println("6的后继节点： "+inorderSucessor(head1,head1.right.left).val);

    }
//正常中序遍历，如果找到p对应的节点，那么下一个遍历节点必定是我们需要的后继节点。
    //这个时候，置flag开关为true。一个被遍历的节点就能被传出来（ainNode）啦，与此同时关闭flag开关。
    static  boolean flag = false;
    static TreeNode next = null;  //用于保存下一个节点
    public static  TreeNode inorderSucessor(TreeNode root,TreeNode p){
        if (root == null){
            return null;
        }
        inorderSucessor(root.left, p);
        if (flag){  //表示上一个节点为p
            next = root;
            flag = false;  //标记清除 找到一次就够了

        }
        if (root== p){
            flag = true;     //当找到p的时候flag为1  注意，我们要找的是后继，所以还要在经过一遍inorderSucessor后的root,root在走
        }
        inorderSucessor(root.right,p);
        return next;

    }


}
