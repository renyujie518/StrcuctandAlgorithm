package Graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName BFS_graph.java
 * @Description 图的宽度优先遍历  利用队列
 * @createTime 2021年03月14日 15:29:00
 */
public class BFS_graph {
    public static void bfs(Node node){
        if (node == null){
            return;
        }

        Queue<Node> queue = new LinkedList<Node>();
        HashSet<Node> set = new HashSet<Node>();  //利用set的key唯一机制，保证在下一层的时候不会再去考虑上一层的连接，避免死循环
        queue.add(node);
        set.add(node);
        while (!queue.isEmpty()){
            Node curr = queue.poll();
            System.out.println(curr.value);//弹出就打印
            for (Node next : curr.nexts) {  //往下一节点走
                if (!set.contains(next)){  //不包含才继续往队列和set里添加
                    set.add(next);
                    queue.add(next);
                }
            }
        }
    }
}
