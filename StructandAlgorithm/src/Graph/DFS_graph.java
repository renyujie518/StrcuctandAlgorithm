package Graph;

import java.util.HashSet;
import java.util.Stack;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName DFS_graph.java
 * @Description 深度优先遍历图  利用栈
 * @createTime 2021年03月14日 15:53:00
 */
public class DFS_graph {
    public static void dfs(Node node){
        if (node == null){
            return;
        }
        Stack<Node> stack = new Stack<Node>();
        HashSet<Node> set = new HashSet<Node>();
        stack.add(node);
        set.add(node);
        System.out.println(node.value);  //先把首先选定的节点打印
        while (!stack.isEmpty()){
            Node curr = stack.pop();
            for (Node next : curr.nexts) {
                if (!set.contains(next)){
                    stack.push(curr);  //把上次弹出的点再压回去
                    stack.push(next);  // 把next也压回去
                    set.add(next);
                    System.out.println(next.value);   //两次压完再打印
                    break;   //这个break就是要一条道走到黑（深度优先）
                }
            }
        }
    }

}
