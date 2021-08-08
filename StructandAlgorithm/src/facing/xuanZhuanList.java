package facing;
import LinkedList.ListNode;
import util.Array2ListNode;
import java.util.Scanner;


/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName xunhaunList.java
 * @Description
 * 给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置
 * likou  61
 * 思路；
 * 找到倒数第K个节点的前一个节点，然后让链表首尾相连，第K个节点作为链表旋转后的新的头节点，其前一个节点作为链表旋转后的尾节点。
 * 一提到寻找倒数第k个节点  立马想到快慢指针
 *
 * 注意：慢指针slow所指节点的下一个节点就是倒数第K个节点
 * https://leetcode-cn.com/problems/rotate-list/solution/dong-hua-yan-shi-kuai-man-zhi-zhen-61-xu-7bp0/
 * @createTime 2021年08月08日 15:08:00
 */
public class xuanZhuanList {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String str = sc.nextLine();
            String[] s = str.split(" ");
            int k = sc.nextInt();
//            System.out.println(data.toString());
//            System.out.println(k);

            //这里想把输入的一个数组变为链表
            ListNode head = Array2ListNode.Array2ListNode(s);
            //打印这个链表
            printLinkedList(head);
            ListNode newListNode = rotateRight(head, 2);
            //转换之后
            printLinkedList(newListNode);
        }
    }
    public static ListNode rotateRight(ListNode head, int k) {
        if (head == null) {
            return head;
        }

        // 计算链表中节点个数
        int len = calculateLen(head);
        //注意到当向右移动的次数 k≥n 时
        //我们仅需要向右移动 k mod n 次即可。因为每 n次移动都会让链表变为原状
        k = k%len;

        // 慢指针初始指向头节点
        ListNode slow = head;
        // 快指针初始指向头节点
        ListNode fast = head;

        // 快指针先向前移动k步
        for(int i = 0; i < k; i++) {
            fast= fast.next;
        }

        // 快慢指针同时向前移动，直到快指针指向的节点的末尾
        // 下一个节点为null
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }

        // 快指针此时在链表末尾
        // 然后其指向的节点的后继指针指向头节点
        // 这时链表首尾相连成环
        fast.next = head;
        // 新的头节点是慢指针所指节点的下一个节点（也是倒数第k个节点）
        head = slow.next;
        // 慢指针所指节点的的后继指针指向null
        // 断开环
        slow.next = null;
        return head;
    }

    private static int calculateLen(ListNode head){
        int len = 0;
        while (head!=null) {
            head = head.next;
            len++;
        }
        return len;
    }

    public static void printLinkedList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }

}
