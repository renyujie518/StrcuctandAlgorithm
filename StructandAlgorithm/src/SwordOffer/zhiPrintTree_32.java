package SwordOffer;

import Tree.TreeNode;

import java.util.*;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName zhiPrintTree_32.java
 * @Description 按之字形顺序打印二叉树
 * 请实现一个函数按照之字形打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右至左的顺序打印，第三行按照从左到右的顺序打印，
 * 其他行以此类推。
 * @createTime 2021年08月21日 16:37:00
 */
public class zhiPrintTree_32 {
    public static List<List<Integer>> zhiPrintTree(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null){
            return res;
        }
        int level = 0;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        //add()和offer()都是向队列中添加一个元素。一些队列有大小限制，
        //因此如果想在一个满的队列中加入一个新项，调用 add() 方法就会抛出一个 unchecked 异常，
        //而调用 offer() 方法会返回 false。因此就可以在程序中进行有效的判断！
        while (!queue.isEmpty()){
            level++;
            List<Integer> list = new ArrayList<>();  //这个list储存的是每一层的元素
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                root = queue.poll();
                list.add(root.val);
                if (root.left != null){
                    queue.offer(root.left);
                }
                if (root.right != null){
                    queue.offer(root.right);
                }
            }
            //偶数层就利用Collections反转一下
            if ((level&1)==0){  //level是先++后来到这里的，所以不应该是2 而是1
                Collections.reverse(list);
            }
            res.add(list);
        }
        return res;
    }

}
