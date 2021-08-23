package facing;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName jieyaSuo.java
 * @Description 腾讯面试题
 * 小Q想要给他的朋友发送一个神秘字符串，但是他发现字符串的过于长了，
 * 于是小Q发明了一种压缩算法对字符串中重复的部分进行了压缩，对于字符串中连续的m个相同字符串S将会压缩为[m|S](m为一个整数且1<=m<=100)，
 * 例如字符串ABCABCABC将会被压缩为[3|ABC]，现在小Q的同学收到了小Q发送过来的字符串，你能帮助他进行解压缩么？
 * 输入：
 * HG[3|B[2|CA]]F
 * 输出：
 * HGBCACABCACABCACAF
 *

 * 这题考的应该是“栈”这个知识点，运用了Stack来查找 ']' 的index
 * @createTime 2021年08月20日 13:28:00
 */
public class jieyaSuo {
    private static LinkedList<Character> stack = new LinkedList<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        int len = str.length();
        for(int i = 0; i < len; i++){
            char ch = str.charAt(i);
            if(ch != ']'){
                stack.push(ch);
            }else{
                //此时遇到了']'
                //需要出栈，直到遇到第一个'['
                StringBuilder sb = new StringBuilder("");
                while(!stack.isEmpty() && stack.peek() != '['){
                    sb.append(stack.pop());
                }
                //'['pop掉
                stack.pop();
                //sb反转，然后处理出现的次数和要重复的字符串
                String tmp = sb.reverse().toString();
                int times = Integer.valueOf(tmp.substring(0, tmp.indexOf('|')));
                String repeatedStr = tmp.substring(tmp.indexOf('|') + 1);
                //重复的字符串按照times放入栈中
                for(int j = 0; j < times; j++){
                    for(int k = 0; k < repeatedStr.length(); k++){
                        stack.push(repeatedStr.charAt(k));
                    }
                }
            }
        }
        StringBuilder stringBuilder = new StringBuilder("");
        while(!stack.isEmpty()){
            stringBuilder.append(stack.pop());
        }
        System.out.println(stringBuilder.reverse().toString());
    }
}
