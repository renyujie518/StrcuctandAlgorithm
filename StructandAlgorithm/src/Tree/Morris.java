package Tree;

import Graph.Node;
import LinkedList.ListNode;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName Morris.java
 * @Description
 * Morris遍历
 * 一种遍历二叉树的方式，并且时间复杂度O(N)，额外空间复杂度O(1)
 * 通过利用原树中大量空闲指针（叶节点）的方式，达到节省空间的目的，会改变这个树
 * Morris遍历细节
 * 假设来到当前节点cur，开始时cur来到头节点位置
 *      1）如果cur没有左孩子，cur向右移动(cur=cur.right)
 *      2）如果cur有左孩子，找到左子树上最右的节点mostRight：
 *          a.如果mostRight的右指针指向空，让其指向cur，然后cur向左移动(cur=cur.left)
 *          b.如果mostRight的右指针指向cur，让其指向null，然后cur向右移动(cur=cur.right)
 *      3）cur为空时遍历停止
 *
 * Morris遍历的实质建立一种机制，对于没有左子树的节点只到达一次，对于有左子树的节点会到达两次
 * 先序、中序可以由morris遍历加工得到后序遍历也可由morris遍历加工得到，但是把处理时机放在，能够达到两次的节点并且是第二次到达的时候
 *
 * 关于怎么知道是curr第几次到达，可以通过curr"左子树上最右的节点的右指针" 指向null第一次 指向curr第二次 没有左树curr只能经过一次
 * @createTime 2021年03月25日 15:02:00
 */
public class Morris {

    public static void morris(TreeNode head){
        if (head == null){
            return;
        }
        TreeNode curr = head;  //开始时cur来到头节点位置
        TreeNode leftTreeMostRight = null;   //左子树上最右的节点
        while (curr !=null){ //3）cur为空时遍历停止
            leftTreeMostRight = curr.left;  //先设为左子树
            if (leftTreeMostRight !=null){//如果cur有左孩子，找到左子树上最右的节点leftTreeMostRight
                //下面是找到左子树的最右节点leftTreeMostRight，其实就是"在初始化左子树后不断右移"
                while (leftTreeMostRight.right!=null && leftTreeMostRight.right!=curr){
                    //终止条件要注意 在左神讲课的过程可以发现，"左子树的最右节点的右指向"是可能指回curr的
                    //所以说不光为空的时候停，还要防止指向curr，这样才能保证是第一次"找到左子树的最右节点"
                    leftTreeMostRight = leftTreeMostRight.right;
                }
                //此时leftTreeMostRight就是"左子树的最右节点" a,b两种情况判断
                if (leftTreeMostRight.right == null){
                    //(a)如果leftTreeMostRight的右指针指向空，让右指针指向cur，然后cur向左移动
                    //注意，这也是第一次来到curr
                    leftTreeMostRight.right = curr;
                    curr = curr.left;
                    continue; //跳到第33行的while继续执行while循环，直到cur为空时遍历停止
                }else {//这里也是第二次来到自己
                    //(b)如果leftTreeMostRight的右指针指向cur，让右指针指向null，然后cur向右移动
                    leftTreeMostRight.right = null;
                    //这里其实很巧妙 注意啊，现在leftTreeMostRight.right = null，刚好破了35行的if,那紧接着就到了55行的else里
                    //也能完成"cur向右移动"的事，这里就可以省略
                }
            }
            //(1)如果cur没有左孩子，cur向右移动
            curr = curr.right;
        }
    }

