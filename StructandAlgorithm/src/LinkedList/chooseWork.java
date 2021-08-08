package LinkedList;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeMap;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName chooseWork.java
 * @Description
 * 为了找到自己满意的工作， 牛牛收集了每种工作的难度和报酬。
 * 牛牛选工作的标准是在难度不超过自身能力值的情况下， 牛牛选择报酬最高的工作。
 * 在牛牛选定了自己的工作后， 牛牛的小伙伴们来找牛牛帮忙选工作， 牛牛依然使用自己的标准来帮助小伙伴们。
 * 牛牛的小伙伴太多了， 于是他只好把这个任务交给了你。
 * class Job {
 * public int money;// 该工作的报酬
 * public int hard; // 该工作的难度
 * public Job(int money, int hard) { this. money = money; this. hard = hard; }
 * }
 * 给定一个Job类型的数组jobarr， 表示所有的工作。 给定一个int类型的数组arr， 表示所有小伙伴的能力。
 * 返回int类型的数组， 表示每一个小伙伴按照牛牛的标准选工作后所能获得的报酬。
 *
 * 思路  ：可以用背包法做  但是一看到这样的组合求最优的情况 可以联想使用"有序表"
 * （难度，工资） 首先按照难度升序（这时候会有难度一样的）  删除那些难度高但是工资少的
 * 此时难度是升序  相同的难度的可以当做一组  每组内再依照工资升序排  只留下相同难度但是工资最高的（留下组长）
 * 这时候再按照能力值>=的原则去尽量安排，这时候的总工资一定是最高的
 *
 * @createTime 2021年08月08日 20:39:00
 */
public class chooseWork {
    public static class Job {
        public int money;
        public int hardWork;

        public Job(int money, int hardWork) {
            this.money = money;
            this.hardWork = hardWork;
        }
    }

    public static class JobComparator implements Comparator<Job> {
        @Override
        public int compare(Job o1, Job o2) {
            //难度hardWork先由小到大，如果难度不一样 moeny由大到小
            return o1.hardWork != o2.hardWork ? (o1.hardWork - o2.hardWork) : (o2.money - o1.money);
        }
    }

    public static int[] getMoneys(Job[] job, int[] ability) {
        Arrays.sort(job, new JobComparator());
        //这个表里存储的是"组长"  key是hardWork  value是money
        //注意,这个map里是"同增同涨"的组长
        /*
        TreeMap实现SortMap接口，能够把它保存的记录根据键排序,默认是按键值的升序排序，也可以指定排序的比较器，
        当用Iterator遍历TreeMap时，得到的记录是排过序的
         */
        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(job[0].hardWork, job[0].money);
        //这里一定注意，之前经过比较器  现在的job已经是：难度hardWork先由小到大，如果难度不一样 moeny由大到小
        //所以job【0】是难度最小，但money最高的（看上去是梦想工作）
        Job zuZhang = job[0];
        for (int i = 1; i < job.length; i++) {
            if (job[i].hardWork != zuZhang.hardWork && job[i].money > zuZhang.money) {
                //之前假设的组长是第一位job[0],而且由于TreeMap的性质里面的元素是按照难度升序的
                //什么时候跟新组长 就是jobi的钱大于组长了（同增同涨） 同时不同难度的也要新增map
                //每次再把组长放到map里
                zuZhang = job[i];
                map.put(zuZhang.hardWork, zuZhang.money);
            }
        }
        int[] res = new int[ability.length];
        for (int i = 0; i < ability.length; i++) {
            //ceilingKey(K key) 返回大于或等于给定键元素的最小键元素，否则返回null。
            //floorKey(K key)	返回小于或等于给定键的最大键，如果没有这样的键，则null
            Integer key = map.ceilingKey(ability[i]);
            //返回int类型的数组， 表示每一个小伙伴按照牛牛的标准选工作后所能获得的报酬
            res[i] = key != null ? map.get(key) : 0;

        }
        return res;
    }


}
