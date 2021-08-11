package MiddleClass;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName Light.java
 * @Description
 * 小Q正在给一条长度为n的道路设计路灯安置方案。
 * 为了让问题更简单,小Q把道路视为n个方格,需要照亮的地方用'.'表示, 不需要 照亮的障碍物格子用'X'表示(X不能放灯)。
 * 小Q现在要在道路上设置一些路灯, 对于安置在 pos位置的路灯, 这盏路灯可以照亮pos - 1 , pos, pos + 1 这三个位置。
 * 小Q希望能安置尽量少的路灯照亮所有'.'区域, 希望你能帮他计算一下最少需 要多少盏路灯。
 * 输入描述： 输入的第一行包含一个正整数t(1 <= t <= 1 000) ,
 * 表示测试用例数 接下来每两行一个测试数据, 第一行一个正整数n(1 <= n <= 1 000) , 表示道路 的长度。
 * 第二行一个字符串s表示道路的构造,只包含'.'和'X'。 输出描述： 对于每个测试用例, 输出一个正整数表示最少需要多少盏路灯。
 *
 *考虑从左到右的贪心(什么是贪心：在对问题求解时，总是做出在当前看来是最好的选择)
 * i位置是.  考虑i+1位置  如果i+1是X  必须在i位置放（否则i位置的无法被覆盖）
 *                      如果i+1是.  放在i+1位置最优（因为可以覆盖到i）
 * i位置是X 不能放 直接考虑i+1位置
 *
 * @createTime 2021年08月11日 10:10:00
 */
public class Light {
    public static int minLightWithTX(String s) {
        char[] str = s.toCharArray();
        int index = 0;
        int light = 0;
        //原则就是，来到index位置，保证之前的灯，彻底不会影响index位置（前面覆盖影响过就不考虑，只考虑自己和后面的）
        while (index < str.length) {
            if (str[index] == 'X') {
                index++;//碰到X的放不了，直接跳过
            } else {//str[index] == '.'当前位置是.
                //首先放灯  因为之前放的灯，不管是有没有覆盖到还是X不让放，彻底不会影响当前位置
                light++;
                if (index + 1 == str.length) {//边界，当前是.但是最后了
                    break;
                } else {//如果下面还有位置
                    if (str[index + 1] == 'X') {
                        index = index + 2;//跳过这个X
                    } else {//index + 1是.  这里注意  .(index)  .(index+1)  不管X/.(index+2)   不管X/.(index+3)
                        //这时候应该把灯放在 index+1上 这样覆盖了index位置（之前的灯还是保证了彻底不会影响index位置)和index+2
                        //所以下次出发的地点在index+3, 不管X/.交给上面的if去判断
                        index = index + 3;
                    }
                }
            }
        }
        return light;
    }

    public static void main(String[] args) {
        String test = "...X.X.X..XX.XX.X.X.X.X.XX.XXX.X.XXX.XX";
        System.out.println(minLightWithTX(test));

    }





}
