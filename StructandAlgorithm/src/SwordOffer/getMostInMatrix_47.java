package SwordOffer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName getMostInMatrix_47.java
 * @Description 路径最优问题
 * 在一个 m*n 的棋盘的每一个格都放有一个礼物，
 * 每个礼物都有一定价值（大于 0）。
 * 从左上角开始拿礼物，每次向右或向下移动一格，直到右下角结束。给定一个棋盘，求拿到礼物的最大价值。例如，对于如下棋盘
 * 1    10   3    8
 * 12   2    9    6
 * 5    7    4    11
 * 3    7    16   5
 * 礼物的最大价值为 1+12+5+7+7+16+5=53。
 * 应该用动态规划求解，而不是深度优先搜索，深度优先搜索过于复杂，不是最优解。
 * dp[i][j]表示从矩阵的左上角走到坐标（i，j）所能拿到的最大礼物。
 *
 * https://leetcode-cn.com/problems/li-wu-de-zui-da-jie-zhi-lcof/solution/shu-ju-jie-gou-he-suan-fa-dong-tai-gui-h-569o/
 * 如果要走到坐标（i，j），我们可以从坐标（i-1，j）往下走一步，或者从坐标（i，j-1）往右走一步，
 * 到底应该从哪里，我们应该取这两个方向的最大值，所以
 * dp[i][j]=max(dp[i-1][j],dp[i][j-1])+grid[i][j];
 *
 * 那么边界条件是什么呢
 * 如果在左上角，dp[0][0]=grid[0][0]，
 * 如果在最上边一行，因为不能从上面走过来，只能从左边走过来，所以当前值是他左边元素的累加。
 * 如果在最左边一列，因为不能从左边走过来，只能从上边走过来，所以当前值是他上边元素的累加

 *
 *
 * @createTime 2021年08月25日 16:45:00
 */
public class getMostInMatrix_47 {

    public static int getMostInMatrix1(int[][] matrix) {
        //边界条件
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = matrix[0][0];
        //初始化dp的最上面一行，只能是从左边走过来，从左到右累加
        for (int i = 1; i < n; i++) {
            dp[0][i] = dp[0][i-1] + matrix[0][i];
        }
        //初始化dp的最左边一列，只能从上面走过来，从上到下累加
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0] + matrix[i][0];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]) + matrix[i][j];
            }
        }
        return dp[m - 1][n - 1];
    }

    /**
     * 为了方便计算我们还可以把dp的宽和高增加1，也就是dp的最上面一行和最左边一列不存储任何数值，他们都是0，这样是为了减少一些判断
     */
    public static int getMostInMatrix2(int[][] matrix) {
        if (matrix == null || matrix.length == 0)
            return 0;
        int m = matrix.length;
        int n = matrix[0].length;
        //为了方便计算，dp的宽和高都增加了1
        int[][] dp = new int[m + 1][n+1];
        //下面是递推公式的计算
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp[i + 1][j + 1] = Math.max(dp[i + 1][j], dp[i][j + 1]) + matrix[i][j];
            }
        }
        return dp[m][n];
    }

    /**
     * 动态规划代码优化
     * 我们来看一下这个公式
     * dp[i][j]=max(dp[i-1][j],dp[i][j-1])+grid[i][j];
     * 我们发现当前值只和左边和上边的值有关，和其他的无关，比如我们在计算第5行的时候，那么很明显第1，2，3行的对我们来说都是无用的，
     * 所以我们这里可以把二维dp改成一维的，其中dp[i][j-1]可以用dp[j-1]来表示，就是当前元素前面的，
     * dp[i-1][j]可以用dp[j]来表示，就是上边的元素。
     *
     * 改成1维的

     */
    public static int getMostInMatrix3(int[][] matrix) {
        if (matrix == null || matrix.length == 0)
            return 0;
        int m = matrix.length;
        int n = matrix[0].length;
        //数组改成一维的
        int[] dp = new int[n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp[j + 1] = Math.max(dp[j], dp[j + 1]) + matrix[i][j];
            }
        }
        return dp[n];
    }

    /**
     * 空间优化
     * 我们再来仔细看这道题，题中没说不可以修改原来数组的值，所以我还可以使用题中的二维数组来代替二维dp数组
     * 把空间复杂度从O(MN)降到O(1)。
     */
    public static int getMostInMatrix4(int[][] matrix) {
        //边界条件判断
        if (matrix == null || matrix.length == 0)
            return 0;
        int m = matrix.length;
        int n = matrix[0].length;
        //初始化dp的最上面一行，从左到右累加
        for (int i = 1; i < n; i++) {
            matrix[0][i] += matrix[0][i - 1];
        }
        //初始化dp的最左边一列，从上到下累加
        for (int i = 1; i < m; i++) {
            matrix[i][0] += matrix[i - 1][0];
        }

        //下面是递推公式的计算
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                matrix[i][j] = Math.max(matrix[i - 1][j], matrix[i][j - 1]) + matrix[i][j];
            }
        }
        return matrix[m - 1][n - 1];
    }

    /**
     * 递归解法
     * 定义函数private int helper(int[][] grid, int i, int j)，表示从左上角到坐标（i,j）所得到的最大礼物价值，
     * 如果要走到坐标(i,j)，我们可以从上面坐标(i-1,j)走下来，或者从左边坐标(i,j-1)走过来，这两种方式我们选择最大的即可，
     * 这里要注意不能走到矩阵的外面。
     *
     *
     */
    public static int getMostInMatrix5(int[][] matrix) {
        //边界条件判断
        if (matrix == null || matrix.length == 0)
            return 0;
        return helper1(matrix, matrix.length - 1, matrix[0].length - 1);
    }
    private static int helper1(int[][] matrix, int i, int j) {
        if (i < 0 || j < 0)
            return 0;
        return Math.max(helper1(matrix, i - 1, j), helper1(matrix, i, j - 1)) + matrix[i][j];
    }
    /**
     * 不过很遗憾的是，运行超时了，因为这里面包含了大量的重复计算，我们还可以把计算的结果用map存起来，下次用的时候查看map中是否有，
     * 如果有就直接从map中取，如果没有在计算，最后再反存回map
     */
    public static int getMostInMatrix6(int[][] matrix) {
        //边界条件判断
        if (matrix == null || matrix.length == 0)
            return 0;
        return helper2(matrix, matrix.length - 1, matrix[0].length - 1, new HashMap<>());
    }

    private static int helper2(int[][] matrix, int i, int j, Map<String, Integer> map) {
        if (i < 0 || j < 0) {
            return 0;
        }
        String key = i + "*" + j;
        Integer res = map.getOrDefault(key, -1);
        //如果以前计算过，就从map中取
        if (res != -1) {
            return res;
        }
        //如果之前没有计算过，则进行计算
        res = Math.max(helper2(matrix, i - 1, j, map), helper2(matrix, i, j - 1, map)) + matrix[i][j];
        //把计算的结果还存到map中
        map.put(key, res);
        return res;
    }
}
