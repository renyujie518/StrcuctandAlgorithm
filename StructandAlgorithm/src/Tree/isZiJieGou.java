package Tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName isZiJieGou.java
 * @Description
 * 输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)
 *
 * B是A的子结构， 即 A中有出现和B相同的结构和节点值。
 *
 * 例如:
 * 给定的树 A:
 *
 *             3
 *            / \
 *          4    5
 *         / \
 *        1  2
 * 给定的树 B：
 *
 *       4
 *      /
 *     1
 * 返回 true，因为 B 与 A 的一个子树拥有相同的结构和节点值。
 *
 *  剑指26
 *
 *
 *  总体思路
 树 B 是树 A 的子结构，则必满足以下三种情况之一：
 *  以 节点 A 为根节点的子树 包含树 B
 *  树 B 是 树 A 左子树 的子结构
 *  树 B 是 树 A 右子树 的子结构
 *
 * 先遍历树A，如果遍历到和B节点值相同的节点，进入helper方法判断接下来的节点是否都相同
 * 节点都相同返回True；不相同返回False，并且继续遍历树A找下一个相同的节点
 * 如果遍历完了A还没有返回过True，说明B不是A的子结构，返回False
 *
 * helper方法：用于判断从A的子树是否有和B相同的部分
 * 正常BFS步骤，用队列存储树A和B相对应的节点nodeA, nodeB
 * 因为入队的条件是只要树B节点存在就入队，如果A已经没有了相应节点返回False：if not A
 * 如果A和B对应节点值不相同也返回False：if nodeA.val != nodeB.val
 * 如果遍历完了B也没有返回过False，说明B是A的子结构，返回True
 *
 * 链接：https://leetcode-cn.com/problems/shu-de-zi-jie-gou-lcof/solution/jian-zhi-offer-26-shu-de-zi-jie-gou-die-0qjeh/
 *
 * @createTime 2021年08月15日 23:35:00
 */
public class isZiJieGou {
    public static boolean isSubStructureWithoutRecursion(TreeNode A, TreeNode B) {
        if (B == null|| A == null) {
            return false;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(A);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.val == B.val) {
                if(helper1(node, B)){//如果B还是node（node是从A的队列中弹出的，所以node是A的子节点）的子树
                    return true;//值同而且B还是这个节点的子树
                }
            }
            if(node.left != null){
                queue.offer(node.left);
            }
            if(node.right != null){
                queue.offer(node.right);
            }

        }
        return false;
    }

    ///：用于判断B是否是A的子树(BFS)
    public static boolean helper1(TreeNode nodeA, TreeNode nodeB){
        Queue<TreeNode> queueA = new LinkedList<>();
        Queue<TreeNode> queueB = new LinkedList<>();
        queueA.offer(nodeA);
        queueB.offer(nodeB);

        while(!queueB.isEmpty()){//如果遍历完了B也没有返回过False，说明B是A的子结构，返回True
            nodeA = queueA.poll();//第一次的时候只弹出了传入的nodeA
            nodeB = queueB.poll();
            if(nodeA == null || nodeA.val != nodeB.val){
                //只要树B节点存在就入队，如果A已经没有了相应节点返回False,A和B对应节点值不相同也返回False
                //为什么B不用==null的判断  即nodeA == null || nodeB == null||nodeA.val != nodeB.val
                ////B是空树，肯定算A的子树
                //nodeA==null就相当于BFS遍历后都没A的节点可判断了，那肯定B不是A的子树
                return false;
            }
            if(nodeB.left != null){//只遍历B的左右树
                queueA.offer(nodeA.left);
                queueB.offer(nodeB.left);
            }
            if(nodeB.right != null){
                queueA.offer(nodeA.right);
                queueB.offer(nodeB.right);
            }
        }
        return true;
    }




    //总体思路和迭代差不多，也是先在树A中找到和B节点值相同的节点，再通过helper方法判断B还是这个节点的子树；
    //只不过把BFS换成了DFS
    public static boolean isSubStructureWithRecursion(TreeNode A, TreeNode B) {
        if (B == null|| A == null) {
            return false;
        }

        //什么是完美子结构  当前值不仅要相同  B左树也是A左树的子树  B右树也是A右树的子树
        //B节点就是A节点的子树
        if (helper2(A, B)) {
            return true;
        }

        //其次  B在A左树中找到子结构和B在A的右树找到子结构  也算
        return isSubStructureWithRecursion(A.left, B) || isSubStructureWithRecursion(A.right, B);

    }

    //用于判断B是否是A的子树(DFS)
    public static boolean helper2(TreeNode nodeA, TreeNode nodeB){
        if(nodeB == null){//B是空树，肯定算A的子树
            return true;
        }

        if(nodeA == null || nodeA.val != nodeB.val){
            return false;  //见上面的分析
        }

        //递归调用  上面只是判断当前节点  现在把左右树都判断下 B左树是否是A左树的子树  B右树是否是A右树的子树
        return helper2(nodeA.left, nodeB.left) && helper2(nodeA.right, nodeB.right);
    }


    public static void main(String[] args) {

        TreeNode A = new TreeNode(3);
        A.left = new TreeNode(4);
        A.right = new TreeNode(5);
        A.left.left = new TreeNode(1);
        A.left.right = new TreeNode(2);
        System.out.println("A树");
        util.PrintTree.printTree(A);
        TreeNode B = new TreeNode(4);
        B.left = new TreeNode(1);
        System.out.println("B树");
        util.PrintTree.printTree(B);
        System.out.println(isSubStructureWithoutRecursion(A, B));
        System.out.println(isSubStructureWithRecursion(A, B));
        System.out.println("============");
        TreeNode C = new TreeNode(4);
        C.left = new TreeNode(9);
        System.out.println("C树");
        util.PrintTree.printTree(C);
        System.out.println(isSubStructureWithoutRecursion(A, C));
        System.out.println(isSubStructureWithRecursion(A, C));


    }


}
