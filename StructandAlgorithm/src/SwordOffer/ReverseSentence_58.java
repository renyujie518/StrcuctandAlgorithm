package SwordOffer;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName ReverseSentence_58.java
 * @Description 翻转单词顺序列
 * Input:
 * "I am a student."
 *
 * Output:
 * "student. a am I"
 *
 * 这里的优化解是不使用额外空间
 * 创建一个字符数组使得空间复杂度为 O(N)
 * 先旋转每个单词，再旋转整个字符串。
 * @createTime 2021年08月27日 22:30:00
 */
public class ReverseSentence_58 {
    private void swap(char[] c, int i, int j) {
        char t = c[i];
        c[i] = c[j];
        c[j] = t;
    }

    //反转一个单词
    private void reverse(char[] c, int i, int j) {
        while (i < j)
            swap(c, i++, j--);
    }

    public String ReverseSentence(String str) {
        int n = str.length();
        char[] chars = str.toCharArray();
        //开始的时候都在头  这点注意
        int i = 0, j = 0;
        while (j <= n) {
            if (j == n || chars[j] == ' ') {//遇到空格和末尾，把这个单词反转下
                reverse(chars, i, j - 1);
                //起点跳到另一个单词
                i = j + 1;
            }
            j++;
        }
        //再反转整个句子
        reverse(chars, 0, n - 1);
        //构造函数的兼容性
        return new String(chars);
    }
}
