package facing;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName findDuplicate.java
 * @Description 找出list1和list2里重复的元素
 * ["1",2,3，3]
 *
 * [3,6,9]
 *
 * 输出：
 * 3

 * @createTime 2021年08月19日 15:39:00
 */
public class findDuplicate {
    public static List<String> findDuplicate1(List<String> list1, List<String> list2) {
        List result = new LinkedList<String>();
        HashMap<String, Integer> map = new HashMap<>();
        //去重 set
        //刚好，还不用改变原list
        ArrayList<String> list1Copy = new ArrayList<>(new HashSet<String>(list1));
        ArrayList<String> list2Copy = new ArrayList<>(new HashSet<String>(list2));
        //放入map统计
        for (String s1 : list1Copy) {
            map.put(s1, 1);
        }
        for (String s2 : list2Copy) {
            //因为已经去重过  而且map遍历过list1  所以在遍历list2的时候发现有填过key(map.get(s2) != null)必然是重复度，设置为2
            map.put(s2, (map.get(s2) == null ? 1 : 2));
        }
        //判断重复元素
        for (Map.Entry<String, Integer> set : map.entrySet()) {
            if (set.getValue() == 2) {
                result.add(set.getKey());
            }
        }
        return result;
    }

    public static List<String> findDuplicate2(List<String> list1, List<String> list2) {
        List<String> result = new ArrayList<>();
        for (String s1 : list1) {
            for (String s2 : list2) {
                if (s2.equals(s1)) {
                    result.add(s1);
                }
            }
        }
        return result.stream().distinct().collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<String> list1 = new ArrayList<String>();
        List<String> list2 = new ArrayList<String>();

        list1.add("1");
        list1.add("3");
        list1.add("8");
        list1.add("9");
        list1.add("9");

        list2.add("2");
        list2.add("3");
        list2.add("3");
        list2.add("7");
        List<String> result1 = findDuplicate1(list1, list2);
        for (String s1 : result1) {
            System.out.println(s1);
        }
        System.out.println("++++++");
        List<String> result2 = findDuplicate2(list1, list2);
       result2.forEach(System.out::println);

    }
}
