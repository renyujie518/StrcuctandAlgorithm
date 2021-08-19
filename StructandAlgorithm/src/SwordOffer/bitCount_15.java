package SwordOffer;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName bitCount_15.java
 * @Description 二进制中 1 的个数
 * 输入一个整数，输出该数二进制表示中 1 的个数。
 * 技巧：
 * n&(n-1)
 * 该位运算去除 n 的位级表示中最低的那一位的1。
 * n       : 10110100
 * n-1     : 10110011
 * n&(n-1) : 10110000
 * @createTime 2021年08月18日 16:32:00
 */
public class bitCount_15 {
    public int NumberOf1(int n) {
        int cnt = 0;
        while (n != 0) {
            //得先++，否恩泽就被消掉了
            cnt++;
            n &= (n - 1);
        }
        return cnt;
    }
}
