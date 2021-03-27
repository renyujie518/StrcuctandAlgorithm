package Tree;

import LinkedList.ListNode;

import java.util.List;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName maxHappy.java
 * @Description
 * 派对的最大快乐值
 * 员工信息的定义如下:
 * class Employee{
 * public int happy;//这名员工可以带来的快乐值
 * List<Employee>subordinates;//这名员工有哪些直接下级
 * }
 * 公司的每个员工都符合Employee类的描述。整个公司的人员结构可以看作是一棵标准的、没有环的    多叉树。
 * 树的头节点是公司唯一的老板。除老板之外的每个员工都有唯一的直接上级。
 * 叶节点是没有任何下属的基层员工(subordinates列表为空)，除基层员工外，每个员工都有一个或多个直接下级。
 * 这个公司现在要办party，你可以决定哪些员工来，哪些员工不来。
 * 但是要遵循如下规则：
 * 1.如果某个员工来了，那么这个员工的所有直接下级都不能来
 * 2.派对的整体快乐值是所有到场员工快乐值的累加
 * 3.你的目标是让派对的整体快乐值尽量大
 *
 * 给定一棵多叉树的头节点boss，请返回派对的最大快乐值。
 *
 * 还是以头节点head（其直接子节点有a,b,c...）参与与否
 * 1：head参与，其直接下级都不能参与  派对max = head快乐值+ a整棵树在a不来的情况下的max快乐值+ b整棵树在b不来的情况下的max快乐值+...
 * 2:head不参与 0+max{a整棵树在a不来的情况下的max快乐值,a整棵树在a来的情况下的max快乐值}+max{b参加与否}+max{c参加与否}+..
 * @createTime 2021年03月25日 14:25:00
 */
public class maxHappy {

    public static class Employee{
        public  int happy;//这名员工可以带来的快乐值
        public List<Employee> nexts;// 这名员工有哪些直接下级
    }
    //采用左神的树形DP
    public static class Info{
        public int laiMaxHappy;  //来情况下的最大快乐值
        public int bulaiMaxHappy; //不来情况下的最大快乐值

        public Info(int laiMaxHappy, int bulaiMaxHappy) {
            this.laiMaxHappy = laiMaxHappy;
            this.bulaiMaxHappy = bulaiMaxHappy;
        }
    }
    public static Info process1(Employee employee){
        //basecase是针对基层员工说的 当employee是基层员工，他的下级就是空 ，来情况下的最大快乐值就是他自己，不来情况下的最大快乐值为0
        if (employee.nexts.isEmpty()){
            return new Info(employee.happy, 0);
        }
        int lai = employee.happy;//某员工来情况下的最大快乐值的初始值是自己
        int bulai = 0;//某员工不来情况下的最大快乐值的初始值是0
        for (Employee next : employee.nexts) { //对于中间节点的该员工的每个子员工(直接下级)
            //先得到子员工的子树上的信息  注意，这里是直接下级的子树的信息
            Info nextInfo = process1(next);
            lai += nextInfo.bulaiMaxHappy;//子员工来，其子树不能来
            bulai += Math.max(nextInfo.laiMaxHappy, nextInfo.bulaiMaxHappy);//子员工不来，其子树可以选择来，也可以选择不来
        }
        return new Info(lai, bulai);
    }

    public static int maxHappy1(Employee boss){
        Info headInfo = process1(boss);
        return Math.max(headInfo.laiMaxHappy, headInfo.bulaiMaxHappy); //返回boss来与不来获得的派对最大值
    }


    //DP
    public static int maxHappy2(int[][] matrix) {
        int[][] dp = new int[matrix.length][2];
        boolean[] visited = new boolean[matrix.length];
        int root = 0;
        for (int i = 0; i < matrix.length; i++) {
            if (i == matrix[i][0]) {
                root = i;
            }
        }
        process2(matrix, dp, visited, root);
        return Math.max(dp[root][0], dp[root][1]);
    }

    public static void process2(int[][] matrix, int[][] dp, boolean[] visited, int root) {
        visited[root] = true;
        dp[root][1] = matrix[root][1];
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][0] == root && !visited[i]) {
                process2(matrix, dp, visited, i);
                dp[root][1] += dp[i][0];
                dp[root][0] += Math.max(dp[i][1], dp[i][0]);
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = { { 1, 8 }, { 1, 9 }, { 1, 10 } };
        System.out.println(maxHappy2(matrix));
    }


}
