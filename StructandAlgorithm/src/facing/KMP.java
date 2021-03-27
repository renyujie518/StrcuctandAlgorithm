package facing;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName KMP.java
 * @Description
 * KMP算法解决的问题字符串str1和str2，str1是否包含str2，如果包含  返回str2在str1中开始的位置。 如果不包含返回-1
 * 如何做到时间复杂度O(N)完成
 *      String str = "abcabcababaccc";
 * 		String match = "ababa";
 *
 * 	这里要说经典过程：
 * 	 比如str从0位置'a'开始 match从0位置'a'开始，一个个往后推。两个都推
 * 	 到str的'c'了，发现对不上了，这时候tr从1位置'b'开始,match再从0位置'a'开始，一个个往后推。
 * 	 这样其实会导致很慢，很冗余
 * @createTime 2021年03月21日 15:58:00
 */
public class KMP {
    public static int getIndexOf(String s, String m) {
        if (s == null || m == null || m.length() < 1 || s.length() < m.length()) {
            return -1;
        }
        char[] str1 = s.toCharArray();
        char[] str2 = m.toCharArray();
        int i1 = 0;  //在str1中比对的位置
        int i2 = 0;//在str2中比对的位置
        int[] next = getNextArray(str2);  //对str2求next数组
        while (i1 < str1.length && i2 < str2.length) {
            if (str1[i1] == str2[i2]) { //比对的位置两者的值一样，那不就正中下怀，str1包含str2，位置++
                i1++;
                i2++;
            } else if (next[i2] == -1) {//等价于i2 == 0 因为next数组的str2 0位置设为-1 这里是str1[i1] != str2[i2]
                //next[i2] == -1,代表str2已经到0位置都str1[i1] != str2[i2]，str2没办法再往前跳，那就str1换一个位置和str2[0]匹配，i1++
                i1++;
            } else {  //i1固定不动，str2还可以往前跳（来到最长前后缀的下一个位置 ）
                i2 = next[i2];
            }
        }
        //while循环结束了，i2 == str2.length，那返回str2在str1中开始的位置就是i1 - i2
        return i2 == str2.length ? i1 - i2 : -1;
    }


    //next数组含义：最长前缀和最长后缀的匹配长度
    public static int[] getNextArray(char[] ms) {
        if (ms.length == 1) {
            return new int[] { -1 };
        }
        int[] next = new int[ms.length];
        next[0] = -1;  //规定 0位置 -1
        next[1] = 0;//规定 1位置 0
        int i = 2;
        int cn = 0; //拿哪个位置的字符和i-1比，也是当前使用的信息
        while (i < next.length) {
            if (ms[i - 1] == ms[cn]) {//如果i-1之前的信息（i-2个）i-1之前的字符串最大前缀和最大后缀的匹配长度 == 当前位置
                next[i++] = ++cn;   //next【i】 = cn+1 cn++
            } else if (cn > 0) { //如果不一样就继续往前跳
                cn = next[cn];
            } else {//跳到最前面都没一样 =0
                next[i++] = 0;
            }
        }
        return next;
    }

    public static void main(String[] args) {
        String str = "abcabcababaccc";
        String match = "ababa";
        System.out.println(getIndexOf(str, match));

    }

}
