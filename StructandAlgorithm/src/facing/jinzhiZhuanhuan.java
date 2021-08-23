package facing;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Scanner;
import java.util.Stack;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName problem2.java
 * @Description 十进制转二进制
 * 将一个长度最多为30位数字的十进制非负整数转换为二进制数输出
 *
 *k * 输入
 * 0
 * 1
 * 3
 * 8
 * 输出
 * 0
 * 1
 * 11
 * 1000
 * @createTime 2021年08月20日 15:59:00
 */
public class jinzhiZhuanhuan {
//    public static void main(String[] args)
//    {
//        Scanner cin=new Scanner(System.in);
//        while(cin.hasNext())//相当于EOF
//        {
//            String str=cin.nextLine();
//            BigInteger a=new BigInteger(str,10);//将string转换为10进制
//            System.out.println(a.toString(2));//将a转换为2进制
//        }
//        cin.close();
//    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int num;
        while (sc.hasNext()) {
            num = sc.nextInt();
            System.out.println(ShiTo2(num));
        }
    }


    public static String ShiTo2(int n){
        //需要用到一个辅助栈（刚好先进后出）和StringBuffer的优势  最后再转Int
        Stack<Integer> stack  = new Stack<Integer>();
        StringBuffer result = new StringBuffer("");
        //十进制取模和取余数
        while(n!=0){
            int moShu = n%2;
            stack.push(moShu);
            n = n/2;
        }
        //将栈中的元素吐出来，放到buffer里
        while(!stack.isEmpty()){
            result.append(stack.pop());
        }
        return result.toString();
    }

    //越界了，字符不会越界
    public static String ShiToer(int n) {
        String str = "";
        int yushu = 0;
        int shang = n;
        while (shang > 0) {
            yushu = shang % n;
            shang = shang / n;
            //把一个数转化为字符
            if (yushu > 9) {
                str = ('a' + (yushu - 10)) + str;
            } else {
                str = yushu + str;
            }
        }
        return str;
    }

}
