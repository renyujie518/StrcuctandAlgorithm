package facing;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName ReverseArry.java
 * @Description 反转一个数
 * 示例 1：
 *
 * 输入：x = 123
 * 输出：321
 * 示例 2：
 *
 * 输入：x = -123
 * 输出：-321
 *
 * 要在没有辅助堆栈 / 数组的帮助下 “弹出” 和 “推入” 数字，我们可以使用数学方法。
 *
 *
 * //pop operation:
 * pop = x % 10;
 * x /= 10;
 *
 * //push operation:
 * temp = rev * 10 + pop;
 * rev = temp;
 *
 * 但是，这种方法很危险，因为当 \text{temp} = \text{rev} \cdot 10 + \text{pop}temp=rev⋅10+pop 时会导致溢出。

 * @createTime 2021年03月07日 19:33:00
 */
public class ReverseNum {
    public int reverse1(int x) {
        long temp = 0;

        while(x != 0){
            int pop = x % 10;
            temp = temp * 10 + pop;

            if(temp > Integer.MAX_VALUE || temp < Integer.MIN_VALUE){
                return 0;
            }
            x /= 10;
        }
        return (int)temp;
    }

    public int reverse2(int x) {
        if(x == Integer.MIN_VALUE) return 0;//因为该数的绝对值越界了，而且其翻转的结果超过了int范围，这里直接处理
        boolean mark = true;//该标记位用来记录x是正数还是负数
        if(x<0) {//如果x是负数，则改变标记位，同时将负数变成正数
            mark = false;
            x = Math.abs(x);
        }
        String str = Integer.toString(x);//正数变成字符串String
        StringBuffer stringBuffer = new StringBuffer(str);//字符串String变成StringBuffer
        str = stringBuffer.reverse().toString();//StringBuffer翻转在变回字符串String
        long result = Long.parseLong(str);//字符串变成长整型，这里为什么用长整型来接收，是因为有很多数已经越界了
        if(mark == false) {//如果标记位为负数，则转回负数
            result = 0 - result;
        }
        if(result>Integer.MAX_VALUE || result<Integer.MIN_VALUE) {//如果翻转后的数字超过int类型范围，则返回0
            return 0;
        }
        return (int)result;
    }
}
