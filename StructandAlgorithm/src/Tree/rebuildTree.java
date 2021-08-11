package Tree;


import static util.printArray.printArray;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName rebuildTree.java
 * @Description
 * 已知一棵二叉树中没有重复节点， 并且给定了这棵树的中序遍历数组和先序遍历 数组， 返回后序遍历数组。
 * 比如给定： int[] pre = { 1 , 2, 4, 5, 3, 6, 7 } ;
 * int[] in = { 4, 2, 5, 1 , 6, 3, 7 } ;
 * 返回： {4, 5, 2, 6, 7, 3, 1 }
 *
 * 前序：中左右   中序：左中右  后序：左右中
 * 首先可以确定  前序的开头（中）是后序的尾  案例中是1， 再到中序中确定4 2 5是左树，6 3 7是右树

 * @createTime 2021年08月11日 15:08:00
 */
public class rebuildTree {
    public static int[] getPosArray(int[] pre, int[] in) {
        if (pre == null || in == null) {
            return null;
        }
        int len = pre.length;
        int[] pos = new int[len];
        process1(pre, in, pos, 0, len - 1, 0, len - 1, 0, len - 1);
        return pos;
    }

    public static void process1(int[] pre, int[] in, int[] pos,
                                int prei, int prej,
                                int ini, int inj,
                                int posi, int posj) {
        //有越界了直接return
        if (prei > prej) {
            return;
        }
        //只剩下一个数了,直接填
        if (prei == prej) {
            pos[posi] = pre[prei];
            return;
        }
        //首先把前序的头放到后序的尾
        pos[posj] = pre[prei];
        //找到被放置的元素在中序中的哪里
        int find = ini;
        for (; find <= inj; find++) {
            if (in[find] == pre[prei]) {
                break;
            }
        }
        //在这里记住一句口诀 两个process操作左右树 下标是操作哪些数是对应的左/右树
        //此时find - ini相当于操作这么多数当做左树
        //prei+1的原因是之前操作过"把前序的头放到后序的尾"
        process1(pre, in, pos, prei + 1, prei + (find - ini), ini, find - 1, posi, posi + (find - ini) - 1);
        //哪些下标对应的是右树
        process1(pre, in, pos, prei + (find - ini) + 1, prej, find + 1, inj, posi + (find - ini), posj - 1);
    }

    public static void main(String[] args) {
        int[] pre = { 1, 2, 4, 5, 3, 6, 7 };
        int[] in = { 4, 2, 5, 1, 6, 3, 7 };
        int[] pos = getPosArray(pre, in);
        printArray(pos);

    }



}
