package recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName printAllSubsquence.java
 * @Description 打印最长子序列
 * 比如给定abc  打印 abc ab,ac,a,bc ,b,c,null
 * 思路  每个字符都选择要还是不要，和二叉树类似，再接着，每个子树又要选择要还是不要
 * @createTime 2021年03月18日 14:07:00
 */
public class printAllSubsquence {
    public static void printAllSubsquence(String str){
        char[] chs = str.toCharArray();
        process(chs, 0,new ArrayList<Character>());

    }
    //来到i的位置，要还是不要两条路 ,res储存的是之前的选择所形成的的列表
    private static void process(char[] chs, int i, List<Character> res) {
        //basecase  来到终止的位置，即遍历完所有的字符串的时候，打印出来结果即可
        if (i ==chs.length){
            printList(res);
            return;
        }
        List<Character> resKeep = copyList(res);//把之前的结果拷贝一份
        resKeep.add(chs[i]);  //加入当前字符
        process(chs,i+1,resKeep);  //要当前字符的路
        List<Character> resNoinclude = copyList(res);////把之前的结果拷贝一份(注意，这里包括了要当前字符的路的结果)
        process(chs,i+1,resNoinclude);//不要当前的路

    }
    //工具函数，将之前存放的结果的list再复制一份放到一个新的list里
    private static List<Character> copyList(List<Character> oldList) {
        if (oldList == null){
            return new ArrayList<Character>();
        }
        ArrayList<Character> newList = new ArrayList<>();
        for (Character ch : oldList) {
            newList.add(ch);
        }
        return newList;
    }
    //工具函数，打印list里所有的字符串
    private static void printList(List<Character> res) {
        for (Character ch : res) {
            System.out.print(ch);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        String test = "abc";
        printAllSubsquence(test);
    }
}
