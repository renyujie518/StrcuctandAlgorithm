package Tree;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName CompleteTreeNodeNumber.java
 * @Description
 * 求完全二叉树节点的个数
 *
 * 完全二叉树：从根往下数，除了最下层外都是全满（都有两个子节点），而最下层所有叶结点都向左边靠拢填满。
 * 完全二叉树是由满二叉树而引出来的，若设二叉树的深度为h，除第 h 层外，其它各层 (1～h-1) 的结点数都达到"最大个数"(即1~h-1层为一个满二叉树)，
 * 第 h 层所有的结点都连续集中在最左边，这就是完全二叉树
 *
 *完全二叉树的特点：叶子结点只能出现在最下层和次下层，且最下层的叶子结点集中在树的左部。
 * 需要注意的是，满二叉树肯定是完全二叉树，而完全二叉树不一定是满二叉树
 *
 *思路：
 * 先求深度，要看该节点的的右树的最左节点有没有到最大深度
 * 到最大深度：左树是高度为n的满二叉树 个数为 2^h -1 +1(root),但是右树还是完全二叉树，+递归求解右树
 * 没到最大深度：左树递归执行  + 但是右树是满的，高度为n-1 个数为 2^(h-1) -1 +1(root)
 *
 *
 * @createTime 2021年08月11日 22:33:00
 */
public class CompleteTreeNodeNumber {


    //node在第level层，h是总的深度（h一直不变）
    //返回  以node为头的的完全二叉树  节点个数是多少
    public static int process(TreeNode node, int level, int h) {
        if (level == h) {
            return 1;  //来到最深了，即到叶子节点了，个数为1
        }
        if (mostLeftLevel(node.right, level + 1) == h) {//右树的最大深度到了h,代表左树是满的
            // 加1是因为node是当前节点，其右树所在的层在下一层（注意mostLeftLevel函数的要求，考虑的点在第level层）
            // 2 ^(h-level)-1+1  （h-level）的原因是过程中的node可能在数中间，计算node到底之间的树高就是（h-level）
            return (1 << (h - level)) + process(node.right, level + 1, h);
        } else {//右树没到最深（而且是最左节点，因为mostLeftLevel是node = node.left走到底） 右树是满的，但是高度-1，递归左树
            return (1 << (h - level-1)) + process(node.left, level + 1, h);
        }
    }

    //如果node在第level层，返回以node为头的子树的最大深度  注意以node为头的子树要保证是完全二叉树
    public static int mostLeftLevel(TreeNode node, int level) {
        while (node != null) {
            level++;
            node = node.left;
        }
        //由于到了null才停，这时候多level++一次，所以-1
        return level - 1;
    }

    //求所有节点
    public static int nodeNum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return process(root, 1, mostLeftLevel(root, 1));
    }

    public static void main(String[] args) {
        TreeNode head = new TreeNode(1);
        head.left = new TreeNode(2);
        head.right = new TreeNode(3);
        head.left.left = new TreeNode(4);
        head.left.right = new TreeNode(5);
        head.right.left = new TreeNode(6);
        System.out.println(nodeNum(head));

    }
}
