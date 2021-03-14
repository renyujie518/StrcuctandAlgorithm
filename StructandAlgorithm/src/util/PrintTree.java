package util;

import Tree.TreeNode;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName PrintTree.java
 * @Description 这是左神提供的打印数结构的一个工具，虽然不能直接看出来，但可以在纸上写出来然后顺时针旋转90度即可
 *注释说明：
 * 1.两个符号之间夹的是具体的值
 * 2.H_H代表根节点,v_v代表该数的父结点在该数的左方偏下(以该数的角度看) ^_^ 代表父节点在该数的左方偏上（以该数的角度看）
 * 3.别忘了顺时针旋转90度再看
 * @createTime 2021年02月28日 20:48:00
 */
public class PrintTree {

    public static void printTree(TreeNode head) {
        System.out.println("Binary Tree:");
        printInOrder(head, 0, "H", 17);
        System.out.println();
    }

    public static void printInOrder(TreeNode head, int height, String to, int len) {
        if (head == null) {
            return;
        }
        printInOrder(head.right, height + 1, "v", len);
        String val = to + head.val + to;
        int lenM = val.length();
        int lenL = (len - lenM) / 2;
        int lenR = len - lenM - lenL;
        val = getSpace(lenL) + val + getSpace(lenR);
        System.out.println(getSpace(height * len) + val);
        printInOrder(head.left, height + 1, "^", len);
    }

    public static String getSpace(int num) {
        String space = " ";
        StringBuffer buf = new StringBuffer("");
        for (int i = 0; i < num; i++) {
            buf.append(space);
        }
        return buf.toString();
    }

}
