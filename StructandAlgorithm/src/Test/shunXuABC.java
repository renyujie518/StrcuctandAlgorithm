package Test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author renyujie518
 * @version 1.0.0
 * @ClassName shunXuABC.java
 * @Description TODO
 * @createTime 2021年08月31日 17:59:00
 */
public class shunXuABC {
    public shunXuABC(int times) {
        this.times = times;
    }

    public static void main(String[] args) {
        shunXuABC duixiang = new shunXuABC(1);
        new Thread(() -> {
            duixiang.printABC("A", 0);
        }).start();
        new Thread(() -> {
            duixiang.printABC("B", 1);
        }).start();
        new Thread(() -> {
            duixiang.printABC("C", 2);
        }).start();

    }

    private  int state;  //当前状态值
    private Lock lock = new ReentrantLock();
    //循环次数
    private int times;


    public void printABC(String name, int target) {

        for (int i = 0; i < times; i++) {
            lock.lock();
            if (state % 3 == target) {
                state++;
                System.out.println(name);
            }
            lock.unlock();
        }
    }
}
