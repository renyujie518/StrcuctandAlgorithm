package LinkedList;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName FindFirstIntersectNode.java
 * @Description
 * 两个单链表相交的一系列问题
 *  *
 *  * 题目描述：
 *  * 给定两个单链表，如果这两个链表相交，则返回相交的第一个节点；如果不相交，则返回 null。
 *  * 链表可能有环，也有可能无环。
 *  * 时间复杂度 O(N+M)，空间复杂度 O(1)。
 *  *
 *  * 思路：
 *  * 1. 先判断链表是否有环：
 *  *    1.1）方法一：使用快慢指针；
 *  *    1.2) 方法二：使用哈希表；
 *
 *
 *  * 2. 如果两个链表都没有环：
 *  *    2.1) 要是使用 map 的话，先将链表 1 的节点放到 map 中，然后每次遍历链表 2 中的一个节点，
 *  *         都去 map 中查，第一个在的节点，就是两个链表相交的第一个节点；如果链表 2 遍历到最后都没有在 map 中找到对应的节点，
 *  *         则说明该两个链表不相交。
 *  *    2.2) 要是不用 map 的话，可以先遍历一遍链表 1，得到链表 1 的长度 len1 和最后一个节点 end1；链表 2 做同样的操作，得到长度 len2 和最后一个节点 end2；
 *  *         比较 end1 和 end2 是不是同一个节点，即内存地址是否相同。
 *  *         2.2.1) 如果 end1 != end2，则该两个链表不可能相交，因为如果相交的话，它俩的最后一个节点应该是同一个；
 *  *         2.2.2) 如果 end1 == end2，则该两个链表是相交的，但未必是第一个相交的节点。此时假设链表 1 的长度为 100，链表 2 的长度为 80，
 *  *              可以先让链表 1 走 20 步，然后链表 1 和链表 2 再一起走，最后就能碰到相交的节点。
 *  * 3. 如果一个链表无环，另一个链表有环，则不可能相交。
 *
 *
 *  * 4. 如果两个链表都有环，则有三种情况：
 *  *    4.1) 如果各自成环，则不可能相交；
 *  *    4.2) 如果两个链表先相交，然后再共享同一个环，则含有相交的第一个节点；
 *  *    4.3) 如果两个链表先不相交，而是直接共享同一个环，则含有相交的第一个节点。
 *  * 5. 如何区分上面的三种情况？
 *  *    5.1) 如果链表 1 的第一个入环节点的内存地址 等于 链表 2 的第一个入环节点的内存地址，则属于情况 4.2)；
 *  *         这时不看带有环的部分，此时又回到了和 2) 相同的问题，一样的操作。
 *  *    5.2) 如果链表 1 的第一个入环节点的内存地址 不等于 链表 2 的第一个入环节点的内存地址，则此时有可能是情况 4.1) 或者是情况 4.3)；
 *  *         5.2.1) 此时不断沿着链表 1 的第一个入环节点的 next 往后判断，如果走了一圈又转回自己，还没有遇到链表 2 的第一个入环节点的话，则为情况 4.1)；
 *  *         5.2.2) 如果在遍历的时候链表 1 在不停的 next 之后遇到链表 2 的第一个入环节点的话，则为情况 4.3)，
 *  *                此时返回距离链表 1 更近的入环节点也可以，返回距离链表 2 更近的入环节点也可以。
 * @createTime 2021年02月27日 23:40:00
 */
