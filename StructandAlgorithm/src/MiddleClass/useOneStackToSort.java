package MiddleClass;

import java.util.Stack;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName useOneStackToSort.java
 * @Description 对一个栈里的整型数据， 按升序进行排序（即排序前， 栈里 的数据是无序的， 排序后最大元素位于栈顶） ，
 * 要求最多只能使用一个额外的 栈存放临时数据， 但不得将元素复制到别的数据结构中
 *
 * 这题的关键是只能使用一个额外的stack,这是个临时栈，用于倒转，还有一个本身的原有栈
 * 大致思路是临时栈中由栈底从大到小排列（降序），遇到不符合规则的(从原始栈提取的大于临时栈顶元素)，将临时栈中不符合规则的，返回原有栈，最后在倒序
 * @createTime 2021年07月22日 10:10:00
 */
public class useOneStackToSort {
    public static void sortStackByStack(Stack<Integer> stack) {
        Stack<Integer> temp = new Stack<>();
        while (!stack.isEmpty()) {
            //stack.peek返回的是一个值，相当于获取一个数，
            //但是stack.pop会在这个stack里面删除一个元素。
            Integer curr = stack.pop();
            while (!temp.isEmpty() && curr > temp.peek()) {
                stack.push(temp.pop());
            }
            //否则的话就把原始栈顶的元素插到临时栈
            temp.push(curr);
        }
        //最后，舍弃临时栈，把临时栈的倒转回原始栈，就是"对一个栈里的整型数据，按升序进行排序"
        while (!temp.isEmpty()){
            stack.push(temp.pop());
        }
    }

    public static void main(String[] args) {
        Stack<Integer> test = new Stack<>();
        test.push(3);
        test.push(1);
        test.push(6);
        test.push(2);
        test.push(5);
        test.push(4);
        sortStackByStack(test);
        System.out.println(test.pop());
        System.out.println(test.pop());
        System.out.println(test.pop());
        System.out.println(test.pop());
        System.out.println(test.pop());
        System.out.println(test.pop());
    }
}
