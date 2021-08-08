package MiddleClass;

import java.util.Stack;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName GetMinStack.java
 * @Description
 * 实现一个特殊的栈， 在实现栈的基本功能的基础上， 再实现返回栈中最小元素 的操作。
 * 要求： 1 . pop、 push、 getMinFromMinStackTop操作的时间复杂度都是O(1)； 2. 设计的栈类型可以 使用现成的栈结构
 *
 * 思路：
 * 用两个栈  一个数据栈 一个最小栈  在往数据栈中压入的时候看和最小栈的栈顶比，谁小压入谁
 * （有可能重复压入，比如新元素和没最小栈顶元素大，会把最小栈顶元素再压一次）
 * 要最小值就从最小栈中取
 * 弹出的时候同步弹出
 *
 *
 * add，push都可以向stack中添加元素。
 * add是继承自Vector的方法，且返回值类型是boolean。
 * push是Stack自身的方法，返回值类型是参数类类型。
 * 共同点：
 * peek，pop都是返回栈顶元素。
 * 不同点：
 * peek()函数返回栈顶的元素，但不弹出该栈顶元素。
 * pop()函数返回栈顶的元素，并且将该栈顶元素出栈。
 * @createTime 2021年07月27日 21:13:00
 */
public class GetMinStack {
    public static class myStack {
        private Stack<Integer> dataStack;
        private Stack<Integer> minStack;

        //构造函数
        public myStack(){
            this.dataStack = new Stack<Integer>();
            this.minStack = new Stack<Integer>();
        }

        public int getMinFromMinStackTop() {
            if (this.minStack.isEmpty()) {
                throw new RuntimeException("Your minStack is empty.");
            }
            //peek()函数返回栈顶的元素，但不弹出该栈顶元素。
            return this.minStack.peek();
        }

        public void push(int newNum) {
            if (this.minStack.isEmpty()) {
                //newNum表示要压入堆栈的元素,这一步代表初始的时候总要把第一个数放到最小栈中当基准
                this.minStack.push(newNum);
            } else if (this.getMinFromMinStackTop() >= newNum) {//如果新数比MinStack的顶部元素还小
                //就把更小的新数压入minStack
                this.minStack.push(newNum);
            }
            //两个栈同步进行
            this.dataStack.push(newNum);
        }

        //pop()函数返回栈顶的元素，并且将该栈顶元素出栈。
        public int pop() {
            if (this.dataStack.isEmpty()) {
                throw new RuntimeException("Your dataStack is empty.");
            }
            Integer value = this.dataStack.pop();
            if (value == this.getMinFromMinStackTop()) {//如果dataStack的顶部元素与MinStack的顶部元素一样
                //就弹出minStack的顶部元素（这是因为两者要同步，dataStack中都没这个数据了，minStack中也不该有，即使它比较小,
                //这在最后一个print中有体现）
                this.minStack.pop();
            }
            return value;
        }
    }

    public static void main(String[] args) {
        myStack myStack = new myStack();
        myStack.push(3);
        System.out.println(myStack.getMinFromMinStackTop());//3
        myStack.push(4);
        System.out.println(myStack.getMinFromMinStackTop());//3
        myStack.push(1);
        System.out.println(myStack.getMinFromMinStackTop());//1
        System.out.println(myStack.pop());//1
        System.out.println(myStack.getMinFromMinStackTop());//3


    }

}
