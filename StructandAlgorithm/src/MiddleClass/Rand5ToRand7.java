package MiddleClass;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName Rand5ToRand7.java
 * @Description
 * 给定一个函数f，可以1～5的数字等概率返回一个。请加工出1～7的数字等概率返回一个的函数g。
 * 给定一个函数f，可以a～b的数字等概率返回一个。请加工出c～d的数字等概率返回一个的函数g。
 * 给定一个函数f，以p概率返回0，以1-p概率返回1。请加工出等概率返回0和1的函数g
 *
 * 用二进制去做
 * @createTime 2021年03月31日 14:39:00
 */
public class Rand5ToRand7 {
    //给定 1～5的数字等概率返回一个
    public static int f(){
        return (int) (Math.random() * 5) + 1;
    }

    //首先创造一个等概率返回0 1的函数
    //思路是利用f 3就继续循环 1，2返回0  4，5返回1
    public static int r01(){
        int res = 0;
        do {
            res = f();
        } while (res == 3);
        return res < 3 ? 0 : 1;
    }

    //有了r01()，如何1～7的数字等概率返回一个（其实是0-6）
    //利用二进制  8421
    //主要是几个二进制位够 1个二进制可以返回0-1  2个二进制可以返回0-3  3个二进制可以返回0-7
    public static int r07(){
        int res = 0;
        do {
            res = (r01() << 2) + (r01() << 1) + r01();
        } while (res == 7);
        return res + 1;
    }

    //给定一个函数f，可以a～b的数字等概率返回一个。请加工出c～d的数字等概率返回一个的函数g。
    //还是一样的思路 先把f加工成01发生器（一半一半，分界点dowhile），然后把c～d减去x变成0-y，看几个二进制位够用就roll几次，最后+x返回


    //给定一个函数f，以p概率返回0，以1-p概率返回1。请加工出等概率返回0和1的函数g
    //一个道理 roll两次 00或11dowhile，，01和10的概率都是p(1-p),所以定下来 01返回0 10返回1




}
