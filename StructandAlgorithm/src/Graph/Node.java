package Graph;

import java.util.ArrayList;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName Node.java
 * @Description TODO
 * @createTime 2021年03月14日 14:07:00
 */
public class Node {
    public int value;  //自己的数据项  点上的值
    public int in;   //入度  有多少点直接接入(无向图就是连的边数)
    public int out;
    public ArrayList<Node> nexts;  //发散出去的直连的点
    public ArrayList<Edge> edges; //发散出去的边

    public Node(int value) {
        this.value = value;
        in = 0;
        out = 0;
        nexts = new ArrayList<>();
        edges = new ArrayList<>();
    }
}
