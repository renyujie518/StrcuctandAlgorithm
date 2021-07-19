package MiddleClass;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName CompleteKuohao.java
 * @Description
 * 一个完整的括号字符串定义规则如下:
 * ①空字符串是完整的。
 * ②如果s是完整的字符串，那么(s)也是完整的。
 * ③如果s和t是完整的字符串，将它们连接起来形成的st也是完整的。
 * 例如，"(()())",""和"(())()"是完整的括号字符串，"())(","()("和")"是不完整的括号字符串。
 * 牛牛有一个括号字符串s,现在需要在其中任意位置尽量少地添加括号,将其转化为一个完整的括号字符串。请问牛牛至少需要添加多少个括号
 * @createTime 2021年03月31日 15:54:00
 */
public class CompleteMinKuohao {
    //首先引出一个原问题判断表达式中的括号是否正确配对
    //基本思路是 设置一个计数器，左括号+1，右括号-1，表达式中计算完毕后，如果为0表示配对
    //这期间 一旦计数器小于0，说明一定不配对
    public static boolean isPeidui(String biaoda){
        char[] biao = biaoda.toCharArray();// 将字符串转化成字符数组
        System.out.println("表达式:      " + biaoda);
        int top = 0;// 计数，左括号 1，右括号 -1，最后总和0则匹配
        boolean end = false;// true 表示匹配  默认不匹配

        // 遍历表达式中所有字符
        for (int i = 0; i < biao.length; i++){
            // 如果是(则加1
            if (biao[i] == '('){
                top++;
            }else if (biao[i] == ')'&& top>0){// 如果是)则-1
                    top--;
                }
            if (top<0){//一旦计数器小于0，说明一定不配对
                end = false;
                break;
            }
        }
        //循环结束，两种可能，如果计数器=说明一定匹配，其他的情况都不匹配
        if (top == 0){
            System.out.println("括号匹配");
            end = true;
        }else {
            System.out.println("括号不匹配");
        }
        return end;
    }

    //本题是需要在其中任意位置尽量少地添加括号,将其转化为一个完整的括号字符串,返回添加多少个括号
    //ans: 到底需要几个括号 ( 计数器count++ ) count--,一旦出现-1，说明需要个左括号，ans++,最后剩余下的count恰好是需要几个右括号的值
    public static int needKuohao(String s){
        int count = 0;
        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i)=='(') {
                count++;
            }else {//遇到的是"）"
                if (count ==0) {
                    ans++;//在count还没--的时候，我就填个左括号，这样也免去了count回调0,还保证count>0,但会的时候不可能为负
                }else {
                    count--;
                }
            }
        }
        return count + ans;
    }


    public static void main(String args[]) {
        String test1 = "(())()";
        String test2 = "())(";
        System.out.println(isPeidui(test1));
        System.out.println("需要的括号数"+needKuohao(test1));
        System.out.println("=====");
        System.out.println(isPeidui(test2));
        System.out.println("需要的括号数"+needKuohao(test2));
    }
}
