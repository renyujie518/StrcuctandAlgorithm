package Tree;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName findMaxHeight.java
 * @Description 寻找二叉树最大深度
 * @createTime 2021年03月07日 23:11:00
 */
public class findMaxHeight {
    public int maxDepth(TreeNode root) {
        if(root == null){
            return 0;
        }
        int leftHeight = maxDepth(root.left);
        int rightHeight = maxDepth(root.right);
        return Math.max(leftHeight,rightHeight)+1;
    }


}
