package facing;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName BuLongFilter.java
 * @Description 模拟布隆过滤器
 * @createTime 2021年03月20日 21:34:00
 */
public class BuLongFilter {

    //首先设置位图
    public static void main(String[] args) {
        int a = 0;
        //一个int 4字节 1个字节8bit
        int[] arr = new int[10];  //32*10 bit
        //想取得178bit的状态
        int i= 178;
        //先定义在10个数中哪个数去找
        int numIndex = i / 32;
        //这个数有32位，具体在哪个数的第几位
        int bitIndex = i % 32;
        //拿到第178位的状态  首先把这个数右移bitIndex 位，就是把目标移到最右了，再拿出来,s不是1就是0
        int s = ((arr[numIndex] >> (bitIndex)) & 1);
        //把i位的状态改为1  （注意 其他位保持不变） 把1左移bitIndex这么多位再或arr[numIndex]
        arr[numIndex] = arr[numIndex] | (1 << (bitIndex));
        //把i位的状态改为0  （注意 其他位保持不变） 把1左移bitIndex这么多位再取反，那得到了除了第i位为0其余都是1这样的数 再与arr[numIndex]
        arr[numIndex] = arr[numIndex] & (~(1 << (bitIndex)));


    }
}
