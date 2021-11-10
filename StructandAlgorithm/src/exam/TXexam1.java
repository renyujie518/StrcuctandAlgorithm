package exam;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName exam1.java
 * @Description
 * 腾讯光子面试
 * 一个数量为N的整数数组，其中一个数字出现次数超过N/2，请将该数字找出来。
 * 超过一半
 *
 * 建立一个map，统计次数  key  = 那个数  value = 次数
 * @createTime 2021年09月23日 09:22:00
 */
public class TXexam1 {
    public static List<Integer> exam1(int[] num) {
        HashMap<Integer, Integer> countMap = new HashMap<>();
        LinkedList<Integer> result = new LinkedList<>();
        int n = num.length;
        //先统计
        for (int i = 0; i < n; i++) {
            if (countMap.containsKey(num[i])) {
                //说明添加过,+1
                countMap.put(num[i], countMap.get(num[i]) + 1);
            } else {//第一次遇见
                countMap.put(num[i], 1);

            }
        }
        //map构建完毕
        for (Integer key : countMap.keySet()) {//num[i]
            if (countMap.get(key) > (n / 2)) {
                result.add(key);
            }

        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 3, 3,3,3,3,6, 7};
        List<Integer> res = exam1(arr);
        for (int i = 0; i < res.size(); i++) {
            System.out.println(res.get(i));
        }
    }
}
