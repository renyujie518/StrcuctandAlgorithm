package SwordOffer;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName leftReverseString_58.java
 * @Description  左旋转字符串
 * 字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部。请定义一个函数实现字符串左旋转操作的功能。
 * 比如，输入字符串"abcdefg"和数字2，该函数将返回左旋转两位得到的结果"cdefgab"。
 * Input:
 * S="abcXYZdef"
 * K=3
 *
 * Output:
 * "XYZdefabc"
 *
 * 思路：先将 "abc" 和 "XYZdef" 分别翻转，得到 "cbafedZYX"，然后再把整个字符串翻转得到 "XYZdefabc"。
 * 字符串为不可变对象
 * @createTime 2021年08月27日 23:01:00
 */
public class leftReverseString_58 {

    private void reverse(char[] chars, int i, int j) {
        while (i < j)
            swap(chars, i++, j--);
    }

    private void swap(char[] chars, int i, int j) {
        char t = chars[i];
        chars[i] = chars[j];
        chars[j] = t;
    }

    public String leftReverseString(String str, int k) {
        if (k >= str.length()) {
            return str;
        }
        char[] chars = str.toCharArray();
        reverse(chars, 0, k - 1);
        reverse(chars, k, chars.length - 1);
        reverse(chars, 0, chars.length - 1);
        return new String(chars);
    }
}
