package Graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName Dijkstra.java 单源最短路 规定出发点
 * @Description 适用权值不要有累加为 负数的环
 *
 * 思路：
 * 每次在剩余节点中找到离起点（起点永远固定）最近的节点(minNode)放到队列中，并用来更新剩下的节点的距离，
 * 再将它标记上表示已经找到到它的最短路径，以后不用更新它了
 * 不断从剩余节点中拿出一个可以确定最短路径的节点最终求得从起点到每个节点的最短距离。
 * @createTime 2021年03月14日 20:09:00
 */
public class Dijkstra {
    public static HashMap<Node,Integer> Dijkstra1(Node head){
        //key: 从选定节点head到key节点   value： 从选定节点head到key节点的最小距离   !!!注意，都是针对到源头head来说的
        //在开始的时候，没啥记录，那这些Integer的值可以看做正无穷
        HashMap<Node, Integer> MinDistance2HeadMap = new HashMap<Node, Integer>();
        MinDistance2HeadMap.put(head, 0);  //初始化，自己到自己的距离为0
        //已经求过距离的节点要锁住，以后不能再碰，利用set
        HashSet<Node> lockSet = new HashSet<Node>();
        Node minNode = getMinDistanceAndUnlockNode(MinDistance2HeadMap, lockSet);
        //获得到本源的距离最小且没有被锁住的节点minNode（程序刚开始这个minNode就是本源head自己），minNode也可以看做待更新节点
        while (minNode!=null){
            Integer distance = MinDistance2HeadMap.get(minNode);  //因为上一步只是返回节点，这里把距离值取出来，用于跟新，这里可以看做原记录值
            //注意 在程序运行开始的时候  这个distance是0，因为getMinDistanceAndUnlockNode只能取到本源，本源到自己不就是0嘛
            //什么时候跟新，就是考察所有的边及每个边的toNode
            for (Edge edge : minNode.edges) {
                Node toNode = edge.to;
                if (!MinDistance2HeadMap.containsKey(toNode)){  //如果在总表中没有过toNode(之前都是正无穷)
                    MinDistance2HeadMap.put(toNode, distance + edge.weight);
                    //toNode在表里没记录（正无穷），那么toNode距离本源head的最小距离是 原记录值+权重
                    //这里可以理解为 toNode让一个原本没路的有路了，就直接跟新总表
                }else {//toNode如果让之前一个有路的（因为图是互通的，考虑A节点时遇到B，处理B节点的toNode也会遇到A）距离更小了，也跟新
                    MinDistance2HeadMap.put(toNode, Math.min(MinDistance2HeadMap.get(toNode), distance + edge.weight));
                }
            }
            //minNode处理完(minNode所有边处理完)，锁住
            lockSet.add(minNode);
            //minNode再跟新
            minNode = getMinDistanceAndUnlockNode(MinDistance2HeadMap, lockSet);
        }
        return MinDistance2HeadMap;
    }


    //在MinDistance2HeadMap中选一个节点，这个节点没有被锁住过，这个节点到本源的距离最小
    public static Node getMinDistanceAndUnlockNode( HashMap<Node, Integer> MinDistance2HeadMap,HashSet<Node> lockSet){
        Node minNode =null;
        int minDistance = Integer.MAX_VALUE;
        for (Map.Entry<Node, Integer> entry : MinDistance2HeadMap.entrySet()) {//在总表Map里转化为entrySet()后遍历
            Node node = entry.getKey();
            Integer distance = entry.getValue();  //把Map中记录的节点和距离都拿出来
            if (!lockSet.contains(node) && distance <minDistance){ //如果这个节点没被发现过而且这个节点到本源的距离更小了，更新返回
                minNode = node;
                minDistance = distance;
            }
        }
        return minNode;  //要是返回null，就是代表所有节点都考虑过了，都被锁住了。29行的while终止
    }

}
