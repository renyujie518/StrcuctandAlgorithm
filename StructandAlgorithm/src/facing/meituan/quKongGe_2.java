package facing.meituan;

import java.util.Scanner;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName quKongGe_2.java
 * @Description
 * 小美得到了一个奇怪的键盘，上面一共有 53 个按键，包括 26 个小写字母、26 个大写字母和空格。这个键盘的奇怪之处如下：
 * 当小美按下一个按键时，该按键可能会被多次触发，即输出一连串按下按键所对应的字符。
 * 键盘会时不时地自动按下空格键。
 * 在使用这个键盘来进行输入时，小美保证了相邻两次按下的按键一定不同以及不主动按下空格键，
 * 现在给你小美使用这个键盘输入一个字符串后得到的结果，请你还原小美原本想要输入的这个字符串。
 * 输入描述
 * 一行，一个包含小写字母、大写字母和空格的字符串，表示小美输入后得到的结果。
 * 输出描述
 * 输出一行，表示小美原本想要输入的字符串。
 *
 * 样例输入
 *  a iC C  C GmyyyySp p(注意，a之前还有空格呢！！！！！所以trim() )
 * 样例输出
 * aiCGmySp
 * @createTime 2021年08月28日 23:19:00
 */
public class quKongGe_2 {
    //实际上就是去空格和连续重
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        //trim() 方法用于删除字符串的头尾空白符。
        String b = s.trim();
        StringBuilder sb = new StringBuilder(b);
        for(int i = 1; i < sb.length(); i++){
            if(sb.charAt(i) == ' '){
                sb.deleteCharAt(i);
                //用于抵消下一步会执行的i++
                i--;
            }
        }
        //连续重复只保留第一次出现
        for (int i = 1; i < sb.length(); i++) {
            if(sb.charAt(i) == sb.charAt(i-1)){
                //replace(int start，int end，String str):子字符串从指定的索引开头开始，并扩展到索引结尾– 1处的字符
                //相当于只将i-1位置的替换为了空格
                sb.replace(i-1, i, " ");
            }
        }
       //由于上面替换了空格，这时候再执行遍删除空格的操作
        for(int i = 1; i < sb.length(); i++){
            if(sb.charAt(i) == ' '){
                sb.deleteCharAt(i);
                i--;
            }
        }
    }
}
