package SwordOffer;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName robortFanWei_13.java
 * @Description   机器人运动范围
 * 地上有一个 m 行和 n 列的方格。 1≤n,m≤100
 * 一个机器人从坐标 (0, 0) 的格子开始移动，每一次只能向左右上下四个方向移动一格，但是不能进入行坐标和列坐标的数位之和大于 k 的格子。
 * 例如，当 k 为 18 时，机器人能够进入方格 (35,37)，因为 3+5+3+7=18。
 * 但是，它不能进入方格 (35,38)，因为 3+5+3+8=19。
 * 请问该机器人能够达到多少个格子？
 *
 *思路：
 * 本体与12题类似
 * 使用深度优先搜索（Depth First Search，DFS）方法进行求解。
 * 回溯是深度优先搜索的一种特例，它在一次搜索过程中需要设置一些本次搜索过程的局部状态，并在本次搜索结束之后清除状态。
 * 而普通的深度优先搜索并不需要使用这些局部状态，虽然还是有可能设置一些全局状态。
 *
 * 一个隐藏的优化：我们在搜索的过程中搜索方向可以缩减为向右和向下，而不必再向上和向左进行搜索
 * 具体分析见：https://leetcode-cn.com/problems/ji-qi-ren-de-yun-dong-fan-wei-lcof/solution/ji-qi-ren-de-yun-dong-fan-wei-by-leetcode-solution/
 * @createTime 2021年08月18日 14:39:00
 */
public class robortFanWei_13 {
    //棋盘的行列
    private static int m, n;
    //记录是否被遍历过
    boolean[][] visited;

    //首先写下本题比较特殊的函数  即行坐标和列坐标的数位之和
    private static int shuWeiHe(int i, int j) {
        int sum = 0;
        while (i != 0) {
            sum += i % 10;
            i /= 10;
        }
        while (j != 0) {
            sum += j % 10;
            j /= 10;
        }
        return sum;
    }

    //返回到达的格子数
    public int robortFanWeiWithDFS(int m, int n, int k) {
        this.m = m;
        this.n = n;
        visited = new boolean[m][n];
        return dfs(0, 0, k);
    }

    //深度优先
    public int dfs(int i, int j, int k) {
        //停止条件
        if (
                /**注意：visited判断一定要写在ij越界判断后面，不然越界再去判断visited会导致数组越界 **/
                i >= m || j >= n  //到达或者超过边界
                || visited[i][j] == true  //这个格子被访问过
                || shuWeiHe(i,j) > k  //行坐标和列坐标的数位之和>k
        ) {
            return 0;
        }
        //到达这里  先标记访问过
        visited[i][j] = true;
        // 沿着当前格子的右边和下边继续访问
        return 1 + dfs(i + 1, j, k) + dfs(i, j + 1, k);
    }


    // 通常利用队列实现广度优先遍历。queue 为空。代表已遍历完所有可达解。
    public int robortFanWeiWithBFS(int m, int n, int k) {
        boolean[][] visited = new boolean[m][n];
        int result = 0;
        //创建一个队列，保存的是访问到的格子坐标，是个二维数组
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0});//从（0，0）开始  加到队尾
        while (queue.size() > 0) {
            int[] x = queue.poll();
            int i = x[0];
            int j = x[1];
            if (i >= m || j >= n || visited[i][j] || shuWeiHe(i, j) > k) {
                continue;
            }
            visited[i][j] = true;
            result++;
            //把当前格子下边格子的坐标加入到队列中
            queue.add(new int[]{i + 1, j});
            //把当前格子右边格子的坐标加入到队列中
            queue.add(new int[]{i, j + 1});

        }
        return result;
    }

}
