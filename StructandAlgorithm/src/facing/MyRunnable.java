package facing;

import java.util.Date;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName MyRunnable.java
 * @Description TODO
 * @createTime 2021年03月15日 14:35:00
 */
public class MyRunnable implements Runnable{
    private String command;

    public MyRunnable(String s) {
        this.command = s;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " Start. Time = " + new Date());
        processCommand();
        System.out.println(Thread.currentThread().getName() + " End. Time = " + new Date());

    }
    private void processCommand() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "MyRunnable{" +
                "command='" + command + '\'' +
                '}';
    }
}
