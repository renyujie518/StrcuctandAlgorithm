package recursion;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName NQueen.java
 * @Description
 *
 * N皇后问题是指在N*N的棋盘上要摆N个皇后，要求任何两个皇后不同行、不同列，也不在同一条斜线上。
 * 给定一个整数n，返回n皇后的摆法有多少种。
 * n=1，返回1
 * n=2或3，2皇后和3皇后问题无论怎么摆都不行，返回0。
 * n=8，返回92。
 * @createTime 2021年03月16日 22:58:00
 */
public class NQueen {

    //record是用一个单数组表示 record[i]:第i行的皇后放在了第几列  比如 record = [1,3,...] 第1行的皇后放在第1列，第2行的皇后放在第3列....
    //record[0,..n-1]的皇后，满足任何两个皇后一定不共行，不共列，不共斜线
    //record[0,..n-1]代表之前的行放了的皇后位置，i是目前来到第i行(从0开始)，n代表一共有多少行  返回值是摆完所有的皇后，合理的摆法有多少种
    public static int process1(int i,int[] record,int n){
        if (i == n){//来到终止行，但此时n-1行都满足不共行，不共列，不共斜线，就是找到了1种，摆法就是record【】储存的方案
            return 1;
        }
        int res = 0;
        for (int j = 0; j < n; j++) {//n*n的棋盘，当前在i行，我要是试每个列的情况，所以j代表列(0,n-1列)
            if (isValid(record,i,j)){//检查 ，之前放的所有皇后（record记录）都不要和当前i行j列的情况冲突
                record[i] = j;   //都不冲突，把第i行的皇后放在第j列
                res = res + process1(i + 1, record, n);  //放完后再去i+1行放
                // 那为什么res累加 因为目前在第i行，第i行也有多种放法，尝试第i行上所有列都合法的情况在累加，就是N皇后其中的一种解法（深度优先）
            }
        }
        return res;
    }
    //检查当前i行j列的皇后合法不合法，合法（与之前i-1行不共行，不共列，不共斜线）返回true
    //共行是不用检查的，因为这些都是建立在每次在每一行依次放,只检查不共列，不共斜线
    //record【0.i-1】需要检查，record【i，..】不需要看
    private static boolean isValid(int[] record, int i, int j) {
        for (int k = 0; k < i; k++) {//k代表之前的某一行，遍历【0，i-1】行所有情况，要都满足才行
            if (record[k] == j || Math.abs(record[k]-j) == Math.abs(i-k)){ //别忘了 record[k]:第k行的皇后放在了第几列
                //比如在网格上，（1，1),(3，3）（1，1),(0，2）|列坐标值相减| = |行坐标值相减| 一定在一条斜线上
                return false;
            }
        }
        return true;
    }
    //时间复杂度 O(n^n)
    public static int method1(int n) {  //n代表是几皇后问题
        if (n < 1) {
            return 0;
        }
        int[] record = new int[n];
        return process1(0, record, n); //从0行开始，记录record,一共n行
    }

    //***************用位运算加速*************************
    public static int process2(int upperLim,
                               int colLim,//占位符  列限制
                               int leftDiaLim,//占位符  左斜线限制
                               int rightDiaLim) {//占位符  右斜线限制
        if (colLim == upperLim) {//所有皇后都填上了，后n位都是1（upperLim后n位也是1），那就是发现了一种有效的
            return 1;
        }
        int mostRightOne = 0;
        int pos = upperLim & (~(colLim | leftDiaLim | rightDiaLim));
        //三限制求或知道下一行哪些位置上不能放（1代表不可以放，0可以放）
        //再取反，1代表可以放，0不可以放
        //再与不变量求与，就是所有可以填皇后的列，1可填 ->pos
        int res = 0;
        while (pos != 0) {//y =x & (~x + 1)  //把二进制数x中最右侧的数提出来当做y
            mostRightOne = pos & (~pos + 1);  //从右向左依次按位提取出二进制pos上的1，依次提取（能放皇后的列我依次试），当做"当前位置"
            pos = pos - mostRightOne;//前取后,减去提取值，原位置变为0,while循环，每一个能放的我都试
            //怎么试  递归，upperLim（标准）不变
            res += process2(upperLim,
                    colLim | mostRightOne,//在"当前位置"（1代表可放）放了皇后，所以接下来"列限制"是他两或 相当于把这一列占住
                    (leftDiaLim | mostRightOne) << 1,//在"当前位置"（1代表可放）放了皇后,所以接下来"左斜线限制"是他两或左移
                    (rightDiaLim | mostRightOne) >>> 1);//在"当前位置"（1代表可放）放了皇后,所以接下来"右斜线限制"是他两或右移
        }//res累加每种情况返回
        return res;
    }

    public static int method2(int n) {
        if (n < 1 || n > 32) {  //由于int内存问题，控制在32位内
            return 0;
        }
        int upperLim = n == 32 ? -1 : (1 << n) - 1;//二进制数 后n位是1，前面都是0（共32位），值没有含义，只是使用位信息，upperLim永远不变
        return process2(upperLim, 0, 0, 0);
    }




    //test
    public static void main(String[] args) {
        int n = 14;

        long start = System.currentTimeMillis();
        System.out.println(method2(n));
        long end = System.currentTimeMillis();
        System.out.println("cost time: " + (end - start) + "ms");

        start = System.currentTimeMillis();
        System.out.println(method1(n));
        end = System.currentTimeMillis();
        System.out.println("cost time: " + (end - start) + "ms");
        /*
        365596
        cost time: 241ms
        365596
        cost time: 5145ms
        可以看到时间上差距很大
         */

    }
}
