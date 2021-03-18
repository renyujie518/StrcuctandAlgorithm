package facing;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName CharMaxCommon.java
 * @Description
 * 问题：
 * 有两个字符串str和str2，求出两个字符串中最长公共子串长度。
 *
 * 比如：str=acbcbcef，str2=abcbced，则str和str2的最长公共子串为bcbce，最长公共子串长度为5。
 *
 * 算法思路：
 * 1、把两个字符串分别以行和列组成一个二维矩阵。
 * 2、比较二维矩阵中每个点对应行列字符中否相等，相等的话值设置为1，否则设置为0。
 * 3、通过查找出值为1的最长对角线就能找到最长公共子串。
 * 了进一步优化算法的效率，我们可以再计算某个二维矩阵的值的时候顺便计算出来当前最长的公共子串的长度，
 * 即某个二维矩阵元素的值由dp[i][j]=1演变为dp[i][j]=1 +dp[i-1][j-1]，这样就避免了后续查找对角线长度的操作了。
 *
 * @createTime 2021年03月17日 18:27:00
 */


public class CharMaxCommon {
    public static void main(String[] args) {
        CharMaxCommon lcs = new CharMaxCommon();
        String str = lcs.CharMaxCommon("acbcbcef","abcbced");
        System.out.println(str);
    }

    private String CharMaxCommon(String A, String B) {
        int[][] dp = new int[B.length()][A.length()];
        int end_index = 0;
        int maxLength = 0;
        for(int i = 0; i < A.length(); i++) {
            for(int j = 0; j < B.length(); j++) {
                if(B.charAt(j) == A.charAt(i)) {
                    if(i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    }

                    if(dp[i][j] > maxLength) {
                        maxLength = dp[i][j];
                        end_index = j;
                    }
                }
            }
        }
        System.out.println("maxLength = " + maxLength);
        System.out.println("end_index = " + end_index);
        end_index += 1;
        return B.substring(end_index - maxLength,end_index);
    }
}




