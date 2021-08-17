package SwordOffer;

import LinkedList.ListNode;

import java.util.ArrayList;
import java.util.Stack;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName niXuPrintLinklist_06.java
 * @Description   逆序打印链表
 *
 * 有三种方法：
 *
 * 使用递归
 * 要逆序打印链表 1->2->3（3,2,1)，可以先逆序打印链表 2->3(3,2)，最后再打印第一个节点 1。
 * 而链表 2->3 可以看成一个新的链表，要逆序打印该链表可以继续使用求解函数，也就是在求解函数中调用自己，这就是递归函数。
 *
 *  使用头插法（虚节点,链表反转）
 * 使用头插法可以得到一个逆序的链表。
 * 头结点和第一个节点的区别：
 * 头结点是在头插法中使用的一个额外节点，这个节点不存储值；
 * 第一个节点就是链表的第一个真正存储值的节点。
 *
 * 使用栈
 * 栈具有后进先出的特点，在遍历链表时将值按顺序放入栈中，最后出栈的顺序即为逆序。
 *
 * @createTime 2021年08月17日 15:54:00
 */
public class niXuPrintLinklist_06 {
    public static ArrayList<Integer> niXuPrintLinklistWithRecursion(ListNode node) {
        ArrayList<Integer> result = new ArrayList<>();
        if (node != null) {
            //addAll() 方法将给定集合中的所有元素添加到 arraylist 中
            result.addAll(niXuPrintLinklistWithRecursion(node.next));
            result.add(node.val);
        }
        return result;
    }


    public static ArrayList<Integer> niXuPrintLinklistWithToucha(ListNode node) {
        //头插法构建逆序链表
        ListNode dummp = new ListNode(-1);
        while (node != null) {
            ListNode curr = node.next;  //下一个节点
            curr.next = dummp.next;//虚节点放到下下个节点
            dummp.next = node;//下个节点放到虚节点后
            node = curr;//再把指针放到下个节点
        }
        //构建ArrayList
        ArrayList<Integer> result = new ArrayList<>();
        dummp = dummp.next;  //先把虚节点跳过去
        while (dummp != null) {
            result.add(dummp.val);
            dummp = dummp.next;
        }
        return result;
        }


    public static ArrayList<Integer> niXuPrintLinklistWithStack(ListNode node) {
        Stack<Integer> stack = new Stack<>();
        while (node != null) {
            stack.add(node.val);
            node = node.next;
        }
        ArrayList<Integer> res = new ArrayList<>();
        while (!stack.isEmpty()) {
            res.add(stack.pop());
        }
        return res;
    }


    }
