package SwordOffer;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName jiShuBeforeOushu.java
 * @Description
 * 调整数组顺序使奇数位于偶数前面
 * 需要保证奇数和奇数，偶数和偶数之间的相对位置不变
 * @createTime 2021年08月19日 20:18:00
 */
public class jiShuBeforeOushu_21 {
    //使用冒泡思想，每次都当前偶数上浮到当前最右边
    public static void jiShuBeforeOushu1(int[] nums) {
        int N = nums.length;
        for (int i = N; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (isOuShu(nums[j]) && !isOuShu(nums[j + 1])) {  //当前是偶数，后一个是奇数，交换
                    swap(nums, j, j + 1);
                }
            }
        }
    }

    private static boolean isOuShu(int x) {
        return x % 2 == 0;
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static int[] jiShuBeforeOushu2(int[] nums) {
        int jishuCount = 0;
        for (int num : nums) {
            if (!isOuShu(num)) {
                jishuCount++;
            }
        }
        //创建一个新数组
        int[] copy = nums.clone();
        int i = 0;
        int j = jishuCount;
        for (int newnum : copy) {
            if (!isOuShu(newnum)) {
                copy[i++] = newnum;
            } else {
                copy[j++] = newnum;
            }
        }
        return copy;
    }
}
