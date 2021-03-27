package DP;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName MinCoin.java
 * @Description 换钱的最少货币数（这道题还是适合暴力递归，Dp考虑的情况有点复杂）
 * 【题目】给定数组arr，arr中所有的值都为正数且不重复。
 * 每个值代表一种面值的货币，每种面值的货币可以使用任意张，再给定一个整数aim，代表要找的钱数，求组成aim的最少货币数。
 * 【举例】arr=[5,2,3]，aim=20。4张5元可以组成20元，其他的找钱方案都要使用更多张的货币，所以返回4。
 *        arr=[5,2,3]，aim=0。不用任何货币就可以组成0元，返回0。
 *        arr=[3,5]，aim=2。根本无法组成2元，钱不能找开的情况下默认返回-1。
 *
 * 注意  这里可以分为两个版本 钱袋子里的面额是否可以重复
 * （1） 如上题所述  每种面值的货币可以使用任意张
 *  (2) 袋子里的钱拿走了就没了 每种面额的货币只有1张
 * @createTime 2021年03月19日 15:06:00
 */
public class MinCoin {
    //这里 讨论 袋子里的钱拿走了就没了 每种面额的货币只有1张 的情况
    //arr[index,...]里挑硬币，rest是剩余需要的额度。返回最小硬币数
    public static int process1(int[] arr,int index,int rest){
        if (rest<0){//下面的函数有个-的过程，如果rest<0,说明之前某个该选没选导致再选后面的会-的过大
            return -1;
        }
        if (rest ==0){ //不用任何货币就可以组成0元，返回0
            return 0;
        }
        if (index == arr.length&& rest>0){//到数组底了但rest还没凑出来，返回-1
            return -1;
        }
        //返回-1的情况会影响min函数，所以分来讨论
        //return Math.min(process(arr, index + 1, rest), (1 + process(arr, index + 1, rest - arr[index])));
        //rest>0且袋子里还有硬币
        int p1 = process1(arr, index + 1, rest); //选择不要当前硬币，后续的rest交给index + 1去做决定
        int p2Next = process1(arr, index + 1, rest - arr[index]);
        //选择要当前硬币，rest额度先把这次的硬币减去（相当于用了），后续的rest交给index + 1去做决定
        if (p1==-1 && p2Next==-1){
            return -1;  //之前做的决定都不对，返回-1
        }else {
            if (p1 == -1){//选择不要当前硬币导致错误，那只能选择"要"，一旦要，代表返回的硬币数+1
                return p2Next + 1;
            }
            if (p2Next == -1){//选择要当前硬币导致错误，那只能选择"不要"，一旦不要，仅仅返回递归的结果给上层即可
                return p1;

            }
            //剩下的就是有多重选法，那我就选择银币数最小的一种
            return Math.min(p1, p2Next+1);
        }


    }
    public static int way1Recursion(int[] arr,int aim){
        return process1(arr, 0, aim);
    }

    public static int way1DP(int[] arr,int aim){
        int N = arr.length;
        int[][]dp = new int[N+1][aim+1];
        //首先处理边界  rest =-1全是-1.但建表的时候是[N+1][aim+1]，所以不用管，可以想象表的左边是一片-1的海洋
        //rest ==0全是0，所以第一列是0
        for (int row = 0; row <= N; row++) {
            dp[row][0] = 0;
        }
        //index == arr.length的时候全是-1，所以最后一列是-1
        for (int column = 1; column <= aim; column++) {
            dp[N][column] = -1;
        }

        //dp顺序从左往右，从下往上的，因为dp中间的值取决于index + 1和rest - arr[index]，即下面和左下角
        for (int index = N-1; index>=0; index--) {  //最后一行已经限制过
            for (int rest = 1;rest<=aim;rest++){  //第一列已经限制过

                int p1 = dp[index + 1][rest];
                int p2Next = -1;  // p2Next有可能越界，别忘了表的左边是一片-1的海洋
                if (rest-arr[index] >=0){  //如果没有越界
                    p2Next = dp[index + 1][rest - arr[index]];
                }
                if (p1==-1 && p2Next==-1){
                    dp[index][rest] = -1;  //之前做的决定都不对，填入-1
                }else{
                    if (p1 == -1){
                        //选择不要当前硬币导致错误，那只能选择"要"，一旦要，代表硬币数+1
                        dp[index][rest] = p2Next + 1;
                    }else if (p2Next == -1){
                        //选择要当前硬币导致错误，那只能选择"不要"，一旦不要，dp[index][rest]填入p1
                        dp[index][rest] = p1;
                    }else {
                        //剩下的就是有多重选法，那我就选择银币数最小的一种
                        dp[index][rest] = Math.min(p1, p2Next + 1);
                    }
                }
            }
        }
        return dp[0][aim];
    }
    //第一种情况的优美DP写法
    public static int way1DP2(int[] arr,int aim){
        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];
        //其实也可以这么考虑，我把除了dp[N][0]为0之外，其他都是-1
        for (int column = 1; column <=aim; column++) {
            dp[N][column] = -1;
        }
        //dp顺序从左往右，从下往上的，因为dp中间的值取决于index + 1和rest - arr[index]，即下面和左下角

