package MiddleClass;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName CordCoverMaxPoint.java
 * @Description
 * 给定一个有序数组arr，代表数轴上从左到右有n个点arr[0]、arr[1]...arr[n－1]，
 * 给定一个正数x，代表一根长度为x的绳子，求绳子最多能覆盖其中的几个点。
 *
 * 滑动窗口  L到达arr的每个点后不动  R移动每个点，看超没超x的距离（单调不递减的方式确定终点）
 * 思路2： 贪心：在arr[0..R]范围上，找满足>=value的最左位置
 * @createTime 2021年03月27日 14:00:00
 */
public class CordCoverMaxPoint {
    //在arr[0..R]范围上，找满足>=value的最左位置(对一个有序的数组arr来说，就是找到从左到右第一个大于value的位置)
    public static int nearstIndex(int[] arr,int R,int value){
        int L  = 0;
        int index = R;  //因为要找>=value的最左位置，对一个有序数组来说最可能的位置是R,R会不断的往前走
        while (L<R){
            //二分法
            int mid = L + ((R - L) / 2);
            if (arr[mid] >= value) {//什么时候R往前移，就是在中间位置都找到了>=value，那肯定这个index就是目标，最左位置，R再减1
                index = mid;
                R = mid - 1;
            }else {
                L = mid + 1;
            }
        }
        return index;
    }

    // 长度为L的绳子最多覆盖几个点
    public static int maxPoint(int[] arr, int L) {
        int res = 1;  //因为先把右节点固定在某个arr[i]上,相当于至少也会覆盖1个
        for (int i = 0; i < arr.length; i++) {
            int nearest = nearstIndex(arr, i, arr[i] - L);//在arr[0..i]范围上，找满足>=(arr[i] - L)的最左位置索引
            res = Math.max(res, i - nearest + 1);
        }
        return res;
    }
}
