package SwordOffer;

import LinkedList.ListNode;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName deleteDuplicationAndBuBaoLiu_18.java
 * @Description
 * 在一个排序的链表中，存在重复的结点，请删除该链表中重复的结点，重复的结点不保留，返回链表头指针。
 * 例如，链表1->2->3->3->4->4->5 处理后为 1->2->5
 * @createTime 2021年08月18日 17:58:00
 */
public class deleteDuplicationAndBuBaoLiu_18 {
    public static ListNode deleteDuplicationAndBuBaoLiu(ListNode pHead) {
        if (pHead == null || pHead.next == null){
            return pHead;
        }
        ListNode next = pHead.next;
        if (pHead.val == next.val) { //有重复
            while (next != null && pHead.val == next.val){
                //因为升序  所以即使相同也在一堆  所以通过"跳跃"的方式（pHeap.next = pHeap.next.nexts）就相当于删掉了
                next = next.next;
            }
            return deleteDuplicationAndBuBaoLiu(next); //递归
        } else {//没重复 啥都不操作  只是对next递归
            pHead.next = deleteDuplicationAndBuBaoLiu(pHead.next);
            return pHead;
        }
    }
}
