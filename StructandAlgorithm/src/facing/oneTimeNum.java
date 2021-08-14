package facing;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName oneTimeNum.java
 * @Description
 * 题目：给定一个整数数组 nums，其中恰好有两个元素只出现一次，其余所有元素均出现两次。 找出只出现一次的那两个元素。
 *
 * 异或：相同为0，不同为1，  性质：A ^ B = B ^ A   A ^ B ^ B = A  A^A = 0
 * 按位与：两个都是为1才是1
 * 思路：
 *
 * 先对所有数字进行一次异或，得到两个出现一次的数字的异或值。
 * 在异或结果中找到任意为 1的位。
 * （这一步的分析见：https://leetcode-cn.com/problems/single-number-iii/solution/zhi-chu-xian-yi-ci-de-shu-zi-iii-by-leet-4i8e/）
 * 根据这一位对所有的数字进行分组。（按照第 i 位给原来的序列分组，如果该位为 0就分到第一组，否则就分到第二组）
 * 在每个组内进行异或操作，得到两个数字。（即排除偶次重复问题）
 *
 * 什么是排除偶次重复：在一个整数数组中，仅存在一个不重复的数字，其余数字均出现两次（或偶数次），找出不重复数字。
 * // 异或方法：将所有整数异或，出现偶数次的整数会被抵消，最终留下不重复整数。
 * int result = 0;
 * for (int index = 0; index < numArray.length; index++) {
 *     result = result ^ numArray[index];
 * }
 * return result;


 * @createTime 2021年08月13日 14:06:00
 */
public class oneTimeNum {
    //给定一个整数数组 nums，其中恰好有两个元素只出现一次，其余所有元素均出现两次。 找出只出现一次的那两个元素
    public static int[] oneTimeNum(int[] nums) {
        // 通过异或操作，最终结果等于两个单次出现的元素的异或值。
        int filterResult = 0;
        for (int num : nums) {
            filterResult ^= num;
        }
        // 计异或结果中找到任意为 1的位（1化为二进制000...001）。
        int div = 1;
        while ((filterResult & div) == 0) {
            //&按位与：两个都是为1才是1  其余都是0，所以想要找到从右往左第一次出现1的位，(filterResult & div) == 0的时候即不都为1的时候一直向左走
            div = div << 1;
        }
        int one = 0;
        int two = 0;
        for (int num : nums) {
            if ((div & num) != 0) {
                one = one ^ num;
            } else {
                two = two ^ num;
            }
        }
        return new int[]{one, two};
    }

    public static void main(String[] args) {
        int[] test = {1, 2, 1, 3, 2, 5};
        for (int result : oneTimeNum(test)) {
            System.out.println(result);
        }
    }
}
