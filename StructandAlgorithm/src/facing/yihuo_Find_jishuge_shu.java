package facing;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName z1.java
 * @Description TODO
 * @createTime 2021年02月08日 18:34:00
 *
 * []arr int
 * (1) 一种数 奇数次 other 偶数次，找到这个数
 * （2）2种数 奇数次 other 偶数次，找到这2个数
 * 要求：o(n)
 *
 *异或(相同为0，不同为1 无进制相加)和顺序无关 a^a =0  0^b = b
 */
public class yihuo_Find_jishuge_shu {
    public static void main(String[] args) {
        int[] arr1 = {2, 1, 3, 1, 3, 1, 3, 2, 1};//找到3
        method1(arr1);

        int[] arr2 = {2, 1, 3, 1, 3, 1,4, 3, 2, 1};//找到3,4
        method2(arr2);

    }

    public static  void method1(int[] arrs){
        int eor = 0;
        for (int arr : arrs) {
            eor =eor^arr;
        }
        System.out.println(eor);
    }


    public static void method2(int[] arrs){
        int eor =0;
        for (int arr : arrs) {
            eor ^= arr;
        }
        //循环完毕，此时由于a!=b,eor = a^b,eor!=0
        //eor必然在某一位上是1
        //& 遇0则0
        int rightOne = eor & (~eor + 1);//提取这个数二进制最右边为1，其余都是0  11001100 -> 00000100
        //现在就假设这一位1是"eor必然在某一位上是1"的那一位

        int aOrb = 0;
        for (int arr : arrs) {
            if ((arr & rightOne) ==1){  //==0也可以
                aOrb ^= arr;  //找到a,b其中的一个放到aorb变量中
            }
        }
        //另一个奇数个找到就简单了  a^b^(aOrb)
        System.out.println(aOrb+" "+(eor^aOrb));

    }
}
