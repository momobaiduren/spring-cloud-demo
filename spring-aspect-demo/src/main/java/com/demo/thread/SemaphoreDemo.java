package com.demo.thread;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author ZhangLong on 2019/11/3  2:31 下午
 * @version V1.0 信号量demo
 */
public class SemaphoreDemo {
    private static Semaphore semaphore = new Semaphore(10);
    public static void main(String[] args) {
        for (int i = 0; i < 15; i++) {
            new Thread(()->{
                try {
                    //从信号量中获取线程执行权限
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName());
                    TimeUnit.SECONDS.sleep(3L);
                } catch (InterruptedException e) {
                    e.getMessage();
                }finally {
                    //线程执行完成后释放信号量
                    semaphore.release();
                }
            }).start();
        }
    }
}
