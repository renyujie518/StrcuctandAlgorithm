package Graph;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName Edge.java
 * @Description TODO
 * @createTime 2021年03月14日 14:07:00
 */
public class Edge {
    public int weight;  //权值
    public Node from; //注意，这个from 和to是针对有向图的，若是无向图就两个node互指即可
    public Node to;

    public Edge(int weight, Node from, Node to) {
        this.weight = weight;
        this.from = from;
        this.to = to;
    }
}
