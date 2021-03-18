package facing;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName ArrayMaxAdd.java
 * @Description
 * 在一维数组中，求出连续子数组的最大和。如果数组中全是整数，
 * 那么最大和为所有元素之和，那么存在负数呢？
 * 例如:{6,-3,-2,7,-15,1,2,2},连续子向量的最大和为8(从第0个开始,到第3个为止)。
 *
 * 解题思路：
 *
 * 1. 首先，我们需要定义一个变量currentSum，用for循环来记录前i项的和，currentSum每次都会更改，
 * 如果currentSum的值小于0，我们再往后加只有减小最大和，所以我们需要将array[i+1]项的值重新赋值给currentSum。
 *
 *
 * 2. 我们需要定义一个最大值max，每次改变currentSum的值时，我们都需要将max和currentSum进行比较，
 * 如果currentSum大于max，我们则将currentSum的值赋值给max。

 * @createTime 2021年03月17日 18:20:00
 */
public class ArrayMaxAdd {

        public static int FindGreatestSumOfSubArray(int[] array) {
            if (array.length==0 || array==null) {
                return 0;
            }
            int currentSum = 0;     //存储当前连续n项的和
            int max = 0;            //存储连续子元素和的最大值
            for (int i = 0; i < array.length; i++) {
                //核心部分，好好理解.
                if(currentSum<=0){      //如过当前连续n项的和小于等于0,则没必要与后面的元素相加
                    currentSum = array[i];      //currentSum重新赋值
                }else{
                    currentSum += array[i];     //如果currentSum的值大于0,则继续与后面的元素相加,
                }
                if(currentSum>max){         //每次改变currentSum的值都有与max进行比较
                    max = currentSum;       //如果currentSum的值大于max,则将currentSum的值赋值给max
                }
            }
            return max;
        }

}
