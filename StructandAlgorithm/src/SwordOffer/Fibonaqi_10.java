package SwordOffer;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName Fibonaqi_10.java
 * @Description 求斐波那契数列的第 n 项，n <= 39。
 * 如果使用递归求解，会重复计算一些子问题。例如，计算 f(4) 需要计算 f(3) 和 f(2)，
 * 计算 f(3) 需要计算 f(2) 和 f(1)，可以看到 f(2) 被重复计算了。
 *
 * 递归是将一个问题划分成多个子问题求解，动态规划也是如此，但是动态规划会把子问题的解缓存起来，从而避免重复求解子问题。
 * 考虑到第 i 项只与第 i-1 和第 i-2 项有关，因此只需要存储前两项的值就能求解第 i 项，从而将空间复杂度由 O(N) 降低为 O(1)。
 * 由于待求解的 n 小于 40，因此可以将前 40 项的结果先进行计算，之后就能以 O(1) 时间复杂度得到第 n 项的值。
 * @createTime 2021年08月17日 20:29:00
 */
public class Fibonaqi_10 {
    private static int[] fib = new int[40];

    public static int Fibonaqi(int n) {
        fib[1] = 1;
        for (int i = 2; i < fib.length; i++) {
            fib[i] = fib[i - 1] + fib[i - 2];
        }
        return fib[n];
    }

}
