package facing;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName ThreadPoolExecutorDemo.java
 * @Description
 * ThreadPoolExecutor 3 个最重要的参数：
 * corePoolSize ：核心线程数，线程数定义了最小可以同时运行的线程数量。
 * maximumPoolSize ：线程池中允许存在的工作线程的最大数量
 * workQueue：当新任务来的时候会先判断当前运行的线程数量是否达到核心线程数，如果达到的话，任务就会被存放在队列中。
 * ThreadPoolExecutor
 * 其他常见参数:
 * keepAliveTime：线程池中的线程数量大于 corePoolSize 的时候，如果这时没有新的任务提交，核心线程外的线程不会立即销毁，而是会等待，直到等待的时间超过了 keepAliveTime才会被回收销毁；
 * unit ：keepAliveTime 参数的时间单位。
 * threadFactory：为线程池提供创建新线程的线程工厂
 * handler ：线程池任务队列超过 maxinumPoolSize 之后的拒绝策略
 *
 * 拒绝策略
 * ThreadPoolExecutor 饱和策略定义:
 *
 * 如果当前同时运行的线程数量达到最大线程数量并且队列也已经被放满了任时，ThreadPoolTaskExecutor 定义一些策略:
 *
 * ThreadPoolExecutor.AbortPolicy：抛出 RejectedExecutionException来拒绝新任务的处理。
 * ThreadPoolExecutor.CallerRunsPolicy：调用执行自己的线程运行任务。您不会任务请求。但是这种策略会降低对于新任务提交速度，影响程序的整体性能。另外，这个策略喜欢增加队列容量。如果您的应用程序可以承受此延迟并且你不能任务丢弃任何一个任务请求的话，你可以选择这个策略。
 * ThreadPoolExecutor.DiscardPolicy：不处理新任务，直接丢弃掉。
 * ThreadPoolExecutor.DiscardOldestPolicy： 此策略将丢弃最早的未处理的任务请求。


 *
 * corePoolSize: 核心线程数为 5。
 * maximumPoolSize ：最大线程数 10
 * keepAliveTime : 等待时间为 1L。
 * unit: 等待时间的单位为 TimeUnit.SECONDS。
 * workQueue：任务队列为 ArrayBlockingQueue，并且容量为 100;
 * handler:饱和策略为 CallerRunsPolicy。
 *
 * 示例代码：
 * ExecutorService executor = Executors.newCachedThreadPool();//创建线程池
 * Task task = new Task(); //创建Callable任务
 * Future<Integer> result = executor.submit(task);//提交任务给线程池执行
 * result.get()；//等待执行结果; 可以传入等待时间参数，指定时间内没返回的话，直接结束

 * @createTime 2021年03月15日 14:38:00
 */
public class ThreadPoolExecutorDemo {
    private static final int CORE_POOL_SIZE = 5;
    private static final int MAX_POOL_SIZE = 10;
    private static final int QUEUE_CAPACITY = 100;
    private static final Long KEEP_ALIVE_TIME = 1L;
    public static void main(String[] args) {

        //使用阿里巴巴推荐的创建线程池的方式
        //通过ThreadPoolExecutor构造函数自定义参数创建
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                CORE_POOL_SIZE,
                MAX_POOL_SIZE,
                KEEP_ALIVE_TIME,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(QUEUE_CAPACITY),
                new ThreadPoolExecutor.CallerRunsPolicy());

        for (int i = 0; i < 10; i++) {
            //创建WorkerThread对象（WorkerThread类实现了Runnable 接口）
            Runnable worker = new MyRunnable("" + i);
            //执行Runnable
            executor.execute(worker);
        }
        //终止线程池
        executor.shutdown();
        while (!executor.isTerminated()) {
        }
        System.out.println("Finished all threads");
    }

}
