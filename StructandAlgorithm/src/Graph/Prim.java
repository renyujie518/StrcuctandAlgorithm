package Graph;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName Prim.java
 * @Description Prim寻找最短路径和
 * 先随机选个点，把它的所有边找出最小的edge，根据这个edge的to找到下一个点。这个点是个新点才把edge放入，循环直至所有边遍历过
 * @createTime 2021年03月14日 19:07:00
 */
public class Prim {
    public static Set<Edge> Dijkstra1(Graph graph){
        //先建立一个小根堆,解锁的边放到这个小根堆里(优先级队列)
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<Edge>(new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return o1.weight- o2.weight;   //从小到大  小根堆
            }
        });
        HashSet<Node> set = new HashSet<Node>();   //挑选的时候要判断这个点是不是新点，用set机制
        HashSet<Edge> result = new HashSet<Edge>();  //挑选的最短路径（边）放入result里
        for (Node node : graph.nodes.values()) {   //随便挑一个点,这也解决了"森林问题"（多个不连通区域）
            //node是起始点，先加入set
            set.add(node);
            //把这个点所有的边放到优先级队列里去,就会按照比较器排序
            for (Edge edge : node.edges) {
                priorityQueue.add(edge);

            }
            //排好序了，开始判断 新点
            while (!priorityQueue.isEmpty()){
                Edge edge = priorityQueue.poll();   //弹出来的是最小的
                Node toNode = edge.to;  //这个边的to节点是不是新点，怎么查，用set
                if (!set.contains(toNode)){  //set不含的时候是新的点,那么上面选出的那个边就符合条件
                    result.add(edge);
                    set.add(toNode); //to节点是被考虑过的，就不是新的点了
                    //接下来是关键    ！！！！ to节点发散出的边放到优先级队列里去 周而复始判断
                    for (Edge nextEdge : toNode.edges) {
                        priorityQueue.add(nextEdge);  //注意，这里可能会把上次选过的edge再次添加进去，实际上是没必要的
                        //所以这里可以作一定的优化，就是再创建一个HashSet，利用set的特点保证不重放，实际上就算重入了，也没关系
                        //因为在result.add(edge)的时候有个条件判断是不是新点，新点是唯一的，所以只是增加了点循环次数(常数时间)
                    }
                }
            }

        }
        return result;
    }
}
