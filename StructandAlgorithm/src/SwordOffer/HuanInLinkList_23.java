package SwordOffer;

import LinkedList.ListNode;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName HuanInLinkList.java
 * @Description
 *  链表中环的入口结点作者：CyC2018
 *  思路：
 *主要使用快慢指针的做法
 * *归结一句话：
 * *快走2，慢走1，如果有环，一定会相遇。且走过的路一定<2*list.length(追逐赛，速度两倍，不可能超过2圈)
 * *相遇后，就让其中一个指针回到链表的头部，另一个不动，然后两个指针再一起走，每次1步，再相遇的节点就是入环节点
 *
 * @createTime 2021年08月19日 21:12:00
 */
public class HuanInLinkList_23 {
    public static ListNode HuanInLinkList(ListNode head) {
        //有环的长度至少为3
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        ListNode slow = head.next;
        ListNode fast = head.next.next;
        while (fast != slow) {
            if (fast.next == null | fast.next.next == null) {
                return null;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        fast = head;
        while (fast != slow) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }



}
