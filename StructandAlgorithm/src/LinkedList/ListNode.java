package LinkedList;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName ListNode.java
 * @Description 单链表模拟
 * @createTime 2021年02月26日 14:26:00
 */
public class ListNode {
     public int val;
     public ListNode next;
     public ListNode() {}
     public ListNode(int val) { this.val = val; }
     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 }