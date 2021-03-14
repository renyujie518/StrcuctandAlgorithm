package Graph;

import java.util.*;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName TopoSort.java
 * @Description 拓扑排序 （寻找依赖关系 有向图 类似编译过程中需要的依赖顺序）
 *
 * 思路 ： 先找如度为0的点，然后去除所有其所有联系 依次类推
 * @createTime 2021年03月14日 16:18:00
 */
public class TopoSort {
    public static List<Node> TopoSort(Graph graph){
        //首先创建一个hashmap用于记录这个图的状况  key = node value = 该node剩余的入度
        HashMap<Node, Integer> inMap = new HashMap<Node, Integer>();
        //在建立一个队列，刚在思路里说明了，入度为0的点才能进入这个队列
        Queue<Node> ZeroInqueue  = new LinkedList<Node>();
        //先找到这个图里入度为0的点当做起始
        for (Node node : graph.nodes.values()) {   //Collection<V> values() 这个values()是工具类里直接获取值的函数
            inMap.put(node, node.in);  //循环所有node的时候把map构建好，主要是每个node对应的入度
            //构建完后找到入度为0的点当做起始
            if (node.in == 0){
                ZeroInqueue.add(node);
            }
        }
        //下面开始排序，最终结果放入result
        ArrayList<Node> result = new ArrayList<Node>();
        while (!ZeroInqueue.isEmpty()){
            Node curr = ZeroInqueue.poll();
            result.add(curr);   //每弹出就加入result里
            for (Node next : curr.nexts) {
                //这里十分重要 怎么去除所有其所有联系 即  该node的下一个节点next的剩余的入度-1 后再放回这个map,相当于一种跟新
                inMap.put(next, inMap.get(next.in) - 1);
                //一旦发信有某个节点的（这里所说的是next是0）放入那个队列
                if (inMap.get(next) == 0){
                    ZeroInqueue.add(next);
                }
            }
        }
        return result;
    }
}
