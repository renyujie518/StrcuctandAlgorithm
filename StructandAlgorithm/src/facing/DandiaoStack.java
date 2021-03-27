package facing;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName DandiaoStsck.java
 * @Description 在数组中想找到一个数，左边和右边比这个数小、且离这个数最近的位置。
 *
 * 单调栈（栈里放index）
 * 如果想找左右边比这个大，栈底到栈顶从大到小
 * 如果想找左右边比这个小，栈底到栈顶从小到大   这里先已找大的为例
 *
 * 当前数y一旦破坏单调性 只要有数据弹出了，比如弹出x 则此时左边离得最近比x大的数就是x在栈中压着的数，右边离得最近比x大的数就是当前数（迫使x弹出的y）
 * 要是到栈底最后一个数了，那就是左边离得最近比x大的数为null,栈弹完了，再把当前数y压栈。最后进入清算阶段（遍历完栈里还有数）
 * 左边离得最近比x大的数还是x在栈中压着的数，但右边离得最近比x大的数null
 *
 * 每个数据只进栈出栈只有1次，所以O(N)
 *
 *实质：组中有没有重复值：弹出的时候。y到x之间的数绝对没有大于x的（要保证栈的单调，否则一出现就会释放x），所以y就是比x大还在右边最近的数
 *                     x压着z,说明z比x早进栈（一定在左侧，）然后又要维持栈栈底到栈顶从大到小，所以下面的z会大于上面的x，而且还离得最近
 *     数组中有重复值: 下标压在一起即可（栈中放的是一个个的小链表，一旦遇到重复，连接到链表后）左边离得最近比x大的数就是链表中最后一个位置
 * @createTime 2021年03月22日 21:47:00
 */
public class DandiaoStack {
    //无重复值的 找小的 所以栈要维持栈底到栈顶从小到大
    public static int[][] getNearLessNoRepeat(int[] arr) {
        int[][] res = new int[arr.length][2];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {//一旦出现当前值比栈顶小
                int popIndex = stack.pop();  //弹出
                int leftLessIndex = stack.isEmpty() ? -1 : stack.peek(); //压着的数也有可能遇到"栈底"边界
                res[popIndex][0] = leftLessIndex;//左边离得最近比x小的数就是x在栈中压着的数,别忘了，上一个行刚pop，所以直接peek即可
                res[popIndex][1] = i;//右边离得最近比x大的数就是当前数
            }
            stack.push(i);
        }
        ///清算阶段
        while (!stack.isEmpty()) {
            int popIndex = stack.pop();
            int leftLessIndex = stack.isEmpty() ? -1 : stack.peek();
            res[popIndex][0] = leftLessIndex;
            res[popIndex][1] = -1;
        }
        return res;
    }
    //有重复值的 找小的 所以栈要维持栈底到栈顶从小到大  同时栈里存放链表
    public static int[][] getNearLess(int[] arr) {
        int[][] res = new int[arr.length][2];
        Stack<List<Integer>> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek().get(0)] > arr[i]) {//一旦出现当前值比栈顶小，
                // get(0)其实是迷惑，因为链表里虽然index不一样，但值一样，取出get(0),其实是比较早之前放入的，刚好符合栈单调的要求
                List<Integer> popIs = stack.pop();  //弹出来是个链表
                // 取位于下面位置的列表中，最晚加入的那个（也是排在链表最后的那个，size() - 1位置）
                int leftLessIndex = stack.isEmpty() ? -1 : stack.peek().get(
                        stack.peek().size() - 1);////左边离得最近比x小的数就是x在栈中压着的数
                for (Integer popi : popIs) {
                    res[popi][0] = leftLessIndex;
                    res[popi][1] = i;//右边离得最近比x大的数就是当前数
                }
            }
            if (!stack.isEmpty() && arr[stack.peek().get(0)] == arr[i]) {//一旦出现当前值=栈顶,将当前值i放入栈中压着的数的链表里
                stack.peek().add(Integer.valueOf(i));
            } else {//一旦出现当前值比栈顶大，建链表，加值，入栈
                ArrayList<Integer> list = new ArrayList<>();
                list.add(i);
                stack.push(list);
            }
        }
        ///清算阶段
        while (!stack.isEmpty()) {
            List<Integer> popIs = stack.pop();
            // 取位于下面位置的列表中，最晚加入的那个
            int leftLessIndex = stack.isEmpty() ? -1 : stack.peek().get(
                    stack.peek().size() - 1);
            for (Integer popi : popIs) {
                res[popi][0] = leftLessIndex;
                res[popi][1] = -1;
            }
        }
        return res;
    }

    // for test
    public static int[] getRandomArrayNoRepeat(int size) {
        int[] arr = new int[(int) (Math.random() * size) + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
        for (int i = 0; i < arr.length; i++) {
            int swapIndex = (int) (Math.random() * arr.length);
            int tmp = arr[swapIndex];
            arr[swapIndex] = arr[i];
            arr[i] = tmp;
        }
        return arr;
    }

    // for test
    public static int[] getRandomArray(int size, int max) {
        int[] arr = new int[(int) (Math.random() * size) + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * max) - (int) (Math.random() * max);
        }
        return arr;
    }

    // for test
    public static int[][] rightWay(int[] arr) {
        int[][] res = new int[arr.length][2];
        for (int i = 0; i < arr.length; i++) {
            int leftLessIndex = -1;
            int rightLessIndex = -1;
            int cur = i - 1;
            while (cur >= 0) {
                if (arr[cur] < arr[i]) {
                    leftLessIndex = cur;
                    break;
                }
                cur--;
            }
            cur = i + 1;
            while (cur < arr.length) {
                if (arr[cur] < arr[i]) {
                    rightLessIndex = cur;
                    break;
                }
                cur++;
            }
            res[i][0] = leftLessIndex;
            res[i][1] = rightLessIndex;
        }
        return res;
    }

    // for test
    public static boolean isEqual(int[][] res1, int[][] res2) {
        if (res1.length != res2.length) {
            return false;
        }
        for (int i = 0; i < res1.length; i++) {
            if (res1[i][0] != res2[i][0] || res1[i][1] != res2[i][1]) {
                return false;
            }
        }

        return true;
    }

    // for test
    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int size = 10;
        int max = 20;
        int testTimes = 2000000;
        for (int i = 0; i < testTimes; i++) {
            int[] arr1 = getRandomArrayNoRepeat(size);
            int[] arr2 = getRandomArray(size, max);
            if (!isEqual(getNearLessNoRepeat(arr1), rightWay(arr1))) {
                System.out.println("Oops!");
                printArray(arr1);
                break;
            }
            if (!isEqual(getNearLess(arr2), rightWay(arr2))) {
                System.out.println("Oops!");
                printArray(arr2);
                break;
            }
        }
    }
}
