package LinkedList;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName findLoopNode.java
 * @Description 判断单向链表有没有环，没有返回null，有的话返回入环的节点
 * 注意： 单向链表如果有环就一定走不出去了，因为只有一个next。所以有环的链表没有null
 * 主要使用快慢指针的做法
 * 归结一句话：
 *   快走2，慢走1，如果有环，一定会相遇。且走过的路一定< 2*list.length(追逐赛，速度两倍，不可能超过2圈)
 *   相遇后，就让其中一个指针回到链表的头部，另一个不动，然后两个指针再一起走，每次1步，再相遇的节点就是入环节点
 * @createTime 2021年02月28日 13:02:00
 */
public class findLoopNode {

    public static ListNode findLoopNode(ListNode head){
        if (head == null || head.next == null || head.next.next == null){  //有环的链表长度至少为3
            return null;
        }

        ListNode slow = head.next;
        ListNode fast = head.next.next;
        while (fast != slow){ // 快指针和慢指针在环中相遇的时候就停
            //快指针肯定走的快，要是快指针首先触碰到null就代表没环
            if(fast.next == null || fast.next.next == null){
                return null;
            }
            fast = fast.next.next;
            slow = slow.next;
        }
        //此时，如果运行到这里，代表快慢指针在环中相遇(同时验证必有还)
        //相遇后，就让其中一个指针回到链表的头部，另一个不动
        fast = head;
        //然后两个指针再一起走，每次1步，再相遇的节点就是入环节点
        while (fast != slow){
            slow = slow.next;
            fast = fast.next;
        }
        //运行到这里，已经找到入环节点了且fast = slow，返回哪个指针都可以
        return slow;

    }

}
