package LinkedList;

import java.util.Stack;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName IsPalindromeList.java
 * @Description 判断一个链表是否是回文（轴对称）结构
 *
 * 思路 1：
 *  * 1. 若可以使用额外空间的话，可以使用一个栈；
 *  * 2. 将链表从头节点灌入栈，然后出栈的时候，每遍历一个节点就和出栈的节点比较；
 *  * 3. 如果每次比较的节点值都相等，则说明此链表具有回文结构。
 *  * 4. 或者用两个指针，快指针走两步，慢指针走一步，等到快指针走完的时候，慢指针大约走到链表的中间节点（注意奇偶数节点的差异）；
 *  * 5. 然后将慢指针指向的节点以及后面的所有节点入栈，此时再从头遍历链表，不停的出栈比较，这也是可行的方法。
 *  * 6. 时间复杂度 O(N)。但空间复杂度高O(N)，适合笔试
 *  *
 *  * 思路 2：
 *  * 1. 若不使用辅助空间（要求空间复杂度O(1)）；
 *  * 2. 开始的时候还是使用快慢指针，当慢指针来到链表的中间节点的时候，令中间节点指向 null；
 *  * 3. 其次修改右半部分每个节点的指针，让其指向前节点；
 *  * 4. 然后，首尾两个指针分别对应比较，一直到比到中间节点都是相等的话，则说明具有回文结构；
 *  * 5. 最后还需要将右半部分节点的 next 指针再次修改回来即可；
 *  * 6. 注意：对于偶数个节点，慢指针需要来到两个相同节点的前一个节点。
 *  7. 空间复杂度O(1)，只用到有限几个变量，适合面试
 *
 *  这里包含一个小题  找链表的中点。
 *  思路：
 *
 * 2个指针从链表的头开始。
 * 一个指针每步+1，一个指针每步+2
 * 然后跑的快的指针到链表尾部的时候，那个慢一点的指针就是中点了
 *
 * @createTime 2021年02月26日 18:54:00
 */
public class IsPalindromeList {
    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        node1.next = new ListNode(2);
        node1.next.next = new ListNode(5);
        node1.next.next.next = new ListNode(2);
        node1.next.next.next.next = new ListNode(1);

        ListNode node2 = new ListNode(1);
        node2.next = new ListNode(2);
        node2.next.next = new ListNode(5);
        node2.next.next.next = new ListNode(5);
        node2.next.next.next.next = new ListNode(2);
        node2.next.next.next.next.next = new ListNode(1);

        printLinkedList(node1);
        System.out.println("使用栈方法判定奇数个是否回环： "+useStack(node1));
        printLinkedList(node2);
        System.out.println("使用栈方法判定偶数个是否回环： "+useStack(node2));
        System.out.println("====================");

        printLinkedList(node1);
        System.out.println("使用一半栈方法判定奇数个是否回环： "+useHalftack(node1));
        printLinkedList(node2);
        System.out.println("使用一半栈方法判定偶数个是否回环： "+useHalftack(node2));
        System.out.println("====================");

        printLinkedList(node1);
        System.out.println("使用快慢指针方法判定奇数个是否回环： "+use2Point(node1));
        printLinkedList(node2);
        System.out.println("使用快慢指针方法判定偶数个是否回环： "+use2Point(node2));
    }
    //法1
    public static boolean useStack(ListNode head){
        if (head == null || head.next == null){
            return true;
        }
        Stack<ListNode> stack = new Stack<>();
        ListNode curr = head;
        //将链表中的元素入栈
        while (curr != null){
            stack.push(curr);
            curr = curr.next;
        }
        // 从头节点开始遍历，比较每个节点值与栈中弹出的节点值是否相等
        while (head != null){
            if (head.val != stack.pop().val){  //栈顶弹出的即是逆序的
                return false;   //一旦不一致就返回false
            }
            head = head.next;
        }
        return true;
    }

    //法2  使用 N/2 的辅助空间（不全入栈，用一个指针标记右半部分，只把一半入栈，相当于法1稍微优一点）
    public static boolean useHalftack(ListNode head){
        if (head == null || head.next == null){
            return true;
        }
        ListNode curr = head;
        ListNode right = head.next;  //这就是那个右指针。这个右指针快一步
        while (curr.next != null && curr.next.next != null){
            right = right.next;
            curr = curr.next.next;
        }
        //将右半部分的节点入栈
        Stack<ListNode> stack = new Stack<>();
        while (right != null){
            stack.push(right);
            right = right.next;
        }
        //再出栈和链表的左半部分(从头开始)比较
        while (!stack.isEmpty()){
            if (head.val != stack.pop().val){
                return false;
            }
            head = head.next;
        }
        return  true;
    }


    //法3 快慢指针
    public static boolean use2Point(ListNode head){
        if (head == null || head.next == null){
            return true;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next !=null && fast.next.next !=null){
            slow = slow.next;
            fast = fast.next.next;   //快慢指针凸显
        }
        //此时slow来到了中间节点(按照路程想，快的速度是慢的两倍，快的到终点，慢的就在中点)
        //对于奇数个节点，slow 来到中间节点.对于偶数个节点，slow 来到相同中间节点的前一个节点

        //接下来要让右半部分(slow.next,不论奇偶，.next一下就是右半部分)全部翻转，调用reverseList
        ListNode secondHalfstart = reverseList(slow.next);
        System.out.println("右边反转后的链表是：");
        printLinkedList(secondHalfstart);
        //此时，比如1 2 5 2 1 -> 1 2 5 1 2  1 2 5 5 2 1 -> 1 2 5 5 1 2
        //经过上面的 while 后，slow仍然是中间节点

        //判断是否是回文链表
        ListNode p1 = head;
        ListNode p2 = secondHalfstart;
        boolean res = true;
        while (res && p2 != null){
            if (p1.val != p2.val){
                res = false;
            }
            p1 = p1.next;
            p2 = p2.next;
        }
        //还原链表并返回
        slow.next = reverseList(secondHalfstart); //对应138行，再拼回来
        System.out.println("修复完的链表是：");
        printLinkedList(head);
        return res;
    }

    //反转链表
    public static ListNode reverseList(ListNode head){
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null){
            ListNode temp =  curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
        }
        return prev;
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
