package MiddleClass;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

import static util.generateRandomArray.generateRandomArray;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName topKTimes.java
 * @Description
 * 给定一个字符串类型的数组arr， 求其中出现次数最多的前K个
 *
 *思路：
 * 1.建立按照次数的大根堆 弹出前k个
 * 2.建立小根堆 限定个数不能超过K 表示目前遍历到这里为止，次数最大的前k个按照小根堆组织,堆顶是"门槛"
 *
 * 以上两种思路都要构建 key = str  value = 次数的hashmap
 *
 * @createTime 2021年07月25日 19:00:00
 */
public class topKTimes {
    //首先建立节点  在堆上保存的就是节点类型
    public static class Node{
        public String str;
        public int times;

        public Node(String str, int times) {
            this.str = str;
            this.times = times;
        }
    }

    //实现一个关于Node中次数的比较器
    public static class NodeComparator implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            //有小到大
            return o1.times- o2.times;
        }
    }

    //关键函数 打印topK并排序 使用的是小根堆的方法
    public static void printTopKAndRank(String[] arr, int topK) {
        if (arr == null || arr.length == 0 || topK < 1) {
            return;
        }
        HashMap<String, Integer> map = new HashMap<>();
        //建立词频map
        for (String str : arr) {
            if (!map.containsKey(str)) {
                map.put(str, 0);
            }
            //否则的话说明map中已经有这个key了，再来只要在原有基础上词频+1
            map.put(str, map.get(str) + 1);
        }
        //这里；只是确保安全
        topK = Math.min(arr.length, topK);
        //利用系统实现的小根堆,比较器限定是依照词频的升序排列，即最小的在上面
        PriorityQueue<Node> heap = new PriorityQueue<>(new NodeComparator());
        //对刚才的词频map操作，放到堆上
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            Node cur = new Node(entry.getKey(), entry.getValue());
            if (heap.size() < topK) {//如果小根堆还没放满，那就直接放  限定小根堆中个数不能超过K
                heap.add(cur);
            } else {//如果已经有了，就要看能不能大过堆顶的门槛把它干掉
                if (cur.times > heap.peek().times) {
                    heap.poll();//弹走就是干掉
                    //为什么没有插入cur和heapfie和heapinset的操作呢？
                    //理由是首先PriorityQueue是个黑盒，自动调整实现了heapfie和heapinse
                    //其次，poll会拿走堆顶，在PriorityQueue自动调整的时候会剔除同时代表小根堆还没放满，再经历上面那个if时会有add操作
                }

            }
        }
        while (!heap.isEmpty()) {
            System.out.println(heap.poll().str);
        }

    }

    public static void printArray(String[] arr) {
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
    public static void main(String[] args) {
        String[] arr1 = { "A", "B", "A", "C", "A", "C", "B", "B", "K" };
        printTopKAndRank(arr1, 2);
        System.out.println("++++++");

        String[] arr2 = generateRandomArray(50, 10);
        int topK = 3;
        printArray(arr2);
        printTopKAndRank(arr2, topK);

    }

}
