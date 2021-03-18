package recursion;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName num2String.java
 * @Description
 * 规定1和A对应、2和B对应、3和C对应...那么一个数字字符串比如"111"，就可以转化为"AAA"、"KA"和"AK"。
 * 给定一个只有数字字符组成的字符串str，返回有多少种转化结果。
 思路：
首先  1和A对应 那字母最多不超过26 【3，9】一定对应的【C,I】
若当前位置是1  有可能是A 也有可能是十几
若当前位置是2.要看超过26没
若当前位置是0，之前做的决定不对，因为没有0开头的，只可能是10，20
 * @createTime 2021年03月18日 23:37:00
 */
public class num2String {
    //在字符串的i位置做决定  i之前的位置，如何转化已经做过决定了。考虑的是i..有多少种可能
    public static int process(char[] str,int i){
        if (i == str.length){
            //如果i到了最后的位置，返回的就是1种决定 就是之前做好的决定
            return 1;
        }
        if (str[i]== 0){//若当前位置是0，之前做的决定虽然有效，但到了i让我没法继续，在之前做的决定下再看整体有效0种
            return 0;
        }
        if (str[i]=='1'){
            //为1是非常通用的情况，这时候我一定可以做一个决定是  i自己作为单独的部分，再考虑后续有多少种方法
            int res = process(str, i + 1);
            if (i+1<str.length){//还有一种决定是我把[i,i+1]作为组合，跳过1条，再考虑后续有多少种方法，单这时候要考虑下是否越界
                res += process(str, i + 2);
            }
            return res;
        }
        if (str[i] =='2'){
            //为2可以参照1，这时候我一定可以做一个决定是  i自己作为单独的部分，再考虑后续有多少种方法
            int res = process(str, i + 1);
            //如i=2下面还有字符，即i+1还有字符而且组合起来<26，才可以考虑组合的情况
            if (i+1<str.length &&(str[i+1]<='6') &&(str[i+1]>='0')){
                res += process(str, i + 2);
            }
            return res;
        }
        //字符范围是3-9的时候，只能做对应的【C,I】的决定
        return process(str, i + 1);
    }

    public static int num2String(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        return process(str.toCharArray(), 0);
    }

    public static void main(String[] args) {
        System.out.println(num2String("111"));
    }
}
