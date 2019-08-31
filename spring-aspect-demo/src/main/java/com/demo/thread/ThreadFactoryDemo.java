package com.demo.thread;

import com.demo.Jdk8Api.Apple;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.SoftReferenceObjectPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolExecutorFactoryBean;

/**
 * @author zhanglong
 * @description: 多线程
 * @date 2019-08-2216:15
 */
public class ThreadFactoryDemo {

    /**
     * @describ 对象池的使用
     * @author zhanglong
     * @date 2019-08-22  16:19
     */
    private static SoftReferenceObjectPool<Apple> softReferenceObjectPool = new SoftReferenceObjectPool<>(
        new PooledObjectFactory<Apple>() {
            @Override
            public PooledObject<Apple> makeObject() throws Exception {
                return null;
            }

            @Override
            public void destroyObject( PooledObject<Apple> p ) throws Exception {

            }

            @Override
            public boolean validateObject( PooledObject<Apple> p ) {
                return false;
            }

            @Override
            public void activateObject( PooledObject<Apple> p ) throws Exception {

            }

            @Override
            public void passivateObject( PooledObject<Apple> p ) throws Exception {

            }
        });

    /**
     * @describ 软引用（对于生命周期相对比较长的对象的使用）
     * @author zhanglong
     * @date 2019-08-22  16:30
     */

    private static SoftReference<Map<String, String>> softReference = new SoftReference<>(
        new ConcurrentHashMap());

    @Autowired
    private ThreadPoolExecutorFactoryBean threadPoolExecutorFactoryBean;

    /**
     * @describ 将Thread.currentThread()作为key泛型的值作为Value存储到一个临时内存空间
     * @author zhanglong
     * @date 2019-08-22  18:50
     */

    private static ThreadLocal<Map<String, String>> threadLocalContext = new ThreadLocal<>();

    private static boolean done = false;

    private static final Map<String, String> context = new ConcurrentHashMap<>();

    private static final Map<String, String> contextTemp = new ConcurrentHashMap<>();

    public static void main( String[] args ) {
//        PooledObject<Apple> pooledObject = softReferenceObjectPool.getFactory().makeObject();
        /**
         * int corePoolSize, 核心线程数
         * int maximumPoolSize, 最大线程数
         * long keepAliveTime, 空闲线程存活时间
         * TimeUnit  unit, 时间单位
         * BlockingQueue<Runnable> workQueue, 线程等待队列
         * ThreadFactory threadFactory 线程工厂
         * RejectedExecutionHandler 拒绝策略
         * @describ 描述
         * @author zhanglong
         * @date 2019-08-22  17:19
         *
         */

//        ExecutorService executorService = Executors.newCachedThreadPool();
//        ExecutorService executorService1 = Executors
//            .newCachedThreadPool(new MyDefaultThreadFactory());
//
//        ExecutorService executorService2 = Executors.newFixedThreadPool(10);
//        ExecutorService executorService3 = Executors
//            .newFixedThreadPool(10, new MyDefaultThreadFactory());
//        executorService2.execute(new MyThread(Thread.currentThread(),new AtomicInteger(1)));
//        Executors.newScheduledThreadPool(10);
//        Executors.newScheduledThreadPool(10,new MyDefaultThreadFactory());
//
//        Executors.newSingleThreadExecutor();
//        Executors.newSingleThreadExecutor(new DefaultManagedAwareThreadFactory());
//
//        Executors.newSingleThreadScheduledExecutor();
//        Executors.newSingleThreadScheduledExecutor(new MyDefaultThreadFactory());
//
//        Executors.newWorkStealingPool();
//        Executors.newWorkStealingPool(10);

        threadLocalContext.set(contextTemp);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10, 10000L,
            TimeUnit.MILLISECONDS,
            new SynchronousQueue<Runnable>());
        context.put("1", "1");
        context.put("2", "2");
        context.put("3", "3");
        context.put("4", "4");
        context.put("5", "5");
        Runnable saveRunable = new SaveThread<>(contextTemp, context);
        saveRunable.run();
        Runnable handlerRunable = new HandlerThread<>(contextTemp, context);
        handlerRunable.run();

    }

}
