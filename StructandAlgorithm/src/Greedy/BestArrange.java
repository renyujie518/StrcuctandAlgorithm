package Greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName BestArrange.java
 * @Description
 * 一些项目要占用一个会议室宣讲，会议室不能同时容纳两个项目的宣讲。
 * 给你每一个项目开始的时间和结束的时间(给你一个数组，里面是一个个具体的项目)，你来安排宣讲的日程，
 * 要求会议室进行的宣讲的场次最多。返回这个最多的宣讲场次
 *
 * 按照会议结束的早来安排
 * @createTime 2021年03月16日 16:18:00
 */
public class BestArrange {

    //会议的结构体
    public static  class Program{
        public int start;
        public int end;

        public Program(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static  int bestArrange(Program[] programs,int start){  //最开始的时间
        Arrays.sort(programs, new Comparator<Program>() {
            @Override
            public int compare(Program o1, Program o2) {
                return o1.end - o2.end;  //以结束时间升序，先结束的排前面
            }
        });
        int result = 0;
        for (int i = 0; i < programs.length; i++) {
            if (start <= programs[i].start){ //准备安排会议的那个时间点（目标时间点） <= 会议开始时间 说明可以单排，大不了空闲一会
                result++;
                start = programs[i].end;  //跟新目标时间点
            }
        }
        return result;
    }
}
