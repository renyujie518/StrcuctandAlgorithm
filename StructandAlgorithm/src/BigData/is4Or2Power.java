package BigData;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName is4Or2Power.java
 * @Description 判断一个32位正数是不是2的幂、4的幂
 * @createTime 2021年03月26日 22:21:00
 */
public class is4Or2Power {
    public static boolean is2Power(int n) {//2进制中只能有一个数是1就是2的幂 比如8421 1000 = 8 0100 =4
        //一个思路是：先拿出一个数最右侧的1，和原来数相等，代表只有1个1 就是2的幂
        return (n & (n - 1)) == 0;//还有一个思路是：假设一个数2进制只有一个1 如果-1，则会把这个唯一的1打散 ，比如1000 -1 = 0111
        //这时候再与原数& 1000&0111 = 0000  必须为0
    }

    public static boolean is4Power(int n) {//是4的幂的前提得是2的幂（二进制只有一个1 ）00100 ，10000都得是奇数位上
        return (n & (n - 1)) == 0 && (n & 0x55555555) != 0; //0x55555555 = 01..10101 一旦&且结果不为0说明奇数位都是1
    }

    public static void main(String[] args) {
        System.out.println(is2Power(8));
        System.out.println(is4Power(16));
    }

}
