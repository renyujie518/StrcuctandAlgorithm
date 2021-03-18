package Greedy;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName LessMoney.java
 * @Description
 * 一块金条切成两半，是需要花费和长度数值一样的铜板的。比如长度为20的金条，不管切成长度多大的两半，都要花费20个铜板。
 * 一群人想整分整块金条，怎么分最省铜板?例如,给定数组{10,20,30}，
 * 代表一共三个人，整块金条长度为10+20+30=60。金条要分成10,20,30三个部分。如果先把长度60的金条分成10和50，花费60；
 * 再把长度50的金条分成20和30，花费50；一共花费110铜板。
 * 但是如果先把长度60的金条分成30和30，花费60；再把长度30金条分成10和20，花费30；一共花费90铜板。
 * 输入一个数组，返回分割的最小代价
 *
 * （哈夫曼编码问题）
 *
 *
 * 贪心策略在实现时，经常使用到的技巧：
 * 1，根据某标准建立一个比较器来排序
 * 2，根据某标准建立一个比较器来组成堆
 *
 * 思路 ：
 * 小根堆，每次 从顶部取出两个后加起来再放回去,直到优先级队列里只剩一个
 * @createTime 2021年03月16日 17:57:00
 */
public class LessMoney {
    //这里用小根堆实现
    public static  int LessMoney(int[] arr){
        PriorityQueue<Integer> pQ = new PriorityQueue<>();
        for (int i = 0; i < arr.length; i++) {
            pQ.add(arr[i]);
        }
        int sum = 0;
        int curr = 0;
        while (pQ.size()>1){
            curr = pQ.poll() + pQ.poll();
            sum += curr;
            pQ.add(curr);
        }
        return sum;
    }

    //比较器 obj1和obj2是要比较的对象。如果对象相等，则此方法返回零。如果obj1大于obj2，则返回正值。否则返回负值。
    //设立小根堆比较器
    public  static class MinheapComparator implements Comparator<Integer>{

        @Override
        public int compare(Integer o1, Integer o2) {
            return o1-o2; // < 0  o1 < o2  负数
        }
    }

    //设立大根堆比较器
    public static class MaxheapComparator implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1; // <   o2 < o1
        }

    }

    public static void main(String[] args) {
        // solution
        int[] arr = { 6, 7, 8, 9 };
        System.out.println(LessMoney(arr));

        int[] arrForHeap = { 3, 5, 2, 7, 0, 1, 6, 4 };

        // min heap
        System.out.println("系统里的优先级队列（预置小根堆）");
        PriorityQueue<Integer> minQ1 = new PriorityQueue<>();
        for (int i = 0; i < arrForHeap.length; i++) {
            minQ1.add(arrForHeap[i]);
        }
        while (!minQ1.isEmpty()) {
            System.out.print(minQ1.poll() + " ");
        }
        System.out.println();

        // min heap use Comparator
        System.out.println("加了自定义小根堆比价器");
        PriorityQueue<Integer> minQ2 = new PriorityQueue<>(new MinheapComparator());
        for (int i = 0; i < arrForHeap.length; i++) {
            minQ2.add(arrForHeap[i]);
        }
        while (!minQ2.isEmpty()) {
            System.out.print(minQ2.poll() + " ");
        }
        System.out.println();

        // max heap use Comparator
        System.out.println("加了自定义大根堆比价器");
        PriorityQueue<Integer> maxQ = new PriorityQueue<>(new MaxheapComparator());
        for (int i = 0; i < arrForHeap.length; i++) {
            maxQ.add(arrForHeap[i]);
        }
        while (!maxQ.isEmpty()) {
            System.out.print(maxQ.poll() + " ");
        }

    }


}
