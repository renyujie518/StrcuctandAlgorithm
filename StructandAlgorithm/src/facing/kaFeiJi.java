package facing;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName caFeiJi.java
 * @Description 华为笔试题  咖啡机问题
 * 问题描述：
 * https://blog.csdn.net/weixin_43857365/article/details/89073715
 *
 * 这里推荐一篇博客  务必认真看完：
 * https://www.cnblogs.com/darope/p/13539521.html
 *
 * 数组arr代表每一个咖啡机冲一杯咖啡的时间，每个咖啡机只能串行的制造咖啡。
 * 现在有n个人需要喝咖啡，只能用咖啡机来制造咖啡。认为每个人喝咖啡的时间非常短，冲好的时间即是喝完的时间。
 * 每个人喝完之后咖啡杯可以选择洗或者自然挥发干净，只有一台洗咖啡杯的机器，只能串行的洗咖啡杯。
 * 洗杯子的机器洗完一个杯子时间为a，任何一个杯子自然挥发干净的时间为b。
 * 四个参数：arr, n, a, b
 * 假设时间点从0开始，返回所有人喝完咖啡并洗完咖啡杯的全部过程结束后，至少来到什么时间点（求最小时间）。
 *
 * 一个大体的方向是将冲咖啡和洗咖啡的过程分开

 * @createTime 2021年07月30日 16:23:00
 */
public class kaFeiJi {

    //首先将咖啡机的工作时间与对应的工作时间完成点(可用时间点)作为一个类放到小根堆，同时按照两者的加和作为小根堆排序
    //以其中一个小根堆中的元素为例子：（0，2）  0的时候可以工作，工作时间为2  下次就变成（2，2），在下次就变成（4，2）
    public static class Machine{
        public int timePoint;
        public int workTime;

        public Machine(int timePoint, int workTime) {
            this.timePoint = timePoint;
            this.workTime = workTime;
        }
    }

    public static class MachineComparator implements Comparator<Machine> {

        @Override
        public int compare(Machine o1, Machine o2) {
            return (o1.timePoint + o1.workTime) - (o2.timePoint + o2.workTime);
        }
    }

    //每个人暴力尝试用每一个咖啡机给自己做咖啡，优化成贪心
    public static int minTime1(int[] arr, int n, int a, int b) {
        PriorityQueue<Machine> heap = new PriorityQueue<Machine>(new MachineComparator());
        //初始的时候，堆里的个数是和咖啡机的个数相关的，初始值都是（0，2）（0,7）...
        for (int i = 0; i < arr.length; i++) {
            heap.add(new Machine(0, arr[i]));
        }
        // drinks 每一个员工喝完的时间 固定变量
        int[] drinks = new int[n];
        for (int i = 0; i < n; i++) {
            Machine curr = heap.poll();
            //像车轮一样不断向前走
            curr.timePoint = curr.timePoint + curr.timePoint;
            drinks[i] = curr.timePoint;
            heap.add(curr);//有弹出，加工一遍，再add
        }
        //上述是做咖啡的过程，两步走的第二步：洗咖啡
        return process(drinks, a, b, 0, 0);
    }

    //洗咖啡的过程
    // a 洗一杯的时间 固定变量
    // b 自己挥发干净的时间 固定变量
    // drinks 每一个员工喝完的时间(喝完立马就洗，所以也可看做) 固定变量
    // drinks[0..index-1]都已经干净了，不用你操心了
    // drinks[index...n-1]都想变干净，这是我操心的
    //washLine表示洗的机器何时可用（每迭代一次会不断增加）
    //本函数返回 drinks[index...n-1]变干净，最少的时间点
    public static int process(int[] drinks, int a, int b, int index, int washLine){
        //basecase是想象一条时间线  现在到了最后一个需要洗的咖啡，这时候只考虑这个咖啡杯
        if (index == drinks.length - 1) {
            //这里需要好好琢磨 首先本函数的目的是返回最小时间，所以这个min很好理解
            //如何洗咖啡杯，实际上有两种方案，专门的洗咖啡机器/自然挥发
            //专门的洗咖啡机器:洗的机器何时可用和咖啡被制作完（喝完）两者的最大值（最大值的原因是得等啊，不能还没喝完就洗，也不能洗的时候机器没空，洗机器只有一台）+洗一杯的时间
            //自然挥发：咖啡被制作完（喝完）+挥发时间
            return Math.min(Math.max(washLine, drinks[index]) + a, drinks[index] + b);
        }
        //考虑 drinks[index...n-1]的最早结束时间，这时候剩的咖啡不止一杯
        //首先采用方案1，用专门的洗咖啡机器，所以要更新washLine
        //newWashLine是我当前(index)的咖啡杯，洗完的时间 具体计算过程和上面解释的一致
        int newWashLine = Math.max(washLine, drinks[index]) + a;
        //那么index+1...n-1变干净的最早时间
        int next1 = process(drinks, a, b, index + 1, newWashLine);
        //这时候有个最容易错的点：注意本函数的目的让drinks[index...n-1]消费完的最短时间，需要做完所有的事情，包括index处杯子洗完的时间和index+1...n-1所有咖啡杯洗完的最早时间
        //考虑一种极端状况，当前index洗碗的时间中可是用到了drinks[index]，假设，做这杯咖啡的时间用了100万年
        //这时候因为，只有一台洗咖啡杯的机器，不能光考虑index+1...n-1所有咖啡杯洗完的时间，连index处的还要洗100万年呢，哪轮着到index+1
        //所以，这是一个坑，因为是分片讨论的，所以yaokaolv上一个到底洗完了吗
        int result1 = Math.max(newWashLine, next1);

        //当前杯子决定挥发
        int dry = drinks[index] + b;
        //注意啊，这时候不用洗咖啡的机器，洗的机器的可用时间就不要更新了
        int next2 = process(drinks, a, b, index + 1, washLine);
        int result2 = Math.max(dry, next2);

        //不管哪种方案，我要来到最小的时间点
        return Math.min(result1, result2);

    }

