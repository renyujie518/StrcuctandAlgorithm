package facing;

import java.util.HashSet;
import java.util.Set;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName maxYihuo.java
 * @Description
 * 给你一个整数数组 nums ，返回 nums[i] XOR nums[j] 的最大运算结果，其中 0 ≤ i ≤ j < n 。
 * 进阶：你可以在 O(n) 的时间解决这个问题吗？
 *
 * 异或：相同为0，不同为1，  性质：A ^ B = B ^ A   A ^ B ^ B = A     a ^ b = c 成立，那么a ^ c = b 与 b ^ c = a 均成立
 *
 * 二进制下，我们希望一个数尽可能大，即希望越高位上越能够出现“1”，这样这个数就是所求的最大数，这是贪心算法的思想。
 * 可以从最高位开始，到最低位，首先假设高位是 “1”，把这 n 个数全部遍历一遍，看看这一位是不是真的可以是“1”，否则这一位就得是“0”，
 *
 * 如果 a ^ b = max 成立 ，max 表示当前得到的“最大值”，那么一定有 max ^ b = a 成立。
 * 我们可以先假设当前数位上的值为 “1”，再把当前得到的数与这个 n 个数的 前缀（因为是从高位到低位看，所以称为“前缀”）进行异或运算，
 * 放在一个哈希表中，再依次把所有 前缀 与这个假设的“最大值”进行异或以后得到的结果放到哈希表里查询一下，如果查得到，就说明这个数位上可以是“1”，
 * 否则就只能是 0（看起来很晕，可以看代码理解）。
 * 如何得到前缀，可以用掩码（mask），掩码可以进行如下构造，将掩码与原数依次进行 “与” 运算，就能得到前缀。

 * @createTime 2021年08月13日 13:28:00
 */
public class maxYihuo {
    public static int findMaximumXOR(int[] nums) {
        int res = 0;
        int mask = 0;
        //题目中说 0 <= nums[i] <= 2^31 - 1
        //mask从最高位开始，从左往右
        for (int i = 30; i >= 0; i--) {
            mask = mask | (1 << i);  // |  有1就是1
            System.out.println(Integer.toBinaryString(mask));
            //再依次把所有 前缀 与这个假设的“最大值”进行异或以后得到的结果放到哈希表里查询一下，如果查得到，就说明这个数位上可以是“1”
            Set<Integer> set = new HashSet<>();
            for (int num : nums) {
                set.add(num & mask);  //按位与 两个都是为1才是1  此时用& 保留前缀的意思（从高位到低位）表里是前缀
            }
            // 这里先假定第 n 位为 1 ，前 n-1 位 res 为之前迭代求得  理想最大值
            int targetMax = res | (1 << i);
            for (Integer prefix : set) {
                int temp = prefix ^ targetMax;//这里的核心思路就是利用异或性质  最大值^a =b  那么 b^a一定可以产生最大值
                if (set.contains(temp)) {
                    res = targetMax;
                    break;
                }
            }
        }
        return res;
    }

}
