package facing;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName jiaYouZhan.java
 * @Description
 * 在一条环路上有N个加油站，其中第i个加油站有汽油gas[i]升。
 *
 * 你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1个加油站需要消耗汽油cost[i]升。
 * 你从其中的一个加油站出发，开始时油箱为空。
 * 如果你可以绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1。
 *
 * 说明:
 *
 * 如果题目有解，该答案即为唯一答案。
 * 输入数组均为非空数组，且长度相同。
 * 输入数组中的元素均为非负数。
 * 示例1:
 *
 * 输入:
 * gas  = [1,2,3,4,5]
 * cost = [3,4,5,1,2]
 *
 * 输出: 3
 *
 * 解释:
 * 从 3 号加油站(索引为 3 处)出发，可获得 4 升汽油。此时油箱有 = 0 + 4 = 4 升汽油
 * 开往 4 号加油站，此时油箱有 4 - 1 + 5 = 8 升汽油
 * 开往 0 号加油站，此时油箱有 8 - 2 + 1 = 7 升汽油
 * 开往 1 号加油站，此时油箱有 7 - 3 + 2 = 6 升汽油
 * 开往 2 号加油站，此时油箱有 6 - 4 + 3 = 5 升汽油
 * 开往 3 号加油站，你需要消耗 5 升汽油，正好足够你返回到 3 号加油站。
 * 因此，3 可为起始索引。
 * 示例 2:
 *
 * 输入:
 * gas  = [2,3,4]
 * cost = [3,4,3]
 *
 * 输出: -1
 *
 * 解释:
 * 你不能从 0 号或 1 号加油站出发，因为没有足够的汽油可以让你行驶到下一个加油站。
 * 我们从 2 号加油站出发，可以获得 4 升汽油。 此时油箱有 = 0 + 4 = 4 升汽油
 * 开往 0 号加油站，此时油箱有 4 - 3 + 2 = 3 升汽油
 * 开往 1 号加油站，此时油箱有 3 - 3 + 3 = 3 升汽油
 * 你无法返回 2 号加油站，因为返程需要消耗 4 升汽油，但是你的油箱只有 3 升汽油。
 * 因此，无论怎样，你都不可能绕环路行驶一周。
 *
 * 最容易想到的解法是：从头到尾遍历每个加油站，并检查以该加油站为起点，最终能否行驶一周。
 * 我们可以通过减小被检查的加油站数目，来降低总的时间复杂度。
 * 我们首先检查第 0 个加油站，并试图判断能否环绕一周；如果不能，就从第一个无法到达的加油站开始继续检查。

 * @createTime 2021年08月04日 14:02:00
 */
public class jiaYouZhan {
    //暴力解法
    public static int compose1(int[] gas, int[] cost) {
        int n = gas.length;
        //考虑从每个点出发
        for (int i = 0; i < n; i++) {
            int j = i;
            int remain = gas[i];
            //当前剩余的油量能否到达下一个点的补给
            while (remain - cost[j] >= 0) {
                //这个过程是减去花费的，再加上新的补给
                //由于是个圆，得到下一个点的时候我们需要取余数
                remain = remain - cost[j] + gas[j + 1] % n;
                j = (j + 1) % n;
                //j回到了i
                if (j == i) {
                    return i;
                }
            }
        }
        //任何点都不行
        return -1;
    }

    //优化解法
    /*
    我们考虑一下下边的情况。
* * * * * *
^     ^
i     j
当考虑 i 能到达的最远的时候，假设是 j。
那么 i + 1 到 j 之间的节点是不是就都不可能绕一圈了？
假设 i + 1 的节点能绕一圈，那么就意味着从 i + 1 开始一定能到达 j + 1。
又因为从 i 能到达 i + 1，所以从 i 也能到达 j + 1。
但事实上，i 最远到达 j 。
产生矛盾，所以 i + 1 的节点一定不能绕一圈。同理，其他的也是一样的证明。

所以下一次的 i 我们不需要从 i + 1 开始考虑，直接从 j + 1 开始考虑即可。
还有一种情况，就是因为到达末尾的时候，会回到 0。
如果对于下边的情况。
* * * * * *
  ^   ^
  j   i
如果 i 最远能够到达 j ，根据上边的结论 i + 1 到 j 之间的节点都不可能绕一圈了。
想象成一个圆，所以 i 后边的节点就都不需要考虑了，直接返回 -1 即可。
     */
    public static int compose2(int[] gas, int[] cost) {
        int n = gas.length;
        for (int i = 0; i < n; i++) {
            int j = i;
            int remain = gas[i];
            while (remain - cost[j] >= 0) {
                //减去花费的加上新的点的补给
                remain = remain - cost[j] + gas[(j + 1) % n];
                j = (j + 1) % n;
            }
            //j 回到了 i
            if (j == i) {
                return i;
            }
            //最远距离绕到了之前，所以 i 后边的都不可能绕一圈了
            if (j < i) {
                return -1;
            }
            //i 直接跳到 j，外层 for 循环执行 i++，相当于从 j + 1 开始考虑
            i = j;
        }
        return -1;
    }


    /*
    暴力破解慢的原因就是会进行很多重复的计算。比如下边的情况：

假设当前在考虑 i,先初始化 j = i
* * * * * *
      ^
      i
      ^
      j

随后 j 会进行后移
* * * * * *
      ^ ^
      i j

继续后移
* * * * * *
      ^   ^
      i   j

继续后移
* * * * * *
^     ^
j     i

此时 j 又回到了第 0 个位置，我们在之前已经考虑过了这个位置。
如果之前考虑第 0 个位置的时候，最远到了第 2 个位置。
那么此时 j 就可以直接跳到第 2 个位置，同时加上当时的剩余汽油，继续考虑
* * * * * *
    ^ ^
    j i
利用上边的思想我们可以进行一个优化，就是每考虑一个点，就将当前点能够到达的最远距离记录下来，同时到达最远距离时候的剩余汽油也要记下来。
     */

    //空间换时间
    public static int compose3(int[] gas, int[] cost) {
        int n = gas.length;
        //记录能到的最远距离
        int[] farIndex = new int[n];
        //首先假设每个都不能到
        for (int i = 0; i < farIndex.length; i++) {
            farIndex[i] = -1;
        }
        //记录到达最远距离时候剩余的汽油
        int[] farIndexRemain = new int[n];
        for (int i = 0; i < n; i++) {
            int j = i;
            //到第一个地点就会有油加进去，最不济也是gas[i]，跟别说之前剩下的
            int remain = gas[i];
            while (remain - cost[i] >= 0) {
                //到达下个点后的剩余
                remain = remain - cost[j];
                j = (j + 1) % n;
                //判断之前有没有考虑过这个点
                if (farIndex[j] != -1) {
                    //加当时的汽油
                    remain = remain + farIndexRemain[j];
                    //j进行跳跃
                    j = farIndex[j];
                } else {//之前没到过这个点，考虑正常情况
                    //加上当前点的补给
                    remain = remain + gas[j];
                }
                if (j == i) {
                    return i;
                }

                //记录当前点最远到达哪里
                farIndex[i] = j;
                //记录当前点的剩余
                farIndexRemain[i] = remain;
            }
        }
        return -1;
    }
}