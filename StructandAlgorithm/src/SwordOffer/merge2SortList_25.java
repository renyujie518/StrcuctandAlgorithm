package SwordOffer;

import LinkedList.ListNode;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName merge2SortList.java
 * @Description 合并两个排序的链表
 * 思路：
 * 链表递增 的，因此容易想到使用双指针遍历两链表，根据val 的大小关系确定节点添加顺序，两节点指针交替前进，直至遍历完毕。
 *
 * 引入伪头节点： 由于初始状态合并链表中无节点，因此循环第一轮时无法将节点添加到合并链表中。
 * 解决方案：初始化一个辅助节点 dum 作为合并链表的伪头节点，将各节点添加至 dum 之后
 * @createTime 2021年08月19日 21:23:00
 */
public class merge2SortList_25 {
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode dum = new ListNode(0);
        ListNode curr = dum;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                curr.next = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
            }
            curr = curr.next;
        }
        //走到这里需要判定下谁长，谁先到，把另外的全放进去
        curr.next = l1 == null ? l2 : l1;
        return dum.next;
    }

    public static ListNode mergeTwoListsWithRecursion(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        if (l1.val <= l2.val) {
            l1.next = mergeTwoListsWithRecursion(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoListsWithRecursion(l1, l2.next);
            return l2;
        }

    }

}
