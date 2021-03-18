package Tree;

import java.time.temporal.Temporal;
import java.util.HashMap;
import java.util.HashSet;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName LowestCommonAncestor.java
 * @Description  给定一个二叉树, 找到该树中两个指定节点的最近公共祖先
 * @createTime 2021年03月06日 16:29:00
 */
public class LowestCommonAncestor {
    public static void main(String[] args) {
        TreeNode head1 = new TreeNode(1);
        head1.left = new TreeNode(2);
        head1.right = new TreeNode(3);
        head1.left.left = new TreeNode(4);
        head1.left.right = new TreeNode(5);
        head1.right.left = new TreeNode(6);
        head1.right.right = new TreeNode(7);
        util.PrintTree.printTree(head1);
        TreeNode res1 = commAncestor1(head1, head1.left.left, head1.left.right);//4和5
        System.out.println("4和5最近公共祖先： "+res1.val);
        TreeNode res2 = commAncestor1(head1, head1.left.left, head1);
        System.out.println("4和头最近公共祖先： "+res2.val);
        TreeNode res3 = commAncestor1(head1,  head1.right.right, head1);
        System.out.println("4和7最近公共祖先： "+res3.val);
        TreeNode res4 = commAncestor1(head1,head1.left,head1.left.left); //2 4
        System.out.println("4和2最近公共祖先： "+res4.val);

    }

    //首先的一种思路是利用hashmap，把父节点这条串放进去，然后让另一个几点在里面找，找到的就是第一个
    public static TreeNode commAncestor1(TreeNode head,TreeNode o1,TreeNode o2){
        if(o1== head || o2 == head || head ==null){
            return head;
        }
        HashMap<TreeNode, TreeNode> fatherMap = new HashMap<>();
        fatherMap.put(head,head);//头结点的父还是自己
        processFindFather(head,fatherMap);  //对这个map操作，里面 key:该节点  value: 该节点的父
        HashSet<TreeNode> set = new HashSet<>();  //设立一个Set，里面存放一条父链  为什么用set，因为保证唯一性
        TreeNode curr = o1;
        //先把curr放到set里 因为有可能o1就是o2的父节点
        set.add(curr);
        while (curr != fatherMap.get(curr)){  //直到取到自己的父还是自己，那就是到root了  目的是构建set
            set.add(curr);
            curr = fatherMap.get(curr);  //向上走

        }
        //至此，这个set里存放的就是o1的所有父链，包括root
        while (o2 != fatherMap.get(o2)){
            if (set.contains(o2)){
                return o2;
            }
            o2 = fatherMap.get(o2);  //o2向上走
        }

        //遍历一圈还没有的话就返回null
        return null;

    }

    public static void processFindFather(TreeNode head,HashMap<TreeNode, TreeNode> fatherMap){  //递归 停止条件 初始化，递归
        if (head == null){
            return;
        }
        fatherMap.put(head.left, head);//左节点的父是head
        fatherMap.put(head.right, head);
        processFindFather(head.left,fatherMap);
        processFindFather(head.right, fatherMap);
    }

//法2  直接递归 这个看看就就好，拿个笔画一下就行，这里最好采用1
    public static TreeNode commAncestor2(TreeNode head, TreeNode o1, TreeNode o2){
        if (head == null || head == o1 || head == o2) {
            return head;
        }
        TreeNode left = commAncestor2(head.left, o1, o2);
        TreeNode right = commAncestor2(head.right, o1, o2);
        if (left != null && right != null) {
            return head;
        }
        return left != null ? left : right;

    }
}
