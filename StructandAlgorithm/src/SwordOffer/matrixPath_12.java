package SwordOffer;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName matrixPath_12.java
 * @Description 矩阵中的路径
 * 判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。
 * 路径可以从矩阵中的任意一个格子开始，每一步可以在矩阵中向上下左右(4种方向)移动一个格子。
 * 如果一条路径经过了矩阵中的某一个格子，则该路径不能再进入该格子。
 *
 * 解题思路：
 * 使用回溯法（backtracking）进行求解，它是一种暴力搜索方法，通过搜索所有可能的结果来求解问题。
 * 回溯法在一次搜索结束时需要进行回溯（回退），将这一次搜索过程中设置的状态进行清除，从而开始一次新的搜索过程。
 * 例如下图示例中，从 f 开始，下一步有 4 种搜索可能，如果先搜索 b，需要将 b 标记为已经使用，防止重复使用。
 * 在这一次搜索结束之后，需要将 b 的已经使用状态清除，并搜索 c。
 * @createTime 2021年08月18日 13:59:00
 */
public class matrixPath_12 {

    public static boolean matrixPathExit(char[][] board, String word) {
        char[] words = word.toCharArray();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                //从(i,j)这个点开始
                if (trackback(board, words, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    //index表示的是查找到字符串words的第几个字符
    public static boolean trackback(char[][] board, char[] words, int i, int j, int index) {
        //终止条件是越界
        //同时 本题放入目的是寻找是否有这样一个路径，如果这个words[index]不等于board[i][j]，说明验证这个坐标路径是走不通的，直接返回false
        if (i >= board.length || i < 0 || j >= board[0].length || j < 0 || board[i][j] != words[index]) {
            return false;
        }
        //如果word的每个字符都查找完了，直接返回true
        if (index == words.length - 1){
            return true;
        }

        //把当前坐标的值保存下来，为了最后复原
        char temp = board[i][j];
        //修改当前值得坐标(相当于用过了，这样下次再走到这会触发board[i][j] != words[index])
        board[i][j] = '.';
        //走递归，沿着当前坐标的上下左右4个方向查找
        boolean result = trackback(board, words, i - 1, j, index+1) ||  //上
                         trackback(board, words, i+1 , j, index+1) ||   //下
                         trackback(board, words, i, j-1, index+1) ||    //左
                         trackback(board, words, i, j+1, index+1) ;     //右
        //递归之后再把当前的撤销选择
        board[i][j] = temp;
        return result;
    }


}
