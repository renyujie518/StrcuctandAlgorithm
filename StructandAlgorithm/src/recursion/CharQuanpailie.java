package recursion;

import java.util.ArrayList;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName CharQuanpailie.java
 * @Description 字符串全排列  并且去重
 * 例如输入字符串abc，则输出由字符a、b、c 所能排列出来的所有字符串：
 * abc、acb、bac、bca、cab 和 cba。
 * @createTime 2021年03月18日 15:14:00
 */
public class CharQuanpailie {
    //str[i..]范围上，所有的字符都可以在i位置上，后续都去尝试
    //str[0...i-1]范围上，是之前做的选择，是已经试好的（可以看做固定的）
    //把所有字符串形成的全排列加到res后返回
    public static void process(char[] str, int i, ArrayList<String> res) {
        //base case i作为指针走到末尾加入结果
        if (i == str.length){
            res.add(String.valueOf(str));  //这个str是被复用的，是递归栈保证了它每次先入栈，在方法调用的时候再从栈里取出而不销毁
            //所以i == str.length时其实是最后一个str被加到res里
        }
        boolean[] visit = new boolean[26];  //为了不重复，分支限界，设置标志位
        for (int j = i; j < str.length; j++) {  //从i位置开始，其余的所有字符都要试一遍组合，直到字符用完
            if (!visit[str[j]-'a']){  //26个字母，减去'a'后变为 a对应0位置，b对应1位置，c对应2位置....,哪个位置被用了，这个位置就置true
                //如果！true就代表没被用，这样就保证了不重复的字母才被考虑
                visit[str[j] - 'a'] = true;  //那我既然在这个if里，之前没用，现在用了，该位置置true
                swap(str, i, j); //str承载了之前的做法排列，i往后所有的字符都可以来到i位置(省空间，直接改变str的布局)
                process(str,i+1,res);//j来到位置i后,从i+1开始尝试
                swap(str, i, j);//尝试完再放回去，依次往复，j从[i,length-1]都尝试一遍
            }
        }
    }
    public static ArrayList<String> CharQuanpailie(String str) {
        ArrayList<String> res = new ArrayList<>();
        if (str == null || str.length() == 0) {
            return res;
        }
        char[] chs = str.toCharArray();
        process(chs, 0, res);  //从i=0开始
        return res;
    }

    public static void main(String[] args) {
        String test = "abc";
        ArrayList<String> res = CharQuanpailie(test);
        for (String result : res) {
            System.out.println(result);
        }

    }
    //工具类，交换位置
    private static void swap(char[] str, int i, int j) {
        char temp = str[i];
        str[i] = str[j];
        str[j] = temp;
    }
}