    //洗咖啡的过程转成dp(这里和上面的思路稍微不同，上面是时间线从前往后，本处的dp是从后往前)
    // a 洗一杯的时间 固定变量
    // b 自己挥发干净的时间 固定变量
    // drinks 每一个员工喝完的时间(喝完立马就洗，所以也可看做) 固定变量
    //上面的贪心中只有两个变量 int index, int washLine  所以一个二维表就够了
    public static int dp(int[] drinks, int a, int b) {
        //考虑最后一个脏杯子，无非就是哪种方案更好，挥发的方案更优
        if (a >= b) {
            return drinks[drinks.length - 1] + b;
        }
        //a<b（采用洗咖啡机的方案）
        int N = drinks.length;
        int limit = 0;  //洗咖啡机什么时候可用（开始的时间点在0处）
        //从右下角往左上角走，首先找到最后洗咖啡机洗完所有的脏杯子来到的点
        for (int i = 0; i < N; i++) {
            limit = Math.max(limit, drinks[i]) + a;
        }

        //从右下角往左上角走  行是脏杯子的出现的时间+1，列是洗的机器而且是洗完所有的脏杯子来到的点
        int[][] dp = new int[N][limit + 1];//分别对应index，washLine
        //basecase,考虑最后一个脏杯子
        for (int washLine = 0; washLine <= limit; washLine++) {
            //考虑N-1处的杯子,具体原因参照贪心法中的解释
            dp[N - 1][washLine] = Math.min(Math.max(washLine, drinks[N - 1]) + a, drinks[N - 1] + b);
        }
        //考虑N-2脏杯子到第一个脏杯子的所有情况
        for (int index = N - 2; index >= 0; index--) {
            for (int washLine = 0; washLine <= limit; washLine++) {//washLine表示洗的机器何时可用
                int result1 = Integer.MAX_VALUE;
                //wash是当前(index)的咖啡杯，洗完的时间
                int wash = Math.max(washLine, drinks[index]) + a;
                //和上面的坑点思想一样（但这里是从后往前，所以是<=）  如果洗杯子的时间<=洗机器可用的时间
                if (wash <= limit) {
                    result1 = Math.max(wash, dp[index + 1][wash]);
                }
                //洗的机器的可用时间就不要更新了
                int result2 = Math.max(drinks[index] + b, dp[index + 1][washLine]);
                dp[index][washLine] = Math.min(result1, result2);
            }
        }
        return dp[0][0];
    }

    // 方法二：最终版本，把洗咖啡杯的暴力尝试进一步优化成动态规划
    public static int minTime2(int[] arr, int n, int a, int b) {
        PriorityQueue<Machine> heap = new PriorityQueue<Machine>(new MachineComparator());
        for (int i = 0; i < arr.length; i++) {
            heap.add(new Machine(0, arr[i]));
        }
        int[] drinks = new int[n];
        for (int i = 0; i < n; i++) {
            Machine cur = heap.poll();
            cur.timePoint += cur.workTime;
            drinks[i] = cur.timePoint;
            heap.add(cur);
        }
        if (a >= b) {
            return drinks[n - 1] + b;
        }
        int[][] dp = new int[n][drinks[n - 1] + n * a];
        for (int i = 0; i < dp[0].length; i++) {
            dp[n - 1][i] = Math.min(Math.max(i, drinks[n - 1]) + a, drinks[n - 1] + b);
        }
        for (int row = n - 2; row >= 0; row--) { // row 咖啡杯的编号
            int washLine = drinks[row] + (row + 1) * a;
            for (int col = 0; col < washLine; col++) {
                int wash = Math.max(col, drinks[row]) + a;
                dp[row][col] = Math.min(Math.max(wash, dp[row + 1][wash]), Math.max(drinks[row] + b, dp[row + 1][col]));
            }
        }
        return dp[0][0];
    }




    // for test
    public static int[] randomArray(int len, int max) {
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * max) + 1;
        }
        return arr;
    }

    // for test
    public static void printArray(int[] arr) {
        System.out.print("arr : ");
        for (int j = 0; j < arr.length; j++) {
            System.out.print(arr[j] + ", ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] test = {1, 1, 5, 5, 7, 10, 12, 12, 12, 12, 12, 12, 15};
        int a1 = 3;
        int b1 = 10;
        System.out.println("测试洗咖啡的贪心和dp的方案是不是一样");
        System.out.println(process(test, a1, b1, 0, 0));
        System.out.println(dp(test, a1, b1));
        System.out.println("=========");

        int len = 5;
        int max = 9;
        int testTime = 50000;
        for (int i = 0; i < testTime; i++) {
            int[] arr = randomArray(len, max);
            int n = (int) (Math.random() * 5) + 1;
            int a = (int) (Math.random() * 5) + 1;
            int b = (int) (Math.random() * 10) + 1;
            int ans1 = minTime1(arr, n, a, b);
            int ans2 = minTime2(arr, n, a, b);
            if (ans1 != ans2) {
                printArray(arr);
                System.out.println("n : " + n);
                System.out.println("a : " + a);
                System.out.println("b : " + b);
                System.out.println(ans1 + " , " + ans2);
                System.out.println("===============");
                break;
            }
        }

    }
}
