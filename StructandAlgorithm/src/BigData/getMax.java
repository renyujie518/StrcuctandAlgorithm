package BigData;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName getMax.java
 * @Description
 * 给定两个有符号32位整数a和b，返回a和b中较大的
 * 【要求】不用做任何比较判断。
 * 技巧 位运算
 * @createTime 2021年03月26日 21:40:00
 */
public class getMax {
    public static int flip(int n) {  //保证输入的参数不是1就是0的情况下 ^1 1变0 0变1
        return n ^ 1;
    }

    //n>=0 返回1 n是负数 返回0
    public static int sign(int n) {
        return flip((n >> 31) & 1);//一个整数向右移动32位，其符号位就移动最右，对于整形 非负数的符号位是0，负数的符号位是1
        //再与1& 非负数仍是0  负数为1   再经过filp 非负数1 负数0
    }

    //这个函数是有问题的 因为a - b有可能溢出
    public static int getMax1(int a, int b) {
        int c = a - b;
        int scA = sign(c); //a-b 为负 scA为0
        int scB = flip(scA);//a-b 为负 scB为1
        return a * scA + b * scB;//互斥条件相加实现类似if-else的作用  scA为0的时候scB必为1，反之同理
    }

    public static int getMax2(int a, int b) {
        int c = a - b;
        int sa = sign(a);
        int sb = sign(b);
        int sc = sign(c);
        int difSab = sa ^ sb;  //a和b的符号一样为0  不一样为1
        int sameSab = flip(difSab); //a和b的符号一样为1  不一样为0
        int returnA = difSab * sa + sameSab * sc;//两加号互斥 要a是max条件: 在ab不同时且a>=0返回a 或者 在ab同号时且两差值>=0(sc =1,不溢出)返回a
        int returnB = flip(returnA);//取互斥
        return a * returnA + b * returnB;
    }

    public static void main(String[] args) {
        int a = -16;
        int b = 1;
        System.out.println(getMax1(a, b));
        System.out.println(getMax2(a, b));
        a = 2147483647;
        b = -2147480000;
        System.out.println(getMax1(a, b)); // wrong answer because of overflow
        System.out.println(getMax2(a, b));

    }
}
