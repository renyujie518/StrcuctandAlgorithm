package LinkedList;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName DoubleNode.java
 * @Description 双链表模拟
 * @createTime 2021年02月26日 15:03:00
 */
public class DoubleNode {
    int val;
    DoubleNode last;   // 上一个
    DoubleNode next;

    DoubleNode(int val) {
        this.val = val;
    }
}
