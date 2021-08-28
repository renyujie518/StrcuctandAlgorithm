package SwordOffer;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName strToInt_67.java
 * @Description  把字符串转换成整数
 * Iuput:
 * +2147483647
 * 1a33
 *
 * Output:
 * 2147483647
 * 0
 * 边界判断：
 * 第一部分是空格，在转换的时候需要过滤掉
 *第二部分：部分是正负号，如果是正号则忽略，是负号则需要记录这个正负号状态
 * 第三部分，这部分字符串中会包含任意字符，但我们只需要"0"到"9"这几个字符
 *
 * 注意点：
 * 只能存储32位大小的有符号整数，所以不能用long做存储，而且需要提前判断整数的大小。
 *
 * 流程：
 * 过滤掉前面若干个空格(如果有的话)
 * 判断正号、负号位，如果是负号则记录下状态，表示输入的是负数。
 * 循环判断后面的字符串是否是0到9，如果是则累加这个值
 * 当前的值跟最大、最小32位整数比较看是否溢出
 * 如果是正数，且大于214748364，直接返回最大值
 * 如果是正数，且等于214748364，再判断最后一位是否大于7
 * 如果是负数，且小于-214748364，直接返回最小值
 * 如果是负数，且等于-214748364，再判断最后一位是否大于8
 * 循环结束后，根据负号的标志位返回对应的正数或负数
 *
 * 链接：https://leetcode-cn.com/problems/ba-zi-fu-chuan-zhuan-huan-cheng-zheng-shu-lcof/solution/tu-jie-mian-shi-ti-67-ba-zi-fu-chuan-zhuan-huan-ch/
 * @createTime 2021年08月28日 18:18:00
 */
public class strToInt_67 {
    public int strToInt(String str) {
        if(str==null) {
            return 0;
        }
        int n = str.length();
        int i = 0;
        int res = 0;
        boolean is_negative = false;
        //第一步，跳过前面若干个空格
        while(i<n && str.charAt(i)==' ') {
            ++i;
        }
        //如果字符串全是空格直接返回
        if(i==n) {
            return 0;
        }
        //第二步，判断正负号
        if(str.charAt(i)=='-') {
            is_negative = true;
        }
        //如果是正负号，还需要将指针i，跳过一位
        if(str.charAt(i)=='-' || str.charAt(i)=='+') {
            ++i;
        }
        //第三步，循环判断字符是否在 0~9之间
        while(i<n && str.charAt(i)>='0' && str.charAt(i)<='9') {
            //'0'的ASCII码是48，'1'的是49，这么一减就从就可以得到真正的整数值
            int tmp = str.charAt(i)-48;
            //判断是否大于 最大32位整数
            if(!is_negative &&(res>214748364 ||(res==214748364 && tmp>=7))) {
                return 2147483647;
            }
            //判断是否小于 最小32位整数
            if(is_negative &&(-res<-214748364 || (-res==-214748364 && tmp>=8))) {
                return -2147483648;
            }
            res = res*10 + tmp;
            ++i;
        }
        //如果有负号标记则返回负数
        if(is_negative) {
            return -res;
        }
        return res;
    }

}
