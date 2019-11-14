//package com.demo.coroutine;
//
//import java.util.Random;
//import java.util.concurrent.ArrayBlockingQueue;
//import java.util.concurrent.BlockingQueue;
//import java.util.concurrent.CountDownLatch;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import java.util.concurrent.TimeUnit;
//import lombok.extern.slf4j.Slf4j;
//
///**
// * created by zhanglong and since  2019/11/11  11:05 上午
// * @description: 描述
// */
//@Slf4j
//public class SimpleBenchMark {
//    private CountDownLatch countDownLatch = new CountDownLatch(10000);
//
//    private Random random = new Random();
//
//    public static void main( String[] args ) throws InterruptedException {
//        new SimpleBenchMark().runBenchMark();
//    }
//
//    public void runBenchMark() throws InterruptedException {
//        final long timeMillis = System.currentTimeMillis();
//        final ExecutorService executorService = Executors.newFixedThreadPool(400);
//        for (int i = 0; i < 10000; i++) {
//            executorService.execute(this::run);
//        }
//        countDownLatch.await();
//        System.out.println(System.currentTimeMillis() - timeMillis + "ms");
//        executorService.shutdown();
//    }
//
//    private void run() {
//        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(1);
//        try {
//            blockingQueue.poll(random.nextInt(10)+100, TimeUnit.MICROSECONDS);
//        } catch (InterruptedException e) {
//            log.error(e.getMessage());
//        }
//        countDownLatch.countDown();
//    }
//
//    public void runBenchMark() throws InterruptedException {
//        final long timeMillis = System.currentTimeMillis();
//        for (int i = 0; i < 10000; i++) {
////            Mailbox
//        }
//        countDownLatch.await();
//        System.out.println(System.currentTimeMillis() - timeMillis + "ms");
//
//    }
//
//    private void run() {
//        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(1);
//        try {
//            blockingQueue.poll(random.nextInt(10)+100, TimeUnit.MICROSECONDS);
//        } catch (InterruptedException e) {
//            log.error(e.getMessage());
//        }
//        countDownLatch.countDown();
//    }
//}
