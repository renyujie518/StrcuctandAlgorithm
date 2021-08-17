package SwordOffer;

import Tree.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName rebuildTree.java
 * @Description 重建二叉树
 * 根据二叉树的前序遍历和中序遍历的结果，重建出该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 * 前：中左右
 * 中：左中右
 * <p>
 * 前序遍历的第一个值为根节点的值，使用这个值将中序遍历结果分成两部分，左部分为树的左子树中序遍历结果，右部分为树的右子树中序遍历的结果
 * @createTime 2021年08月17日 16:51:00
 */
public class rebuildTree_07 {
    //中序入map,前序遍历
    private static Map<Integer, Integer> inOrderMap = new HashMap<Integer, Integer>();

    public static TreeNode rebuildTree(int[] pre, int[] in) {
        for (int i = 0; i < in.length; i++) {
            inOrderMap.put(in[i], i);  // key:中序中对应的值  value:对应的index
        }
        return process(pre, 0, pre.length - 1, 0);
    }

    public static TreeNode process(int[] pre, int preL, int preR, int inL) {
        //终止条件
        if (preL > preR) {
            return null;
        }
        TreeNode root = new TreeNode(pre[preL]);
        Integer rootIndex = inOrderMap.get(root);
        int leftTreeSize = rootIndex - inL;
        root.left = process(pre, preL + 1, preL + leftTreeSize, inL);
        root.right = process(pre, preL + leftTreeSize + 1, preR, inL + leftTreeSize + 1);
        return root;
    }


}
