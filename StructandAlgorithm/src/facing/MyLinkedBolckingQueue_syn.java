package facing;

import java.util.ArrayList;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName MyLinkedBolckingQueue.java
 * @Description 自己手写阻塞队列  空了阻塞头弹出  ，满了阻塞尾插
 * @createTime 2021年03月15日 13:23:00
 */
public class MyLinkedBolckingQueue_syn {
    private final Object[] items;
    private int takeIndex;   //弹出下标
    private int putIndex;    //插入下标
    private int count;    //队列中元素的个数


    public MyLinkedBolckingQueue_syn(int capacity){
        if (capacity < 0){
            throw new IllegalArgumentException();
        }
        items = new Object[capacity];
    }

    //入队操作
    private void enqueue(Object e){
        items[putIndex] = e;
        if (++putIndex == items.length){// putIndex向后移一位，如果已到末尾则返回队列开头(位置0)
            putIndex = 0;
        }
        count++;
    }

    //出队操作
    private  Object dequeue(){
        Object e = items[takeIndex];// 取出takeIndex指向位置中的元素并将该位置清空
        items[takeIndex] = null;
        if (++takeIndex == items.length){
            takeIndex = 0;// takeIndex向后移一位，如果已到末尾则返回队列开头(位置0)
        }
        count--;
        return e;
    }

    public void put(Object e) throws InterruptedException{
       synchronized (this){
           while (count == items.length){
               this.wait();
           }
           // 执行入队操作，将对象e实际放入队列中
           enqueue(e);
           // 唤醒所有休眠等待的进程
           this.notifyAll();
       }
    }

    public Object take() throws InterruptedException{
        synchronized (this) {
            while (count == 0) {
                // 队列为空时进入休眠
                this.wait();
            }
            // 执行出队操作，将队列中的第一个元素弹出
            Object e = dequeue();
            // 唤醒所有休眠等待的进程
            this.notifyAll();
            return e;
        }
    }

    public static void main(String[] args) throws Exception{
        // 创建一个大小为2的阻塞队列
        final MyLinkedBolckingQueue_syn q = new MyLinkedBolckingQueue_syn(2);
        // 创建2个线程
        final int threads = 2;
        // 每个线程执行10次
        final int times = 10;
        // 线程列表，用于等待所有线程完成
        ArrayList<Thread> threadlist = new ArrayList<>();
        long starttime = System.currentTimeMillis();
        // 创建2个生产者线程，向队列中并发放入数字0到19，每个线程放入10个数字
        for (int i = 0; i < threads; i++) {
            final  int offset = i*times;
            Thread producer = new Thread(()->{
                for (int j = 0; j < times; j++) {
                    try {
                        q.put(offset+j);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            threadlist.add(producer);
            producer.start();
        }
        // 创建2个消费者线程，从队列中弹出20次数字并打印弹出的数字
        for (int i = 0; i < threads; ++i) {
            Thread consumer = new Thread(() -> {
                try {
                    for (int j = 0; j < times; ++j) {
                        Object element = q.take();
                        System.out.println(element);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            threadlist.add(consumer);
            consumer.start();
        }
// 等待所有线程执行完成
        for (Thread thread : threadlist) {
            thread.join();
        }
// 打印运行耗时
        long endTime = System.currentTimeMillis();
        System.out.println(String.format("总耗时：%.2fs", (endTime - starttime) / 1e3));
    }
}

