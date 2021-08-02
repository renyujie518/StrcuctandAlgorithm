package MiddleClass;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName nearMultiple4Times.java
 * @Description
 * 给定一个数组arr， 如果通过调整可以做到arr中任意两个相邻的数字相乘是4的倍数， 返回true； 如果不能返回false
 *
 * 思路：
 * 遍历数组  找到四种数：奇数 a个  偶数只有一个2因子 b个 偶数包含4因子 c  个
 * 思路：
 * 当b = 0的时候 什么情况下用到c的个数最少： 奇4奇4...  观察发现  a>=1  c就要>=a-1
 * 当b!=0   22...4奇4奇....  在2都放在前面的情况下 a = 0 c>=0;a>=1 c>=a
 *
 *
 * @createTime 2021年07月30日 16:49:00
 */
public class nearMultiple4Times {
    public static boolean nearMultiple4Times(int[] arr) {
        //奇数 a个
        int a = 0;
        //偶数只有一个2因子(是偶数但不是4的倍数的数) b个
        int b = 0;
        //是4的倍数的数 c个
        int c = 0;
        for (int i = 0; i < arr.length; i++) {
            if ((arr[i] & 1) != 0) {  //&按位与  (两个为真才为真 一个数 & 1的结果就是取二进制的最末位。
                // 这可以用来判断一个整数的奇偶，二进制的最末位为0表示该数为偶数，最末位为1表示该数为奇数
                a++;
            } else {  //一定是偶数
                if (arr[i] % 4 == 0) {
                    c++;
                } else {
                    b++;
                }
            }
        }
        return b == 0 ? (c >= a - 1) : (c >= a);
    }

}
