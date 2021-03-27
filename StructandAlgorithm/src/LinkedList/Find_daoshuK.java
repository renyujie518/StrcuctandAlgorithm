package LinkedList;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName Find_daoshuK.java
 * @Description 单向链表找倒数第K个数(删除倒数第K个数就多个步骤)
 * 采取双重遍历肯定是可以解决问题的,但题目要求我们一次遍历解决问题,那我们的思路得发散一下。
 * 我们可以设想假设设定了双指针p和q的话,当q指向末尾的NULL,p与q之间相隔的元素个数为n时,那么删除掉p的下一个指针就完成了要求
 * 设置虚拟节点 dummyHead指向head(在head之前)
 * 设定双指针p和q,初始都指向虚拟节点 dummyHead
 * 移动q,直到p与q之间相隔的元素个数为n，同时移动p与q,直到q指向的为NULL
 * 将p的下一个节点指向下下个节点
 * @createTime 2021年03月23日 15:24:00
 */
public class Find_daoshuK {
    public ListNode  Find_daoshuK(ListNode head,int K){
        ListNode xu = new ListNode(0, head);//虚节点 相当于再head前再加一个虚节点
        ListNode first = head;
        ListNode second = xu;  //构建双指针
        for (int i = 0; i < K; i++) {
            first = first.next;  //先移动首指针，直到两者相差K
        }
        //然后两个指针一起移动，first会先触底，所以拿first作为边界
        while (first!=null){
            first = first.next;
            second = second.next;
        }
        //这时候！！！second就是倒数第K个节点
        //return second;
        //如果要删除，将secnd指向下下节点，相当于把这个节点删除了
        second.next = second.next.next;
        ListNode ans = xu.next;  //xu是指向头结点的，而删除的操作是针对second(ListNode second = xu)
        //dummy.next相当于对操作完后的head返回
        return ans;
    }
}
