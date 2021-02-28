package util;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName swap.java
 * @Description 工具类  交换数组的两个元素
 * @createTime 2021年02月27日 19:45:00
 */
 public interface swap {
    public static <T> void swap(T[] list,int i,int j){
        T temp = list[i];
        list[i]= list[j];
        list[j] = temp;
    }
}
