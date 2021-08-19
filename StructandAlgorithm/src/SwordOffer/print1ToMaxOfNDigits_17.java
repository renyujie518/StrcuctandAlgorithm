package SwordOffer;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName print1ToMaxOfNDigits_17.java
 * @Description 打印从 1 到最大的 n 位数
 * 输入数字 n，按顺序打印出从 1 到最大的 n 位十进制数。
 * 比如输入 3，则打印出 1、2、3 一直到最大的 3 位数即 999。

 * 解题思路
 * 由于 n 可能会非常大，因此不能直接用 int 表示数字，而是用 char 数组进行存储。
 * 使用回溯法得到所有的数。
 * @createTime 2021年08月18日 16:51:00
 */
public class print1ToMaxOfNDigits_17 {
    public static void print1ToMaxOfNDigitsWithDFS(int n) {
        if (n < 0) {
            return;
        }
        char[] number = new char[n];
        dfs(number, 0);
    }

    //number代表几位数,表示每次的数字   digit代表在个位还是十位...  相当于index
    public static void dfs(char[] number, int digit) {
        //结束条件
        if (digit == number.length) {  //实际上是digit == n
            printNumber(number);
            return;  //跳出函数
        }
        for (int i = 0; i < 10; i++) {
            //每一位都是字符'0'~'9'
            number[digit] = (char) (i + '0');  // '0'的 ASCII 编码 48  这里相当于把一个整形数int转化为字符char类型
            dfs(number, digit + 1);

        }
    }

    //给定是几位数，打印从1到10^n -1
    public static void printNumber(char[] number) {
        int index = 0;//统计出几位数
        while (index < number.length && number[index] == '0') {
            index++;
        }
        //打印
        while (index < number.length) {
            System.out.println(number[index++]);

        }
        System.out.println();
    }

    public static void main(String[] args) {
        print1ToMaxOfNDigitsWithDFS(3);
    }

}
