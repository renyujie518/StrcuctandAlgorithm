package SwordOffer;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName overhalfprint_39.java
 * @Description
 * 找出数组中出现次数大于数组长度一半的数，实现moreThanHaft函数
 *  * 思路：
 *  * 建立一个hashmap  记录数组中出现的次数    key = 那个数  value = 次数
 *  * 再用一个list存储符合条件的数
 * @createTime 2021年08月19日 22:01:00
 */
public class overhalfprint_39 {
    public static void main(String[] args) {
        int[] arr = {1, 3, 2, 2, 6, 2, 2, 7, 2, 2, 2, 2};
        moreThanHaft(arr);
    }

    public static void moreThanHaft(int[] num) {
        HashMap<Integer, Integer> map = new HashMap<>();
        List<Integer> list = new LinkedList<>();
        int length = num.length ;
        for (int i = 0; i < num.length; i++) {
            if (map.containsKey(num[i])) {//说明添加过
                map.put(num[i], map.get(num[i]) + 1); //次数加1
            } else {//第一次遇见
                map.put(num[i], 1);  //初始值设为1
            }
        }
        //map构建完毕
        for (Integer key : map.keySet()) {
            if (map.get(key) > (length / 2)) {
                list.add(key);
            }
        }



        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
}
