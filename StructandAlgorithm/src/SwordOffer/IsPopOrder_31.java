package SwordOffer;

import java.util.Stack;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName IsPopOrder_31.java
 * @Description 栈的压入、弹出序列
 * 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否可能为该栈的弹出顺序。
 * 假设压入栈的所有数字均不相等。
 * 例如序列1,2,3,4,5是某栈的压入顺序，序列4,5,3,2,1是该压栈序列对应的一个弹出序列，但4,3,5,1,2就不可能是该压栈序列的弹出序列。
 * （注意：这两个序列的长度是相等的）
 *
 * 思路：
 * 使用一个栈来模拟压入弹出操作
 * @createTime 2021年08月21日 15:13:00
 */
public class IsPopOrder_31 {
    public static boolean IsPopOrder(int[] pushQuene, int[] popQuene) {
        int n = pushQuene.length;
        Stack<Integer> stack = new Stack<>();
        for (int pushIndex = 0, popIndex = 0; pushIndex < n; pushIndex++) {
            stack.push(pushQuene[pushIndex]);
            while (popIndex < n && !stack.isEmpty()
                    && stack.peek() == popQuene[popIndex]) {  //关键就是这里，查看popQuene和stack栈顶的是否一致
                stack.pop();//一致的话就相当于验证过了
                popIndex++;
            }
        }
        return stack.isEmpty();//最终的判断依据就是stack中有没有剩余
    }
}
