package LinkedList;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName sortLinklLst.java
 * @Description 链表排序
 * 要求使用插入排序的方法对链表进行排序，插入排序的时间复杂度是 O(n^2)
 * 其中 nn 是链表的长度。这道题考虑时间复杂度更低的排序算法。题目的进阶问题要求达到
 * O(nlogn) 的时间复杂度和 O(1) 的空间复杂度，时间复杂度是O(nlogn) 的排序算法包括
 * 归并排序、堆排序和快速排序（快速排序的最差时间复杂度是 O(n^2)其中最适合链表的排序算法是归并排序。

 * 归并排序基于分治算法。最容易想到的实现方式是自顶向下的递归实现
 * 考虑到递归调用的栈空间，自顶向下归并排序的空间复杂度是O(logn)。
 * 如果要达到 O(1)的空间复杂度，则需要使用自底向上的实现方式。
 * 自底向上
 * 自底向上
 * 自底向上
 * @createTime 2021年03月07日 22:28:00
 */
public class sortLinklLst {
    public static void main(String[] args) {
        ListNode node2 = new ListNode(5);
        node2.next = new ListNode(4);
        node2.next.next = new ListNode(3);
        node2.next.next.next= new ListNode(2);
        node2.next.next.next.next = new ListNode(2);
        printLinkedList(node2);
        printLinkedList(merge_sort(node2));

    }

    //找到中间点，然后分割
    public static ListNode getMiddle(ListNode head) {
        if (head == null) {
            return head;
        }
        //快慢指针
        ListNode slow, fast;
        slow = fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // 合并排好序的链表,这个之前写过 mergeTwoLists
    public static ListNode merge(ListNode a, ListNode b) {
        ListNode dummyHead, curr;
        dummyHead = new ListNode(0);
        curr = dummyHead;
        while (a != null && b != null) {
            if (a.val <= b.val) {
                curr.next = a;
                a = a.next;
            } else {
                curr.next = b;
                b = b.next;
            }
            curr = curr.next;
        }
        // 合并后 l1 和 l2 最多只有一个还未被合并完，
        //我们直接将链表末尾指向未合并完的链表即可
        curr.next = (a == null) ? b : a;
        return dummyHead.next;
    }

    //单链表的归并排序
    public static ListNode merge_sort(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        //得到链表中间的数
        ListNode middle = getMiddle(head);
        ListNode sHalf = middle.next;
        //拆分链表
        middle.next = null;
        //递归调用
        return merge(merge_sort(head), merge_sort(sHalf));
    }
    public static void printLinkedList(ListNode head) {
        System.out.print("Linked List: ");
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }
}
