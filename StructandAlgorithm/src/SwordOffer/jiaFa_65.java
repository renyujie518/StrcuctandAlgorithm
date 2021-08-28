package SwordOffer;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName jiaFa_65.java
 * @Description 不用加减乘除做加法
 * 写一个函数，求两个整数之和，要求不得使用 +、-、*、/ 四则运算符号。
 *
 * a ^ b 表示没有考虑进位的情况下两数的和，(a & b) << 1 就是进位。
 * 递归会终止的原因是 (a & b) << 1 最右边会多一个 0，那么继续递归，进位最右边的 0 会慢慢增多，最后进位会变为 0，递归终止。
 * @createTime 2021年08月28日 18:13:00
 */
public class jiaFa_65 {
    public int Add(int a, int b) {
        return b == 0 ? a : Add(a ^ b, (a & b) << 1);
    }
}
