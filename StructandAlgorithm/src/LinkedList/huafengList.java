package LinkedList;

import util.swap;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName huafengList.java
 * @Description 将单向链表按某值划分成左边小、中间相等、右边大的形式
 *
 *
 * 题目描述 1：
 *  * 给定一个单向链表的头节点 head，在给定一个整数 pivot，实现一个调整链表的函数。
 *  * 将链表调整为左部分的值都是小于 pivot 的节点，中间都是等于 pivot 的节点，右部分的值都是大于 pivot 的部分。
 *  *
 *  * 题目描述 2 (进阶)：
 *  * 使该算法具有稳定性（相对顺序和之前一样），时间复杂度为 O(N)，额外空间复杂度为 O(1)
 *  *
 *  * 思路 1：
 *  * 开一个节点类型的数组，使用荷兰国旗问题后，再将其串成链表即可。
 *  *
 *  * 思路 2：
 *  * 1. 设置三个 node 类型的变量：less、equal、more；
 *  * 2. 遍历一遍链表，将第一个小于 pivot 的节点给 less，将第一个等于 pivot 的节点给 equal，将第一个大于 pivot 的节点给 more；
 *  * 3. 再遍历一遍链表，同时将不同的值放进不同的区域中，然后串起来；
 *  * 4. 最后将“小于区域”的尾部与“等于区域”的头部重连，将“等于区域”的尾部与“大于区域”的头部重连即可；
 *  * 5. 相当于将一个大连表拆分成三个小链表，然后将这三个小链表重新串起来。
 *
 * @createTime 2021年02月27日 10:41:00
 */
public class huafengList {
    public static void main(String[] args) {
        ListNode node1 = new ListNode(3);
        node1.next = new ListNode(2);
        node1.next.next = new ListNode(5);
        node1.next.next.next = new ListNode(1);
        node1.next.next.next.next = new ListNode(5);
        node1.next.next.next.next.next = new ListNode(7);
        node1.next.next.next.next.next.next = new ListNode(11);
        System.out.println("数组法处理之前 ");
        printLinkedList(node1);
        System.out.println("数组法处理之后 ");
        usePartition(node1, 5);
        printLinkedList(node1);

        ListNode node2 = new ListNode(3);
        node2.next = new ListNode(2);
        node2.next.next = new ListNode(5);
        node2.next.next.next = new ListNode(1);
        node2.next.next.next.next = new ListNode(5);
        node2.next.next.next.next.next = new ListNode(7);
        node2.next.next.next.next.next.next = new ListNode(11);
        node2.next.next.next.next.next.next.next = new ListNode(1);


        System.out.println("6指针法处理之前 ");
        printLinkedList(node2);
        System.out.println("6指针法处理之后 ");
        //use6Point(node2, 5); //Linked List: 3 2 1 5 5 7 11
        //use6Point(node2, 11); //Linked List: 3 2 5 1 5 7 11
        //use6Point(node2, 4);//Linked List: 3 2 1 5 5 7 11
        use6Point(node2, 1);      ///////有bug
        printLinkedList(node2);

    }

    //法1 先转为数组，再用partition的思路
    public static ListNode usePartition(ListNode head, int pivot){
        if(head == null || head.next == null){
            return head;
        }
        ListNode curr = head;
        int i = 0;
        //统计节点的个数
        while (curr != null){
            i++;
            curr = curr.next;
        }
        //建立一个链表的数组,长度为之前的个数
        ListNode[] list = new ListNode[i];
        int j = 0;
        curr = head;
        //填充链表数组
        for (j = 0;j != list.length;j++){
            list[j] = curr;
            curr = curr.next;
        }
        //使用荷兰国旗partition的方法对数据划分为 <=>三个部分
        LinlistParitition(list,pivot);
        //划分完后要将数组中的链表再串起来
        for (i = 1;i != list.length;i++){
            list[i - 1].next = list[i];  //i是从1开始的，这种写法要记住
        }
        //这一步一定不要漏，在处理链表的时候要处理边界问题
        //处理最后一个节点
        list[i -1].next = null;  //这时候i还没有被垃圾回收，i此时为list.length，-1是因为保证数组的长度是0-list.length-1
        return list[0];  //返回处理好的head
    }

    public static void LinlistParitition(ListNode[] list,int pivot){
        int less = -1;//这个-1参照之前在letcode上的国旗问题，使得<的边界在初始的左边
        int more = list.length;//同上，使得>的边界在初始的右边
        int i =0;
        while (i<more){
            if (list[i].val<pivot){
                swap.swap(list,++less,i++);
            }else if (list[i].val > pivot){
                swap.swap(list,--more,i);
            }else {
                i++;
            }
        }
    }


    //法2  使用6个指针（有限的几个变量，额外空间复杂度为 O(1)）来划分三个区域
    //将一个大连表拆分成三个小链表，然后将这三个小链表重新串起来
    public static ListNode use6Point(ListNode head,int pivot){
        if (head == null || head.next ==null){
            return head;
        }
        ListNode smallHead = null;
        ListNode smallTail = null;
        ListNode equalHead = null;
        ListNode equalTail = null;
        ListNode moreHead = null;
        ListNode moreTail = null;
        //临时变量，储存下一个节点,避免链表断开
        ListNode nextNode = null;
        while (head != null){
            nextNode = head.next;
            //初始的时候让头结点和链表断开，不过上一步已经储存了，不用担心
            head.next = null;
            //遍历一遍链表，将第一个小于 pivot 的节点给 less，将第一个等于 pivot 的节点给 equal，将第一个大于 pivot 的节点给 more；
            //以下会涉及到很多边界的处理
            if (head.val < pivot) {
                //如果一开始small区域没有节点，就直接存进去
                if (smallHead == null) {
                    smallHead = head;
                    smallTail = head;
                } else {//否则代表small里已经有其他节点了，那就让尾部接上这个新加的节点,别忘了处理该段的尾指针后移到新节点
                    smallTail.next = head;
                    smallTail = head;
                }
            }else if (head.val == pivot){
                if (equalHead == null){
                    equalHead = head;
                    equalTail = head;
                }else {
                    equalTail.next = head;
                    equalTail = head;
                }
            }else {
                if (moreHead == null){
                    moreHead = head;
                    moreTail = head;
                }else {
                    moreTail.next = head;
                    moreTail = head;
                }
            }
            head = nextNode;  //利用之前储存的位置后移
        }
        //划分完后连接small和equal区域
        if (smallTail != null){ //small区域内有东西再连接
            smallTail.next = equalHead;
            //那如果equal内没东西，那equal区域不应该连接，否则会空指针异常，则equalTail直接等于smallTail即可(自己闭环自己，把自己消掉).如果有东西就放着不动
            equalTail = equalTail == null ? smallTail : equalTail;
        }else if (smallTail == null){ //假如small区域是空，那么就考虑从equal区域的连接
            equalTail = equalTail == null ? smallTail : equalTail;
            //smallTail.next = equalHead;
            printLinkedList(equalHead);
            equalTail.next = moreHead;
            //return equalHead;

        }
//        //再连接more，连接more要考虑equal是否为空，more区域空不空无所谓
//        if (equalTail !=null){
//            equalTail.next = moreHead;
//        }


        //最后防止返回的结果是个空，再做个判断
        if (smallHead == null && equalHead !=null){
            return  equalHead;
        }else if (equalHead == null && smallHead ==null){
            return moreHead;
        }else {
            return smallHead;
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
