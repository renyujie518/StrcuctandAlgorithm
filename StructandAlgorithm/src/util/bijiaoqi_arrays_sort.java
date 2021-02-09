package util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName bijiaoqi.java
 * @Description 比较器用于替代仿真平台，在很多次的随机数组的测试下观察自己写的方法和系统默认的方法是否得出的排序（针对数组数组）是否一致
 * @createTime 2021年02月08日 21:43:00
 */
public interface bijiaoqi_arrays_sort {
    // 调系统给出的默认方式
    static void comparator(int[] arr) {
        Arrays.sort(arr);
    }

    // 构造一个随机长度，值随机的整形数组
    static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());//相减更具有随机性
        }
        return arr;
    }

    // 复制一份数组
    static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    // 比较两个数组是否一致
    static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    // 打印数组
    static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // for test
    static void test(String classname) {
        int testTime = 100;
        int maxSize = 100;
        int maxValue = 100;
        Class clazz = null;
        String methodname = null;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            try {
                clazz = Class.forName(classname);
                methodname = clazz.getName().toLowerCase();
                methodname =methodname.substring(5, methodname.length());
                //System.out.println(methodname);
                Method method = clazz.getMethod(methodname, int[].class);
                Object o = clazz.getConstructor().newInstance();
                method.invoke(o, arr1);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            //selectionSort(arr1);
            comparator(arr2);//系统默认方法改造arr2
            System.out.println("本方法排序结果： ");
            printArray(arr1);
            System.out.println("系统默认方法排序结果：");
            printArray(arr2);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                break;//两者出现不一致情况说明
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }
}
