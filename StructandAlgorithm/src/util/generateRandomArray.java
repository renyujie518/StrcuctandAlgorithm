package util;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName generateRandomArray.java
 * @Description 指定数组的长度和最大值  生成一个随机数组
 * @createTime 2021年07月25日 20:07:00
 */
public class generateRandomArray {
    public static String[] generateRandomArray(int len, int max) {
        String[] res = new String[len];
        for (int i = 0; i != len; i++) {
            res[i] = String.valueOf((int) (Math.random() * (max + 1)));
        }
        return res;
    }
}

