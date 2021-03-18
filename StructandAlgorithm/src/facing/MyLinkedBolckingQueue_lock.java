package facing;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName MyLinkedBolckingQueue_lock.java
 * @Description
 * 在阻塞队列中，我们可以使用Object.notify()或者condition.signal()这样只唤醒一个线程的方法，但是会有一些前提条件：
 * 1.首先，在一个条件变量上等待的线程必须是同一类型的。比如一个条件变量上只有消费者线程在等待，另一个条件变量上只有生产者线程在等待。
 * 这么做的目的就是防止发生我们在插入时想唤醒的是消费者线程，但是唤醒了一个生产者线程，这个生产者线程又因为队列已满又进入了等待状态，
 * 这样我们需要唤醒的消费者线程就永远不会被唤醒了。(死锁)
 * 2.另外还有一点就是这个条件变量上等待的线程只能互斥执行，如果N个生产者线程可以同时执行，我们也就不需要一个一个唤醒了，这样反而会让效率降低。
 * !!!!!!当然，在我们的阻塞队列当中，不管是插入还是弹出操作同一时间都只能有一个线程在执行，所以自然就满足这个要求了。
 * 所以，我们只需要满足第一个要求让不同类型的线程在不同的条件变量上等待就可以了。那么具体要怎么做呢？
 * 首先，我们自然是要把原来的一个条件变量condition给拆分成两个实例变量notFull和notEmpty，这两个条件变量虽然对应于同一互斥锁，
 * 但是两个条件变量的等待和唤醒操作是完全隔离的。这两个条件变量分别代表队列未满和队列非空两个条件，
 *
 *
 * 消费者线程因为是被队列为空的情况所阻塞的，所以就应该等待队列非空条件得到满足；
 * 而生产者线程因为是被队列已满的情况所阻塞的，自然就要等待队列未满条件的成立。
 * @createTime 2021年03月15日 14:14:00
 */
public class MyLinkedBolckingQueue_lock {
    private final Object[] items;
    private int takeIndex;   //弹出下标
    private int putIndex;    //插入下标
    private int count;    //队列中元素的个数
    /** 显式锁 */
    private final ReentrantLock lock = new ReentrantLock();
    /** 锁对应的条件变量 */
    /** 队列未满的条件变量 */
    private final Condition notFull = lock.newCondition();
    /** 队列非空的条件变量 */
    private final Condition notEmpty = lock.newCondition();

    public MyLinkedBolckingQueue_lock(int capacity){
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
       lock.lockInterruptibly();
       try {
           while (count == items.length){
               notFull.await();// 队列已满时进入休眠.等待队列未满条件得到满足
           }
           // 执行入队操作，将对象e实际放入队列中
           enqueue(e);
           // 插入元素后唤醒一个等待队列非空条件成立的线程
           notEmpty.signal();
       }finally {
           lock.unlock();
       }
    }

    public Object take() throws InterruptedException{
       lock.lockInterruptibly();
       try {
           while (count == 0) {
               // 队列为空时进入休眠
               // 等待队列非空条件得到满足
               notEmpty.await();
           }
           // 执行出队操作，将队列中的第一个元素弹出
           Object e = dequeue();
           // 弹出元素后唤醒一个等待队列未满条件成立的线程
           notFull.signal();
           return e;

       }finally {
           lock.unlock();
       }
    }

    public static void main(String[] args) throws Exception{
        // 创建一个大小为2的阻塞队列
        final MyLinkedBolckingQueue_lock q = new MyLinkedBolckingQueue_lock(2);
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
