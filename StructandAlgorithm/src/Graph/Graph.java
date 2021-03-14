package Graph;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName Graph.java
 * @Description TODO
 * @createTime 2021年03月14日 14:06:00
 */
public class Graph {
    public HashMap<Integer,Node> nodes;
    public HashSet<Edge> edges;

    public Graph() {
        nodes = new HashMap<>();
        edges = new HashSet<>();
    }
}
