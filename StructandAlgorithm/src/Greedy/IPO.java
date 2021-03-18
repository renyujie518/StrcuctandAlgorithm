package Greedy;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName IPO.java
 * @Description
 * 输入：正数数组costs
 * 正数数组profits
 * 正数k
 * 正数W
 * 含义：costs[i]表示i号项目的花费
 * profits[i]表示i号项目在扣除花费之后还能挣到的钱(利润)
 * k表示你只能串行的最多做k个项目
 * W表示你初始的资金
 * 说明：你每做完一个项目，马上获得的收益，可以支持你去做下一个项目。
 * 输出：你最后获得的最大钱数。
 *
 *
 * //思路：
 * 按照const放到一个小根堆（被锁住），一旦poll出，按照profits放到一个大根堆（解锁）
 * @createTime 2021年03月16日 18:13:00
 */
public class IPO {
    public static  class  IPONode{
        public int costs;
        public int profits;

        public IPONode(int costs, int profits) {
            this.costs = costs;
            this.profits = profits;
        }
    }

    public static  class MinCostComparator implements Comparator<IPONode>{

        @Override
        public int compare(IPONode o1, IPONode o2) {
            return o1.costs - o2.costs;
        }
    }

    public static  class  MaxProfitsComparator implements  Comparator<IPONode>{

        @Override
        public int compare(IPONode o1, IPONode o2) {
            return o2.profits - o1.profits;
        }
    }
    public static  int findMaxcCapital(int k,int W,int[] Costs,int[] Profits){
        IPONode[] nodes = new IPONode[Profits.length];//Costs也行，因为他两是成对出现的
        for (int i = 0; i < Profits.length; i++) {
            nodes[i] = new IPONode(Costs[i], Profits[i]);
        }
        //建立两个优先级队列
        PriorityQueue<IPONode> maxProfitsQ = new PriorityQueue<>(new MaxProfitsComparator());
        PriorityQueue<IPONode> minCostsQ = new PriorityQueue<>(new MinCostComparator());
        //先放到最小花费里
        for (int i = 0; i < nodes.length; i++) {
            minCostsQ.add(nodes[i]);
        }
        //只有k次机会
        for (int i = 0; i < k; i++) {
            //最小花费队列不为空（锁住的） && 现有资金W >= 栈顶（小根堆，所以是最小花费）小cost项目的cost  才能进入最大利润Q(解锁)里
            while (!minCostsQ.isEmpty() && W >= minCostsQ.peek().costs){
                maxProfitsQ.add(minCostsQ.poll());
            }
            //中途会断，设置终止条件 一旦资金链跟不上(maxQ为空，因为没放进去就不满足，不满足就是资金链断了)，返回现有W
            if (maxProfitsQ.isEmpty()){
                return W;
            }
            //否则W是  上次W+利润
            W = W + maxProfitsQ.poll().profits;
        }
        return W;
    }
}
