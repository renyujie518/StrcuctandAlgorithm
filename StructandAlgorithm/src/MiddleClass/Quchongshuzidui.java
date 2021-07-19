package MiddleClass;

import LinkedList.ListNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName Quchongshuzidui.java
 * @Description 给定一个数组arr，求差值为k的去重数字对。
 * 比如[3,2,5,0,7,0]  k=2 (0,2)这个数字对只能出现一次 (5,7) (3,5)
 *  不重复 利用hashset 遍历每个元素 看arr[i]+k在不在set中
 * @createTime 2021年03月31日 21:08:00
 */
public class Quchongshuzidui {
    public static List<List<Integer>> Quchongshuzidui(int[] arr,int k){
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < arr.length; i++) {
            set.add(arr[i]);
        }
        List<List<Integer>> res = new ArrayList<>();
        for (Integer curr : set) {
            if (set.contains(curr+k)){
                res.add(Arrays.asList(curr, curr + k));
            }
        }
        return res;
    }

}
