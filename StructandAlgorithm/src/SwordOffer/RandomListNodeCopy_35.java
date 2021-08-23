package SwordOffer;

import java.util.HashMap;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName RandomListNodeCopy_35.java
 * @Description 复杂链表的复制
 * 输入一个复杂链表（每个节点中有节点值，以及两个指针，一个指向下一个节点，另一个特殊指针指向任意一个节点），返回结果为复制后复杂链表的 head。
 *
 * 思路：
 * 运用hashmap  建立 原节点 -> 新节点 的映射关系来建立新链表。
 * 因为每个新节点都和原节点是对应的，建立这个映射关系后，
 * 新链表每个节点的 next 和 random 都可以通过对应原链表节点的 next 和 random 来获取。

 * @createTime 2021年08月21日 19:18:00
 */
public class RandomListNodeCopy_35 {
    public class RandomListNode {
        int val;
        RandomListNode next = null;
        RandomListNode random = null;

        RandomListNode(int val) {
            this.val = val;
        }
    }

    public RandomListNode RandomListNodeCopy(RandomListNode head) {
        if (head == null) {
            return null;
        }
        RandomListNode curr = head;
        HashMap<RandomListNode, RandomListNode> map = new HashMap<>();  //key =原链表节点  value =新链表对应节点
        while (curr != null) {
            map.put(curr, new RandomListNode(curr.val));
            curr = curr.next;
        }
        curr = head;
        //构建新链表的 next 和 random 指向
        while (curr != null) {
            map.get(curr).next = map.get(curr.next);
            map.get(curr).random = map.get(curr.random);
            curr = curr.next;
        }
        //返回新链表头
        return map.get(head);
    }


}
