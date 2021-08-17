package SwordOffer;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName rectangleFuGai.java
 * @Description 矩形覆盖
 * 我们可以用 2*1 的小矩形横着或者竖着去覆盖更大的矩形。请问用 n 个 2*1 的小矩形无重叠地覆盖一个 2*n 的大矩形（竖桩形），总共有多少种方法？
 *当 n 为 1 时，只有一种覆盖方法（）躺着放
 * 当 n 为 2 时，有两种覆盖方法 但都能成一个2x2的正方向
 *
 * 要覆盖 2*n 的大矩形，可以先覆盖 2*1 的矩形，再覆盖 2*(n-1) 的矩形；
 * 或者先覆盖 2*2 的矩形，再覆盖 2*(n-2) 的矩形。
 * 而覆盖 2*(n-1) 和 2*(n-2) 的矩形可以看成子问题
 * n>1时  f(n) = f(n−1)+f(n−2)
 *
 * 一模一样的题：
 * 一只青蛙一次可以跳上 1 级台阶，也可以跳上 2 级。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。
 *
 * @createTime 2021年08月17日 20:44:00
 */
public class rectangleFuGai {

    public static int rectangleFuGai(int n) {
        //n为数量  返回方法数
        if (n <= 2) {
            return n;
        }
        int pre1 = 1;
        int pre2 = 2;
        int result = 0;
        for (int i = 3; i <= n; i++) {
            result = pre1 + pre2;
            pre2 = pre1;
            pre1 = result;
        }

        return result;

    }


}
