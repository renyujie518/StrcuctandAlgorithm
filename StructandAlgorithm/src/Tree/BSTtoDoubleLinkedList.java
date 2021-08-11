package Tree;
import java.util.Stack;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName BSTtoDoubleLinkedList.java
 * @Description
 * 双向链表节点结构和二叉树节点结构是一样的，
 * 如果你把last认为是left， next认为是next的话。
 * 给定一个搜索二叉树(左小右大)的头节点head， 请转化成一条有序的双向链表， 并返回链表的头节点。
 *
 * 使用左神的二叉树的递归套路  提取公共的，从左右树中索取，递归
 *
 * 或者搜索二叉树的中序遍历（左中右）就是升序的，然后再构建双向链表
 *
 * 使用中序遍历访问树的各节点 cur ；
 * 并在访问每个节点时构建 cur 和前驱节点 pre 的引用指向；中序遍历完成后，最后构建头节点和尾节点的引用指向即可。
 * @createTime 2021年08月09日 14:29:00
 */
public class BSTtoDoubleLinkedList {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    //递归方式中序遍历  dfs  转链表
    //题中说了还要返回链表的头节点，所以我们还要使用一个变量head来记录第一个节点的指针。
    static Node head = null;
    //pre来记录遍历当前节点之前遍历的那个节点，主要用它来和当前节点给串起来。
    static Node pre = null;

    public static Node convertWithRecursion(Node root) {
        if (root == null) {
            return root;
        }
        //转化为双向链表（中序遍历每个节点时构建 cur 和前驱节点 pre 的引用指向）
        dfs(root);
        /**在剑指offer36中是要求构建"循环双向链表"
        进行头节点和尾节点的相互指向
        pre.right = head;
        head.left = pre;**/
        //本题只是一个双向链表返回头结点，注意，在上述dfs后，pre一步步走，最终pre指向双向链表中的尾节点
        //head还是在那个if里被被指定了
        //所以pre直接右指向null把这里断开就可以了
        pre.right = null;
        return head;
    }

    public static void dfs(Node curr) {
        if (curr == null) {
            return;
        }
        //pre用于记录双向链表中位于cur左侧的节点，即上一次迭代中的cur,
        curr.left = pre;
        //先遍历左子节点
        dfs(curr.left);
        /**本来这一部分是print在中序遍历的时候，现在把它转化为To链表的逻辑**/
        //当pre==null时，cur左侧没有节点,即此时cur为双向链表中的头节点
        if (pre == null) {
            head = curr;
        } else {//反之，pre!=null时，cur左侧存在节点pre，需要进行pre.right=cur的操作。
            //串起来的结果就是前一个节点pre的right指向当前节点curr，然后当前节点curr的left指向前一个节点pre
            pre.right = curr;
        }
        pre = curr;//pre指向当前的cur
        /**本来这一部分是print在中序遍历的时候，现在把它转化为To链表的逻辑**/
        dfs(curr.right);//在递归右子树

        //全部迭代完成后，pre指向双向链表中的尾节点
    }

