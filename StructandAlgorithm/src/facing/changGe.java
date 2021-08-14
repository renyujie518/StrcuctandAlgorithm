package facing;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName changGe.java
 * @Description
 * CC里面有一个土豪很喜欢一位女直播Kiki唱歌， 平时就经常给她点赞、 送礼、 私聊。
 * 最近CC直播平台在举行 中秋之星主播唱歌比赛，
 * 假设一开始该女主播的初始人气值为start， 能够晋升下一轮人气需要刚好达到end， 土豪给主播增加人气的可以采取的方法有：
 * a. 点赞 花费x C币， 人气 + 2
 * b. 送礼 花费y C币， 人气 * 2
 * c. 私聊 花费z C币， 人气 - 2
 * 其中 end 远大于start且start,end为偶数，
 * 请写一个程序帮助土豪计算一下， 最少花费多少C币就能帮助该主播 Ki将人气刚好达到end(精确到达)， 从而能够晋级下一轮？
 * 输入描述： 第一行输入5个数据， 分别为： x y z start end， 每项数据以空格分开。
 * 其中： 0＜x, y, z＜＝10000， 0＜start, end＜＝1000000
 * 输出描述： 需要花费的最少C币。
 * 示例1: 输入 3 100 1 2 6 输出 6
 *
 * 这是个纯code问题  最主要的就是递归+考虑边界
 * 如果是单纯的递归会导致递归不完（偶数的问题+有-）所以要设置好basecase
 * 限制1：点赞可以看做平凡解，效果还不如平凡解的的可以在递归中停止
 * 限制2：成长的人气增长的幅度没有必要超过2*end
 * @createTime 2021年08月12日 18:48:00
 */
public class changGe {
    public static int minCoins1(int x, int y, int z, int start, int end) {
        if (start > end) {
            return -1;
        }
        //这里要解释下limitCoin  点赞 花费x C币， 人气 + 2的方式代价大，看做平凡解，假设全都要一步步加上去，共需要的代价是((end - start) / 2) * x
        return process(0, end, x, y, z, start, end * 2, ((end - start) / 2) * x);
    }

    //preMoney 之前花了多少钱  可变
    //aim 目标  固定
    // jia chen jian 固定(对应x,y,z)
    //curr当前来到的人气
    //limitAim 人气大到什么程度不需要再尝试了  固定
    //limitCoin 使用的币大到什么程度了不用再尝试了  固定
    //返回最小币数

    public static int process(int preMoney, int aim, int jia, int chen, int jian, int curr, int limitAim, int limitCoin) {
        if (preMoney > limitCoin) {
            return Integer.MAX_VALUE;
        }
        if (aim < 0) {
            return Integer.MAX_VALUE;
        }
        if (aim == curr) {
            return preMoney;
        }
        int result = Integer.MAX_VALUE;
        //人气+2的方式
        int p1 = process(preMoney + jia, aim, jia, chen, jian, curr + 2, limitAim, limitCoin);
        if (p1 != Integer.MAX_VALUE) {
            result = p1;
        }
        //人气*2
        int p2 = process(preMoney + chen, aim, jia, chen, jian, curr *2, limitAim, limitCoin);
        if (p2 != Integer.MAX_VALUE) {
            result = Math.min(result, p2);
        }
        //人气-2
        int p3 = process(preMoney + jian, aim, jia, chen, jian, curr -2, limitAim, limitCoin);
        if (p3 != Integer.MAX_VALUE) {
            result = Math.min(result, p3);
        }
        return result;
    }

    //二维动态的改动态规划
    public static int minCoins2(int jia, int chen, int jian, int start, int end) {
        if (start > end) {
            return -1;
        }
        int limitCoin = ((end - start) / 2) * jia;
        int limitAim = end * 2;
        int[][] dp = new int[limitCoin + 1][limitAim + 1];//这个就是思路中的限制，把最终解限制在这么个矩阵中
        //basecase
        for (int preMonney = 0; preMonney <= limitCoin; preMonney++) {
            for (int aim = 0; aim <= limitAim; aim++) {
                if (aim == start) {
                    //初始的时候aim == start  疑问  ？？？为什么这里不设置为0即开始的时候所花的钱为0
                    dp[preMonney][aim] = preMonney;
                } else {
                    dp[preMonney][aim] = Integer.MAX_VALUE;
                }
            }
        }
        for (int preMoney = limitCoin; preMoney >= 0; preMoney--) {
            for (int aim = 0; aim <= limitAim; aim++) {
                if (aim - 2 >= 0 && preMoney + jia <= limitCoin) {
                    dp[preMoney][aim] = Math.min(dp[preMoney][aim], dp[preMoney + jia][aim - 2]);
                }
                if (aim + 2 <= limitAim && preMoney + jian <= limitCoin) {
                    dp[preMoney][aim] = Math.min(dp[preMoney][aim], dp[preMoney + jian][aim + 2]);
                }
                if ((aim & 1) == 0) {
                    if (aim / 2 >= 0 && preMoney + chen <= limitCoin) {
                        dp[preMoney][aim] = Math.min(dp[preMoney][aim], dp[preMoney + chen][aim / 2]);
                    }
                }
            }
        }
        //上面的两层for循环是从右上到左下 行的含义是limitCoin，越小越好
        //limitAim = end * 2  end一定在limitAim中

        return dp[0][end];
    }

    public static void main(String[] args) {
        int x = 6;
        int y = 5;
        int z = 1;
        int start = 10;
        int end = 30;
        System.out.println(minCoins1(x, y, z, start, end));
        System.out.println(minCoins2(x, y, z, start, end));

    }

}
