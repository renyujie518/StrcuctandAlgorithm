package Tree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName tongGouTree.java
 * @Description  寻找重复子树
 *
 * 给定一棵二叉树，返回所有重复的子树。对于同一类的重复子树，你只需要返回其中任意一棵的根结点即可。
 *
 * 理解题意：
 * 题目给出了一棵二叉树
 * 返回树中出现的重复子树的根节点
 * 如果有棵子树重复多次，只需要在几颗重复的子树中，选择其中一颗的根节点返回
 * 最后将所有重复子树的根节点用列表返回
 *
 * 整体思路：
 * 需要知道当前根节点子树的结构，还有其他根节点子树的结构，所以我使用中序  中 左 右序列化：
 *    1
 *   / \
 *  2   3
 *     / \
 *    4   5
 *例如上面这棵树序列化结果为 1,2,#,#,3,4,#,#,5,#,#。每棵不同子树的序列化结果都是唯一的。
 *
 * 用 HashMap 来存储所有的子树以及它出现的次数，用 LinkedList 来存储重复子树的根节点
 * 用 HashMap 的 getOrDefault 方法可以获取是否存在当前子树，不存在就新增这个子树到HashMap中
 * 如果存在相同子树且这颗子树只有一颗，那么返回列表就新增当前的根节点
 *
 * @createTime 2021年08月15日 23:07:00
 */
public class tongGouTree {
    // 记录所有的子树和出现的次数
    static HashMap<String, Integer> countMap = new HashMap<>();
    // 记录重复子树的根节点
    static LinkedList<TreeNode> res = new LinkedList<>();


    //使用深度优先搜索，其中递归函数返回当前子树的序列化结果。
    //把每个节点开始的子树序列化结果保存在 map 中，然后判断是否存在重复的子树
    public static List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        collect(root);
        return res;
    }

    public static String collect(TreeNode node) {
        if (node == null) {
            return "#";
        }
        //序列化格式的拼接（里面包含着中序的递归）
        String serial = node.val + "," + collect(node.left) + "," + collect(node.right);
        //getOrDefault() 方法获取指定 key 对应对 value，如果找不到 key ，则返回设置的默认值。这里默认返回0
        //这里相当于累加  每次遇到先+1,再把之前的次数加上去
        countMap.put(serial, 1 + countMap.getOrDefault(serial, 0));
        if (countMap.get(serial) == 2){
            //重复  即次数为2
            res.add(node);
        }
        return serial;
    }


}
