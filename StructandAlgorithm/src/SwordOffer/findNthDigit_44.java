package SwordOffer;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName findNthDigit_44.java
 * @Description 数字序列中某一位的数字
 * 数字以0123456789101112131415…的格式序列化到一个字符序列中。在这个序列中，第5位（从下标0开始计数）是5，第13位是1，第19位是4，等等。
 *
 * 请写一个函数，求任意第n位对应的数字
 *
 * 解题思路
 * 1. 首先确定n来自几位数：
 * 1） 从start = 10的n次方 到 end = 10的n次方-1，即某n+1位数的数字范围，其位数长度就是(end-start + 1)*(n+1)，记作intervalLength。start为当前位数区间的最小数字。
 * 2） intervalLength结果恰好等于 (n+1)start9,这里n+1代表位数，记作digit。
 * 3） 需要注意的是 区间长度可能越界，所以要用long型记录 start 元素和 区间长度intervalLength
 *
 * 2. 确定 n 代表的是哪个数：
 * 1）首先上一步中 只有 n > intervalLength 才能进行 n - intervalLength，这就代表最后 n 的结果至少为 1 ，因为 n 为 0 的前提是 n == intervalLength 确定哪一个数就是从 start 开始统计当前的 n 属于哪一个数，记作 realnumber。
 * 2）由于是从 start 开始，start 本身占了一位，也就是start 就是该区间的 第 0个数字，这时需要对 n 进行 - 1 操作，这就保证了如果 n 是 realnumber 的最后一位数时 (n - 1) / digit 得到结果是到 realnumber 中间有几个数，最终 start + 间隔数 就访问到了现在的数字
 *
 * 3. 确定 n 代表的数字的哪一位：
 * 1）确定 n 在 realnumber 中的索引，（n-1） % digit，这里的 n - 1 是因为 n是从1开始计数的，而索引应该从0开始
 *

 * @createTime 2021年08月24日 17:11:00
 */
public class findNthDigit_44 {
    public static int findNthDigit(int index) {
        if (index < 0) {
            return -1;
        }
        int place = 1;// 1 表示个位，2 表示 十位...
        while (true) {
            int amount = getAmountOfPlace(place);
            int totalAmount = amount * place;
            if (index < totalAmount){
                return getDigitAtIndex(index, place);
            }
            index -= totalAmount;
            place++;
        }

    }

    /**
     * place 位数的数字组成的字符串长度
     * 10, 90, 900, ...
     */
    public static int getAmountOfPlace(int place) {
        if (place == 1) {
            return 10;
        }
        return (int) Math.pow(10, place - 1) * 9;
    }

    /**
     * place 位数的起始数字
     * 0, 10, 100, ...
     */
    private int getBeginNumberOfPlace(int place) {
        if (place == 1)
            return 0;
        return (int) Math.pow(10, place - 1);
    }

    /**
     * 在 place 位数组成的字符串中，第 index 个数
     */
    public static int getDigitAtIndex(int index, int place) {
        int beginNumber = getAmountOfPlace(place);
        int shiftNumber = index / place;
        String number = (beginNumber + shiftNumber) + "";
        int count = index % place;
        return number.charAt(count) - '0';
    }

}
