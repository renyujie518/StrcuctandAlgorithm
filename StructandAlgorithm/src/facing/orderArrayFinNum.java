package facing;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName orderArrayFinNum.java
 * @Description
 * 在一个二维数组中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
 * 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数
 * 两种思路
 *     一种是：
 *     把每一行看成有序递增的数组，
 *     利用二分查找，
 *     通过遍历每一行得到答案，
 *     时间复杂度是nlogn
 *
 *
 *  另外一种思路是：
 *     利用二维数组由上到下，由左到右递增的规律，
 *     那么选取右上角或者左下角的元素a[row][col]与target进行比较，
 *     当target小于元素a[row][col]时，那么target必定在元素a所在行的左边,
 *     即col--；
 *     当target大于元素a[row][col]时，那么target必定在元素a所在列的下边,
 *     即row++；
 * @createTime 2021年08月20日 14:21:00
 */
public class orderArrayFinNum {

    public boolean Find1(int [][] array,int target) {

        for(int i=0;i<array.length;i++){
            int low=0;
            int high=array[i].length-1;
            while(low<=high){
                int mid=(low+high)/2;
                if(target>array[i][mid])
                    low=mid+1;
                else if(target<array[i][mid])
                    high=mid-1;
                else
                    return true;
            }
        }
        return false;

    }

    public boolean Find2(int [][] array,int target) {
        int row=0;
        int col=array[0].length-1;
        while(row<=array.length-1&&col>=0){
            if(target==array[row][col])
                return true;
            else if(target>array[row][col])
                row++;
            else
                col--;
        }
        return false;

    }
}
