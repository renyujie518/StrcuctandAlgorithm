package SwordOffer;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName intPower_16.java
 * @Description  数值的整数次方
 * 给定一个 double 类型的浮点数 base 和 int 类型的整数 exponent，求 base 的 exponent 次方。  base^exp
 *比如要求x^11 正常的乘积需要循环乘11次，时间复杂度为O(n)
 * 将指数11 可以转成二进制数1011，则原来的式子可以转化成
 * x^11 = x^(2^3+2^1+2^0) = x^(2^3) * x(2^1) * x(2^0)
 * 此时只运算了3次乘积，时间复杂度降至O(logn)
 * @createTime 2021年08月18日 16:34:00
 */
public class intPower_16 {
    public static double myPow(double x, int n) {
        if (x == 0) {
            return 0;
        }
        long b = n;
        double res = 1.0;
        if (b < 0) {
            x = 1 / x;
            b = -b;
        }
        while (b > 0) {
            // 最后一位为1，需要乘上该位上的权重
            if ((b & 1) == 1) {
                res *= x;
            }
            //累乘
            x *= x;
            b = b >> 1;
        }
        return res;
    }

}
