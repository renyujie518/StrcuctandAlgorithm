package facing;

import java.util.Scanner;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName chiTang.java
 * @Description
 * 小Q的父母要出差N天，走之前给小Q留下了M块巧克力。
 * 小Q决定每天吃的巧克力数量不少于前一天吃的一半，但是他又不想在父母回来之前的某一天没有巧克力吃，请问他第一天最多能吃多少块巧克力。
 *
 * 例：input：出差 3 天，７块奶糖
 * 　　output：4
 * 即：第一天吃4块糖，第二天吃2块糖，第三天吃1块糖。
 * 这里用的是折半查找(二分查找)的思想，先找最中间的。
 * 二分查找：
 * 首先赋低位min为0，高位max为所有巧克力数n，
 * while(min<max),循环试探（min+max）/2向上取整个数个巧克力是否满足题意，
 * 1：如果满足题意，那么再判断一下（min+max）/2向上取整+1个数个巧克力是否满足题意：
 *      （1）如果不满足题意，那么就说明当前的（min+max）/2向上取整个巧克力就是要找的最大的
 *       （2）如果满足题意，那么就说明当前的（min+max）/2向上取整个巧克力取小了，赋低位min=（min+max）/2向上取整，继续while循环试探
 * 2：如果不满足题意，那么说明当前的（min+max）/2向上取整个巧克力取大了，赋高位max=（min+max）/2向上取整，继续while循环试探

 * @createTime 2021年08月20日 14:09:00
 */
public class chiTang {

    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        int day = scan.nextInt();
        int number = scan.nextInt();
        System.out.print(fun(day,number));
    }

    //计算第一天吃s个巧克力一共需要多少个多少个巧克力
    public static int sum(int s, int n, int m){
        int sum=0;
        for(int i=0;i<n;i++){
            sum+=s;
            s=(s + 1)/2;//向上取整
        }
        return sum;
    }
    //二分查找
    public static int fun(int n, int m){
        if(n==1) return m;
        int low=1;
        int high=m;//第一天的巧克力一定是大于等于1小于等于m的
        while(low<=high){
            int mid=(low+high + 1)/2;//向上取整
            if(sum(mid, n, m)==m) return mid;//如果第一天吃mid个巧克力，刚刚好吃完所有巧克力，那么直接返回
            else if(sum(mid, n, m)<m){
                low=mid+1;
            }else{
                high=mid-1;
            }
        }
        return high;
    }


}
