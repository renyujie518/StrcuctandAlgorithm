package Tree;

import javax.swing.table.TableRowSorter;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName MaxDistanceInTree.java
 * @Description
 * 叉树节点间的最大距离问题
 * 从二叉树的节点a出发，可以向上或者向下走，但沿途的节点只能经过一次，到达节点b时路径上的节点个数叫作a到b的距离，
 * 那么二叉树任何两个节点之间都有距离，求整棵树上的最大距离
 *
 * 要分头节点x参与与否两种情况
 * x不参与:最大距离在左树或者右树
 * x参与：左树高+右树高+1（头节点）
 * 两者再取最大值
 * @createTime 2021年03月25日 13:26:00
 */
public class MaxDistanceInTree {
    //根据上面的分析，需要左右树需要提供两个变量  最大距离 高度
    public static class Info{
        public int maxDistance;
        public int height;
        //构造函数
        public Info(int maxDistance,int height){
            this.maxDistance = maxDistance;
            this.height = height;
        }
    }

    //返回以head为头的整棵树的两棵树信息
    public static Info process(TreeNode head){
        if (head == null){
            return new Info(0, 0);
        }
        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);
        //提取信息
        int p1 = leftInfo.maxDistance;
        int p2 = rightInfo.maxDistance;
        int p3 = leftInfo.height+rightInfo.height+1;
        int maxDistance = Math.max(p3, Math.max(p1, p2));
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        return new Info(maxDistance, height);
    }

    //主函数  需要的只是最大距离
    public static int MaxDistanceInTree(TreeNode head){
        return process(head).maxDistance;
    }

    public static void main(String[] args) {
        TreeNode head1 = new TreeNode(1);
        head1.left = new TreeNode(2);
        head1.right = new TreeNode(3);
        head1.left.left = new TreeNode(4);
        head1.left.right = new TreeNode(5);
        head1.right.left = new TreeNode(6);
        head1.right.right = new TreeNode(7);
        head1.left.left.left = new TreeNode(8);
        head1.right.left.right = new TreeNode(9);
        System.out.println(MaxDistanceInTree(head1));

        TreeNode head2 = new TreeNode(1);
        head2.left = new TreeNode(2);
        head2.right = new TreeNode(3);
        head2.right.left = new TreeNode(4);
        head2.right.right = new TreeNode(5);
        head2.right.left.left = new TreeNode(6);
        head2.right.right.right = new TreeNode(7);
        head2.right.left.left.left = new TreeNode(8);
        head2.right.right.right.right = new TreeNode(9);
        System.out.println(MaxDistanceInTree(head2));

    }

    /**
     * 路径 被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列
     * 。同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，且不一定经过根节点。
     * 路径和 是路径中各节点值的总和(而不是上面的个数和)
     * 给你一个二叉树的根节点 root ，返回其 最大路径和 。
     *
     * 思路：
     * 如果当前处在root节点，左右节点应该告诉我们什么信息才能得到答案？
     * 根据题中对路径的定义，对于此题我们来回答以上问题。
     * 当我们遍历到树中某个节点时，我希望左子节点告诉我，在左子树中，以左子节点为开始（端点）的路径和最大为多少
     * 同理我也希望右子节点告诉我类似的信息。
     * 如果有了以上信息，再来思考最后一个问题：有了这个信息如何得到答案？
     *
     * 显然，对于当前节点有四个选择：
     * 我自己就是一条路径
     * 只跟左子节点合并成一条路径
     * 只跟右子节点合并成一条路径
     * 以自己为桥梁，跟左、右子节点合并成一条路径
     */

    int pathSum = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        dfs(root);
        return pathSum;
    }
    // dfs 返回以该节点为端点的最大路径和
    public int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int left = dfs(node.left);
        int right = dfs(node.right);
        // 当前节点有三个选择：
        // 1）独立成线，直接返回自己的值
        // 2）跟左子节点合成一条路径
        // 3）跟右子节点合成一条路径
        int selfIsRootRes = Math.max(node.val, node.val + Math.max(left, right));
        //以自己为桥梁，跟左、右子节点合并成一条路径
        //我们在递归求解的时候，第四种情况是不能作为递归的返回值的，
        //因为它不符合我们对递归所期望返回值的定义（因为此时该子节点并不是拥有最大路径和路径的起点（端点）），
        //但它也是一个可能的解，所以我们用一个全局变量记录上面四种值的最大值，递归结束后，该变量就是答案。
        pathSum = Math.max(pathSum, Math.max(selfIsRootRes, node.val + left + right));
        return selfIsRootRes;
    }


}
