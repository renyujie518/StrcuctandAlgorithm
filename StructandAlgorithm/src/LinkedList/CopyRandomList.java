package LinkedList;

import java.util.HashMap;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName CopyRandomList.java
 * @Description
 *

复制含有随机指针节点的链表
【题目】一种特殊的单链表节点类描述如下
class Node{
        int value;
        Node next;
        Node rand;
    Node(int val){
        value= val;
    }
}

rand指针是单链表节点结构中新增的指针,rand可能指向链表中的任意一个节点,也可能指向nul。给定一个由Node节点类型组成的无环单链表的头节点
head,请实现一个函数完成这个链表的复制,并返回复制的新链表的头节点。
【要求】时间复杂度0(N,额外空间复杂度0(1)


法1：
先创建一个哈希表，再遍历原链表，遍历的同时再不断创建新节点
我们将原节点作为key，新节点作为value放入哈希表中
第二步我们再遍历原链表，这次我们要将新链表的next和random指针给设置上
原节点和新节点是一一对应的关系，所以

map.get(原节点)，得到的就是对应的新节点
map.get(原节点.next)，得到的就是对应的新节点.next
map.get(原节点.random)，得到的就是对应的新节点.random
所以，我们只需要再次遍历原链表，然后设置：
新节点.next -> map.get(原节点.next)
新节点.random -> map.get(原节点.random)
这样新链表的next和random都被串联起来了
最后，我们然后map.get(head)，也就是对应的新链表的头节点，就可以解决此问题了。

法2：
第一步：把链表的每个节点复制并将它放在原节点后面，只复制next指针；
这样可以发现：
原节点1的随机指针指向原节点3，新节点1的随机指针指向的是原节点3的next
原节点3的随机指针指向原节点2，新节点3的随机指针指向的是原节点2的next

第二步：如果要找到cur的复制节点的random指针，可以通过cur->random->next找到；
第三步：将原节点和新的节点分类开即可.


 * @createTime 2021年02月27日 22:43:00
 */
public class CopyRandomList {
    public static void main(String[] args) {
        Node head = null;
        Node copy1 = null;
        Node copy2 = null;
        System.out.println("原本：");
        printRandLinkedList(head);

        System.out.println("使用哈希表复制的方法：");
        copy1 = useHashmapCopy(head);
        printRandLinkedList(copy1);

        System.out.println("使用紧接着复制+拆分的方法：");
        copy2 = useCpoyandSplit(head);
        printRandLinkedList(copy2);
        System.out.println("===========以上验证null的处理==============");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.next = new Node(6);

        head.rand = head.next.next.next.next.next; // 1 -> 6
        head.next.rand = head.next.next.next.next.next; // 2 -> 6
        head.next.next.rand = head.next.next.next.next; // 3 -> 5
        head.next.next.next.rand = head.next.next; // 4 -> 3
        head.next.next.next.next.rand = null; // 5 -> null
        head.next.next.next.next.next.rand = head.next.next.next; // 6 -> 4
        System.out.println("原本：");
        printRandLinkedList(head);

        System.out.println("使用哈希表复制的方法：");
        copy1 = useHashmapCopy(head);
        printRandLinkedList(copy1);

        System.out.println("使用紧接着复制+拆分的方法：");
        copy2 = useCpoyandSplit(head);
        printRandLinkedList(copy2);
        System.out.println("===========以上是更普遍的验证==============");
    }

    public static class Node{
        int value;
        Node next;
        Node rand;
        Node(int val){
            this.value = val;
        }
    }

    //法1，用hashmap,构成两两结对的方式
    public static  Node useHashmapCopy(Node head){
        HashMap<Node, Node> map = new HashMap<>();
        Node curr = head;
        //构建map
        while (curr!= null){
            map.put(curr,new Node(curr.value));  //eg 1->2 key:1 value:1' key:2 value:2'
            curr = curr.next;
        }
        //确定1'的next指向和rand指向，这里巧妙的运用map的get方法

        curr = head;
        while (curr != null){
            map.get(curr).next = map.get(curr.next);  //1'的下一个 = 取出1的下一个
            map.get(curr).rand= map.get(curr.rand);
            curr = curr.next;
        }
        return map.get(head);  //始终别忘了get得到的就是'的，因为value储存的是'的，所以get(head)
    }

    //法2 根据遍历到的原节点创建对应的新节点，每个新创建的节点是在原节点后面
    public static Node useCpoyandSplit(Node head){
        if (head == null){
            return null;
        }
        //首先，把链表的每个节点复制并将它放在原节点后面，只复制next指针
        Node curr = head;
        Node next = null;  //相当于一个临时变量
        while (curr != null){
            next = curr.next;
            curr.next = new Node(curr.value);
            curr.next.next = next;
            curr = next;//前进
        }
        //处置random指针，如果要找到curr的复制节点curr'的random指针，可以通过cur->rand->next找到
        curr = head;
        Node currCopy = null;  //用于记录1'，2'...
        while (curr!= null){
            next = curr.next.next; //此时已经经历了第一步，1->1'->2->2'  所以要保存两个next之后的
            currCopy = curr.next;
            currCopy.rand = curr.rand != null ? curr.rand.next : null;
            curr = next; //前进，只不过每次跳两步
        }
        //.next和.rand指针都处理完了，这里先把copy的头拿出来得到result
        Node result = head.next;
        curr = head;
        //拆分
        while (curr != null){
            next = curr.next.next; //原
            currCopy = curr.next; //新
            curr.next = next;  //每次跳两步把1，2，..拿出来
            currCopy.next = next !=null ? next.next : null; //next.next 原的后面跟着新，而且我只用更新next即可，即下一句即可
            curr = next;
        }
        return result;
    }
    public static void printRandLinkedList(Node head) {
        Node cur = head;
        System.out.print("order: ");
        while (cur != null) {
            System.out.print(cur.value + " ");
            cur = cur.next;
        }
        System.out.println();
        cur = head;
        System.out.print("rand:  ");
        while (cur != null) {
            System.out.print(cur.rand == null ? "- " : cur.rand.value + " ");
            cur = cur.next;
        }
        System.out.println();
    }

}
