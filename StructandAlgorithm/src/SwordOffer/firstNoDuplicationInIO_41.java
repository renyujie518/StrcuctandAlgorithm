package SwordOffer;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName firstNoDuplicationInIO_41.java
 * @Description 字符流中第一个不重复的字符
 * 例如，当从字符流中只读出前两个字符 "go" 时，第一个只出现一次的字符是 "g"。
 * 当从该字符流中读出前六个字符“google" 时，第一个只出现一次的字符是 "l"。
 * @createTime 2021年08月24日 11:48:00
 */
public class firstNoDuplicationInIO_41 {
    private int[] cnts = new int[256];
    private Queue<Character> queue = new LinkedList<>();

    public void Insert(char ch) {
        cnts[ch]++;//构建字典表   对应的索引是相应的AscII,对应的值是出现的次数
        queue.add(ch);
        while (!queue.isEmpty() && cnts[queue.peek()] > 1) {//第一次出现的在队列的首（先进先出），该位置的值大于1就不是第一次出现，弹出
            queue.poll();
        }
    }

    public char firstNoDuplicationInIO(){
        return queue.isEmpty() ? '@' : queue.peek();
    }

}
