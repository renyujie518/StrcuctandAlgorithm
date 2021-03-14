package Tree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

import static util.PrintTree.printTree;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName FindTreeMaxWidth.java
 * @Description 寻找二叉树的最大宽度  以宽度优先为基础（用到队列），弹出的时候统计num，再加上哈希表  比如
 * *  *                     1
 *  *  *                /        \
 *  *  *              2           3
 *  *  *            /   \       /   \
 *  *  *           4     5     6     7
 *  *  *         /  \   /  \  /  \  /  \
 *  *  *        n   n  n   n n   n n   n     （n代表null）
 *
 *  其max宽度就为4
 *
 * @createTime 2021年03月01日 13:13:00
 */
public class FindTreeMaxWidth {
    public static  int FindTreeMaxWidth(TreeNode root){
        if (root == null){
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);  //初始化队列  先把头节点放入队列
        HashMap<TreeNode, Integer> levelMap = new HashMap<>();  //key为节点  value为当前层数
        levelMap.put(root, 1);  //初始化哈希表  代表根节点在第一层

        int currLevel = 1;  //当前的层数  已经初始化过队列和哈希表了，从第1层开始
        int currLevleNodesNum =  0;  //当前层节点个数（root还没进队列呢，还么发现，所以是0）
        int max = 0;  //最大宽度初始化为0

        while (!queue.isEmpty()){
            TreeNode curr = queue.poll();  //弹出（先弹出，出来的时候才有统计和左/右孩子进队列的操作）
            Integer currNodeLevel = levelMap.get(curr);  //根据哈希表获得当前弹出的这个节点的层数
            if (currNodeLevel == currLevel){  //如果当前弹出的这个节点的层数还是在当前层，说明宽度优先，这一层还没弹完，那么统计就还么结束
                currLevleNodesNum++;
            }else {//进这个else说明这一层弹完了，也统计完了，所以要更新一些变量
                max = Math.max(max,currLevleNodesNum); // 统计节点数，保留最大的
                currLevel++;  //这一层弹完了，进入下一层
                currLevleNodesNum = 1 ;//这一层弹完了也统计完了，进入下一层,更新统计节点为1
                //为什么统计节点为1，是因为进入到这个else代表这一层弹完了，已经进入下一层了，新的层发现了节点，就是41行poll的这个
            }

            //深度遍历为基础 先左后右，但是别忘了在进queue的时候更新哈希表,而且是先跟新哈希表    ！！这个顺序一定不能错
            //每个节点在弹出的时候知道他是哪一层，进队列的时候保证哈希表已经记录该节点的层数了（所以先更新哈希表）
            if (curr.left != null){
                levelMap.put(curr.left,currNodeLevel+1);
                //注意，这里把currNodeLevel看做稳定变量，这个变量是针对每个节点，弹出的时候从hashMap中取出来用作判定这个节点还在这一层吗
                //在往下层延伸的时候（queue添加的时候，这个变量用于将即将加入队列的节点在hashMap中打上标记，【key=节点的左树.value=下一层】
                //那为什么不选currLevel这个量呢，，他是个标杆，是用来判定当前弹出的这个节点的还是不是在当前层，只在else分支才会变，而我需要的是一个记录好的稳定变量
                queue.add(curr.left);
            }
            if (curr.right != null){
                levelMap.put(curr.right,currNodeLevel+1);
                queue.add(curr.right);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        TreeNode head1 = new TreeNode(1);
        head1.left = new TreeNode(2);
        head1.right = new TreeNode(3);
        head1.left.left = new TreeNode(4);
        head1.right.left = new TreeNode(5);
        head1.right.right = new TreeNode(6);
        head1.left.left.right = new TreeNode(7);
        printTree(head1);
        System.out.println("该树的最大宽度是： "+FindTreeMaxWidth(head1));
        System.out.println("===================");

        TreeNode head2 = new TreeNode(1);
        head2.left = new TreeNode(1);
        head2.right = new TreeNode(1);
        head2.left.left = new TreeNode(1);
        head2.right.left = new TreeNode(1);
        head2.right.right = new TreeNode(1);
        head2.left.left.right = new TreeNode(1);
        printTree(head2);
        System.out.println("该树的最大宽度是： "+FindTreeMaxWidth(head2));
        
    }
}
