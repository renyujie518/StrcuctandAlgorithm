package Greedy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName quickFindMid.java
 * @Description 一个数据流中，随时可以取得中位数 要求要快，数据流在随时变化
 *
 * 思路 用空间换时间
 * 建立一个大根堆，一个小根堆 先把第一个数放大根堆。当前数字curr<=大根堆堆顶，入大根堆，否则入小根堆
 * 一旦其中的一个size-另一个size>2,堆顶弹出进另一个
 * 这样的效果是较小的N/2个数在大根堆  较大的N/2个数在小根堆  中位数从堆顶取（分奇偶）
 *
 * 大小跟堆每次在放数的过程中都是log(N)的
 * @createTime 2021年03月16日 19:05:00
 */
public class quickFindMid {
    public static class MedianHolder{
        private PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });
        private PriorityQueue<Integer> minHeap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1-o2;
            }
        });

        //调整两个堆大小的类内函数
        private void modifyTwoHeapsize(){
            if (this.maxHeap.size() == this.minHeap.size()+2){
                this.minHeap.add(this.maxHeap.poll());
            }
            if (this.minHeap.size() == this.maxHeap.size() + 2) {
                this.maxHeap.add(this.minHeap.poll());
            }
        }
        //数据流来了放数的策略（类内函数）
        public void  addNumber(int num){
            if (maxHeap.isEmpty() || num <= maxHeap.peek()){  //初始的时候 || 放入的数 <= 大根堆堆顶，放大根堆
                maxHeap.add(num);
            }else {
                minHeap.add(num);
            }
            modifyTwoHeapsize();   //每放完一次，调整两个堆大小
        }

        //获取中值的策略（类内函数）
        public Integer getMedian(){
            int maxHeapSize = this.maxHeap.size();
            int minHeapSize = this.minHeap.size();
            if (maxHeapSize + minHeapSize == 0) {
                return null;
            }
            Integer maxHeapHead = this.maxHeap.peek();
            Integer minHeapHead = this.minHeap.peek();
            //如果是偶数个单独处理下
            if ((maxHeapSize+minHeapSize &1 )==0){
                return (maxHeapHead + minHeapHead) / 2;
            }
            return maxHeapSize > minHeapSize ? maxHeapHead : minHeapHead;  //谁的siza大返回谁的
        }
    }
    // for test 生成随机数组  指定最大长度和最大值
    public static int[] getRandomArray(int maxLen, int maxValue) {
        int[] res = new int[(int) (Math.random() * maxLen) + 1];
        for (int i = 0; i != res.length; i++) {
            res[i] = (int) (Math.random() * maxValue);
        }
        return res;
    }

    // for test, 系统实现的取中值方法
    public static int getMedianOfArray(int[] arr) {
        int[] newArr = Arrays.copyOf(arr, arr.length);
        Arrays.sort(newArr);  //排序，排好序再取mid
        int mid = (newArr.length - 1) / 2;
        if ((newArr.length & 1) == 0) {
            return (newArr[mid] + newArr[mid + 1]) / 2;
        } else {
            return newArr[mid];
        }
    }
    public static void printArray(int[] arr) {
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        boolean err = false;
        int testTimes = 200;
        for (int i = 0; i != testTimes; i++) {
            int len = 30;
            int maxValue = 1000;
            int[] arr = getRandomArray(len, maxValue);
            MedianHolder medianHold = new MedianHolder();
            for (int j = 0; j != arr.length; j++) {
                medianHold.addNumber(arr[j]);  //调用填数据方法
            }
            if (medianHold.getMedian() != getMedianOfArray(arr)) { //一旦两者方法不一致
                err = true;
                printArray(arr);  //打印出问题的数组
                break;
            }
        }
        System.out.println(err ? "Oops..what a fuck!" : "today is a beautiful day^_^");

    }
}
