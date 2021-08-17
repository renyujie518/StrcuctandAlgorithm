package SwordOffer;

import java.util.HashSet;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName duplicatiomArrayQuanPaiLie_08.java
 * @Description
 *  有重复字符串的排列组合。编写一种方法，计算某字符串的所有排列组合。
 *
 *     示例1:
 *
 *     输入：S = "qqe"
 *     输出：["eqq","qeq","qqe"]
 *     示例2:
 *
 *     输入：S = "ab"
 *     输出：["ab", "ba"]
 *     提示:
 *
 *     字符都是英文字母。
 *     字符串长度在[1, 9]之间。
 *
 *     主要用到回溯算法
 *
 *解决一个回溯问题，实际上就是一个决策树的遍历过程。你只需要思考 3 个问题：
 * 1、路径：也就是已经做出的选择。
 * 2、选择列表：也就是你当前可以做的选择。
 * 3、结束条件：也就是到达决策树底层，无法再做选择的条件。
 * 模板：
 *  result = []
 * def backtrack(路径, 选择列表):
 *     if 满足结束条件:
 *         result.add(路径)
 *         return
 *
 *     for 选择 in 选择列表:
 *         做选择
 *         backtrack(路径, 选择列表)
 *         撤销选择
 *
 *  其核心就是 for 循环里面的递归，在递归调用之前「做选择」，在递归调用之后「撤销选择」
 *  https://zhuanlan.zhihu.com/p/93530380
 * @createTime 2021年08月17日 18:19:00
 */
public class duplicatiomArrayQuanPaiLie_08 {
    //利用set去重
    private static HashSet<String> resultSet = new HashSet<>();

    public static String[] duplicatiomArrayQuanPaiLie(String s) {
        char[] chars = s.toCharArray();

        //回溯  boolean[]用于判断每个节点判断过没
        backtrace(chars, new StringBuffer(), new boolean[chars.length]);
        //toArray()方法用于生成与HashSet相同的元素组成的数组。本质上，它将所有元素从HashSet复制到新数组。
        //为什么有new String[0]：
        //toArray() 返回的是一个Object数组  怎么做到传回想要的类型
        //传递一个用户想要的数组类型的一个数组实例进去，多长都无所谓（因此我们常常使用一个0长度的，毕竟把类型带进去就OK了）
        //于是，toArray内部就会按照你想要的这种类型，给构造一个数组出来
        return resultSet.toArray(new String[0]);

    }

    public static void backtrace(char[] chars, StringBuffer buffer, boolean[] visited) {
        //终止条件  拼成的串和原先的串字数相同   加到set当结果
        if (buffer.length() == chars.length) {
            resultSet.add(buffer.toString());
        }

        for (int i = 0; i < chars.length; i++) {
            if (visited[i]) {
                continue;
            }
            //遍历到源字符串的i位置  就见过一次,再拼接到新字符串上
            visited[i] = true;
            buffer.append(chars[i]);

            //递归 进入下次决策树 默认没见过，同时删掉buffer最后一位（取消选择）
            //回溯算法要求：在递归之前做出选择，在递归之后撤销刚才的选择，就能正确得到每个节点的选择列表和路径。
            backtrace(chars, buffer, visited);
            visited[i] = false;
            buffer.deleteCharAt(buffer.length() - 1);
        }
    }




}
