package facing;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName robotWithZhangAi.java
 * @Description
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
 * 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
 * 网格中的数字为1 就代表是障碍物
 *

dp[i][j] 表示走到格子 (i, j)的方法数。
如果网格 (i, j)上有障碍物，则 dp[i][j] 值为 0，表示走到该格子的方法数为 0；
否则网格 (i, j) 可以从网格 (i - 1, j)或者 网格 (i,j−1) 走过来，
dp[i,j]=dp[i−1,j]+dp[i,j−1]。

c初始：
第 1 列的格子只有从其上边格子走过去这一种走法，因此初始化 dp[i][0] 值为 1，存在障碍物时为 0；
第 1 行的格子只有从其左边格子走过去这一种走法，因此初始化 dp[0][j] 值为 1，存在障碍物时为 0。

 * @createTime 2021年08月29日 22:28:00
 */
public class robotWithZhangAi {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0) {
            return 0;
        }
        int n = obstacleGrid.length;
        int m = obstacleGrid[0].length;
        int[][] dp = new int[n][m];
        //(0,0)这个格子可能有障碍物
        dp[0][0] = (obstacleGrid[0][0] == 1) ? 0 : 1;
        //处理第一列
        for(int i = 1; i < n; ++i) {
            if(obstacleGrid[i][0] == 1 || dp[i - 1][0] == 0) {
                dp[i][0] = 0;
            } else {
                dp[i][0] = 1;
            }
        }
        //处理第一行
        for(int j = 1; j < m; ++j) {
            if(obstacleGrid[0][j] == 1 || dp[0][j - 1] == 0) {
                dp[0][j] = 0;
            } else {
                dp[0][j] = 1;
            }
        }
        for(int i = 1; i < n; ++i) {
            for(int j = 1; j < m; ++j) {
                //如果当前格子是障碍物
                if(obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                    //路径总数来自于上方(dp[i-1][j])和左方(dp[i][j-1])
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[n - 1][m - 1];
    }
}
