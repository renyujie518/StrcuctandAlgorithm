package Tree;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName IsBalenceTree.java
 * @Description 平衡二叉树
 * 它是一棵空树或它的左右两个子树的高度差的绝对值不超过1，并且左右两个子树都是一棵平衡二叉树
 * 可以使用左神的df  左/右树需要提供的信息  树是平衡吗  高度
 *
 * @createTime 2021年03月06日 14:30:00
 */
public class IsBalenceTree {
    public static void main(String[] args) {
        TreeNode head1 = new TreeNode(1);
        head1.left = new TreeNode(2);
        head1.right = new TreeNode(3);
        head1.left.left = new TreeNode(4);
        head1.left.right = new TreeNode(5);
        head1.right.left = new TreeNode(6);
        head1.right.right = new TreeNode(7);
        util.PrintTree.printTree(head1);
        System.out.println("是平衡二叉树？"+isBalenceTree(head1));

        TreeNode head2 = new TreeNode(5);
        head2.left = new TreeNode(3);
        head2.left.left = new TreeNode(2);
        head2.left.right = new TreeNode(4);
        head2.left.left.left = new TreeNode(1);
        head2.right = new TreeNode(7);
        head2.right.left = new TreeNode(6);
        head2.right.right = new TreeNode(8);
        util.PrintTree.printTree(head2);
        System.out.println("是平衡二叉树？"+isBalenceTree(head2));
    }

    public static  boolean isBalenceTree(TreeNode root){
        return process(root).isBalence;

    }

    public static class BalenceDatatype{
        public boolean isBalence;
        public int height;

        public BalenceDatatype(boolean isbalence,int h){
            this.isBalence = isbalence;
            this.height = h;
        }
    }


    public static BalenceDatatype process(TreeNode root){
        if (root == null){
            return new BalenceDatatype(true, 0);
        }
        //返回左右树的信息
        BalenceDatatype leftData = process(root.left);
        BalenceDatatype rightData = process(root.right);

        //这个函数是要返回BalenceDatatype，要递归，也得满足有这两个变量
        boolean isBalence = leftData.isBalence && rightData.isBalence && (Math.abs((rightData.height)- leftData.height)<2);
        int height  = Math.max(leftData.height, rightData.height)+1;
        return new BalenceDatatype(isBalence, height);
    }
}
