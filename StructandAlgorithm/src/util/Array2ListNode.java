package util;

import LinkedList.ListNode;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName Array2ListNode.java
 * @Description TODO
 * @createTime 2021年08月08日 16:36:00
 */
public class Array2ListNode {
    public static ListNode Array2ListNode(String[] str) {
        int[] data = new int[str.length];
        for (int i = 0; i < str.length; i++) {
            data[i] = Integer.parseInt(str[i]);
        }
        //这里想把输入的一个数组变为链表
        ListNode head = new ListNode(0);
        ListNode p = head;
        for(int i = 0; i < data.length; i++){
            p.next = new ListNode(data[i]);
            p = p.next;
        }
        //保证最后一位是null
        head = head.next;
        return head;
    }
}