public class FindFirstIntersectNode {
    public static void main(String[] args) {
        // 1->2->3->4->5->6->7->null
        ListNode head1 = new ListNode(1);
        head1.next = new ListNode(2);
        head1.next.next = new ListNode(3);
        head1.next.next.next = new ListNode(4);
        head1.next.next.next.next = new ListNode(5);
        head1.next.next.next.next.next = new ListNode(6);
        head1.next.next.next.next.next.next = new ListNode(7);

        // 0->9->8->6->7->null
        ListNode head2 = new ListNode(0);
        head2.next = new ListNode(9);
        head2.next.next = new ListNode(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        printLinkedList(head1);
        printLinkedList(head2);
        System.out.println("两无环,第一个相交节点是 ："+FindFirstTntersectNode(head1, head2).val);
        System.out.println("==================");

        // 1->2->3->4->5->6->7->4...
        head1 = new ListNode(1);
        head1.next = new ListNode(2);
        head1.next.next = new ListNode(3);
        head1.next.next.next = new ListNode(4);
        head1.next.next.next.next = new ListNode(5);
        head1.next.next.next.next.next = new ListNode(6);
        head1.next.next.next.next.next.next = new ListNode(7);
        head1.next.next.next.next.next.next = head1.next.next.next; // 7->4

        // 0->9->8->2...
        head2 = new ListNode(0);
        head2.next = new ListNode(9);
        head2.next.next = new ListNode(8);
        head2.next.next.next = head1.next; // 8->2
        System.out.println("两有环，\"弹弓+单耳环\",第一个相交节点是 ："+FindFirstTntersectNode(head1, head2).val);
        System.out.println("==================");

        // 0->9->8->6->4->5->6..
        head2 = new ListNode(0);
        head2.next = new ListNode(9);
        head2.next.next = new ListNode(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println("两有环,\"猫耳朵+圆脸\"。第一个相交节点是 ："+FindFirstTntersectNode(head1, head2).val);

    }

    //返回相交的第一个节点
    public static ListNode FindFirstTntersectNode(ListNode head1,ListNode head2){
        if (head1 == null || head2 == null){
            return null;
        }
        ListNode loop1 = findLoopNode.findLoopNode(head1);  //调用寻找入环节点的方法。如果有，返回入环的节点
        ListNode loop2 = findLoopNode.findLoopNode(head2);

        if (loop1 == null && loop2 == null){  // 两个链表都无环的相交问题
            return  noloop(head1,head2);
        }
        if (loop1 != null && loop2 !=null){  // 两个链表都有环的相交问题
            return bothloop(head1,head2,loop1,loop2);
        }
        // 如果一个链表无环，另一个链表有环，则不可能相交
        return null;
    }


    //考虑相交，如果两个都没有环，要么是"双平行"，要么是"弹弓型"，注意，这个弹弓可能不等长，当然也有可能某个链表是null，那更不可能相交
    private static ListNode noloop(ListNode head1, ListNode head2) {
        if (head1 == null || head2 == null){
            return null;
        }
        ListNode curr1 = head1;
        ListNode curr2 = head2;
        //这里的n被复用了，很巧妙，最后得到的n是两个链表长度的差值
        int n =0;
        while (curr1.next !=null){ //注意，这里需要统计的是链表的长度，所以不是curr1 != null,而是其下一个为空，保证把链表的所有的节点包进来
            n++;
            curr1 = curr1.next;
        }
        while (curr2.next != null){
            n--;
            curr2 = curr2.next;
        }
        //现在curr1和curr2都走到底了，如果两者的内存地址不一样，代表没有相交（"平行"），那就不可能相交
        if (curr1 != curr2){
            return null;
        }
        //以下就是考虑"不等长弹弓"的情况，一般来说不等长，可以用两链表长度的差值n来判定
        //复用curr1和curr2，把长的给curr1,短的给curr2
        curr1 = n>0 ? head1 : head2;  //由于是先加后减，n>0,head1长
        curr2 = curr1 == head1 ? head2 : head1;
        n = Math.abs(n);
        //此时假设链表 1 的长度为 100，链表 2 的长度为 80，
        // 可以先让链表 1 走 20 步(差值n)，然后链表 1 和链表 2 再一起走，最后就能碰到相交的节点，该节点就是第一个相交节点
        while (n != 0){
            n--;
            curr1 = curr1.next;  //长的先走
        }
        while (curr1 != curr2){  // 一旦相等就停止.此时返回哪一个都无所谓
            curr1 = curr1.next;
            curr2 = curr2.next;
        }
        return curr1;
    }

    ///考虑相交，如果两个都有环，有三种情况 "双耳环" "弹弓+单耳环" "猫耳朵+圆脸"
    private static ListNode bothloop(ListNode head1, ListNode head2, ListNode loop1, ListNode loop2) {
        ListNode curr1 = head1;
        ListNode curr2 = head2;

        //先考虑"弹弓+单耳环"（两loop相等），要找的是第一个相交的点，和下面的环就没关系，那就可以套用noloop的方法，找到弹弓交叉点即可
        //唯一有所不同的是，，终止点不再是传统的null，而是loop1或loop2(两相等) ，这样就把环给截断了
        if (loop1 == loop2){
            curr1 = head1;
            curr2 = head2;
            int n =0;
            while (curr1 != loop1){
                n++;
                curr1 = curr1.next;
            }
            while (curr2 != loop2){
                n--;
                curr2 = curr2.next;
            }
            curr1 = n>0 ? head1 : head2;
            curr2 = curr1 == head1 ? head2 : head1;
            n = Math.abs(n);
            //先走差值，再遇相等返回
            while (n != 0){
                n--;
                curr1 = curr1.next;
            }
            while (curr1 != curr2){
                curr1 = curr1.next;
                curr2 = curr2.next;
            }
            return curr1;
        } else { //其余两种情况的分类还是要依靠loop1和loop2两个辅助条件
            // 不断沿着链表 1 的第一个入环节点（loop1）的 next 往后判断，如果走了一圈又转回自己，还没有遇到链表 2 的第一个入环节点的话，就是 "双耳环"
            //否则走着走着也遇到了loop2（内存地址一致）说明遇到了 "猫耳朵+圆脸"
            curr1 = loop1.next;
            while (curr1 != loop1){  //走一圈即可
                if (curr1 == loop2){  //猫耳朵+圆脸，这种情况下的第一个相交点其实是两个loop中的任意一个，只不过是远近问题
                    return loop1;
                    //return loop2;
                }
                curr1 = curr1.next;  //别忘了前进
            }  //走了一圈了没有触发上面的if   "双耳环"  没交点，返回null
            return null;
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
