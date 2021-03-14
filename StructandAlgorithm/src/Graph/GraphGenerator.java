package Graph;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName GraphGenerator.java
 * @Description 由一个矩阵构造图
 *
 *N*3的矩阵    【weight,from节点上的值，to节点上的值】
 * @createTime 2021年03月14日 15:03:00
 */
public class GraphGenerator {
    public static Graph createGraph(Integer[][] matrix) {
        Graph graph = new Graph();
        for (int i = 0; i < matrix.length; i++) {
            Integer weight = matrix[i][0];
            Integer from = matrix[i][1];
            Integer to = matrix[i][2];
            if (!graph.nodes.containsKey(from)) {  //HashMap<Integer,Node> nodes; key是节点所带的值 value是这个节点
                graph.nodes.put(from, new Node(from));
            }
            if (!graph.nodes.containsKey(to)) {
                graph.nodes.put(to, new Node(to));
            }
            Node fromNode = graph.nodes.get(from);
            Node toNode = graph.nodes.get(to);
            Edge newEdge = new Edge(weight, fromNode, toNode);  //把边建立出来

            fromNode.nexts.add(toNode);
            fromNode.out++;
            toNode.in++;
            fromNode.edges.add(newEdge);
            graph.edges.add(newEdge);
        }
        return graph;
    }

}