    //morris先序 一个节点只能到达它1次直接打印（没有左树）  到达2次只打印第1次，再向右移动
    public static void morrisPre(TreeNode head) {
        if (head == null){
            return;
        }
        TreeNode curr = head;  //开始时cur来到头节点位置
        TreeNode leftTreeMostRight = null;   //左子树上最右的节点
        while (curr !=null){ //3）cur为空时遍历停止
            leftTreeMostRight = curr.left;  //先设为左子树
            if (leftTreeMostRight !=null){//如果cur有左孩子，找到左子树上最右的节点leftTreeMostRight
                //下面是找到左子树的最右节点leftTreeMostRight，其实就是"在初始化左子树后不断右移"
                while (leftTreeMostRight.right!=null && leftTreeMostRight.right!=curr){
                    //终止条件要注意 在左神讲课的过程可以发现，"左子树的最右节点的右指向"是可能指回curr的
                    //所以说不光为空的时候停，还要防止指向curr，这样才能保证是第一次"找到左子树的最右节点"
                    leftTreeMostRight = leftTreeMostRight.right;
                }
                //此时leftTreeMostRight就是"左子树的最右节点" a,b两种情况判断
                if (leftTreeMostRight.right == null){
                    //(a)如果leftTreeMostRight的右指针指向空，让右指针指向cur，然后cur向左移动
                    //注意，这也是第一次来到curr
                    System.out.println(curr.val);
                    leftTreeMostRight.right = curr;
                    curr = curr.left;
                    continue; //跳到第33行的while继续执行while循环，直到cur为空时遍历停止
                }else {//这里也是第二次来到自己
                    //(b)如果leftTreeMostRight的右指针指向cur，让右指针指向null，然后cur向右移动
                    leftTreeMostRight.right = null;
                    //这里其实很巧妙 注意啊，现在leftTreeMostRight.right = null，刚好破了35行的if,那紧接着就到了55行的else里
                    //也能完成"cur向右移动"的事，这里就可以省略
                }
            }else {//(1)如果cur没有左孩子，说明第一次，直接打印
                System.out.println(curr.val);
            }
            curr = curr.right;
        }
    }
    //只一次的节点直接打印，第二次出现的节点第二次打印
    public static void morrisIn(TreeNode head) {
        if (head == null){
            return;
        }
        TreeNode curr = head;  //开始时cur来到头节点位置
        TreeNode leftTreeMostRight = null;   //左子树上最右的节点
        while (curr !=null){ //3）cur为空时遍历停止
            leftTreeMostRight = curr.left;  //先设为左子树
            if (leftTreeMostRight !=null){//如果cur有左孩子，找到左子树上最右的节点leftTreeMostRight
                //下面是找到左子树的最右节点leftTreeMostRight，其实就是"在初始化左子树后不断右移"
                while (leftTreeMostRight.right!=null && leftTreeMostRight.right!=curr){
                    //终止条件要注意 在左神讲课的过程可以发现，"左子树的最右节点的右指向"是可能指回curr的
                    //所以说不光为空的时候停，还要防止指向curr，这样才能保证是第一次"找到左子树的最右节点"
                    leftTreeMostRight = leftTreeMostRight.right;
                }
                //此时leftTreeMostRight就是"左子树的最右节点" a,b两种情况判断
                if (leftTreeMostRight.right == null){
                    //(a)如果leftTreeMostRight的右指针指向空，让右指针指向cur，然后cur向左移动
                    //注意，这也是第一次来到curr
                    leftTreeMostRight.right = curr;
                    curr = curr.left;
                    continue; //跳到第33行的while继续执行while循环，直到cur为空时遍历停止
                }else {
                    //(b)如果leftTreeMostRight的右指针指向cur，让右指针指向null，然后cur向右移动
                    //这里也是第二次来到自己
                    leftTreeMostRight.right = null;
                    //这里其实很巧妙 注意啊，现在leftTreeMostRight.right = null，刚好破了35行的if,那紧接着就到了55行的else里
                    //也能完成"cur向右移动"的事，这里就可以省略
                }
            }
            //(1)如果cur没有左孩子，cur向右移动
            //没有左树，直接跳过上述的if ,代表curr最多只会在这里遍历一次，直接打印
            //有左树的情况，在continu之后又回到while执行，对于能够回到curr2次的节点会从129 130跳出来，执行打印
            System.out.println(curr.val);
            curr = curr.right;
        }

    }
    //后序 把打印时机只放到能回到两次的节点，第二次回来的时候  "逆序打印左树右边界" 从上到下打印左树右边界，最后单独逆序打印整棵树的右边界
    public static void morrisPos(TreeNode head) {
        if (head == null){
            return;
        }
        TreeNode curr = head;  //开始时cur来到头节点位置
        TreeNode leftTreeMostRight = null;   //左子树上最右的节点
        while (curr !=null){ //3）cur为空时遍历停止
            leftTreeMostRight = curr.left;  //先设为左子树
            if (leftTreeMostRight !=null){//如果cur有左孩子，找到左子树上最右的节点leftTreeMostRight
                //下面是找到左子树的最右节点leftTreeMostRight，其实就是"在初始化左子树后不断右移"
                while (leftTreeMostRight.right!=null && leftTreeMostRight.right!=curr){
                    //终止条件要注意 在左神讲课的过程可以发现，"左子树的最右节点的右指向"是可能指回curr的
                    //所以说不光为空的时候停，还要防止指向curr，这样才能保证是第一次"找到左子树的最右节点"
                    leftTreeMostRight = leftTreeMostRight.right;
                }
                //此时leftTreeMostRight就是"左子树的最右节点" a,b两种情况判断
                if (leftTreeMostRight.right == null){
                    //(a)如果leftTreeMostRight的右指针指向空，让右指针指向cur，然后cur向左移动
                    //注意，这也是第一次来到curr
                    leftTreeMostRight.right = curr;
                    curr = curr.left;
                    continue; //跳到第33行的while继续执行while循环，直到cur为空时遍历停止
                }else {//这里也是第二次来到自己
                    //(b)如果leftTreeMostRight的右指针指向cur，让右指针指向null，然后cur向右移动
                    leftTreeMostRight.right = null;
                    //这里其实很巧妙 注意啊，现在leftTreeMostRight.right = null，刚好破了35行的if,那紧接着就到了55行的else里
                    //也能完成"cur向右移动"的事，这里就可以省略

                    //第二次回来的时候  "逆序打印左树右边界"
                    printEdgr(curr.left);
                }
            }
            //(1)如果cur没有左孩子，cur向右移动
            curr = curr.right;
        }
        //整棵树跑完后再单独逆序打印整棵树的右边界
        printEdgr(head);
    }

