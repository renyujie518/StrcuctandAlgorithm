package Tree;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName IsFullTree.java
 * @Description 满二叉树
 * 最大深度为l  总结点数 m   m = 2^l - 1
 * 更好的办法是用左神的树的动态规划 分解为对每棵树都要统计高度和子节点个数(先向左数要，再向右树要)
 * @createTime 2021年03月06日 14:31:00
 */
public class IsFullTree {

    public static void main(String[] args) {
        TreeNode head1 = new TreeNode(1);
        head1.left = new TreeNode(2);
        head1.right = new TreeNode(3);
        head1.left.left = new TreeNode(4);
        head1.left.right = new TreeNode(5);
        head1.right.left = new TreeNode(6);
        head1.right.right = new TreeNode(7);
        util.PrintTree.printTree(head1);
        System.out.println("该树是满二叉树吗： "+IsFulltree(head1));


        TreeNode head2 = new TreeNode(5);
        head2.left = new TreeNode(3);
        head2.left.left = new TreeNode(2);
        head2.left.right = new TreeNode(4);
        head2.left.left.left = new TreeNode(1);
        head2.right = new TreeNode(7);
        head2.right.left = new TreeNode(6);
        head2.right.right = new TreeNode(8);
        util.PrintTree.printTree(head2);
        System.out.println("该树是满二叉树吗： "+IsFulltree(head2));

    }

    public static boolean IsFulltree(TreeNode root){
        if(root == null){
            return true;
        }
        FullReturndata data = process(root);  //收集两个树的公共信息，这里是数高度和节点数
        //结合题意去判断
        return data.nodesNum == (Math.pow(2,data.height ) - 1);

    }
    //提出递归中所需的公共部分
    public static class FullReturndata{
        public  int height;
        public int nodesNum;
        //设定一个构造函数
        public FullReturndata(int h,int n){
            this.height = h;
            this.nodesNum = n;
        }
    }
    //递归
    public static FullReturndata process(TreeNode root){
        if (root == null){
            return new FullReturndata(0, 0);
        }
        FullReturndata leftData = process(root.left);
        FullReturndata rightData = process(root.right);
        //加工这个递归里的信息
        int height = Math.max(leftData.height, rightData.height)+1;  //加1是别忘了最头上的root
        int nodesNum = leftData.nodesNum + rightData.nodesNum + 1;

        return new FullReturndata(height, nodesNum);

    }
}
