package facing;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName LRU.java
 * @Description
 * 最近最少使用算法，怎么理解？就是使用一个有序固定容量大小的队列维持一堆数据，
 * 当往队列插入一个不存在的数据时，就会淘汰掉最长时间没有使用的数据，我们把这个算法成为LRU算法。
 *
 * LRU 算法实际上是让你设计数据结构：首先要接收一个 capacity 参数作为缓存的最大容量，
 * 然后实现两个 API，一个是 put(key, val) 方法存入键值对，另一个是 get(key) 方法获取 key 对应的 val，如果 key 不存在则返回 -1。
 *
 * 使用链表和哈希表实现LRU
 * 首先我们需要使用链表提供一个固定大小、并且是能够实现有序排列功能的队列，为什么不用数组？
 * 因为数组对于移动数据的时间复杂度太高了，所以不考虑使用数组。
 *
 * 其次我们需要使用哈希表来实现高效的get、put操作，它的平均时间复杂度能达到O(1)。
 * @createTime 2021年08月24日 23:27:00
 */
public class LRU {
    private LinkedList<Integer> mQueue;
    private HashMap<Integer, Integer> mMap;
    private int mCapacity;
    public LRU(int capacity) {
        this.mCapacity = capacity;
        mQueue = new LinkedList<Integer>();
        mMap = new HashMap<Integer, Integer>();
    }

    public int get(int key) {
        Integer result = mMap.get(key);
        if(result == null) {
            return -1;
        } else {//当发现队列里面存在数据时
            // 我get了，说明这个key很新鲜。我们需要把它从队列里面移动到队尾,先删后offer(addlast)
            mQueue.remove((Integer)key);
            mQueue.offer(key);
            return result;
        }
    }

    public void put(int key, int value) {
        Integer result = mMap.get(key);
        if(result != null) {
            // 当发现队列里面存在该数据,则把它移动到队尾
            mQueue.remove((Integer)key);
            mQueue.offer(key);
            mMap.put(key, value);
        } else {// 当发现队列里面不存在数据时
            if(mQueue.size() >= mCapacity) {
                // 如果队列大小超过了容量值，就需要把队头元素删掉
                int head = mQueue.poll();
                // 并且从hashMap里面抹掉
                mMap.remove(head);
            }
            mQueue.offer(key);   //如果不满，放入队尾元素，更新哈希表
            mMap.put(key, value);
        }
    }

}
