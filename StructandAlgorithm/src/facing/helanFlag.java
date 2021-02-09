package facing;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName helanFlag.java
 * @Description 荷兰国旗问题(不要求顺序)
 *给定一个数组 arr 和一个数 num，需要把小于 num 的数放在数组的左边，等于 num 的数放在数组的中间，大于 num 的数放在数组的右边。
 *
 *
 * 思路：
 *  * 1. 将数组划分成小于 num 的区域、等于 num 的区域以及大于 num 的区域三部分； <  =  >
 *  * 2. cur 指向数组的当前元素(指针)，如果当前元素等于 num 的话，则 cur 直接后移；
 *  * 3. 如果当前元素小于 num，则将当前元素与小于区域的下一个元素进行交换，然后 cur++，同时小于区域要向右扩大一个位置；
 *  * 4. 如果当前元素大于 num，则将当前元素与大于区域的前一个元素进行交换，然后大于区域向左扩大一个位置。此时由于被换过来的元素不知道大小，
 *  *    所以还需要比较 小于 num 或等于 num 或大于 num。
 *  * 5. 直到 cur 与大于区域相遇的时候，流程结束。 类似一种)(夹击逼近的思想
 *
 * @createTime 2021年02月09日 23:57:00
 */
public class helanFlag {
    public static void main(String[] args) {
        int[] arrs = {5, 7,4,3,2,7,9, 7, 8, 8, 9};
        int[] result = netherlanFlag(arrs, 0, arrs.length - 1, 7);
        for (int a : result) {
            System.out.print(a); //54327778899
        }
    }


    //有个交换的过程，所以先写个交换
    private static  void swap(int[] arrs,int i,int j){
        int temp = arrs[i];
        arrs[i] = arrs[j];
        arrs[j] = temp;
    }

    //荷兰国旗问题是快排的基础
    public static  int[] netherlanFlag(int[] arrs,int L,int R,int num){
        //确立边界)(和指针的初始位置，这里把指针的初始位置放到最左侧，注意刚开始的时候会自己和自己交换，最后临近相撞也是自己和自己交换
        int less = L-1;
        int more = R+1;
        int p = L;

        while (p < more){//在右边界范围内,指针不能与右边界相遇，一旦相遇代表排序完成
            if (arrs[p]<num){//小于 num，则将当前元素与左边界  )  的下一个元素进行交换，然后 cur++
                // 同时小于区域要向右扩大一个位置（此时就把指针看的的这个数囊括了)
                swap(arrs,++less,p++);
            }else if (arrs[p]>num){
                //第二种情况：若果 cur 指向的元素比 num 大，则将 cur 所指向的元素与右边界  （  的前一个元素交换，
                // 然后 cur 不变，cur不变是由于指针还没见过这个从右边界（的前一个元素交换过来的数，需要下一次继续判断 cur 与 num 的大小
                swap(arrs,--more,p);
            }else {
                //如果 cur 指向的元素和 num 相同，则 cur 直接后移
                p++;
            }
        }
        //!!!!!!!!等于 num 区域的左边界和右边界做成一个数组返回  即  <  =  >,把中间等于区域组成一个新数组返回
        return new int[]{less + 1, more - 1};
        //return arrs;
    }

}
