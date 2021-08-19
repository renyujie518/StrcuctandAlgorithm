package facing;

import LinkedList.ListNode;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName resverseList.java
 * @Description 反转链表
 * class Node {
 *     public int value;
 *     public Node next;
 *     public Node(int data) {
 *         this.value = data;
 *     }
 * }
 * @createTime 2021年08月19日 15:10:00
 */
public class resverseList {
    //递归
    public static ListNode reverseListWithRecursion(ListNode head) {
        //边界
        if (head == null || head.next == null) {
            return null;
        }
        ListNode next = head.next;
        head.next = null;
        ListNode newHead = reverseListWithRecursion(next);
        //拼接
        next.next = head;
        return newHead;
    }

    //迭代  头插法
    public static ListNode reverseListWithDieDai(ListNode head) {
        //首先设立个虚节点
        ListNode newList = new ListNode(-1);
        while (head != null) {
            //取出下一个节点
            ListNode next = head.next;
            //拼接
            head.next = newList.next;
            newList.next = head;
            head = next;
        }
        //再去掉虚节点
        return newList.next;
    }
}
