package LinkedList;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName mergeTwoLists.java
 * @Description   合并两个有序链表
 * 首先，我们设定一个哨兵节点 prehead ，这可以在最后让我们比较容易地返回合并后的链表。我们维护一个 prev 指针，
 * 我们需要做的是调整它的 next 指针。然后，我们重复以下过程，直到 l1 或者 l2 指向了 null ：
 * 如果 l1 当前节点的值小于等于 l2 ，我们就把 l1 当前的节点接在 prev 节点的后面同时将 l1 指针往后移一位。
 * 否则，我们对 l2 做同样的操作。不管我们将哪一个元素接在了后面，我们都需要把 prev 向后移一位。

 * 在循环终止的时候， l1 和 l2 至多有一个是非空的。由于输入的两个链表都是有序的，
 * 所以不管哪个链表是非空的，它包含的所有元素都比前面已经合并链表中的所有元素都要大。
 * 这意味着我们只需要简单地将非空链表接在合并链表的后面，并返回合并链表即可。

 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 * @createTime 2021年03月07日 20:06:00
 */
public class mergeTwoLists {
    public  static ListNode mergeTwoSortList(ListNode l1,ListNode l2){

        ListNode prehead = new ListNode(-1);
        ListNode pre = prehead;
        while (l1 != null && l2 != null){
            if (l1.val <= l2.val){
                pre.next = l1;
                l1 = l1.next;
            }else {
                pre.next = l2;
                l2 = l2.next;
            }
            pre = pre.next;
        }
        // 合并后 l1 和 l2 最多只有一个还未被合并完，
        //我们直接将链表末尾指向未合并完的链表即可
        pre.next = l1 == null ? l2 : l1;
        return prehead.next;
    }
}
