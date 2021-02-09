package facing;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName find_xiaohe.java
 * @Description 求小和
 * 在一个数组中，每一个数左边比当前数小的数累加起来，叫做这个数组的小和。
 * 求一个数组的小和。例子:[1,3,4,2,5]1左边比1小的数，没有;3左边比3小的数 （1）;4左边比4小的数（1，3）;2左边比2小的数（1）;5左边比5小的数（1，3，4，2），
 * 1、3、4、2;所以小和为1+1+3+1+1+3+4+2=16

要求 lognn
 *
 * 转换思想  求每个元素的右边有几个数比这个数大,个数为n，那么这个元素就会产生（元素*n）的小和分支 + 归并排序思想
 * @createTime 2021年02月09日 16:33:00
 */
public class find_xiaohe {
    public static void main(String[] args) {
        int[] arrs = {1,3,4,2,5};
        int result = process_sortandsum(arrs, 0, arrs.length - 1);
        System.out.println("小和的结果： "+result);

    }


    public static int process_sortandsum(int[] arrs, int L, int R){
        if (L==R){
            return 0;
        }
        int mid = L+((R-L)>>1);
        return
                process_sortandsum(arrs, L, mid)+ //左排+求左边的小和
                process_sortandsum(arrs, mid+1, R)+ //右排+求右边的小和
                 mergeandsum(arrs, L, mid, R); //合起来排再加所有的小和
    }

    public static int mergeandsum(int[] arrs,int L,int mid,int R){
        int[] temp = new int[R-L+1];//创建一个临时和原数组大小一样的临时数组
        int i= 0;
        int p1 = L;
        int p2 = mid+1;
        int res = 0;
        while (p1<=mid && p2<=R){
            res += arrs[p1]<arrs[p2] ? (R-p2+1)*arrs[p1]:0;  //参照转换思想求小和
            // 注意，这里必须保证左边严格小,这样保证在出左右有重值得时候，
            //先是右边的先放入temp(进入下面的while (p2<=R)中)，这也保证了右边一定比左边大（利用归并排序的这一优点）
            //右边所有的数不会导致小和 因为(R-p2+1)*arrs[p1] 乘的是p1
            temp[i++] = arrs[p1]< arrs[p2] ? arrs[p1++] :arrs[p2++]; //赋值完再移动指针
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
