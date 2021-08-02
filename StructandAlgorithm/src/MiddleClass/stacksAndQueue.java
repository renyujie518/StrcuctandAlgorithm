package MiddleClass;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName stacksAndQueue.java
 * @Description
 * 如何仅用队列结构实现栈结构? 如何仅用栈结构实现队列结构?
 * 考察的是对这两种结构的熟悉程度
 *
 * 栈先进后出  可以用两个队列  比如用户给12345  希望弹出54321  在第一个队列中全部放入：队列中从左到右54321
 * 先把1234放在另一个队列里（在另一个队列中从左到右是4321）把5给用户返回 接下来操作另一个队列当做新队列，交替进行
 * 总结一句话就是  留着最后一个数给用户返回
 *
 * 队列先进先出  可以用两个栈 一个push栈，一个pop栈，倒一下
 * 1.push栈中倒东西一次倒完 2.pop中有东西不要，push栈不要从里面倒数据
 *
 * @createTime 2021年07月27日 22:04:00
 */
public class stacksAndQueue {
    //用两个栈实现队列
    public static class TwoStackQueue {
        private Stack<Integer> pushStack;
        private Stack<Integer> popStack;

        public TwoStackQueue(){
            pushStack = new Stack<Integer>();
            popStack = new Stack<Integer>();
        }
        //push的操作全由pushStack完成,这是stack所独有，在这里是要利用栈的push操作
        //队列的队尾添加add功能直接用栈的push实现
        public void add(int pushInt) {
            pushStack.push(pushInt);
        }

        //实现新队列的poll功能（poll：从队首获取元素，同时获取的这个元素将从原队列删除）
        public int poll() {
            if (popStack.empty() && pushStack.empty()) {
                throw new RuntimeException("Queue is empty!");
                //以下这部分就是倒数据的核心逻辑
            } else if (popStack.empty()) {//首先负责弹出的popStack必须是空状态下才能从负责压入的pushStack中取数
                while (!pushStack.empty()) {
                    //具体取的过程遵循"一次取完" 即取到上一个容器不能再取为止
                    popStack.push(pushStack.pop());
                }
            }
            //在进过上述的倒转后才从popStack真正弹出
            return popStack.pop();
        }

        //实现新队列的peek功能（peek：查看首个元素，不会移除首个元素，如果队列是空的就返回null）
        //基本与poll的实现逻辑类似（因为本身两者的差别就是到底移不移除，这与popStack（辅助栈）中的是怎么放的逻辑无关
        //最终的差别是一个调用栈的pop()功能，与队列的poll对应，都移除
        //一个是调用栈的peek（）功能，与队列的peek对应，不移除）

        /**
         仍然是 负责弹出的popStack必须是空状态下才能从负责压入的pushStack中取数
               具体取的过程遵循"一次取完" 即取到上一个容器不能再取为止
         在进过上述的倒转后才从popStack真正弹出
         */
        public int peek() {
            if (popStack.empty() && pushStack.empty()) {
                throw new RuntimeException("Queue is empty!");
            } else if (popStack.empty()) {
                while (!pushStack.empty()) {
                    popStack.push(pushStack.pop());
                }
            }
            return popStack.peek();
        }
    }


    //用两个队列实现栈  注意这路的队列底层使用LinkedList初始化的
    public static class TwoQueuesStack{
        private Queue<Integer> queue;
        private Queue<Integer> help;

        public TwoQueuesStack() {
            queue = new LinkedList<Integer>();
            help = new LinkedList<Integer>();
        }

        //栈的压入栈顶push功能直接用队列的add实现
        public void push(int pushInt) {
            //add:将元素插入队列尾部(保证了队列的先进先出原则)
            queue.add(pushInt);
        }

        //peek()函数返回栈顶的元素，但不弹出该栈顶元素。
        public int peek() {
            if (queue.isEmpty()) {
                throw new RuntimeException("Stack is empty!");
            }
            while (queue.size() != 1) {//这就是在思路中的例子，把队列中的队首的前n-1个都放到help中，在原队列中只剩一个
                help.add(queue.poll());
            }
            int res = queue.poll();//原队列中的剩余的这一个是队尾的最后一个，这时候我再弹出，刚好符合栈的先进后出
            //这时候有个注意点 help中到底再复原不复原  因为help.add（添加队尾）之前取出的队尾的最后一个，其实就相当于复原了上一个队列
            //为什么复原也很好理解，我目前是模拟栈的peek啊，本身就是不移除数据的
            help.add(res);
            swap();
            //在处理完交换逻辑后，真正返回结果
            return res;
        }

        //实现新栈的pop功能（pop()函数返回栈顶的元素，并且将该栈顶元素出栈。）
        //基本与peek的实现逻辑类似（因为本身两者的差别就是到底移不移除，这与怎么交换的逻辑无关
        //最终的差别是一个在取到队尾的最后一个的时候  如果复原，就是模拟栈的peek  如果不复原，就相当于剔除，模拟栈的pop
        public int pop() {
            if (queue.isEmpty()) {
                throw new RuntimeException("Stack is empty!");
            }
            while (queue.size() > 1) {
                help.add(queue.poll());
            }
            int res = queue.poll();
            swap();
            return res;
        }

        //在思路中有这么一句话"接下来操作另一个队列当做新队列"  这里实现的就是这种交换，这里是地址指针指向的交换，真正的内存地址实际没换
        private void swap() {
            Queue<Integer> tmp = help;
            help = queue;
            queue = tmp;
        }

    }


}
