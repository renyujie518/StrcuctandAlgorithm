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

}
