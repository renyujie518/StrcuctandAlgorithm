package facing;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName zhuanZhengxing.java
 * @Description
 * "1213"  -> 1213
 * 手动实现paraseInt  字节面试题  一言难尽
 * 我直接把源码粘来看看
 *
 * Integer.parseInt(str)  字符串参数作为有符号的十进制整数
 * 思路：
 * 就是按十进制处理字符串，先判断正负号，完后依次取出字符串中的每个字符，转换为int，
 * 用的是Character.digit(char ch, int radix)方法，最后做一些乘法比如获得1，2，3，那也得1x100+2x10+3x1得到result
 *
 * 注意点：对于每个进制的数, 只能包含这个进制所能包含的有效数字, 不然会抛出 NumberFormatException
 * int 变量正数和负数的范围是不一致的, int 的范围是  2^32~2^32-1 , 即 -2147483648 ~ 2147483647, 正数和负数的范围是不一致的.
 * 我们在解析负数的时候, 是使用一个 int 值 result 和 boolean 值 negative 组合来完成最后数值的解析的.
 * 如果我们要解析的数是 -2147483648, 那么, 在解析的过程中, 需要存储一个 2147483648 的值, 那么直接用 int 存储 2147483648, 会发生溢出.
 * 因此这里的解决方案是, 使用负数来进行存储, 避免溢出.
 *
 *
 *
 * 作者：mlya
 * 链接：https://www.jianshu.com/p/da80a793dd57
 * 来源：简书
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * https://www.jianshu.com/p/da80a793dd57
  * @createTime 2021年08月05日 20:41:00
 */
public class zhuanZhengXing {
    public static int myParseInt(String s, int radix) throws Exception {  //进制
        if (s == null) {
            throw new NumberFormatException("null");
        }
        //对于进制的超限判断就省略了
        boolean negative = false;  //正负号
        int i = 0;
        int len = s.length();
        int limit = -Integer.MAX_VALUE;  //默认取-最大值  这里看上面的解析
        int result = 0;

        if (len > 0) {
            //1.判断最首位的加减号
            char firstChar = s.charAt(0);
            if (firstChar < '0') {  //代表此时可能会有加减号
                if (firstChar == '-') {
                    negative = true;
                    limit = Integer.MIN_VALUE;//如果是负值的话, 重新指定限制范围
                } else if (firstChar != '+') {  //这里为啥不考虑 == '+'的情况  因为上述的默认值就是+的情况
                    throw new Exception();
                }
                if (len == 1) { //一个字符串  不可能没有'+'  '-'的前缀
                    throw new Exception();
                }
                i++;
            }
            //firstChar >= '0'的情况
            //计算multmin
            //multmin:用于在添加下一位数字的前判断是否溢出的值,负数跟整数的limit是不同的
            int multmin = limit / radix;

            //3.依次取出字符串中的每个字符，转换为int  比如输入"123" 10进制
            while (i < len) {
                //获取字符串转成对应进制的整数 这里第一次循环得到1  第二次得到2  第三次得到3
                //Character.digit:把一个字符Char类型转化为一个整数  如果该字符不是进制内的就返回-1.
                int digit = Character.digit(s.charAt(i++), radix);
                if (digit < 0) {
                    throw new Exception();
                }
                //判断，在追加后一个数字前，判断其是否能能够在继续追加数字，比如multmin = 123
                //那么再继续追加就会变为123*10+下一个数字，就会溢出，溢出的标准就是之前的multmin
                if (result < multmin) {//比限定小值还小
                    throw new Exception();
                }
                result = result * radix;
                result *= radix; // 就是因为要进行这一步操作, 所以要在更新 result 值之前, 先比较 result 和 multmin, 如果已经越界了, 那么再进行这一步操作, 就会越界
                if (result < limit + digit) { // 判断增加了当前值之后, 会不会越界
                    throw new Exception();
                }
                result -= digit; // 添加当前数值, 注意我们是使用负数存储的, 通过上面的代码, 保证到这一步一定不会发生溢出.
            }
        }
        return negative ? result : -result;
        //negative 值为false，所以 -result = -(-123) = 123  返回结
    }


    //左神也讲了这道题  补充下
    //只有第一个位置可能有-  其余都要符合平常数
    public static boolean isValid(char[] chas) {
        if (chas[0] != '-' && (chas[0] < '0' || chas[0] > '9')) {
            return false;
        }
        //排除-0的情况
        if (chas[0] == '-' && (chas.length == 1 || chas[1] == '0')) {
            return false;
        }
        //排除类似021，0xx的情况
        if (chas[0] == '0' && chas.length > 1) {
            return false;
        }
        //排除其他位置出现不合法字符
        for (int i = 1; i < chas.length; i++) {
            if (chas[i] < '0' || chas[i] > '9') {
                return false;
            }
        }
        return true;
    }

    public static int convert(String str) {
        if (str == null || str.equals("")) {
            return 0;
        }
        char[] chas = str.toCharArray();
        if (!isValid(chas)) {
            return 0;
        }
        boolean isZheng = chas[0] == '-' ? false : true;
        int fangZhiX10Yuejie = Integer.MAX_VALUE / 10;
        int fangZhiJiaShuYuejie = Integer.MIN_VALUE % 10;
        int result = 0;
        int curr = 0;
        for (int i = isZheng ? 0 : 1; i < chas.length; i++) {
            //!!都是以负数讨论  具体原因见分析
            curr = '0' - chas[i];
            //记住，现在这里都是负数，如果中间某一步<Integer.MAX_VALUE / 10,那resultx10后会更小从而越界
            //同理 极端情况 中间某一步 result == Integer.MAX_VALUE / 10，剩下要+的那个数必然不能小于Integer.MIN_VALUE % 10，否则越界
            if ((result < fangZhiX10Yuejie) || (result == fangZhiX10Yuejie && curr < fangZhiJiaShuYuejie)) {
                return 0;
            }
            //这步骤是基础
            result = result * 10 + curr;
        }
        if (isZheng && result == Integer.MIN_VALUE) {
            return 0; // 正数范围比负数小1
        }
        return isZheng ? -result : result;
    }

    public static void main(String[] args) {
        String test1 = "2147483647"; // max in java
        System.out.println(convert(test1));

        String test2 = "-2147483648"; // min in java
        System.out.println(convert(test2));

        String test3 = "2147483648"; // overflow
        System.out.println(convert(test3));

        String test4 = "-2147483649"; // overflow
        System.out.println(convert(test4));

        String test5 = "-123";
        System.out.println(convert(test5));

    }

}
