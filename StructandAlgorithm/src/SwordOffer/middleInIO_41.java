package SwordOffer;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName middleInIO_41.java
 * @Description 如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。
 * 如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。
 * 我们使用Insert()方法读取数据流，使用GetMedian()方法获取当前读取数据的中位数。
 *
建立一个 小顶堆 A 和 大顶堆 B ，各保存列表的一半元素
A 保存 较大 的一半，B 保存 较小 的一半
这样中位数很明显 若是奇数个 在偏后的A的堆顶  若是偶数个，两个堆顶元素相加除2

插入的时候注意，不是直接往A或者B里插入，比如总数是偶数个，假设插入数字 num，可能属于 “较小的一半” （即属于 B ）
因此不能将 nums直接插入至 A 。而应先将 num 插入至 B ，再将 B堆顶元素插入至 A 。
这样就可以始终保持 A 保存较大一半、 B 保存较小一半。
（由于B是大根堆，堆顶是大元素，将经过筛选的大元素放入小根堆，小根堆就做到了存储较大的元素，只不过在堆底而已）对抗思想
 * @createTime 2021年08月24日 10:43:00
 */
public class middleInIO_41 {
    private  Queue<Integer> A, B;
    public middleInIO_41(){
        A = new PriorityQueue<>();//小根堆，保存较大 的一半
        B = new PriorityQueue<>((o1, o2) -> (o2 - o1));//大根堆，储存较小的一半
    }

    public void addNum(int num) {
        if (A.size() != B.size()) {//目前是奇数个
            A.add(num);
            B.add(A.poll());
        } else {//偶数个
            B.add(num);
            A.add(B.poll());
        }
    }
    public double findMedian() {
        return A.size() != B.size() ? A.peek() : (A.peek() + B.peek()) / 2.0;
    }

}
