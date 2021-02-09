package facing;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName nixudui.java
 * @Description 逆序对问题在一个数组中，左边的数如果比右边的数大，则折两个数构成一个逆序对，请打印逆序对的个数
 * https://leetcode-cn.com/problems/shu-zu-zhong-de-ni-xu-dui-lcof/solution/shu-zu-zhong-de-ni-xu-dui-by-leetcode-solution/
 *
 * ！！！前提：左右半边都有序，利用小于最小=小于所有，大于最大=大于所有的思想
 * 逆序对产生的过程出现在并的时候，假设左，右半部分都排好序，现在要放回去，谁小谁并进去，这时候出现要开始放右边的情况，要放得数为x，
 * 此时一定代表左边所有的数都会比要放进的x大（因为左右是已经排好升序的，既然x比左边的第一个都小，那肯定比所有的都小），
 * 那我后续不可能不管左边的数了啊，左边的数之后一定会处理，这时候就产生了逆序对（左大右小），注意，我们讨论逆序对要针对原数组，递归的思路只是辅助
 * 对于原数组来说，左半部分（排好升序）此时都比x大。在位置的角度来说，x在原数组的右半边，在原数组上"右小"，这不就是个逆序对吗，逆序对个数为左边的个数
 *逆序对也可以打印出来 （左1，x),（左2，x),（左3，x)...
 *
 * 现在考虑开始放左边的情况，放左边的数y的时候，y与x的产生逆序对的羁绊已经由上步骤的x负责了，就不会再产生逆序对
 * 注意，不要忽略一点，谁小谁放入，y一定比现在比的数z（z属于右半边）小，才放进去的，别忘了，右边的是有序的啊，z是剩余右边最小的，所以y一定比右边所有的剩余数小
 * 针对原数组，在位置的角度来说，y在原数组的左半边，在原数组上"左小"，左小啥也不是，综上，放左半边的数的时候，不会产生逆序对
 *
 * 换句话说 ，只有右指针右移的时候，逆序对总数 = 原本+左边剩余元素个数
 *
 *
 *
 * @createTime 2021年02月09日 18:55:00
 */
public class nixudui {
    public static void main(String[] args) {
        int[] arrs = {2, 3, 5, 7, 1, 4, 6, 8}; //7 (2,1)(3,1)(5,1)(7,1)  (5,4)(7,4) (7,6)
        int result = nixudui(arrs);
        System.out.println("逆序对总数 = "+ result);


    }

    public static int nixudui(int[] arrs){
        if (arrs.length<2){
            return 0;
        }
        int[] copy = new int[arrs.length];  //这是个好习惯，提前问面试官原数组能不能改变
        for (int i = 0; i < arrs.length; i++) {
            copy[i] = arrs[i];
        }


        return process_nixudui(copy,0,arrs.length-1);  //这是计算逆序对的个数并排序的递归函数
    }

    private static int process_nixudui(int[] arrs, int L, int R) {
        if (L == R){
            return 0;
        }
        int mid = L + ((R - L) >> 1);
        int leftnums = process_nixudui(arrs, L, mid);
        int rightnums = process_nixudui(arrs, mid+1, R);
        int crossnums = mergeancount(arrs, L, mid, R);
        return leftnums + rightnums + crossnums;
    }

    private static int mergeancount(int[] arrs, int L, int mid, int R) {
        int[] temp = new int[R-L+1];//创建一个临时和原数组大小一样的临时数组
        //视屏里说在递归里new新数组不是特别好的选择，导致创建回收频繁+数组下标容易出错
        int p1 = L;
        int p2 = mid+1;
        int res = 0;
        int i =0;
        while (p1<=mid && p2<=R){
            res += arrs[p1]<=arrs[p2] ? p2-(mid+1):0;  //逆序对总数 = 原本+左边剩余元素个数
            temp[i++] = arrs[p1]<= arrs[p2] ? arrs[p1++] :arrs[p2++]; //赋值完再移动指针

        }
        while (p1<=mid){
            temp[i++] = arrs[p1++];//p2越界，触发这个while,把左半部分都放到临时
        }
        while (p2<=R){
            temp[i++] = arrs[p2++];//p1越界，触发这个while,把右半部分都放到临时
        }
        for (int j = 0; j < temp.length; j++) {
            arrs[L + j] = temp[j];//排序完再把临时的还给arrs
        }
        return res;
    }
}
