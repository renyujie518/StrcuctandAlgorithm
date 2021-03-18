package facing;

import java.util.Stack;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName TenjinzhiTurn.java
 * @Description 将十进制数转换为n（2<=n<=16）进制数，原理跟数学上的转换方法相同，
 * 即对一个数不断的进行除n取余运算，直至商为0，将余数倒序排出即为转换结果。
 * @createTime 2021年03月17日 18:22:00
 */
public class TenjinzhiTurn {
    public static void convert(int number, int system) {
        Stack<Integer> stack = new Stack<Integer>();

        while (number > 0) {
            stack.add(number % system);
            number /= system;
        }

        while (!stack.empty()) {
            System.out.print(stack.pop());
        }
    }

}
