package facing;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName bigSum.java
 * @Description 大数相加
 *
 * 使用string  -AscII的方式
 * 涉及到进位  （反转）  倒着来
 * /  %
 * @createTime 2021年08月30日 20:10:00
 */
public class bigSum {
    public static void main(String[] args) {
        String str1 = "1";
        String str2 = "1000000000000";
        System.out.println(bigSum(str1,str2));

    }

    public static String bigSum(String str1, String str2) {
        //防御性编程
        if (str1 == null || "".equals(str1)) {
            return str2;
        }
        if (str2 == null || "".equals(str2)) {
            return str1;
        }
        //获得下长度
        int l1 = str1.length();
        int l2 = str2.length();
        int minLength = Math.min(l1, l2);
        //先反转一下
        String s1 = new StringBuffer(str1).reverse().toString();
        String s2 = new StringBuffer(str2).reverse().toString();
        //结果
        StringBuffer res = new StringBuffer(Math.max(l1, l2) + 1);
        //654321   321字符
        //定义一个变量   进位
        int carry = 0;
        //当前位上的数值
        int currNum = 0;
        int i = 0;
        //先把短的处理了
        for (; i < minLength; i++) {
            //分别获取两个字符串对应的  然
            //后相加 再加上进位
            currNum = s1.charAt(i) + s2.charAt(i) + carry - 2 * '0';
            //获取进位
            carry = currNum / 10;
            //当前值
            currNum = currNum % 10;
            res.append(String.valueOf(currNum));
        }
        if (l2 > l1) {
            //把长的取出来，值操作S1,又因为i位置还是在那，所以i位置不用管
            s1 = s2;
        }

        //654 [321]
        //321
       //基于较长的字符串   有长出来的一部分  对这部分循环  加进位
        for (; i < s1.length(); i++) {
            //这里只加进位
            currNum = s1.charAt(i) + carry - '0';
            //获取进位
            carry = currNum / 10;
            //当前最终位
            currNum = currNum % 10;
            res.append(String.valueOf(currNum));
        }
        //最后在考虑进位，因为进位可能会一直到最高位还有
        if (carry > 0) {
            res.append(String.valueOf(carry));
        }
        //因为之前反转了，这里再反转一下
        return res.reverse().toString();
    }


}
