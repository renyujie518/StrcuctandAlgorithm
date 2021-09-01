package facing;

import java.util.*;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName topKWord.java
 * @Description 给一非空的单词列表，返回前 k 个出现次数最多的单词。
 * 返回的答案应该按单词出现频率由高到低排序。如果不同的单词有相同出现频率，按字母顺序排序。
 * 输入: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
 * 输出: ["i", "love"]
 * 解析: "i" 和 "love" 为出现次数最多的两个单词，均为2次。
 *     注意，按字母顺序 "i" 在 "love" 之前。
 *
 *https://leetcode-cn.com/problems/top-k-frequent-words/solution/xiao-gen-dui-huo-zhe-hashbiao-pai-xu-by-9uj06/
 * 通常在问题中看到 前 K大，前 K小 或者 第 K 个， K 个最 等等类似字样,一般情况下我们都可以用堆来做。
 * 构建一个 大小为 K的小根堆按照上述规则自定义排序的比较器。
 * 然后依次将单词加入堆中，当堆中的单词个数超过 K个后，我们需要弹出顶部最小的元素使得堆中始终保留 K个元素，
 * 所以从顶部弹出的元素顺序是从小到大的，所以最后我们还需要反转集和。

 * @createTime 2021年09月01日 11:30:00
 */
public class topKWord {
    public static List<String> topKWordWithDui(String[] words, int k) {
        // 1.先用哈希表统计单词出现的频率
        HashMap<String, Integer> countMap = new HashMap<>();
        for (String word : words) {
            countMap.put(word, countMap.getOrDefault(word, 0) + 1);
        }
        //2.构建小根堆 这里需要自己构建比较规则 此处为 lambda 写法 Java 的优先队列默认实现就是小根堆
        PriorityQueue<String> minDui = new PriorityQueue<>(new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                if (countMap.get(s1).equals(countMap.get(s2))) {
                    //如果频数相等，按照字符串的字典序从小到大排列。
                    //s2<s1  返回1（正数，升序，从小到大）  s2》s1  返回-1（负数，逆序，从大到小）
                    //(后面会反转，来满足题意字典序应该是从小到大）
                    return s2.compareTo(s1);
                } else {
                    //字符串频率不同的时候按照字符的出现次数从小到大排序。（后面会反转，来满足题意次数应该是从大到小）
                    return countMap.get(s1) - countMap.get(s2);
                }
            }
        });
        // 3.依次向堆加入元素。
        for (String s : countMap.keySet()) {
            minDui.offer(s);
            // 当堆中元素个数大于 k 个的时候，需要弹出堆顶最小的元素。
            if (minDui.size() > k) {
                minDui.poll();
            }
        }
        // 4.依次弹出堆中的 K 个元素，放入结果集合中。
        List<String> res = new ArrayList<String>(k);
        while (minDui.size() > 0) {
            res.add(minDui.poll());
        }
        // 5.注意最后需要反转元素的顺序。
        Collections.reverse(res);
        return res;

    }

    public static List<String> topKWordWithMap(String[] words, int k) {
        // 1.初始化 哈希表 key -> 字符串 value -> 出现的次数。
        Map<String, Integer> countMap = new HashMap<>();
        for (String word : words) {
            countMap.put(word, countMap.getOrDefault(word, 0) + 1);
        }
        // 2.用 list 存储字符 key 然后自定义 Comparator 比较器对 value 进行排序。

        /**
         *  注意，这里再构造list的时候，传参是countMap.keySet()，也就是那堆word，这个由于构造函数的兼容性
         *  直接就把所有的word以String的格式放到list里了，这是这种构造函数的优势，就不用再放一遍了
         *  在利用list的sort+自定义Comparator保证按照题意取出合适的res
         */
        List<String> list = new ArrayList<>(countMap.keySet());
        list.sort(new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                if (countMap.get(a).equals(countMap.get(b))) {
                    //这时候直接就是题意，字符串的出现频率相等的时候我们需要按照字符串的字典序从小到大排列。
                    return a.compareTo(b);
                } else {
                    //字符串频率不同的时候按照字符的出现次数从大到小排序。
                    return countMap.get(b) - countMap.get(a);
                }
            }
        });
        // 3.截取前 K 大个高频单词返回结果。
        return list.subList(0, k);
    }
}
