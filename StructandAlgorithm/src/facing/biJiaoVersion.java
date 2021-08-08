package facing;

import java.util.Scanner;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName biJiaoVersion.java
 * @Description
 * 给你两个版本号 version1 和 version2 ，请你比较它们。
 * 版本号由一个或多个修订号组成，各修订号由一个 '.' 连接。每个修订号由 多位数字 组成，可能包含 前导零 。
 * 每个版本号至少包含一个字符。修订号从左到右编号，下标从 0 开始，最左边的修订号下标为 0 ，下一个修订号下标为 1 ，以此类推。
 * 例如，2.5.33 和 0.1 都是有效的版本号。
 * 比较版本号时，请按从左到右的顺序依次比较它们的修订号。比较修订号时，只需比较 忽略任何前导零后的整数值 。
 * 也就是说，修订号 1 和修订号 001 相等 。如果版本号没有指定某个下标处的修订号，则该修订号视为 0 。
 * 例如，版本 1.0 小于版本 1.1 ，因为它们下标为 0 的修订号相同，而下标为 1 的修订号分别为 0 和 1 ，0 < 1 。
 *
 * 返回规则如下：
 * 如果version1>version2返回1，
 * 如果version1<version2 返回 -1，
 * 除此之外返回 0。
 *leetcode  165 15 61
 * @createTime 2021年08月08日 14:13:00
 */
public class biJiaoVersion {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String version1 = sc.next();
        String version2 = sc.next();
        System.out.println(biJiaoVersion(version1, version2));
    }

    public static int biJiaoVersion(String version1, String version2) {
        int l1 = version1.length();
        int l2 = version2.length();
        //利用双指针
        int p1 = 0;
        int p2 = 0;
        while(p1<l1 || p2<l2){
            //记录版本号
            int v1 = 0;
            int v2 = 0;

            while(p1<l1 && version1.charAt(p1) != '.'){
                v1 = v1*10 + version1.charAt(p1) - '0';
                p1++;
            }
            while(p2<l2 && version2.charAt(p2) != '.'){
                v2 = v2*10 +version2.charAt(p2) - '0';
                p2++;
            }

            if(v1 != v2){
                if(v1 > v2){
                    return 1;
                }else if(v2>v1){
                    return -1;
                }
            }
            //这里为什么会再++呢  因为初始化的时候l1 = version1.length()  没有减1，而指针又是从0开始的，所以再++一遍跳出p1<l1 || p2<l2的限制
            p1++;
            p2++;
        }
        return 0;
    }
}
