package com.demo.computer;

import com.demo.Jdk8Api.Apple;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * created by zhanglong and since  2019/11/19  3:52 下午
 *
 * @description: 描述
 */
public class ExecuteMain {

//    public static void main( String[] args ) {
//
//        ShardingOperation.instance().run(mergeResutl->{
//            System.out.println(mergeResutl.size());
//            System.out.println("--------------");
//            mergeResutl.forEach((clss, map)->{
//                System.out.println(map.size());
//            });
//
//        }, new ShardingContext<>(MergeHandlerImpl.class, new MergeHandlerImpl(),"123"));
//    }

    public static void main( String[] args ) {
        final long x = System.currentTimeMillis();

//        CustomThreadFactory threadFactory = new CustomThreadFactory();
//        threadFactory.bindingThreadGroup(new ThreadGroup("THREAD_GROUP_NAME"), "THREAD_NAME");
//        ThreadPoolProperties threadPoolProperties = new ThreadPoolProperties();
//        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
//            threadPoolProperties.getCorePoolSize(),
//            threadPoolProperties.getMaximumPoolSize(),
//            threadPoolProperties.getKeepAliveTime(),
//            TimeUnit.SECONDS, threadPoolProperties.getWorkQuezue(), threadFactory);
//        int index = 250000;
//        int threadnum = 40;
//        CountDownLatch countDownLatch = new CountDownLatch(threadnum);
//        Map<String, List<Apple>> result = new ConcurrentHashMap<>(threadnum);
//        for (int i = 0; i < threadnum; i++) {
//            int finalI = i;
//            threadPoolExecutor.execute(threadFactory.newThread(() -> {
//                List<Apple> objects = new ArrayList<>();
//                for (int j = 0; j < index; j++) {
//                    objects.add(new Apple(BigDecimal.ONE));
//                }
//                result.put("thread-" + finalI, objects);
//                System.out.println("thread-" + finalI + ">>>>>>>>>>>>>>>" + objects.size() );
//                countDownLatch.countDown();
//            }));
//        }
//        threadPoolExecutor.shutdown();
//        while (countDownLatch.getCount() != 0) {
//            System.out.println(countDownLatch.getCount());
//            Sharding.releaseCpuSource(500L);
//        }
//        result.forEach((key,val) ->{
//            System.out.println(val.size());
//
//        });
        AtomicInteger size = new AtomicInteger();
        ShardingOperation.instance().run(result->{
            System.out.println(result.size());
            result.forEach((key, va)->{
                System.out.println("handler>>sharding>>>>>>>"+va.size());
                va.forEach((sharding, apples) ->{
                    List<Apple> appleList = (List<Apple>) apples;
                    size.set(size.get() + appleList.size());

                });
            });
        }, new ShardingContext<>(MergeHandlerImpl.class, new MergeHandlerImpl(),null));
        System.out.println(size.get());
        System.out.println("耗时："+(System.currentTimeMillis()-x));

    }


}
