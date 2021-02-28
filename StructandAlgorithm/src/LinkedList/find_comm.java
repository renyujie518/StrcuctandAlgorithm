package LinkedList;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName find_comm.java
 * @Description   给定两个有序链表的头指针 head1 和 head2，打印两个链表的公共部分。
 * 首先分析看到的是有序链表，这样问题就变的简单了许多。
 *
 * 从两个链表的头进行如下判断
 *
 * 如果head1的值小于head2的值，则head1向后移动
 * 如果head2的值小于head1的值，则head2向后移动
 * 如果head2的值等于head1的值，则打印这个值，并且head1和head2都向后移动
 * 循环的过程中如果head1或者head2有一个为空，则结束循环
 *
 *head1: 1->2->5->8->9->10->22->null
 *head2: 5->4->3->2->1->null
第一次：1比5小，head1下移
第二次：2比5小，head1下移
第三次：5=5，打印，head1和head2同时下移
第四次：4比8小，head2下移
…
直到一方移动到null，结束整个过程。
 *
 *
 * @createTime 2021年02月26日 16:35:00
 */
public class find_comm {
    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        node1.next = new ListNode(2);
        node1.next.next = new ListNode(5);
        node1.next.next.next = new ListNode(8);
        node1.next.next.next.next= new ListNode(9);
        node1.next.next.next.next.next = new ListNode(10);
        printLinkedList(node1);

        ListNode node2 = new ListNode(5);
        node2.next = new ListNode(4);
        node2.next.next = new ListNode(3);
        node2.next.next.next= new ListNode(2);
        node2.next.next.next.next = new ListNode(2);
        printLinkedList(node2);
        commonPrint(node1,node2);

    }

    public static void commonPrint(ListNode node1,ListNode node2){
        while (node1.next != null && node2.next !=null){
            if (node1.val < node2.val){
                node1 = node1.next;
            }else if (node2.val< node1.val){
                node2 = node2.next;
            }else{
                System.out.println("common: "+node1.val);
                //别忘了要下移
                node1 = node1.next;
                node2 = node2.next;
            }
        }
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
