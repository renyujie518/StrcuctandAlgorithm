package MiddleClass;

import java.util.Arrays;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName xiyiji.java
 * @Description 洗衣机问题
 * 有n个打包机器从左到右一字排开， 上方有一个自动装置会抓取一批放物品到每个打包机上， 放到每个机器上的这些物品数量有多有少，
 * 由于物品数量不相同， 需要工人 将每个机器上的物品进行移动从而到达物品数量相等才能打包。每个物品重量太大、
 * 每次只能搬一个物品进行移动， 为了省力， 只在相邻的机器上移动。
 * 请计算在搬动最小轮数的前提下， 使每个机器上的物品数量相等。      注意  相当于求至少
 * 如果不能使每个机器上的物品相同， 返回-1。
 *
 * 例如[1,0,5]表示有3个机器， 每个机器上分别有1、 0、 5个物品，
 * 经过这些轮后：
 * 第一轮： 1 0 <- 5 => 1 1 4
 * 第二轮： 1 <- 1 <- 4 => 2 1 3（右边先给中间，中间再给左边，两次移动都是在一轮里的，注意：每次只能搬一个物品进行移动）
 * 第三轮： 2 1 <- 3 => 2 2 2
 * 移动了3轮， 每个机器上的物品相等， 所以返回3
 * 例如[2,2,3]表示有3个机器， 每个机器上分别有2、 2、 3个物品， 这些物品不管怎么移动， 都不能使三个机器上物品数量相等， 返回-1
 *
 * 思路：
 *
 *结合荷兰国旗的思路考虑   比如有60个物品 3个机器，最终的结果一定是每个上20个  考虑供求关系
 * 情况1：两边都是负  说明都要靠中间的供 至少需要|左|+|右|
 * 情况2：两边都是正 说明都是中间的需要（但是中间可以从两侧一起接） 本题的陷进之一就是，每次移动一个，但是一轮可以移动多次
 *       所以此时至少需要max(左，右)。相当于多的等少的扔完还得扔
 * 情况3：一正一负：不管那边是正负，都要等缺的或者多的扔完 所以至少需要max(|左|，|右|)
 *
 * 综上 在每个位置考虑瓶颈 最痛的位置的瓶颈就是整体所需的最小的论数（所谓瓶颈，就是result一直在比）
 * @createTime 2021年07月22日 21:19:00
 */
public class xiyiji {
    public static int packingMachineMinOps(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int size = arr.length;
        int sum = 0;
        //先求累加和
        sum = Arrays.stream(arr).sum();
        //如果不能整除，肯定是不能均分
        if (sum % size != 0) {
            return -1;
        }
        int avg = sum / size;
        // 左侧已经遍历部分的累计和,也是左侧实际的物品数量
        int leftSum = 0;
        int result = 0;

        for (int i = 0; i < arr.length; i++) {
            //针对每个位置  负需要输入 正需要拿掉    左侧实际-左侧需要
            int leftRest = leftSum - i * avg;
            // 右侧实际（i+1位置及之后所有=所有-左实际-i位置上的）-右侧需要
            int rightRest = (sum - leftSum - arr[i]) - (size - i - 1) * avg;

            //情况1
            if (leftRest < 0 && rightRest < 0) {
                result = Math.max(result, Math.abs(leftRest) + Math.abs(rightRest));
            } else { //情况2，3的合并
                result = Math.max(result, Math.max(Math.abs(leftRest), Math.abs(rightRest)));
            }
            leftSum += arr[i];
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1,0,5};
        System.out.println(packingMachineMinOps(arr));

    }
}
