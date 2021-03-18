package recursion;

import java.util.Stack;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName reverseStack.java
 * @Description 给你一个栈，请你逆序这个栈，不能申请额外的数据结构，只能使用递归函数。如何实现?
 * @createTime 2021年03月18日 21:51:00
 */
public class reverseStack {
    //移除栈底元素并返回,剩下的盖下来
    public static int getAndRemoveLastElement(Stack<Integer> stack){
        Integer result = stack.pop();
        if (stack.isEmpty()){
            return result;
        }else {
            int last = getAndRemoveLastElement(stack);
            stack.push(result);
            return last;
        }
    }

    public static void reverseStack(Stack<Integer>stack){
        if (stack.isEmpty()){
            return;
        }
        int i = getAndRemoveLastElement(stack);
        reverseStack(stack);
        stack.push(i);
    }
    public static void main(String[] args) {
        Stack<Integer> test = new Stack<Integer>();
        test.push(1);
        test.push(2);
        test.push(3);
        test.push(4);
        test.push(5);
        while (!test.isEmpty()) {
            System.out.println(test.pop());
        }
        System.out.println("======");
        test.push(1);
        test.push(2);
        test.push(3);
        test.push(4);
        test.push(5);
        reverseStack(test);
        while (!test.isEmpty()) {
            System.out.println(test.pop());
        }

    }


}
