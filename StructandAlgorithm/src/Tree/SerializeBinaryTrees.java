package Tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName SerializeBinaryTrees.java
 * @Description
 * 序列化二叉树
 *  *
 *  * 题目描述：
 *  * 请实现两个函数，分别用于序列化和反序列化二叉树。
 *  *
 *  * 思路一：递归+先序遍历
 *  * 0. 序列化后的字符串其实是二叉树的层序遍历结果，但是通常使用的先序、中序、后序、层序遍历记录二叉树的信息不完整，
 *  *    也就是可能对应多种二叉树的结果。题目要求是可逆的，因此序列化的字符串需要携带完整的二叉树信息，需要拥有单独
 *  *    表示二叉树的能力；
 *  * 序列化：
 *  * 1. 先序遍历二叉树，如果当前节点不为空，则采用 “节点_” 的形式记录；如果当前节点为空，则采用 “#_” 记录；
 *  *  1.1 用 “#” 占据空位置的目的就是防止二叉树节点有相同值的情况下造成的歧义。
 *  *  1.2 用 “_” 的目的是为了区分每个节点的值，如节点 12 和 3，节点 1 和 23。
 *  * 反序列化：
 *  * 1. 将字符串用 “_” 进行分割，保存到数组中；
 *  * 2. 将数组中的每个值都入队（或者将队列用数组和索引代替）；
 *  * 3. 采用先序遍历进行反序列化。
 *  *
 *  * 思路二：
 *  * 1. 使用层次遍历，然后需要使用队列。
 * @createTime 2021年03月06日 21:51:00
 */
public class SerializeBinaryTrees {

    public static void main(String[] args) {
        TreeNode head1 = new TreeNode(1);
        head1.left = new TreeNode(2);
        head1.right = new TreeNode(3);
        head1.left.left = new TreeNode(4);
        head1.left.right = new TreeNode(5);
        head1.right.left = new TreeNode(6);
        head1.right.right = new TreeNode(7);
        util.PrintTree.printTree(head1);
        System.out.println("先序遍历的结果是");
        bianliTreeWith3order.preOrderRecursion(head1);
        System.out.println("序列化后的结果：");
        String preStr = seriaByPre(head1);
        System.out.println(preStr);
        System.out.println("逆序列化后的结果：");
        util.PrintTree.printTree(unseria(preStr));

    }
 //序列化
    public static String seriaByPre(TreeNode root){
        if (root == null){
            return "#_";
        }
        String res = root.val + "_";  //在先序遍历的位置设置字符串
        res=res+seriaByPre(root.left);
        res = res + seriaByPre(root.right);
        return res;
    }
    //反序列化
    public static TreeNode unseria(String preStr){
        //首先拆分
        String[] values = preStr.split("_");
        //先进先出，所以用个队列
        Queue<String> queue =  new LinkedList<>();
        for (int i = 0; i < values.length; i++) {
            queue.add(values[i]);
        }
        //这个队列整好了，放给先序的方式去消费
        return unseriaByPre(queue);

    }

    private static TreeNode unseriaByPre(Queue<String> queue) {
        //先弹出来  在先序遍历的位置操作
        String val = queue.poll();
        if (val.equals("#")){
            return null;
        }
        TreeNode head = new TreeNode(Integer.valueOf(val));
        head.left = unseriaByPre(queue);
        head.right = unseriaByPre(queue);
        return head;
    }


    //这里补充序列化和反序列化bfs的做法
    //序列化
    public static String serialize(TreeNode root){
        if(root == null){
            return "[]";
        }
        StringBuilder result = new StringBuilder("[");
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        while(!queue.isEmpty()){
            TreeNode curr = queue.poll();
            if(curr!=null){
                result.append(curr.val+",");
                queue.add(curr.left);
                queue.add(curr.right);
            }else{
                result.append("null,");
            }
        }
        result.deleteCharAt(result.length()-1);
        result.append("]");
        return result.toString();
    }

    //反序列化
    public static TreeNode unserilaize(String data) {
        if (data.equals("[]")) {
            return null;
        }
        int index = 1;
        String[] dataChar = data.substring(1, data.length() - 1).split(",");
        TreeNode root = new TreeNode(new Integer(dataChar[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            if (!dataChar[index].equals("[]")) {
                curr.left = new TreeNode(new Integer(dataChar[index]));
                queue.add(curr.left);
            }
            index++;
            if (!dataChar[index].equals("[]")) {
                curr.right = new TreeNode(new Integer(dataChar[index]));
                queue.add(curr.right);
            }
            index++;
        }
        return root;
    }


}
