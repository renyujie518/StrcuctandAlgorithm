package Tree;

import java.util.LinkedList;
import java.util.Stack;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName IsBinarySearchTree.java
 * @Description 判断一棵树是否是二叉搜索树（对于每个子树，左边的小，右边的大）
 *
 * 思路 ：中序遍历（左中右）是绝对升序（不能有相等） 就是
 * @createTime 2021年03月01日 15:58:00
 */
public class IsSearchTree {
    public static void main(String[] args) {
        TreeNode head1 = new TreeNode(1);
        head1.left = new TreeNode(2);
        head1.right = new TreeNode(3);
        head1.left.left = new TreeNode(4);
        head1.left.right = new TreeNode(5);
        head1.right.left = new TreeNode(6);
        head1.right.right = new TreeNode(7);
        util.PrintTree.printTree(head1);
        System.out.println("该树是搜索二叉树吗： "+IsBstRecursive(head1));
        System.out.println("该树是搜索二叉树吗： "+IsBstUnrecursive(head1));
        System.out.println("该树是搜索二叉树吗： "+IsSearchWithDP(head1));

        TreeNode head2 = new TreeNode(5);
        head2.left = new TreeNode(3);
        head2.left.left = new TreeNode(2);
        head2.left.right = new TreeNode(4);
        head2.left.left.left = new TreeNode(1);
        head2.right = new TreeNode(7);
        head2.right.left = new TreeNode(6);
        head2.right.right = new TreeNode(8);
        util.PrintTree.printTree(head2);
        System.out.println("该树是搜索二叉树吗： "+IsBstRecursive(head2));
        System.out.println("该树是搜索二叉树吗： "+IsBstUnrecursive(head2));
        System.out.println("该树是搜索二叉树吗： "+IsSearchWithDP(head2));

    }

    //递归方式判断(需要一个全局变量，用于记录上一次的值)
    public static long preValue = Integer.MIN_VALUE;
    public static boolean IsBstRecursive(TreeNode head){

//        if (root == null){
//            return true;
//        }
//        boolean isLeftBst = IsBstRecursive(root.left);
//        if (!isLeftBst){ //左子树不是搜索二叉树，直接返回false
//            return false;
//        }
        //这时候的preValue是左子树的最后一个节点的val，且左数是搜索二叉树
//        if (root.val <= preValue){ //必须严格递增，
//            return false;
//        }else {
//            preValue = root.val;  //这个else代表判断到此节点 是严格递增的，这时候更新这个全局变量为当前值，方便右树判断
//        }
//        //由于47行的判断，保证了执行到这里的时候左子树是搜索二叉树而且全局变量此时已经跟新了，只要右子树是搜索二叉树即可
//        return IsBstRecursive(root.right);
        if (head == null) {
            return true;
        }
        LinkedList<TreeNode> inOrderList = new LinkedList<>();
        process(head, inOrderList);  //得到中序遍历后的list
        int pre = Integer.MIN_VALUE;
        for (TreeNode cur : inOrderList) {
            if (cur.val <= pre) {  //如果不是严格递增
                return false;
            }
            pre = cur.val;
        }
        return true;  //以上检验都通过了，就是true
    }

    //非递归方式判断，其实就是把中序遍历稍微改改
    //每颗子树 左边界进栈，依次弹出的过程中打印（这里改为判断），一旦弹出，对弹出节点的右树
    //只要来到一个节点有左边界就一路压进去，压到底了就弹出一个栈里的节点（弹出就打印），把弹出节点的右树周而复始
    public static boolean IsBstUnrecursive(TreeNode root){
        if(root == null){
            return true;
        }
        long pre = Integer.MIN_VALUE;
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || root != null){
            if (root != null){
                stack.push(root);
                root = root.left;
            }else {
                root = stack.pop();
                //
                if (pre >= root.val){
                    return false;
                }else {
                    pre = root.val;
                }
                //
                root = root.right;
            }
        }//这个while都执行完了还没返回false就一定是搜索二叉树
        return true;
    }
    public static void process(TreeNode node, LinkedList<TreeNode> inOrderList) {
        if (node == null) {
            return;
        }
        process(node.left, inOrderList);
        inOrderList.add(node);   //得到中序遍历的顺序放到list里
        process(node.right, inOrderList);
    }


    //以下是用左神的数动态规划去解决是否是搜索二叉树  提取左右树需要给到的信息 ：
    //该树的最小值，该树的最大值，（左树的max <root,右树的min>root） 是否是搜索
    public static class SearchDataType{
        boolean isSearch;
        int min;
        int max;

        public SearchDataType(boolean isSearch, int max, int min){
            this.isSearch = isSearch;
            this.max = max;
            this.min = min;
        }
    }

    public static SearchDataType process(TreeNode root){
        if (root == null){
            return null;
        }
        SearchDataType leftData = process(root.left);
        SearchDataType rightData = process(root.right);
        int min = root.val;  //整树最小的数
        int max = root.val;
        if (leftData != null){   //说明左树有东西
            min = Math.min(min, leftData.min);  //自己值当前值和 左树中已找到的最小是pick一下
            max = Math.max(max, leftData.max);
        }
        if (rightData != null){
            min = Math.min(min, rightData.min);
            max = Math.max(max, rightData.max);
        }
        boolean isSearch = true;
        if (leftData != null && (!leftData.isSearch  || leftData.max >= root.val)){
            //判断违规情况 左树的max >=root,右树的min<=root
            isSearch = false;
        }
        if (rightData != null && (!rightData.isSearch || rightData.min <= root.val)){
            isSearch = false;
        }
        return new SearchDataType(isSearch, max, min);

    }

    public static boolean IsSearchWithDP(TreeNode root){
        return process(root).isSearch;

    }
}
