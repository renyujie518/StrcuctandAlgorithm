package facing;

import java.util.HashMap;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName LFU.java
 * LRU(Least Recently Used) 最近最少使用算法，它是根据时间维度来选择将要淘汰的元素，即删除掉最长时间没被访问的元素。
 * LFU(Least Frequently Used) 最近最不常用算法，它是根据频率维度来选择将要淘汰的元素，即删除访问频率最低的元素。
 * 如果两个元素的访问频率相同，则淘汰最久没被访问的元素。
 * 也就是说LFU淘汰的时候会选择两个维度，先比较频率，选择访问频率最小的元素；如果频率相同，则按时间维度淘汰掉最久远的那个元素。
 *
 * LRU的实现是一个哈希表加上一个双链表
 * 而LFU则要复杂多了，需要用两个哈希表再加上N个双链表才能实现

 * @Description 最不经常使用（LFU）缓存算法设计并实现数据结构。
 * <p>
 * 实现 LFUCache 类：
 * <p>
 * LFUCache(int capacity) - 用数据结构的容量capacity 初始化对象
 * int get(int key)- 如果键存在于缓存中，则获取键的值，否则返回 -1。
 * void put(int key, int value)- 如果键已存在，则变更其值；如果键不存在，请插入键值对。
 * 当缓存达到其容量时，则应该在插入新项之前，使最不经常使用的项无效。
 * 在此问题中，当存在平局（即两个或更多个键具有相同使用频率）时，应该去除 最近最久未使用 (最早使用)的键。
 * 注意「项的使用次数」就是自插入该项以来对其调用 get 和 put 函数的次数之和。使用次数会在对应项被移除后置为 0 。
 * 为了确定最不常使用的键，可以为缓存中的每个键维护一个 使用计数器 。使用计数最小的键是最久未使用的键。
 * 当一个键首次插入到缓存中时，它的使用计数器被设置为 1 (由于 put 操作)。对缓存中的键执行 get 或 put 操作，使用计数器的值将会递增。
 * <p>
 * 思路：
 * 双hashmap
 * @createTime 2021年08月24日 20:09:00
 */
public class LFU {
    class ListNode{
        int key;
        int value;
        int time;
        ListNode next;
        ListNode pre;

        public ListNode(int key, int value) {
            this.key = key;
            this.value = value;
        }

        public ListNode() {
        }
    }
    ListNode head = new ListNode();//链表的头
    ListNode tail = new ListNode();//链表的尾
    HashMap<Integer, ListNode> map = new HashMap<>();//key=结点的key，value=指向的结点
    HashMap<Integer,Integer> countMap=new HashMap<>();//key=使用次数，value=使用次数为key的所有结点的最后一个的key
    //比如节点1和节点2使用次数都是1，如果1先使用，那它就在链表尾，value=1(从尾部删除保证了删除的是最早（旧）的)
    int capacity =0;//map的大小
    public LFU(int capacity) {
        head.time=Integer.MAX_VALUE;
        tail.time=0;//每次都先放到尾部去
        this.capacity =capacity;
        head.next=tail;//构建双向链表
        tail.pre=head;
    }

    //先写几个辅助函数，也是对链表的操作函数

    //将节点从链表中拿开
    public void take(ListNode node) {
        node.next.pre = node.pre;
        node.pre.next = node.next;
    }

    //将node移动到where节点之后
    public void move(ListNode node,ListNode where){
        node.next = where.next;
        node.next.pre = node;
        where.next = node;
        node.pre = where;
    }
    //找到要移动到哪里(找到次数最相近的（大1的）)
    public ListNode findWhere(int time){
        ListNode temp = head;
        int nearCount = Integer.MAX_VALUE;
        for (Integer count : countMap.keySet()) {
            if (count - time > 0 && count - time < nearCount) {
                nearCount = count - time;
                if (nearCount == 1) {
                    break;
                }
            }
        }
        //在这里找到countMap中相对于传参time大1的的次数即nearCount
        //找到使用次数虽然比该节点大，但是也是大中最小的那个（即出现次数只大了1）
        //因为尾部删除保证了删除的是最早（旧）的，所以在有新节点进来时要找到在nearCount之前的那个节点
        if (nearCount != Integer.MAX_VALUE) {
            temp = map.get(countMap.get(time + nearCount));
        }
        return temp;
    }

    //返回次数time
    public int get(int key) {
        if (capacity == 0) {
            return -1;
        }
        //map里有元素
        if (map.containsKey(key)) {
            ListNode node = map.get(key);
            //检查一下该结点是不是在和它使用次数相同结点的首部(首部这一点在put时保证)
            if (countMap.get(node.time) == key) {
                //是,所以先移除该节点在countMap的记录
                countMap.remove(node.time);
                int newKey = node.pre.key;
                //  //检查前一个结点，看看他的使用次数是否和当前元素相同，相同的话，就更新一下
                if (map.containsKey(newKey) && map.get(newKey).time == node.time) {
                    countMap.put(node.time, newKey);
                }
            }
            //该节点没有遇到次数相同的节点
            //使用次数加1
            node.time++;
            //  //检查加1后，countmap里是否有使用次数为更新后次数的节点，没有就更新countMap
            if(!countMap.containsKey(node.time)){
                countMap.put(node.time,key);
            }
            //将节点从链表中拿开
            take(node);
            //找到要插入的位置,并将node移到该位置后
            move(node, findWhere(node.time));
            return node.time;
        }

        return -1;
    }


    public void put(int key, int value) {
        if(capacity ==0){
            return;
        }
        if (get(key) > -1) {//如果能get到，就将新value赋值
            map.get(key).value = value;
        } else {//否则就在链表中新建节点（key,value,time=1）
            ListNode node = new ListNode(key, value);
            node.time = 1;
            if (map.size() == capacity) {//新增会遇到容量问题，先删除最近最少使用的缓存，再进行插入。
                //还要保证从尾部删除的是最早（旧）的
                int rk = tail.pre.key;
                int tk = tail.pre.time;
                map.remove(rk);
                countMap.remove(tk);
                int prk = tail.pre.pre.key;
                int ptk = tail.pre.pre.time;
                if (ptk != Integer.MAX_VALUE) {
                    countMap.put(ptk, prk);//这一步是真正跟新 countMap的，在遇到容量问题删除链表尾部前节点，同时把尾部前前节点放入countMap
                }
                //删除尾部节点（插的时候是从头部插的，所以尾部是最旧的）
                take(tail.pre);
            }
            if (!countMap.containsKey(1)) {
                countMap.put(1, key);
            }
            //最后跟新map
            map.put(key, node);
            //依据node.time把node放链表中合适的位置
            move(node, findWhere(node.time));
        }
    }



}
