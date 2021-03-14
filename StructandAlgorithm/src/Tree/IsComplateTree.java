package Tree;


import java.util.LinkedList;
import java.util.Queue;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName ComplateTree.java
 * @Description complete binary tree 完全二叉树：
 * 从根往下数，除了最下层外都是全满（都有两个子节点），而最下层所有叶结点都向左边靠拢填满。
 * 按照宽度遍历
 * 1. 按层遍历二叉树，如果当前节点有右孩子而没有左孩子，则一定不是二叉树；（左边有个洞）
 * 2.在1的条件下 如果当前遇到的第一个节点左右孩子不齐全，后续的都得是叶节点
 * @createTime 2021年03月06日 13:13:00
 */
public class IsComplateTree {

    public static boolean IsCBT(TreeNode root){
        if (root == null){
            return true;
        }
        //宽度优先用队列
        Queue<TreeNode> queue = new LinkedList<>();
        boolean key = false;  //是否遇到不双全的节点
        TreeNode L = null;
        TreeNode R = null;
        queue.add(root);
        while (!queue.isEmpty()){
            root = queue.poll();
            L = root.left;
            R = root.right;
            // ps 判断叶节点 L==null && R==null
            if (
                    (key && (L != null || R != null))//当前遇到的第一个节点左右孩子不齐全。且孩子们都不是叶节点（当且节点居然有孩子）
                    ||
                            (R != null && L == null) //条件1 有右无左直接返回false
            ){
                return false;
            }
            if (L!=null){  //先左后右
                queue.add(L);
            }
            if (R!= null){
                queue.add(R);
            }
            //遇到不双全的情况要改key值
            if (L == null || R == null){
                key = true;     //key只有从flase变成true，没有形成闭环，一旦改变，就是思路里提到的遇到第一个不全的节点
            }


        }
        //这些if在遍历的过程中都没碰到，就返回true
        return true;

    }
}