    /**
     首先复习非递归写法：
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                list.add(cur.val);
                cur = cur.right;
            }
        }
        return list;
    }
     **/
    //非递归方式中序遍历转链表
    public static Node convertWithoutRecursion(Node root) {
        if (root == null) {
            return null;
        }
        Stack<Node> stack = new Stack<>();
        //题中说了还要返回链表的头节点，所以我们还要使用一个变量head来记录第一个节点的指针。
        Node head = null;
        //pre来记录遍历当前节点之前遍历的那个节点，主要用它来和当前节点给串起来。
        Node pre = null;
        Node curr = root;
        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {
                stack.push(root);
                curr = curr.left;
            } else {
                curr = stack.pop();
                /**本来这一部分是print在中序遍历的时候，现在把它转化为To链表的逻辑**/
                curr.left = pre;
                if (pre == null) {//当pre==null时，cur左侧没有节点,即此时cur为双向链表中的头节点
                    head = root;
                } else {
                    pre.right = curr;
                }
                pre = curr;
                /**本来这一部分是print在中序遍历的时候，现在把它转化为To链表的逻辑**/
                curr = curr.right;
            }
        }
        //这一步看上面递归的分析
        pre.right = null;
        return head;
    }

    //左神二叉树递归的套路
    //公共信息就是二叉树转化为链表后的头和尾
    public static class convertType {
        public Node start;
        public Node end;

        public convertType(Node start, Node end) {
            this.start = start;
            this.end = end;
        }
    }

    public static Node convertWithZS(Node root) {
        if (root == null) {
            return null;
        }
        //返回的是链表的头结点信息
        return process(root).start;
    }

    public static convertType process(Node root){
        if (root == null) {
            return new convertType(null, null);
        }
        //获取左右节点的公共信息
        convertType leftInfo = process(root.left);
        convertType rightInfo = process(root.right);
        if (leftInfo.end != null) {//防止空指针异常
            //左树的最后一个节点的右侧连接root 构成链表的左半部分
            leftInfo.end.right = root;
        }
        //单独处理链表中间部分的root
        root.left = leftInfo.end;
        root.right = rightInfo.start;
        if (rightInfo.start != null) {
            //右树的第一个节点的左侧连接root 构成链表的右半部分
            rightInfo.start.left = root;
        }

        //这时候要考虑两种畸形  比如没有左半树只有root后右半树这样的半个数（右边同理）
        //这时候要保证返回的链表信息中的正确性  leftInfo.start和rightInfo.end都是上述的畸形情况，说明没有那半个
        //最理想的情况当然是返回convertType（leftInfo.start，rightInfo.end）
        return new convertType(
                leftInfo.start != null ? leftInfo.start : root,
                rightInfo.end != null ? rightInfo.end : root
        );
    }

    public static void printBSTInOrder(Node head) {
        System.out.print("二叉搜索树中序: ");
        if (head != null) {
            inOrderPrint(head);
        }
        System.out.println();
    }

    //中序打印树
    public static void inOrderPrint(Node head) {
        if (head == null) {
            return;
        }
        inOrderPrint(head.left);
        System.out.print(head.value + " ");
        inOrderPrint(head.right);
    }

    //打印双向链表
    public static void printDoubleLinkedList(Node head) {
        System.out.print("双向链表: ");
        Node end = null;
        while (head != null) {
            System.out.print(head.value + " ");
            end = head;
            head = head.right;
        }
        System.out.print("| ");
        while (end != null) {
            System.out.print(end.value + " ");
            end = end.left;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head1 = new Node(5);
        head1.left = new Node(2);
        head1.right = new Node(9);
        head1.left.left = new Node(1);
        head1.left.right = new Node(3);
        head1.left.right.right = new Node(4);
        head1.right.left = new Node(7);
        head1.right.right = new Node(10);
        head1.left.left = new Node(1);
        head1.right.left.left = new Node(6);
        head1.right.left.right = new Node(8);

        System.out.println("非递归中序");
        printBSTInOrder(head1);
        head1 = convertWithoutRecursion(head1);
        printDoubleLinkedList(head1);

        Node head2 = new Node(5);
        head2.left = new Node(2);
        head2.right = new Node(9);
        head2.left.left = new Node(1);
        head2.left.right = new Node(3);
        head2.left.right.right = new Node(4);
        head2.right.left = new Node(7);
        head2.right.right = new Node(10);
        head2.left.left = new Node(1);
        head2.right.left.left = new Node(6);
        head2.right.left.right = new Node(8);

        System.out.println("递归中序");
        printBSTInOrder(head2);
        head2 = convertWithRecursion(head2);
        printDoubleLinkedList(head2);


        Node head3 = new Node(5);
        head3.left = new Node(2);
        head3.right = new Node(9);
        head3.left.left = new Node(1);
        head3.left.right = new Node(3);
        head3.left.right.right = new Node(4);
        head3.right.left = new Node(7);
        head3.right.right = new Node(10);
        head3.left.left = new Node(1);
        head3.right.left.left = new Node(6);
        head3.right.left.right = new Node(8);

        System.out.println("左神递归");
        printBSTInOrder(head3);
        head3 = convertWithZS(head3);
        printDoubleLinkedList(head3);

    }




}