    //以X为头的树，逆序打印这棵树的右边界
    public static  void printEdgr(TreeNode X){
        TreeNode tail = reverseEdge(X);  //先翻转得到临时的tail
        TreeNode curr = tail;
        while (curr!= null){
            System.out.println(curr.val);
            curr = curr.right;
        }
        reverseEdge(tail);  //打印任务完成后再翻转回来
    }

    //翻转一个右边界 这里参考链表的翻转
    public  static TreeNode reverseEdge(TreeNode from){
        TreeNode pre = null;
        TreeNode next = null;
        while (from!=null){  //到空的时候停止
            next = from.right;
            from.right = pre;
            pre = from;
            from = next;
        }
        return pre;
    }

    //用morris判断是不是搜索二叉树（对于每个子树，左边的小，右边的大）
    //中序遍历（左中右）是绝对升序（不能有相等） 就是
    public static boolean isBSTwithMorris(TreeNode head){
        if (head == null){
            return true;
        }
        TreeNode curr = head;  //开始时cur来到头节点位置
        TreeNode leftTreeMostRight = null;   //左子树上最右的节点
        int preValue = Integer.MIN_VALUE;  //用于记录之前的值，看是不是递增
        while (curr !=null){ //3）cur为空时遍历停止
            leftTreeMostRight = curr.left;  //先设为左子树
            if (leftTreeMostRight !=null){//如果cur有左孩子，找到左子树上最右的节点leftTreeMostRight
                //下面是找到左子树的最右节点leftTreeMostRight，其实就是"在初始化左子树后不断右移"
                while (leftTreeMostRight.right!=null && leftTreeMostRight.right!=curr){
                    //终止条件要注意 在左神讲课的过程可以发现，"左子树的最右节点的右指向"是可能指回curr的
                    //所以说不光为空的时候停，还要防止指向curr，这样才能保证是第一次"找到左子树的最右节点"
                    leftTreeMostRight = leftTreeMostRight.right;
                }
                //此时leftTreeMostRight就是"左子树的最右节点" a,b两种情况判断
                if (leftTreeMostRight.right == null){
                    //(a)如果leftTreeMostRight的右指针指向空，让右指针指向cur，然后cur向左移动
                    //注意，这也是第一次来到curr
                    leftTreeMostRight.right = curr;
                    curr = curr.left;
                    continue; //跳到第33行的while继续执行while循环，直到cur为空时遍历停止
                }else {
                    //(b)如果leftTreeMostRight的右指针指向cur，让右指针指向null，然后cur向右移动
                    //这里也是第二次来到自己
                    leftTreeMostRight.right = null;
                    //这里其实很巧妙 注意啊，现在leftTreeMostRight.right = null，刚好破了35行的if,那紧接着就到了55行的else里
                    //也能完成"cur向右移动"的事，这里就可以省略
                }
            }
            //(1)如果cur没有左孩子，cur向右移动
            //没有左树，直接跳过上述的if ,代表curr最多只会在这里遍历一次，直接打印
            //有左树的情况，在continu之后又回到while执行，对于能够回到curr2次的节点会从129 130跳出来，执行打印
            //System.out.println(curr.val);
            if (curr.val<preValue){
                return false;  //一旦出现没有递增的情况，就不是BST
            }
            preValue = curr.val;  //每结束次while就跟新praValue一次
            curr = curr.right;
        }
        return true;  //如果所有节点都满足条件 就说明是BST
    }



    public static void main(String[] args) {
        TreeNode head1 = new TreeNode(1);
        head1.left = new TreeNode(2);
        head1.right = new TreeNode(3);
        head1.left.left = new TreeNode(4);
        head1.left.right = new TreeNode(5);
        head1.right.left = new TreeNode(6);
        head1.right.right = new TreeNode(7);
        util.PrintTree.printTree(head1);
        //morris序 1 2 4 2 5 1 3 6 3 7
        morrisPre(head1);//先序 1 2 4 5 3 6 7
        System.out.println("======");
        morrisIn(head1); //中序 4 2 5 1 6 3 7
        System.out.println("=======");
        morrisPos(head1);//后序  4 5 2 6 7 3 1
        System.out.println("是不是BST:"+isBSTwithMorris(head1));
        TreeNode head2 = new TreeNode(5);
        head2.left = new TreeNode(3);
        head2.left.left = new TreeNode(2);
        head2.left.right = new TreeNode(4);
        head2.left.left.left = new TreeNode(1);
        head2.right = new TreeNode(7);
        head2.right.left = new TreeNode(6);
        head2.right.right = new TreeNode(8);
        util.PrintTree.printTree(head2);
        System.out.println("是不是BST:"+isBSTwithMorris(head2));
    }

}
