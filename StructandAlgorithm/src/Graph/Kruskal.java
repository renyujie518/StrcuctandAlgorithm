package Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName Kruskal.java
 * @Description Kruskal k算法实现生产最小生成树（保证联通，路径和最小）
 *
 * 先把边从小到大排序 依次放入 看是否会不会形成环，形成环就不要
 *
 * 一开始认为所有的点都是一个集合，都不是联通的，每次加边的时候把这两个点（from,to）合成一个集合，如果下次发现在集合里有，就不要这个边（并查集）
 * 这里自己实现一个并查集简易版
 * @createTime 2021年03月14日 17:01:00
 */
public class Kruskal {

    //关键功能简易版 初始化 isSameSet union
    public static class Mylists{
        public HashMap<Node, List<Node>> setMap;  //一个点所对应的集合。相当于总表

        //初始化 输入一个node的列表集合
        public void Mylists(List<Node> nodes){
            for (Node node : nodes) {
                ArrayList<Node> list = new ArrayList<Node>();
                list.add(node);
                setMap.put(node, list);  //这个点node对应的是集合list，一开始认为每个的点都是自己的一个list
            }
        }

        //判断from,to是否是一个集合
        public boolean isSameSet(Node from,Node to){
            List<Node> fromList = setMap.get(from);
            List<Node> toList = setMap.get(to);   //从总表中拿出 from列表 to列表
            return fromList == toList;  //from to是针对edge连接的两个节点，若是无向图，from=to .这里是两个List的地址值相等，代表在总表里在一个List里
        }

        //from,to合成一个集合
        public void union(Node from,Node to){
            List<Node> fromList = setMap.get(from);
            List<Node> toList = setMap.get(to);   //从总表中拿出 from列表 to列表
            //把Tolist中的所有to节点放到FromList里
            for (Node toNode : toList) {
                fromList.add(toNode);
                //在跟新总表，把to节点归属于from集合
                setMap.put(toNode, fromList);

                //至此。所有的from节点和to节点都在FromList里，同时老tolist里的元素也在总表里归属于from

            }
        }
    }
//在class06里的源代码去找

//    public static Set<Edge> kruskalMST(Graph graph) {
//        UnionFind unionFind = new UnionFind();   // 并查集
//        unionFind.makeSets(graph.nodes.values());  //初始化
//        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(new EdgeComparator());
//        for (Edge edge : graph.edges) {
//            priorityQueue.add(edge);       //比较器从大到小排边
//        }
//        Set<Edge> result = new HashSet<>();
//        while (!priorityQueue.isEmpty()) {
//            Edge edge = priorityQueue.poll();
//            if (!unionFind.isSameSet(edge.from, edge.to)) {  //判断from,to是否是一个集合，不是说明这个边要
//                result.add(edge);
//                unionFind.union(edge.from, edge.to);  //合并，下次就不要了
//            }
//        }
//        return result;
//    }
}
