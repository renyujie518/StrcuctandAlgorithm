package MiddleClass;

import java.util.Arrays;
import java.util.HashSet;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName MagicOP.java
 * @Description 给一个包含n个整数元素的集合a，一个包含m个整数元素的集合b。(集合中不能有重复元素)
 * 定义magic操作为，从一个集合中取出一个元素，放到另一个集合里，且操作过后每个集合的平均值都大大于于操作前。
 * 注意以下两点：
 * 1）不可以把一个集合的元素取空，这样就没有平均值了
 * 2）值为x的元素从集合b取出放入集合a，但集合a中已经有值为x的元素，则a的平均值不变（因为集合元素不会重复），
 * b的平均值可能会改变（因为x被取出了）问最多可以进行多少次magic操作？
 *
 * //通过分析得到以下结论：
 * 1.集合A，B的平均值一样x，不管从哪里拿以及,拿>=<的都不行
 * 2.集合A的平均值x < 集合B的平均值y 不能从A拿，只能拿B中的大于x小于y的且在B中不存在
 * 3.在2的情况下挑选最小，（把A最拖后腿的拿走，但对B影响最小，这样次数最多）
 * @createTime 2021年03月31日 21:42:00
 */
public class MagicOP {
    public static int maxMagicOP(int[] arr1,int[] arr2){
        double sum1 = 0;
        for (int i = 0; i < arr1.length; i++) {
            sum1 += (double) arr1[i];
        }
        double sum2 =0;
        for (int i = 0; i < arr2.length; i++) {
            sum2 += (double) arr2[i];

        }
        if (avg(sum1, arr1.length) == avg(sum2, arr2.length)) {
            return 0;//参照分析1
        }
        //平均值不相等，要区分大小 重定位
        int[] arrMore = null;
        int[] arrLess = null;
        double sumMore = 0;
        double sumLess = 0;
        if (avg(sum1, arr1.length)> avg(sum2, arr2.length)) {
            arrMore = arr1;
            sumMore = sum1;
            arrLess = arr2;
            sumLess = sum2;
        }else {
            arrMore = arr2;
            sumMore = sum2;
            arrLess = arr1;
            sumLess = sum1;
        }
        //先把较大的集合排个序，因为在分析3中说了，要选达标的中最小的，从左往右选的时候一旦>x<y,就是最先符合条件的
        Arrays.sort(arrMore);
        //要保证集合的唯一性 可就要用set了,有的就不搬了
        HashSet<Integer> setLess = new HashSet<>();
        for (int less : arrLess) {
            setLess.add(less);
        }
        int moreSize = arrMore.length;  //平均值大的集合还剩几个数
        int lessSize = arrLess.length;  //平均值小的集合还剩几个数
        int ops = 0;
        for (int i = 0; i < arrMore.length; i++) {
            double curr = arrMore[i];
            //从大的拿到小的里,符合分析2
            if (curr < avg(sumMore, moreSize) && curr > avg(sumLess, lessSize) && !setLess.contains(arrMore[i])) {
                sumMore -= curr;//大集合总数-curr
                moreSize--;
                sumLess += curr;//小集合总数+curr
                lessSize++;
                setLess.add(arrMore[i]);//注意，操作元数据
                ops++;
            }
        }
        return ops;
    }
    public static double avg(double sum, int size) {
        return sum / (double) (size);
    }
    public static void main(String[] args) {
        int[] arr1 = { 1, 2, 5 };
        int[] arr2 = { 2, 3, 4, 5, 6 };
        System.out.println(maxMagicOP(arr1, arr2));

    }
}
