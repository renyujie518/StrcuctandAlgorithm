package recursion;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName BAGMAX.java
 * @Description 给定两个长度都为N的数组weights和values，
 * weights[i]和values[i]分别代表i号物品的重量和价值。
 * 给定一个正数bag，表示一个载重bag的袋子，你装的物品不能超过这个重量。返回你能装下最多的价值是多少？
 * @createTime 2021年03月19日 00:24:00
 */
public class BagMax {
    //i以后货物 之前做好决定已经有的重量alreadyWeight  不要超过bag
    public static int process1(int[] weights,int[] values,int i,int alreadyWeight,int alreadyValue,int bag){
        if (alreadyWeight >bag){
            return 0;  //如果超重了，这个方案就不该有，返回0
        }
        //注意，这里要注意一点，i代表是i以后的货物做出选择对最大价值的影响，i之前的默认假设都是正确的
        if (i== weights.length){
            return alreadyValue;  //i都来到最后一个货物了，i以后的货物没法考虑
        }
        //到了第i货物，我可以选择不要i货物，i+1位置的货物再去决定，那alreadyWeight不跟新,价值也不增加
        //到了第i货物，我可以选择要i货物，alreadyWeight,alreadyValue首先跟新，后续还能有多少价值，i+1去决定
        return Math.max(
               process1(weights,values,i+1,alreadyWeight,alreadyValue,bag),
                process1(weights, values, i + 1, alreadyWeight + weights[i],alreadyValue+values[i], bag)
        );
    }
    public static int maxValue1(int[] weights, int[] values, int bag) {
        return process1(weights, values, 0, 0, 0,bag);
    }
    public static int maxValue2(int[] c, int[] p, int bag) {
        int[][] dp = new int[c.length + 1][bag + 1];
        for (int i = c.length - 1; i >= 0; i--) {
            for (int j = bag; j >= 0; j--) {
                dp[i][j] = dp[i + 1][j];
                if (j + c[i] <= bag) {
                    dp[i][j] = Math.max(dp[i][j], p[i] + dp[i + 1][j + c[i]]);
                }
            }
        }
        return dp[0][0];
    }
    public static void main(String[] args) {
        int[] weights = { 3, 2, 4, 7 };
        int[] values = { 5, 6, 3, 19 };
        int bag = 11;
        System.out.println(maxValue1(weights, values, bag));
        System.out.println(maxValue2(weights, values, bag));
    }
}
