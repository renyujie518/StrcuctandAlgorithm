package SwordOffer;

import java.util.LinkedList;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName maxInQueue_59.java
 * @Description 队列的最大值
 * 请定义一个队列并实现函数 max_value 得到队列里的最大值，要求函数max_value、push_back 和 pop_front 的均摊时间复杂度都是O(1)。
 *
 * 单调的双向队列：一端既可以入队又可以出队的队列，从队首到队尾是单调递减的
 * 从队列尾部插入元素时，我们可以提前取出队列中所有比这个元素小的元素，使得队列中只保留对结果有影响的数字、
 * 那么如何高效实现一个始终递减的队列呢？（从队首到队尾是单调递减的。）
 * 我们只需要在插入每一个元素 value 时，从队列尾部依次取出比当前元素 value 小的元素，
 * 直到遇到一个比当前元素大的元素 value' 即可。
 * 在获取最大值时，直接取maxQueue队首的元素就可以了
 *
 * @createTime 2021年08月27日 23:39:00
 */
public class maxInQueue_59 {
    //用于元素入队出队(是真正要实现的队列)
    private LinkedList<Integer> originQueue;
    // 单调队列，队首元素是originQueue中元素的最大值（是为了完成返回最大值的题意 ）
    private LinkedList<Integer> maxQueue;

    public maxInQueue_59(){
        originQueue = new LinkedList<>();
        maxQueue = new LinkedList<>();
    }

    //获取最大值
    public int max_value() {
        if (maxQueue.isEmpty()) {
            return -1;
        }
        return maxQueue.peekFirst();
    }

    public void push_back(int value) {
        //首先每次往队列中放都是放队尾
        originQueue.addLast(value);
        //在跟新maxQueue的队尾元素之前
        //为了保持maxQueue的单调性,如果当前入队元素大于等于maxQueue队尾元素的最大值,那么就需要将maxQueue队尾元素出队(小的本身也用不到)
        // 直到队列为空或者新的队尾元素大于入队元素（保证了maxQueue从头到尾是递减的）
        while (!maxQueue.isEmpty() && value >= maxQueue.peekLast()) {
            maxQueue.removeLast();
        }
        maxQueue.addLast(value);
    }


    public int pop_front(){
        if (originQueue.isEmpty()) {
            return -1;
        }
        //在移除originQueue队首元素时，如果移除元素值等于maxQueue队首元素值
        // 则需要将maxQueue队首元素出队，因为它已经不再队列originQueue中了
        //（注意，本题的初衷是构造一个队列，这里只是取巧用了LinkedList的API,真正的原始数据都在originQueue，
        //maxQueue只是为了辅助得到max,originQueue没有的，maxQueue都不考虑）
        int originHead = originQueue.removeFirst();
        if (originHead == maxQueue.peekFirst()) {
            maxQueue.removeFirst();
        }
        return originHead;
    }


}
