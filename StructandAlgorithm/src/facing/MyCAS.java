package facing;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName MyCAS.java
 * @Description 手写CAS
 * CAS是英文单词CompareAndSwap的缩写，中文意思是：比较并替换。C
 * AS需要有3个操作数：内存地址V，旧的预期值A，即将要更新的目标值B。
 *
 * CAS指令执行时，当且仅当内存地址V的值与预期值A相等时，将内存地址V的值修改为B，否则就什么都不做。整个比较并替换的操作是一个原子操作。
 * CAS是乐观锁技术，当多个线程尝试使用CAS同时更新同一个变量时，只有其中一个线程能更新变量的值，
 * 而其它线程都失败，失败的线程并不会被挂起，而是被告知这次竞争中失败，并可以再次尝试。

 * @createTime 2021年03月15日 14:56:00
 */
public class MyCAS {
    private int value;  //内存值
    public static int count  = 0;

    public synchronized  int get(){
        return this.value;
    }

    //比较交换值
    public synchronized  int CompareAndSwap(int excepted,int newvalue){
        int oldValue = excepted;
        //如果预期值等于内存值，则可以修改
        if(excepted == value){
            value = newvalue;
        }
        //返回旧值
        return oldValue;
    }
    //判断CAS机制
    public synchronized boolean CompareAndSet(int excepted,int newvalue){
        return excepted == CompareAndSwap(excepted, newvalue);
    }

    public static void main(String[] args) {
        MyCAS cas = new MyCAS();
        for(int i=0;i<100;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    boolean flag = false;
                    while (!flag){
                        //如果没有成功 进行自旋 知道设置成功为止
                        int excepeted = cas.get();
                        flag = cas.CompareAndSet(excepeted, (int)(Math.random()));
                        System.out.println(flag);
                    }
                }
            }).start();
        }

    }

}
