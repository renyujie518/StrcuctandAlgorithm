package Greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName lowestZiDianXu.java
 * @Description
 * 给定一个字符串类型的数组strs，找到一种拼接方式，使得把所有字符串拼起来之后形成的字符串具有最小的字典序。
 * 比如  abc > aaa  c < cda   c >ba
 * 错的想法： a,b  a<=b,  a放前 否则b放前  反例： b,ba  如果按照这个意思这么想 拼接后应该是 bba  但实际上是bab最小
 *
 * 正确： a,b   a.b<=b.a a放前，否则b放前  (.是拼接的意思)
 * @createTime 2021年03月16日 16:56:00
 */
public class lowestZiDianXu {
    public static String lowestString(String[] strs){
        if (strs == null || strs.length ==0){
            return "";
        }
        Arrays.sort(strs, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                return (a + b).compareTo(b + a); //如果指定的数与参数相等返回0。如果指定的数小于参数（括号内）返回 -1。如果指定的数大于参数返回 1。
                //比较器 obj1和obj2是要比较的对象。如果对象相等，则此方法返回零。如果obj1大于obj2，则返回正值。否则返回负值。
            }
        });
        String res = "";
        for (int i = 0; i < strs.length; i++) {
            res = res + strs[i];  //拼接
        }
        return res;
    }

    public static void main(String[] args) {
        String[] strs1 = { "jibw", "ji", "jp", "bw", "jibw" };
        System.out.println(lowestString(strs1));//bw jibw jibw ji  jp

        String[] strs2 = { "ba", "b" };
        System.out.println(lowestString(strs2));

    }
}
