package sort;

import util.bijiaoqi_arrays_sort;

import java.util.Random;

/**
 * @ClassName quick.java
 * @author admin
 * @version 1.0.0
 * @Description 快速排序
 *  不稳定的排序算法：快选希堆
 *  *
 *  * 快速排序
 *  * 不稳定
 *  * 时间复杂度：O(N*logN)
 *  * 空间复杂度：O(logN)
 *  *
 *  * 经典快排：
 *  * 选取数组的最后一个数 x，将小于等于 x 的放在左边，大于 x 的放在右边，然后对于左右两部分按照同样的方式进行递归处理。

 *  * 一次划分只搞定一个位置上的数。
 *  * 缺点：和数据规模有关系，例如{1,2,3,4,5}，每次拿最后一个去划分，则对于每个数都需要O(N)的时间，总的时间复杂度就是 O(N^2)。
 *  * 如果划分得很好，左部分和右部分的数据规模大体相同的话，则时间复杂度就是 O(N*logN)。
 *  *
 *  * 对经典快排进行改进：
 *  * 选取数组的最后一个数 x，将小于 x 的放在左边，大于 x 的放在右边，等于 x 放在中间，然后对于左右两部分按照同样的方式进行递归处理。
 *  * 一次划分搞定多个等于 x 的位置上的数。
 *  *
 *  * 随机快排：
 *  * 从数组中随机选一个数和最后一个数进行交换，用这个随机选择的数进行划分。这就成了一个概率问题。长期期望就是 O(N*logN)的。
 *  *
 *  * 绕开数据本身的数据状况，可以使用随机选举的方式和哈希。
 *  *
 *  * 归并排序输在需要开辟一个 O(N) 的辅助空间，其次常数项也比快排多。
 *  *
 *  * 为什么随机快排的额外空间复杂度是 O(logN)?
 *  * 用在划分点上了，只有知道了划分点的位置，左侧递归完事儿之后才能知道右边从哪个位置开始划分。
 *  * 而左右两个部分都需要分别记录划分点。
 *  * 然而在哪儿打断点也是一个概率问题，如果好的情况下，每次打在中间的位置，就像一个二叉树，则时间复杂度是 O(logN)；
 *  * 如果断点每次都打在一遍，就像向左偏或向右偏的二叉树，则时间复杂度就是 O(N)。
 * @createTime 2021年02月08日 14:38:00
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] arrs = {3, 2, 1, 5, 6, 4,4,213,1,34,556,78,1,23,234,56};
        int[] result = quicksort(arrs);
        for (int x : result) {
            System.out.print(x+"\t");
        }

        //test
        bijiaoqi_arrays_sort.test("sort.QuickSort");

    }

    //有个交换的过程，所以先写个交换
    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static int[] quicksort(int[] arrs) {  //这个只不过是带返回值的结果，在main方法中调用
        if (arrs == null || arrs.length < 2) {
            return new int[0];
        }

        quicksortCode(arrs, 0, arrs.length - 1);
        return arrs;
    }

    private static void quicksortCode(int[] arrs, int L, int R) { //这个真真的是递归下的快排
        if(L < R){
            //首先要创建一个随机整数作为随机索引，这个随机整数要和数组的左边界，右边界有关，这样保证了长期期望就是 O(N*logN)的。
            Random random = new Random();
            int randomIndex = L + random.nextInt(R - L + 1);
            //从数组中随机选择一个索引，将该索引上的数与数组中 最后一个数 x进行交换
            swap(arrs,randomIndex,R);

            //分治
            int[] p = partition(arrs,L,R);
            //partition函数功能返回的是等于数组最后一个元素 x 的范围的左右边界（长度一定为2）
            //！！！！！这最后一个数（也是随机数，因为换到最后了）就相当于荷兰国旗问题里被选择的那个数num划分值
            //要注意一点，我刚刚做了交换啊，把随机数（荷兰国旗问题里被选择的那个数num）放到了最后，random= R的,就是右边界
            //所以，此时，可以看做是先基于这个随机数把数组划分为  <random  =radom >radom  三个部分，中间这部分就已经排好序了
            //下一步是对其余的两部分递归调用即可，而这个partition。返回的就是 =radom 这一部分的边界
            //上述的这些发生在第一次执行程序递归的时候，再后面就不知道这个最后一个元素x是啥了，
            //只知道对<random快排的这部分里的x一定比random小，而且紧挨着第一次迭代后的random,右半部分同理，
            //之后每次又会选随机数，再与最后一个交换，再针对这个随机数已经排好序了，直到L>R

            //递归
            quicksortCode(arrs,L,p[0]-1);//对<random快排
            quicksortCode(arrs,p[1]+1,R);//对>random快排

        }
    }

    private static int[] partition(int[] arrs, int L, int R) {  //类似荷兰国旗问题
        int less = L - 1; //设定左边界
        int more = R;// 这里将 大于区域的范围 直接定位到 right
        //这与 荷兰国旗不同了 ，我不传入划分值了，arrs[R]就是划分值（）,同时我也不设定指针p了，L就是p
        while (L < more) {
            // 小于 num 的情况。实际上是当前值与小于区域的下一个元素先交换，然后边界扩充把它包进去，指针再右移 swap(nums, i, less);less++;i++;
            if (arrs[L] < arrs[R]) {
                swap(arrs, ++less, L++);
                // 大于 num 的情况，此时 指针left 需要留在原地，继续考察与 num 的大小关系
                //实际上是当前值与大于区域的下一个元素先交换，然后边界扩充把它包进去，指针不动 swap(nums, i, more);more--;i;
            } else if (arrs[L] > arrs[R]) {
                swap(arrs, --more, L);
                // 等于 num 的情况,指针left直接++
            } else {
                L++;
            }
        }
        // 由于改进后的 partition 函数，初始的时候大于 x 的区域是包含 x 的，所以在划分完之后，
        // 需要将最后一个 x 的位置与 大于 x 区域的第一个数交换，这样就实现了小于 x 的在左边，
        // 等于 x 的在中间，大于 x 的在右边
        // 也就是说 x 一开始就不参与遍历，最后通过 swap 让其归位
        swap(arrs, more, R);
        return new int[]{less + 1, more};
    }


}
