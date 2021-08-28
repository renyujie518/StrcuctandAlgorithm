package SwordOffer;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName lastNumInQuan_62.java
 * @Description
 * 0, 1,···,n-1这n个数字排成一个圆圈，从数字0开始，每次从这个圆圈里删除第m个数字（删除后从下一个数字开始计数）。
 * 求出这个圆圈里剩下的最后一个数字。
 *
 * 例如，0、1、2、3、4这5个数字组成一个圆圈，从数字0开始每次删除第3个数字，则删除的前4个数字依次是2、0、4、1，因此最后剩下的数字是3。
 * “约瑟夫环” 问题，可使用 动态规划 解决
 * 数学证明：
 * https://leetcode-cn.com/problems/yuan-quan-zhong-zui-hou-sheng-xia-de-shu-zi-lcof/solution/si-bu-he-xin-gong-shi-qing-song-nong-don-3vln/
 *  设「i, m问题」的解为 dp[i]；
 *  dp[i]=(dp[i−1]+m)%i
 *  dp[1]=0
 * @createTime 2021年08月28日 17:45:00
 */
public class lastNumInQuan_62 {
    public int lastRemaining(int n, int m) {
        int x = 0;
        for (int i = 2; i <= n; i++) {
            x = (x + m) % i;
        }
        return x;
    }
}
