package MiddleClass;

import java.util.TreeMap;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName GetFolderTree.java
 * @Description
 * 给你一个字符串类型的数组arr，
 * 譬如： String[] arr = { " b\\cst", "d\\", " a\\d\\e", "a\\b\\c" } ;
 * 你把这些路径中蕴含的目录结构给画出来， 子目录直接列在父目录下面， 并比父目录向右进两格， 就像这样:
 * a
 *   b
 *     c
 *   d
 *     e
 * b
 *   cst
 * d
 * 同一级的需要按字母顺序排列， 不能乱。
 *
 *
 * 思路：
 * 前缀树（路是相应的字母）  再深度优先遍历得到路径 遍历的时候加0个，2个，4个...空格
 * 但为了好操作  在本例中的节点中还是在节点中也加入了相应的字母（name）
 *
 *
 * @createTime 2021年08月09日 10:08:00
 */
public class GetFolderTree {
    public static class Node{
        public String name;  //路径名  如a b c
        public TreeMap<String, Node> nextMap; //key 下级路是啥  value 下一个节点
        //注意，这里用了有序map 希望在同一级里的有序输出  比如输出为a b c而不是b c a这样的

        public Node(String name) {
            this.name = name;
            nextMap = new TreeMap<>();
        }
    }

    //构建这颗前缀树
    public static Node generateFolderTree(String[] paths) {
        //首先初始化头节点为" "
        Node head = new Node(" ");
        for (String path : paths) {
            String[] pathName = path.split("\\\\");
            //这里是java特性 首先1号和3号位是转义 -> \\ 然后前面的那个\又是正则
            //这时候假如函数输入的时候是["a\\b\\c"] 现在就是[a,b,c]
            Node curr = head;
            for (int i = 0; i < pathName.length; i++) {
                if (!curr.nextMap.containsKey(pathName[i])) {//如果下节点map中没有，就添加
                    curr.nextMap.put(pathName[i], new Node(pathName[i]));
                }
                //要是有这个节点了，直接往下走
                curr = curr.nextMap.get(pathName[i]);
            }
        }
        return head;
    }

    public static void print(String[] folderPaths) {
        if (folderPaths == null || folderPaths.length == 0) {
            return;
        }
        Node head = generateFolderTree(folderPaths);
        printProcess(head, 0);
    }

    //深度优先打印 head当前在level层
    public static void printProcess(Node head, int level) {
        if (level != 0) {
            //2*level-1
            System.out.println(get2nSpace(level) + head.name);
        }
        for (Node next : head.nextMap.values()) {
            printProcess(next, level + 1);
        }
    }

    //因为空格是0，2，4，8这样累加的
    public static String get2nSpace(int level) {
        String res = " ";
        for (int i = 1; i < level; i++) {
            res = res + " ";
        }
        return res;
    }
    public static void main(String[] args) {
        String[] arr = { "b\\cst", "d\\", "a\\d\\e", "a\\b\\c" };
        print(arr);
    }

}