        for (int index = N-1; index>=0; index--) {  //最后一行已经限制过
            for (int rest = 1;rest<=aim;rest++){  //第一列已经限制过
                dp[index][rest] = -1; // 初始时先设置dp[index][rest]的值无效
                if (dp[index + 1][rest] != -1) { // 下面的值如果有效
                    dp[index][rest] = dp[index + 1][rest]; // dp[index][rest]的值先设置成下面的值
                }
                // 左边的位置不越界并且有效
                if (rest - arr[index] >= 0 && dp[index][rest - arr[index]] != -1) {
                    if (dp[index][rest] == -1) { // 如果之前下面的值无效
                        dp[index][rest] = dp[index][rest - arr[index]] + 1;
                    } else { // 说明下面和左边的值都有效，取最小的
                        dp[index][rest] = Math.min(dp[index][rest],
                                dp[index][rest - arr[index]] + 1);
                    }
                }
            }
        }
        return dp[0][aim];
    }




    //这里 讨论 每种面值的货币可以使用任意张 的情况
    //arr里都是正数，没有重复值，每一个值代表一种货币，每一种都可以用无限张
    //可以自由使用arr[index...]之后的所有面值
    //需要搞定的钱数是rest
    //返回找零的方法数

    public static int process2(int[] arr,int index,int rest){
        if (index == arr.length){ //如果没有面值可以选了
            //如果需要满足的钱目标是0 ，也算一种方法，但如果还没满足钱的余额，返回0
            return rest == 0 ? 1 : 0;
        }
        //对于arr[index]这种面值，尝试使用1张，2张..，但不能超过余额数
        int ways = 0;
        for (int zhang = 0;arr[index]*zhang <=rest;zhang++){
            ways += process2(arr, index + 1, rest - arr[index] * zhang);
        }
        return ways;

    }
    private static int way2Recursion(int[] test, int aim) {
        return process2(test, 0, aim);
    }
    private static int way2DP(int[] arr, int aim) {
        if (arr == null || arr.length ==0){return 0;}
        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] =1;  //basecase if (index == arr.length){ //如果没有面值可以选了 return rest == 0 ? 1 : 0; }
        //每一行依赖自己的下一行，所以从上往下  至于同一行没有直接联系，所以无所谓，这里选择从左往右
        for (int index = N-1;index>=0;index--){
            for (int rest = 0; rest <= aim; rest++) {
                int ways = 0;
                for (int zhang = 0;arr[index]*zhang <=rest;zhang++){
                    ways+= dp[index+1][rest-arr[index]*zhang];
                }
                dp[index][rest] = ways;
            }
        }
        return dp[0][aim];
    }
    //第二种情况的优美DP写法  观察发现  每一个dp[index][rest]在算的时候出现枚举，导致很多算过的结果没有用上
    //通过在纸上画图发现 在不越界的情况下，赖自己的下一行，然后每间隔"rest - arr[index]"距离取数
    //那么，其实靠自己左边的数就可以了，间隔距离也是"rest - arr[index]"，这样就避免了很多重复计算，直接拿来用
    //况且rest也是从左到右，所以左边的数先算，完全可以用得到
    private static int way2DP2(int[] arr, int aim) {
        if (arr == null || arr.length ==0){return 0;}
        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] =1;  //basecase if (index == arr.length){ //如果没有面值可以选了 return rest == 0 ? 1 : 0; }
        //每一行依赖自己的下一行，所以从上往下  至于同一行没有直接联系，所以无所谓，这里选择从左往右
        for (int index = N-1;index>=0;index--){
            for (int rest = 0; rest <= aim; rest++) {
                dp[index][rest] = dp[index + 1][rest];  //先把下一行的取到
                if (rest - arr[index]>=0){  //不越界
                    dp[index][rest] += dp[index][rest - arr[index]];//累加
                }
            }
        }
        return dp[0][aim];
    }




    public static void main(String[] args) {
        int[] test = {5,2,3};
        System.out.println("袋子里的钱拿走了就没了 每种面额的货币只有1张");
        System.out.println(way1Recursion(test,10));
        System.out.println(way1Recursion(test,5));
        System.out.println(way1Recursion(test,0));
        System.out.println(way1Recursion(test,1));
        System.out.println("++++++++");
        System.out.println(way1DP(test,10));
        System.out.println(way1DP(test,5));
        System.out.println(way1DP(test,0));
        System.out.println(way1DP(test,1));
        System.out.println("++++++++");
        System.out.println(way1DP2(test,10));
        System.out.println(way1DP2(test,5));
        System.out.println(way1DP2(test,0));
        System.out.println(way1DP2(test,1));
        System.out.println("每种面值的货币可以使用任意张");
        System.out.println(way2Recursion(test,10));
        System.out.println(way2Recursion(test,5));
        System.out.println(way2Recursion(test,0));
        System.out.println(way2Recursion(test,1));
        System.out.println("++++++++");
        System.out.println(way2DP(test,10));
        System.out.println(way2DP(test,5));
        System.out.println(way2DP(test,0));
        System.out.println(way2DP(test,1));
        System.out.println("++++++++");
        System.out.println(way2DP2(test,10));
        System.out.println(way2DP2(test,5));
        System.out.println(way2DP2(test,0));
        System.out.println(way2DP2(test,1));
    }



}
