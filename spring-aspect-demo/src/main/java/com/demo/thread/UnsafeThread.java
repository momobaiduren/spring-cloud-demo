package com.demo.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author ZhangLong on 2019/10/10  9:22 下午
 * @version V1.0
 */
@Slf4j
public class UnsafeThread {
    private static int num;

    private static void add() {
        num++;
    }

    public static void main(String[] args) {
        AtomicInteger index = new AtomicInteger();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 100; j++) {
//                    index.getAndIncrement();
                    index.addAndGet(10);
                    add();
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        log.error(e.getMessage());
                    }
                    System.out.println(index.get() + ":" + num);
//                    if (index.get() != num){
//                    }
                }
            }).start();
        }

    }
}
