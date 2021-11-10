package facing.combineAndpermute;

import java.util.ArrayList;
import java.util.List;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName dianHua.java
 * @Description 电话按键的组合
 * 输入："23" 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 * https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/
 *
 * 组合问题  回溯
 * @createTime 2021年09月17日 12:52:00
 */
public class dianHua {

    List<String> list = new ArrayList<String>();
    StringBuilder temp = new StringBuilder();

    public  List<String> dianHua(String digits) {
        if (digits == null || digits.length() == 0) {
            return null;
        }
        //初始对应所有的数字，为了直接对应2-9，新增了两个无效的字符串""对应0，1
        String[] numString = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        //回溯
        backtrace(digits, numString, 0);
        return list;
    }

    public void backtrace(String digits, String[] numString, int num) {
        //遍历全部一次记录一次得到的字符串
        if (num == digits.length()) {
            list.add(temp.toString());
            return;
        }
        //str 表示当前输入的按键数字num对应的字符串  比如2对应'abc'
        String str = numString[digits.charAt(num) - '0'];
        //下面就是组合问题
        for (int i = 0; i < str.length(); i++) {
            temp.append(str.charAt(i));
            //递归
            backtrace(digits, numString, num + 1);
            //移除末尾继续尝试
            temp.deleteCharAt(temp.length() - 1);
        }
    }


}
