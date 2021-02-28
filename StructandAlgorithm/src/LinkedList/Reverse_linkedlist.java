package LinkedList;

import java.util.LinkedList;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName Reverse_linkedlist.java
 * @Description 反转链表

 * * public class ListNode {
 *  *     int val;
 *  *     ListNode next;
 *  *     ListNode() {}
 *  *     ListNode(int val) { this.val = val; }
 *  *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 *  * }
 *
 *
 *  public class DoubleNode {
 *     int val;
 *     DoubleNode last;   // 上一个
 *     DoubleNode next;
 *
 *     DoubleNode(int val) {
 *         this.val = val;
 *     }
 * }

 * @createTime 2021年02月26日 13:51:00
 */
public class Reverse_linkedlist {
    public static void main(String[] args) {
        ListNode first = new ListNode(2);
        ListNode test = new ListNode(1, first);
        test.next.next = new ListNode(3);

        System.out.println(test.val);
        System.out.println(test.next.val);
        System.out.println(test.next.next.val);
        System.out.println("====迭代===");
        ListNode listNode1 = reverselinklist_diedai(test);
        System.out.println(listNode1.val);
        System.out.println(listNode1.next.val);
        System.out.println(listNode1.next.next.val);
        System.out.println("====递归===");
        ListNode listNode2 = reverselinklist_digui(listNode1);
        System.out.println(listNode2.val);
        System.out.println(listNode2.next.val);
        System.out.println(listNode2.next.next.val);

        System.out.println("====双链表===");
        DoubleNode doubleNode = new DoubleNode(8);
        doubleNode.last = new DoubleNode(7);
        doubleNode.next = new DoubleNode(9);
        System.out.println(doubleNode.last.val);
        System.out.println(doubleNode.val);
        System.out.println(doubleNode.next.val);
        System.out.println("====双链表反转===");
        DoubleNode doubleNode_hasdone = reverseDoubleList(doubleNode);
        System.out.println(doubleNode_hasdone.last);
        System.out.println(doubleNode_hasdone.val);
        System.out.println(doubleNode_hasdone.next.val);
        System.out.println(doubleNode_hasdone.next.next);

    }

   public static ListNode reverselinklist_diedai(ListNode head){
       // 单链表没有指向前一个节点的指针域，因此我们需要增加一个指向前一个节点的指针pre，
        // 用于存储每一个节点的前一个节点。此外，还需要定义一个保存当前节点的指针cur，以及下一个节点的next。
        // 定义好这三个指针后，遍历单链表，将当前节点的指针域指向前一个节点，之后将定义三个指针往后移动，
        // 直至遍历到最后一个节点停止
       ListNode pre = null;
       ListNode curr = head;

      while (curr != null){
          ListNode next = curr.next;  //next代表指向下一个节点
          curr.next = pre;//将当前节点的next域指向下一个节点
          pre = curr;  //pre前一个节点向后移动
          curr = next;//curr当前节点向后移动
      }
      return pre;  //注意，这里返回的是pre,通过视屏了解到最后需要返回的头结点（位置是原来的最后一个）是pre
       //https://leetcode-cn.com/problems/reverse-linked-list/solution/shi-pin-jiang-jie-die-dai-he-di-gui-hen-hswxy/
   }


   public static ListNode reverselinklist_digui(ListNode head){
        //递归终止条件
       if (head == null || head.next == null){
           return head;  //相当于就剩他自己一个
       }

       // 想象递归已经层层返回，到了最后一步
       // 以链表 1->2->3->4->5 为例，现在链表变成了 5->4->3->2->null，1->2->null（是一个链表，不是两个链表）
       // 此时 newHead是5，head是1（newHead就是一个超级头结点，或者说虚拟头结点。它被定义为递的终点处的head节点，然后在归的过程传递回来，顺便将链表反转。）
       ListNode hasdone = reverselinklist_digui(head.next); //把大问题分为 head和head.next两部分

       // 最后的操作是把链表 1->2->null 变成 2->1->null
       // head是1，head.next是2，head.next.next = head 就是2指向1，此时链表为 2->1->2
       head.next.next = head;
       // 防止链表循环，1指向null，此时链表为 2->1->null，整个链表为 5->4->3->2->1->null
       head.next = null;
       return  hasdone;
   }

   public static DoubleNode reverseDoubleList(DoubleNode head){
        DoubleNode pre = null;
       DoubleNode next = null;
       while (head != null){
           next = head.next;
           head.next = pre;
           head.last = next;
           pre = head;
           head = next;
       }
       return pre;
   }


    public static void printLinkedList(ListNode head) {
        System.out.print("Linked List: ");
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }

    public static void printDoubleLinkedList(DoubleNode head) {
        System.out.print("Double Linked List: ");
        DoubleNode end = null;
        while (head != null) {
            System.out.print(head.val + " ");
            end = head;
        }
    }

}
