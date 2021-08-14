package facing;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName magicIsZhengchu3.java
 * @Description
 * 小Q得到一个神奇数列: 1 , 12, 123, . . . 12345678910, 1234567891011. . . 。
 * 并且小Q对于能否被3整除这个性质很感兴趣。
 * 小Q现在希望你能帮他计算一下从数列的第l个到第r个(包含端点)有多少个数可以被3整除。
 * 输入描述： 输入包括两个整数l和r(1 <= l <= r <= 1 e9) , 表示要求解的区间两端。
 * 输出描述： 输出一个整数, 表示区间内能被3整除的数字个数。
 * 示例1: 输入 2 5
 *       输出 3
 *       解题思路： 判断一个数能不能被3整除， 等价于一个数的每位之和能否被3整除。
 *       刚开始想打表， 但发现数据 量是太大， 一维数组最多只能开到1e8.所以就不能纯暴力判断了，
 *       不过数据是有规律的， 第一个数是1、 第二个数 是12， 第三个数是123， 所以只用判断n*(n+1)/2%3即可（等差数列）。
 *       因为数量太大了， 所以用long
 * @createTime 2021年08月12日 16:29:00
 */
public class magicIsZhengchu3 {
    public static int magicIsZhengchu3(int l, int r) {
        int sum = 0;
        for (int i = l; i <= r; i++) {
            long tmp = (long) (i + 1) * (long) i / 2L;
            if (tmp % 3 == 0) {
                sum++;
            }
        }
        return sum;
    }

}
