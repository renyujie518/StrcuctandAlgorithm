package sort;

import util.bijiaoqi_arrays_sort;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName HeapSort.java
 * @Description 堆排序
 * @createTime 2021年02月10日 20:16:00
 *
 *  * 堆排序
 *  *
 *  * 不稳定
 *  * 时间复杂度：O(N*logN)
 *  * 空间复杂度：O(1)
 *  *
 *  * 如何定义大根堆？
 *  * 1. 所谓堆，就是一个用一维数组来表示一个完全二叉树的这么一个数据结构。
 *  * 2. 在一棵完全二叉树中，任何一棵子树的最大值都是这棵子树的头部，其形成的结构即大根堆。
 *  * 3. 不是说整棵树的最大值是这个数的头部，而是对于每一棵子树来说的。
 *  * 4. 可以使用二叉树的性质，即一个树的左孩子可以通过其索引除以 2 的方式找到它的父节点；
 *  *    一个树的右孩子可以通过其索引减一后再除以 2 的方式找到它的父节点，如下所示：
 *  *                    16-1
 *  *                    /  \
 *  *                   /    \
 *  *                 14-2   10-3
 *  *    其中以“节点值-索引值”的形式给出了一个二叉树，则节点 10 可以通过 (3-1)/2=1 的方式找到其父节点 16；
 *  *    节点 14 可通过 2/2=1 的方式找到其父节点 16。
 *  * 5. 当然，如果节点 16 的索引从 0 开始的话，则其左孩子的索引为 2*0+1=1，其右孩子的索引为 2*0+2=2。
 *
 *  构建堆的时候   左孩子  2*i+1   右孩子 2*i+2  父节点 int()
 *  *
 *  * 给定一个数组如何建立成大根堆？
 *  * 在建立大根堆的过程中，如果出现了不满足大根堆的情况，如何进行调整？
 *  * 建立大根堆的时候，每插入一个值，则只和树的高度有关。代价是 O(logN)
 *  * 第 i 个节点加入到 0~i-1 中，代价是 log(i-1)，因此，所有节点加入的代价
 *  * 就是 log1+log2+log3+...+logN-1=O(N)
 *  *
 *  * heapify 过程：
 *  * 如果在一个大根堆中，某个数字变化了，则有可能不满足大顶堆条件，则需要进行堆化处理。
 *  * 找到当前节点的左右孩子，将当前节点与左右孩子中最大的那个孩子交换，依次执行下去即可。
 *  * 当前节点的左孩子就是 2*i+1，当前节点的右孩子就是 2*i+2,父节点  (i-1)/2  其中i为下标索引
 *  *
 *  * 减堆操作：
 *  * 拿大根堆来说，将根节点与最后一个节点进行交换，然后堆的长度减一，
 *  * 此时不一定满足大根堆的要求，所以要进行调整(heapify)成大根堆。
 *  *
 *  * 堆排序：
 *  * 1. 先让待排序的数组形成一个大根堆，然后堆顶和堆中最后一个元素交换，此时最大的数就是数组中最后一个数；
 *  * 2. 交换完成后，在 0~heapSize-1 的范围里进行 heapify 的过程，重新调整成大根堆；
 *  * 3. 随着堆中元素的减少，每次减少的元素都会从数组的最后往前填充；
 *  * 4. 每次都这么做，直到堆中的元素减完，最后就将原先无序的数组排好序了。
 *  */
public class HeapSort {
    public static void main(String[] args) {
        int[] arrs = {-3,4,5,123,45,13,435,765,12,1,2,3,345,56,78,9,3};
        int[] heapsort = heapsort(arrs);
        for (int x : heapsort) {
            System.out.println(x);
        }
        //test
       // bijiaoqi_arrays_sort.test("sort.HeapSort");

    }

    //用到交换，先写出来
    private static void swap(int[] arrs,int i,int j){
        int temp = arrs[i];
        arrs[i] = arrs[j];
        arrs[j] = temp;
    }

    //对堆的增加操作(从 0~(i-1) 位置已经构成大根堆了，现在需要把第 i 的位置上的元素加进去)
    private  static void heapInsert(int[] arrs,int i){
        //如果当前元素比父节点大，交换，同时当前索引变为交换之前父节点位置
        while (arrs[i] > arrs[(i-1)/2]){
            swap(arrs,i,(i-1)/2);
             i = (i-1)/2;
        }
    }

    //此函数表示 0~heapSize-1 上已经形成了堆，由于 i 所指元素变小了，则开始进行堆化处理
    //i 所指的元素开始往下沉     heapSize 表示堆中的实际个数
    private static void heapify(int[] arrs,int i,int heapSize){
        int left = i*2+1;
        while (left<heapSize){//这里注意一点，右孩子的索引一定是大于左孩子的，所以保证了左孩子在循环内（在堆中），右孩子也没越界
            //left+1是右孩子 右孩子不越界（右比左大所以也保证了左孩子不越界）同时保证两者选大的值的索引当做lagest
            int largest = (left+1<heapSize)&&(arrs[left]<arrs[left+1]) ? left+1:left;
            largest = (arrs[largest]>arrs[i]) ? largest:i;
            //别忘了，largest是索引。当前最大值的索引，真正下沉的是值，所以还要判断当前所指i和最大值索引的对应值大小
            //以上判断了当前节点和其两孩子哪个是最大值

            //如果当前节点i发生变化后还是最大就不下沉，
            if (largest == i){
                break;
            }
            //所谓下沉，其实就是交换，把最大值给换到当前位置，构成大根堆
            swap(arrs,largest,i);
            i = largest;//传递
            left = i*2+1;//闭环
        }
    }

    private  static int[] heapsort(int[] arrs){
        if (arrs.length <2 || arrs ==null){
            return new int[0];
        }
        //建立大根堆的过程
        for (int i = 0; i < arrs.length; i++) {
            heapInsert(arrs,i);
        }
        //堆排序的实质是  不断将堆顶和堆最后交换，再下沉
        int heapSize = arrs.length;
        swap(arrs,0,--heapSize); //先换
        while (heapSize>0){
            heapify(arrs,0,heapSize);
            swap(arrs,0,--heapSize);
        }
        return arrs;

    }
}
