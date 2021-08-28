package SwordOffer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName maxInWindow_59.java
 * @Description 滑动窗口的最大值
 * 给定一个数组和滑动窗口的大小，找出所有滑动窗口里数值的最大值。
 * 例如，如果输入数组 {2, 3, 4, 2, 6, 2, 5, 1} 及滑动窗口的大小 3，那么一共存在 6 个滑动窗口，
 * 他们的最大值分别为 {4, 4, 6, 6, 6, 5}。
 * 我们可以想到，对于两个相邻（只差了一个位置）的滑动窗口，它们共用着 k-1个元素，而只有 1个元素是变化的。我们可以根据这个特点进行优化。
 * 大根堆可以帮助我们实时维护一系列元素中的最大值
 * 每当我们向右移动窗口时，我们就可以把一个新的元素放入堆中，此时堆顶的元素就是堆中所有元素的最大值
 * 然而这个最大值可能并不在滑动窗口中，在这种情况下，这个值在数组 nums 中的位置出现在滑动窗口左边界的左侧。
 * 因此，当我们后续继续向右移动窗口时，这个值就永远不可能出现在滑动窗口中了，我们可以将其永久地从优先队列中移除。
 * 我们不断地移除堆顶的元素，直到其确实出现在滑动窗口中。
 * 此时，堆顶元素就是滑动窗口中的最大值。
 * 为了方便判断堆顶元素与滑动窗口的位置关系，我们可以在优先队列中存储二元组(num,index)，
 * 表示元素num 在数组中的下标为index。
 *
 * 总结：
 * 使用优先队列来维持一个大小为k的容器，大顶堆，此时容器的顶端是最大值

 * 使用数组来存储元素的值和位置，容器移动，判断最大值下标是否在容器内
 * 如果容器的最大值的下标，小于容器的左边界，弹出顶端
 * 直到顶端位置在容器内，将顶端的值，放入ans 数组中
 * 链接：https://leetcode-cn.com/problems/hua-dong-chuang-kou-de-zui-da-zhi-lcof/solution/jian-zhi-offer-59-i-hua-dong-chuang-kou-wyz8v/
 * @createTime 2021年08月27日 23:06:00
 */
public class maxInWindow_59 {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        PriorityQueue<int[]> maxDui = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {//0位置 该元素  1位置 该元素在数组中的位置
                //先比较元素大小，大的在前面，相同就比较位置，后面的在前面
                return a[0] != b[0] ? b[0] - a[0] : b[1] - a[1];
            }
        });
        //初始化，将前k个元素放入队列中
        for(int i = 0 ; i < k ; i++){
            maxDui.add(new int[]{nums[i] , i});
        }
        //结果数组，大小为n - k + 1
        int[] ans = new int [n - k + 1] ;
        //此时顶就是前k个元素的最大值
        ans[0] = maxDui.peek()[0];
        //！！！！！！注意  这里是堆移动 实际上就是先把所有元素放到堆里，再判断
        for(int i = k ; i < n;i++){
            maxDui.add(new int[]{nums[i] , i});
            //顶点小于左边界（窗口的边界），就弹出来,要都弹出来  至于为什么是左边界，因为是右滑动的，所以考虑左边界越界即可
            while (maxDui.peek()[1] <= i - k) {//窗口的大小是i-k+1，但是针对坐标而言，就是i-k
                //比如 5 6 7 8  i = 2 我要判断6有没有越界，直接是8-2
                maxDui.poll();
            }
            //还在堆顶的放入res，其中，res元素的个数即能产生窗口的个数，每i-k+1产生一个，所以第i-k+1是该堆顶元素
            ans[i - k + 1] = maxDui.peek()[0];
        }
        return ans;

    }


    //另一种堆的实现
    public int[] maxSlidingWindow2(int[] nums, int k) {
        if (nums.length==0){
            return new int[0];
        }
        PriorityQueue<Integer> queue = new PriorityQueue<>(k, Comparator.reverseOrder());
        ArrayList<Integer> result = new ArrayList<>();
        //这个index代表由于窗口是挨个滑动的，可以吧这个index认为是考虑的num中的元素（每个元素都有可能放入结果）
        int index=0;
        for (int i = 0; i < nums.length; i++) {
            if (queue.size()!=k){
                queue.offer(nums[i]);
                if (queue.size()==k){
                    //queue.peek()大根堆的性质保证了peek的值是最大的
                    result.add(queue.peek());
                }
            }else {//在上述peek后所在的==k的if执行后，紧接着进入这个else，把已考虑过的元素去掉
                //注意if和else两者只能选其一，所以i又+1了，所以紧接着还是要把nums[i]放入大根堆中
                //同时，不要忘了else代表的含义是queue.size()==k，又满足一个窗口，所以还是要peek堆顶元素
                //个人感觉这种方法会有很多重复计算，很可能是作者纯debug出来的
                queue.remove(nums[index++]);
                queue.offer(nums[i]);
                result.add(queue.peek());
            }
        }
        //list转int[]
        return result.stream().mapToInt(Integer::intValue).toArray();
    }
}
