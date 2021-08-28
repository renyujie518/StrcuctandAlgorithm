package SwordOffer;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName onceInArray_56.java
 * @Description 数组中只出现一次的数字
 * 一个整型数组里除了两个数字之外，其他的数字都出现了两次，找出这两个数。
 * 题目要求时间复杂度 O(N)，空间复杂度 O(1)
   考虑异或操作的性质：对于两个操作数的每一位，相同结果为 0，不同结果为 1，0异或任何数还是自己

 * 那么在计算过程中，成对出现的数字的所有位会两两抵消为 0，最终得到的结果就是那个出现了一次的数字的异或值。
 * 设两个只出现一次的数字为 x , y，由于 x!=y ，则 x 和 y 二进制至少有一位不同（即分别为 0 和 1 ），根据此位可以将 nums
 * 拆分为分别包含 x 和 y 的两个子数组。

在异或结果中找到任意为 1的位。
根据这一位对所有的数字进行分组。
在每个组内进行异或操作，得到两个数字
 */
public class onceInArray_56 {
    public int[] singleNumbers(int[] nums) {
        int x = 0, y = 0;
        int yihuoResult = 0;
        int shouwei1 = 1; //这里看做000...1
        for(int num : nums)                                // 1. 遍历异或
            yihuoResult ^= num;
        while((yihuoResult & shouwei1) == 0)               // 2. 利用按位与的性质，找到从右往左yihuoResult首位出现1的位置
            shouwei1 = shouwei1 << 1;
        for(int num: nums) {                                // 3. 遍历 nums 分组
            if((num & shouwei1) != 0) { //同样是利用按位与的性质，依照&后到底是0还是1分组
                x ^= num;              //分别遍历两个子数组执行异或(相同抵消)
            } else{
                y ^= num;
            }
        }
        return new int[] {x, y};
    }
}
