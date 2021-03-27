package DP;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName HouseJump.java
 * @Description 象棋中马的跳法【题目】请同学们自行搜索或者想象一个象棋的棋盘，
 * 然后把整个棋盘放入第一象限，棋盘的最左下角是(0,0)位置。那么整个棋盘就是横坐标上9条线、纵坐标上10条线的一个区域。
 * 给你三个参数，x，y，k，返回如果“马”从(0,0)位置出发，必须走k步，最后落在(x,y)上的方法数有多少种？
 * @createTime 2021年03月19日 19:20:00
 */
public class HouseJump {
    public static int HouseJumpRecursion(int x, int y, int step) {
        return process(x, y, step);
    }

    //step跳过了多少步，潜台词 一律规定从（0，0）位置出发，去（x,y）位置，必须跳step步  三个变量
    public static int process(int x, int y, int step) {
        if (x < 0 || x > 8 || y < 0 || y > 9) {//棋盘 横9【0，8】竖10【0，9】 越界了代表不是正确的走法
            return 0;
        }
        if (step == 0) { //step == 0有两种情况 步数用光了 步数本身就输入0步，如果是后者，说明马还在原地，这时候也算一种走法 返回1
            return (x == 0 && y == 0) ? 1 : 0;
        }

        //这里要倒着推  到达（x,y）的前一步怎么走个"日"可以到（x,y），在图上画一画可以发现8个位置，但此时step - 1
        return process(x - 1, y + 2, step - 1)
                + process(x + 1, y + 2, step - 1)
                + process(x + 2, y + 1, step - 1)
                + process(x + 2, y - 1, step - 1)
                + process(x + 1, y - 2, step - 1)
                + process(x - 1, y - 2, step - 1)
                + process(x - 2, y - 1, step - 1)
                + process(x - 2, y + 1, step - 1);
    }

    private static int HouseJumpDP(int x, int y, int step) {
        //一个体 高是step 本层的结果依赖下一层，第0层会因为basecase推导出来，所以从第一层开始往上推
        ////棋盘 横9【0，8】竖10【0，9】
        if (x<0 || x>8 ||y<0 || y>9||step<0){
            return 0;
        }
        int[][][] dp = new int[9][10][step + 1];
        dp[0][0][0] = 1;  //第0层的面只有（0，0）是1，其余全是0
        for (int h = 1; h <= step; h++) { //从第一层开始
            for (int r = 0; r < 9; r++) {
                for (int c = 0; c < 10; c++) {//x,y维度上的顺序无所谓，关键是高度要从低向上
                    dp[r][c][h] += getValue(dp, r - 1, c + 2, h - 1);
                    dp[r][c][h] += getValue(dp, r + 1, c + 2, h - 1);
                    dp[r][c][h] += getValue(dp, r + 2, c + 1, h - 1);
                    dp[r][c][h] += getValue(dp, r + 2, c - 1, h - 1);
                    dp[r][c][h] += getValue(dp, r + 1, c - 2, h - 1);
                    dp[r][c][h] += getValue(dp, r - 1, c - 2, h - 1);
                    dp[r][c][h] += getValue(dp, r - 2, c - 1, h - 1);
                    dp[r][c][h] += getValue(dp, r - 2, c + 1, h - 1);
                }
            }
        }
        return dp[x][y][step];//我想要的是x,y,step位置上的值
    }
    //防止越界 想象这个立方体泡在一个全是0的池子里，一旦越界返回0，不越界返回该位置的值
    public static int getValue(int[][][] dp, int row, int col, int step) {
        if (row < 0 || row > 8 || col < 0 || col > 9) {
            return 0;
        }
        return dp[row][col][step];
    }
    public static void main(String[] args) {
        int x = 7;
        int y = 7;
        int step = 10;
        System.out.println(HouseJumpRecursion(x, y, step));
        System.out.println(HouseJumpDP(x, y, step));
    }


}
