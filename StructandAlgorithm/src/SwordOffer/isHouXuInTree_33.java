package SwordOffer;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName isHouXuInTree.java
 * @Description 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。假设输入的数组的任意两个数字都互不相同。
 * 注意  这棵树还是二叉搜索书
 * 二叉搜索树定义： 左子树中所有节点的值 < 根节点的值；右子树中所有节点的值 > 根节点的值；其左、右子树也分别为二叉搜索树。
 *
 * https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-hou-xu-bian-li-xu-lie-lcof/solution/di-gui-he-zhan-liang-chong-fang-shi-jie-jue-zui-ha/
 *
 * 后续 左右中
 * @createTime 2021年08月21日 17:22:00
 */
public class isHouXuInTree_33 {
    public static boolean VerifySquenceOfHouXu(int[] quence) {
        if (quence == null || quence.length == 0) {
            return true;
        }
        return verify(quence, 0, quence.length - 1);
    }


    public static boolean verify(int[] quence, int first, int last) {

        if (last - first <= 1) {
            return true;// 当前区域不合法的时候直接返回true就好
        }
        //后序的最后一个节点是root
        int rootVal = quence[last];
        int currIndex = first;
        while (currIndex < last && quence[currIndex] < rootVal) {//找到左树的终结位置（左树已验证完毕）
            currIndex++;
        }
        //注意这里currIndex在超出条件后由于++的缘故已经在左树的终结位置+1了，所以是右树的第一个位置
        for (int i = currIndex; i <= last - 1; i++) {
            if (quence[i] < rootVal) {//右树一定比rootVal大，一旦出现小的，说明不是
                return false;
            }
        }
        //目前currIndex还指向右树第一个位置
        //左子树也要二叉搜索书  右子树也要二叉搜索书
        return verify(quence, first, currIndex-1) && verify(quence, currIndex , last - 1);
    }

}
