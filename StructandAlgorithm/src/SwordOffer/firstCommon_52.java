package SwordOffer;

import LinkedList.ListNode;

import java.util.HashSet;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName firstCommmon_52.java
 * @Description
 * 两个链表的第一个公共结点

 * 设 A 的长度为 a + c，B 的长度为 b + c，其中 c 为尾部公共部分长度，可知 a + c + b = b + c + a。
   两个链表长度分别为L1+C、L2+C， C为公共部分的长度
   第一个人走了L1+C步后，回到第二个人起点走L2步；
    第2个人走了L2+C步后，回到第一个人起点走L1步。
当两个人走的步数都为L1+L2+C时即第一个公共节点
 * @createTime 2021年08月27日 11:39:00
 */
public class firstCommon_52 {
    public static ListNode firstCommon1(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode pa = headA;
        ListNode pb = headB;
        while (pa != pb) {
            pa = pa == null ? headB : pa.next;
            pb = pb == null ? headA : pb.next;
        }
        //至此两节点相遇在第一个公共节点
        return pa;//return pb;
    }


    //hashmap法
    public static ListNode firstCommon2(ListNode headA, ListNode headB) {
        HashSet<ListNode> visited = new HashSet<>();
        ListNode tmp = headA;
        while (tmp != null) {
            visited.add(tmp);
            tmp = tmp.next;
        }
        tmp = headB;
        while (tmp != null) {
            if (visited.contains(tmp)) {
                return tmp;
            }
            tmp = tmp.next;
        }
        return null;
    }
}

