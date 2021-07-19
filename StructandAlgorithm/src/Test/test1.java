package Test;

import java.util.PriorityQueue;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName test1.java
 * @Description TODO
 * @createTime 2021年03月12日 15:41:00
 */
public class test1 {
    class Solution {
        public int maxSumDivThree(int[] nums) {
            PriorityQueue<Integer> list1 = new PriorityQueue<>();
            PriorityQueue<Integer> list2 = new PriorityQueue<>();
            int total = 0;
            for (int i = 0; i < nums.length; i++) {
                int result = nums[i] % 3;
                if(result==1)list1.offer(nums[i]);
                if(result==2)list2.offer(nums[i]);
                total += nums[i];
            }
            int result = total % 3;
            while(result!=0){
                if(result==1){
                    if(list1.isEmpty() && list2.size()<2){
                        return 0;
                    }
                    int total1 = minus(list1,total);
                    int total2 = minus(list2,minus(list2,total));
                    total = Math.max(total1,total2);
                }
                if(result==2){
                    if(list2.isEmpty() && list1.size()<2){
                        return 0;
                    }
                    int total1 = minus(list1,minus(list1,total));
                    int total2 = minus(list2,total);
                    total = Math.max(total1,total2);
                }
                result = total % 3;
            }
            return total;
        }
        private int minus(PriorityQueue<Integer> list,int total){
            if(list.isEmpty())return -1;
            total = total - list.poll();
            return total;
        }
    }
}
