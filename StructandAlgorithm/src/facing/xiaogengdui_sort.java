package facing;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName xiaogengdui_sort.java
 * @Description
 * 已知一个几乎有序的数组，几乎有序是指，如果把数组排好顺序的话，每个元素移动的距离可以不超过k，
 * 并且k相对于数组来说比较小。请选择一个合适的排序算法针对这个数据进行排序
 *
 *
 * 直接用小根堆，在一取一放的过程中，不断顶替，取出的就是有序的数
 *
 * 再接着把比较器尝试放进去
 * @createTime 2021年02月14日 16:41:00
 */
public class xiaogengdui_sort {
    public static void main(String[] args) {
        int[] arrs = {1, 3, 2, 6, 4, 2, 6, 3, 2};
        int[] xx = sortedArrDistancelessK(arrs, 5);
        for (int x : xx) {
            System.out.println(x);
        }
    }

    public static int[] sortedArrDistancelessK(int[] arrs,int k) {
        //PriorityQueue<Integer> heap = new PriorityQueue<>();//直接默认生成小根堆
        PriorityQueue<Integer> heap = new PriorityQueue<>(new AComp());
        int index = 0;
        for (;index<=Math.min(arrs.length,k);index++){  //防止给的k过大
            heap.add(arrs[index]);
        }
        int i =0;
        for (;index<arrs.length;i++,index++){
            heap.add(arrs[index]);
            arrs[i] = heap.poll();
        }
        while (!heap.isEmpty()){
            arrs[i++] = heap.poll();
        }
        return arrs;

    }

    public  static class  AComp implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
//            return o1-o2;
            return o2-o1;
        }
    }


}
