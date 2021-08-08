package facing;

import util.swap;

import java.util.Stack;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName max.java
 * @Description 12345  只移动一位
 * 一个正整数，移动一位，使得正整数的值最大
 *
 * 思路：
 * 从高位开始，遍历每一位，找到它后面 最大的数 交换一下
 * 注意要尽可能的靠后找，要把这个小数尽可能放到后面去

 * @createTime 2021年08月05日 20:15:00
 */
public class yiDongYiCi {
    public static int yidongyiwei(int num) {
        String s = String.valueOf(num);
        char[] data = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            //这个maxTempIndex 就是当前位 ，默认为最大数
            char maxTemp = data[i];
            int maxTempIndex = i;
            //从i之后开始遍历，更新上面两个变量
            for (int j = i + 1; j < s.length(); j++) {
                if (data[j] > maxTemp) {
                    maxTemp = data[j];
                    maxTempIndex = j;
                }
            }

            //注意，这里是是关键  目前相当于找到i位置之后最大的数，这时候最好把大数往前提
            if (maxTemp > data[i]) {
                char temp = data[i];
                data[i] = data[maxTempIndex];
                data[maxTempIndex] = temp;
                break;//只交换一次
            }
        }
        return Integer.parseInt(new String(data));
    }

}
