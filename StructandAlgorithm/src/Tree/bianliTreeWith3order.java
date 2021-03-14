package Tree;
import java.util.*;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName bianliTreeWith3order.java
 * @Description 二叉树的前中后遍历（递归和非递归）
 *注意：三种序都可以从递归序中获得
 *  * 递归序，依次访问到的节点有哪些；
 *  *                     1
 *  *                /        \
 *  *              2           3
 *  *            /   \       /   \
 *  *           4     5     6     7
 *  *         /  \   /  \  /  \  /  \
 *  *        n   n  n   n n   n n   n     （n代表null）
 *  *  递归序：1->2->4->4->4->2->5->5->5->2->1->3->6->6->6->3->7->7->7->3->1
 *  *  可以看到，对于递归来说，每个节点都被访问到了 3 次。
 *
 *  先序（中 左 右）    1 2 4 5 3 6 7
 *  中序（左 中 右）    4 2 5 1 6 3 7
 *  后序（左 右 中）    4 5 2 6 7 3 1
 *  宽度优先           1 2 3 4 5 6 7
 *
 *  下面通过递归和非递归的方式实现三种序
 *
 *  补充 递归方法一定是可以转换为非递归的，就是把系统写好的压栈过程自己写一遍
 *      二叉树的先序遍历就是深度优先遍历
 *
 * @createTime 2021年02月28日 16:53:00
 */
public class bianliTreeWith3order {
    public static void main(String[] args) {
//        TreeNode  head = new TreeNode (5);
//        head.left = new TreeNode (3);
//        head.right = new TreeNode (8);
//        head.left.left = new TreeNode (2);
//        head.left.right = new TreeNode (4);
//        head.left.left.left = new TreeNode (1);
//        head.right.left = new TreeNode (7);
//        head.right.left.left = new TreeNode (6);
//        head.right.right = new TreeNode (10);
//        head.right.right.left = new TreeNode (9);
//        head.right.right.right = new TreeNode (11);

        TreeNode head = new TreeNode(1);
        head.left = new TreeNode(2);
        head.right = new TreeNode(3);
        head.left.left = new TreeNode(4);
        head.left.right = new TreeNode(5);
        head.right.left = new TreeNode(6);
        head.right.right = new TreeNode(7);
        util.PrintTree.printTree(head);

        System.out.println("==============recursive==============");
        System.out.println("pre-order: ");
        preOrderRecursion(head);
        System.out.println("in-order: ");
        inOrderRecursion(head);
        System.out.println("post-order: ");
        postOrderRecursion(head);

        System.out.println("==============Unrecursive==============");
        System.out.println("pre-order: ");
        preOrderUnRecursion(head);
        System.out.println("in-order: ");
        inOrderUnRecursion(head);
        System.out.println("post-order: ");
        postOrderUnRecursion(head);

        System.out.println("==============Breadthfirst==============");
        System.out.println("Breadthfirst: ");
        Breadthfirst(head);

        System.out.println("==============Other==============");
        List<List<Integer>> lists = levelOrder(head);
        for (List<Integer> list : lists) {
            for (Integer res : list) {
                System.out.println(res);
            }
        }
    }

    //先序遍历（递归）  在递归序中第一次遇到节点就打印
    public static void preOrderRecursion(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.println(root.val);
        preOrderRecursion(root.left);
        preOrderRecursion(root.right);
    }

    //中序遍历(递归) 在递归序中第2次遇到节点就打印
    public static void inOrderRecursion(TreeNode root) {
        if (root == null) {
            return;
        }
        inOrderRecursion(root.left);
        System.out.println(root.val);
        inOrderRecursion(root.right);
    }

    //后序遍历(递归) 在递归序中第3次遇到节点就打印
    public static void postOrderRecursion(TreeNode root) {
        if (root == null) {
            return;
        }
        postOrderRecursion(root.left);
        postOrderRecursion(root.right);
        System.out.println(root.val);
    }

    //先序遍历(非递归)
    //先压头节点，每次从栈中弹出个节点就打印，一旦弹出，先右再左（如果有）依次往复
    public static void preOrderUnRecursion(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            System.out.println(node.val);
            //先右后左，如果有
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
    }

    //中序遍历(非递归)
    //每颗子树 左边界进栈，依次弹出的过程中打印，一旦弹出，对弹出节点的右树
    //只要来到一个节点有左边界就一路压进去，压到底了就弹出一个栈里的节点（弹出就打印），把弹出节点的右树周而复始
    public static void inOrderUnRecursion(TreeNode root) {
        if (root != null) {
            Stack<TreeNode> stack = new Stack<>();
            while (!stack.isEmpty() || root != null) {
                if (root != null) {  //这里的root看做左边界，每次一口气把所有左边界放进去，直到左边界没有（null）进else
                    stack.push(root);
                    root = root.left;
                } else { //这里十分巧妙的复用了节点root ，进到这个else代表root = null,即左边界到底了，这时候再把root指向栈顶弹出
                    root = stack.pop();  //现在root看做弹出的节点
                    System.out.println(root.val);  //一旦弹出就打印
                    root = root.right;  //这里形成个闭环，root开始指向右树，右树如果有东西（root != null）又回到上面的if开始压左边界
                    //如果root指向right后没东西,右树也压完了，即没有右树（root =null）又进到这个else里，接着弹出+打印，root也在前进
                }
            }
        }
    }

    //后序遍历（非递归）
    //在先序(中左右)的基础上 如果是中右左再倒过来就成了（左右中）后序
    //所以 先放头，弹出后再放入一个新栈，一旦弹出，先左后右（如果有），最后把栈2弹出
    public static void postOrderUnRecursion(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();

        stack1.push(root);
        while (!stack1.isEmpty()) {
            TreeNode node = stack1.pop();
            stack2.push(node);

            if (node.left != null) {
                stack1.push(node.left);
            }
            if (node.right != null) {
                stack1.push((node.right));
            }
        }
        //到这里栈1空，栈2满
        while (!stack2.isEmpty()) {
            TreeNode res = stack2.pop();
            System.out.println(res.val);
        }
    }

    //宽度优先  主要使用队列的思想（先进先出）  先把头结放到队列里去，每次弹出就打印，先放左后放右，周而复始
    public static void Breadthfirst(TreeNode root){
        if (root == null){
            return;
        }
        //在java里双向链表可以作队列
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            TreeNode curr = queue.poll();
            System.out.println(curr.val);
            if (curr.left != null){
                queue.add(curr.left);
            }
            if(curr.right != null){
                queue.add(curr.right);
            }
        }
    }

    //这里写一个比较好玩的遍历 从上到下，第一层从左到右，第二层从右到左
    //思路：基于深度优先，只需要将偶数层倒序即可，但每次要知道该层的个数为多少，否则不知道设么时候倒序，用个list
    public static List<List<Integer>> levelOrder(TreeNode root){
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



