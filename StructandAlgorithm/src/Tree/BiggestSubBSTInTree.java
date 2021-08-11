package Tree;
import static util.PrintTree.printTree;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName BiggestSubBSTInTree.java
 * @Description
 * 找到一棵二叉树中， 最大的搜索二叉子树， 返回最大搜索二叉子树的节点个数。
 *
 * 子树：选定一个头节点后下面所有的子节点都要
 *
 * 思路：左神的树递归套路
 * 考虑头节点 x  最大子树与x相关性
 * 可能1：这颗树是否是最大子树与x无关，左树是BiggestSubBST
 * 可能2：这颗树是否是最大子树与x无关，右树是BiggestSubBST
 * 可能3：这颗树是否是最大子树与x有关，即x的整棵树就是答案，但需要确定左（右）树是不是搜索二叉树，左max<x,右min>x
 * 同时注意，为了过程中可以比较（答案要求最大个数）所以信息中要包括maxBSTSize
 *
 * (关于判断一棵树是不是搜索二叉树的公共条件和判断套路可以参见之前的isSearchTree)
 *
 * @createTime 2021年08月09日 22:40:00
 */
public class BiggestSubBSTInTree {

    public static class returnType {
        public TreeNode maxBSTHead;
        public int min;
        public int max;
        public int maxBSTSize;
        public boolean isBST;

        public returnType(TreeNode maxBSTHead, int min, int max, int maxBSTSize, boolean isBST) {
            this.maxBSTHead = maxBSTHead;
            this.min = min;
            this.max = max;
            this.maxBSTSize = maxBSTSize;
            this.isBST = isBST;
        }
    }

    public static TreeNode getMaxBST(TreeNode head) {
        return process(head).maxBSTHead;
    }

    private static returnType process(TreeNode head) {
        if (head == null) {
            return null;
        }
        returnType leftInfo = process(head.left);
        returnType rightInfo = process(head.right);
        //首先处理最容易处理的min max
        int min = head.val;
        int max = head.val;
        if (leftInfo != null) {
            min = Math.min(min, leftInfo.min);
            max = Math.max(max, leftInfo.max);
        }
        if (rightInfo != null) {
            min = Math.min(min, rightInfo.min);
            max = Math.max(max, rightInfo.max);
        }
        //下面开始讨论可能性1，2，3
        int maxBSTSize = 0;
        TreeNode maxBSTHead = null;
        if (leftInfo != null) {  //可能性1
            maxBSTSize = leftInfo.maxBSTSize;
            maxBSTHead = leftInfo.maxBSTHead;
        }
        if (rightInfo != null && rightInfo.maxBSTSize > leftInfo.maxBSTSize) {//可能性2比可能性1好再更新
            maxBSTSize = rightInfo.maxBSTSize;
            maxBSTHead = rightInfo.maxBSTHead;
        }
        boolean isBST = false;
        if (//null也算搜索二叉树  搜索二叉树的定义包括左树是BST，右树也是BST
                ((leftInfo == null) || leftInfo.isBST) && ((rightInfo == null) || (rightInfo.isBST))
        ) {//可能性3即包含x的时候左树和右树同时是BST且连起来，这样才大
            if ((leftInfo == null) || (leftInfo.max< head.val) && ((rightInfo == null) || (rightInfo.min > head.val))){
                //这时候可能性3形成的BST最大
                isBST = true;
                maxBSTHead = head;
                //因为之前在判定BST的时候null一直都算，所以这里在计算size的时候要考虑null
                int leftSize = leftInfo == null ? 0 : leftInfo.maxBSTSize;
                int rightSize = rightInfo == null ? 0 : rightInfo.maxBSTSize;
                maxBSTSize = leftSize + 1 + rightSize;
            }
        }
        return new returnType(maxBSTHead, min, max, maxBSTSize, isBST);
    }

    public static void main(String[] args) {

        TreeNode head = new TreeNode(6);
        head.left = new TreeNode(1);
        head.left.left = new TreeNode(0);
        head.left.right = new TreeNode(3);
        head.right = new TreeNode(12);
        head.right.left = new TreeNode(10);
        head.right.left.left = new TreeNode(4);
        head.right.left.left.left = new TreeNode(2);
        head.right.left.left.right = new TreeNode(5);
        head.right.left.right = new TreeNode(14);
        head.right.left.right.left = new TreeNode(11);
        head.right.left.right.right = new TreeNode(15);
        head.right.right = new TreeNode(13);
        head.right.right.left = new TreeNode(20);
        head.right.right.right = new TreeNode(16);

        printTree(head);
        TreeNode bst = getMaxBST(head);
        printTree(bst);

    }

}
