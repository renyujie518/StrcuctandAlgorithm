package facing;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName minLuJinInMatrix.java
 * @Description 给你一个m * n的网格，其中每个单元格不是0（空）就是1（障碍物）。每一步，您都可以在空白单元格中上、下、左、右移动。
 * 如果您 最多 可以消除 k 个障碍物，请找出从左上角 (0, 0) 到右下角 (m-1, n-1) 的最短路径，并返回通过该路径所需的步数。
 * 如果找不到这样的路径，则返回 -1。
 *
 * 贪心策略：
 * 本题精髓在于对标记访问数组 visited值的扩展，常规0|1标记是否访问，
 * 但还需要记录走到当前位置所剩的消除障碍物机会，越多越好。因为后面的路障谁都不清楚够不够用。
 * 、
 * 通用的BFS代码模板：

 * @createTime 2021年08月29日 22:37:00
 */
public class minLuJinInMatrix {
    //假如没有障碍物
    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0){
            return 0;
        }
        int rows = grid.length;
        int column = grid[0].length;
        int[][] dp = new int[rows][column];
        dp[0][0] = grid[0][0];//左上角开始
        for (int i = 1; i < rows; i++) {
            dp[i][0] = dp[i-1][0] + grid[i][0];
        }
        for (int j = 1; j <column ; j++) {
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }
        for (int i = 1; i <rows ; i++) {
            for (int j = 1; j <column ; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        return dp[rows - 1][column - 1];//返回最右下角

    }
    /**
     * 通用的BFS代码模板：
     * // 节点访问标识,访问过的节点无需访问（剪枝）
     * int[][] visited = new int[m][n];
     * // 队列初始化
     * Queue<Node> queue = new LinkedList();
     *
     * // 【第1步】将起点加入队列, 非空进入循环
     * queue.add(第一个数据)
     * while(!queue.isEmpty()) {
     *     // 【第2步】 获取当前队列长度即同一层级（辈分）节点个数，并遍历
     *     int size = queue.size(); // 一定要先获取，queue后面要加入下一层级节点
     *     for (int i = 0; i < size; i++) {
     *         // 【第3步】 对同一层级节点逐个寻找下一层有效**路径节点**，找到目标直接返回结果终止搜索。
     *         Node node = queue.poll();
     *         // 下一层节点 比如网格上下左右移动
     *         Node nextNode = node.x + xj;
     *         //  1. 不符合要求的下一层节点直接过滤（比如越界、已经被visited[][]标记访问了）
     *         //  2. 找到目标节点 直接返回结果
     *         //  3. 符合要求的下一层节点放入队列
     *         queue.offer(nextNode)
     *     }
     * }
     * // 【第4步】 BFS搜索完成没找到结果，返回-1
     * return -1;
     *
     *
     */

    //可以消除障碍
    public int shortestPath(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        // 非法参数处理
        if (validateInputParams(k, m, n)) {
            return -1;
        }
        // 特殊场景处理
        if (m == 1 && n == 1) {
            return 0;
        }

        // BFS对于当前点的下一个点选择，如果grid[i][j]=0则有效入队列 visited[i][j]记录消除障碍次数
        // 若grid[i][j]=1则看是否还有消除障碍机会，若没有 此点丢弃
        // 若有消除障碍机会， （上一个点剩余消除障碍机会 - 1）比visited[i][j] 值比大 此点入队， 小则丢弃（贪心）
        // 例子：k=1, 坐标(0,2)可以为消除(0,1)障碍过来的 visited[0][2] = 0，搜索层级为2
        // 也可能为不消除任何障碍过来的 visited[0][2] = 1，层级为6，更新visited[0][2] = 1并入队
        // 因为到后面还需要消除障碍才能到达目标，先消除障碍走到visited[0][2] = 0的肯定到不了目标...
        // 0 1 0 0 0 1 0 0
        // 0 1 0 1 0 1 0 1
        // 0 0 0 1 0 0 1 0

        // 二维标记数组初始状态为-1，值记录剩余消除障碍的次数，剩余次数越多 越有价值（此处贪心，记录局部最优）
        int[][] visited = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                visited[i][j] = -1;
            }
        }
        // 初始步数为0，m=n=1的特殊场景已处理
        int minSteps = 0;

        // 初始位置标记已访问,值记录剩余消除障碍物次数  越多越好
        // 1. 对于其他路径到达此点且剩余消除障碍物次数小于等于当前值 —— 剪枝
        // 2. 对于其他路径到达此点且剩余消除障碍物次数大于当前值 —— 取代并入队
        visited[0][0] = k;
        Queue<Point> queue = new LinkedList<>();
        Point startPoint = new Point(0, 0, 0);
        queue.offer(startPoint);

        // 定义四个方向移动坐标
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        // BFS搜索-队列遍历
        while (!queue.isEmpty()) {
            minSteps++;
            // BFS搜索-遍历相同层级下所有节点
            // 当前队列长度一定要在循环外部定义，循环内部有插入对列操作
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Point current = queue.poll();
                int x = current.x;
                int y = current.y;
                int oneCount = current.oneCount;

                // 对当前节点四个方向进行识别处理
                for (int j = 0; j < 4; j++) {
                    int xNew = x + dx[j];
                    int yNew = y + dy[j];
                    // 越界判断
                    if (xNew < 0 || xNew >= m || yNew < 0 || yNew >= n) {
                        continue;
                    }
                    // 搜索到目标节点直接返回结果，按层级就是最短步数
                    if (xNew == m - 1 && yNew == n - 1) {
                        return minSteps;
                    }
                    // 穿越障碍次数已满
                    if (grid[xNew][yNew] == 1 && oneCount >= k) {
                        continue;
                    }
                    int oneCountNew = grid[xNew][yNew] == 1 ? oneCount + 1 : oneCount;
                    // 剪枝 - 节点已被访问过，且当前visited记录的剩余障碍物消除次数 >= 当前搜索节点层级的剩余消除次数
                    if (visited[xNew][yNew] != -1 && visited[xNew][yNew] >= k - oneCountNew) {
                        continue;
                    } else {
                        // 否则，贪心将最优值更新，并将该层级节点入队
                        visited[xNew][yNew] = k - oneCountNew;
                    }
                    queue.offer(new Point(xNew, yNew, oneCountNew));
                }
            }
        }
        // BFS没搜索到目标，返回-1
        return -1;
    }

    private boolean validateInputParams(int k, int m, int n) {
        return m > 40 || m < 1 || n > 40 || n < 1 || k < 1 || k > m * n;
    }

    class Point {
        int x;
        int y;
        int oneCount;

        public Point(int x, int y, int oneCount) {
            this.x = x;
            this.y = y;
            this.oneCount = oneCount;
        }
    }
}
