package facing;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName zhezhi2Tree.java
 * @Description 张纸条对折后的结果(中序遍历一棵树)
 * @createTime 2021年03月06日 22:56:00
 */
public class zhezhi2Tree {
    public static void main(String[] args) {
        int N = 3;
        printProcess(1,N,true);  //先对折一下是凹
    }

//递归过程  来到某一个节点  i是层数  N 一共的层数   down = true 凹  down = false 凸
    public static void printProcess(int i,int N,boolean down){
        if (i>N){
            return;
        }
        printProcess(i+1,N,true);  //先是凹 比如对折一下
        System.out.println(down ? "凹" : "凸");
        printProcess(i+1,N,false);
    }
}
