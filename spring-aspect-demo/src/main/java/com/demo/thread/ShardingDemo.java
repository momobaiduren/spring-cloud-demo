package com.demo.thread;

import com.demo.Jdk8Api.Apple;
import com.demo.thread.computer.ListResult;
import com.demo.thread.computer.MergeHandler;
import com.demo.thread.computer.MergeModel;
import com.demo.thread.computer.ShardingOperation;
import java.util.ArrayList;

public class ShardingDemo {

    public static void main( String[] args ) {
        System.out.println(System.currentTimeMillis());
//        ShardingOperation.instance().run(new UnpaymentReportComputerHandler(), null);
//        AtomicReference<BigDecimal> resultBigd = new AtomicReference<>(BigDecimal.ZERO);
        ShardingOperation.instance().run(Apple.class, new ListResult<>(new ArrayList<>()), result -> {});
//            if (!result.getResultMapList().isEmpty()) {
//                System.out.println(result.getResultMapList().size());
//                result.getResultMapList().forEach((clazz, appleList) ->{
//                    if(CollectionUtils.isNotEmpty(appleList)) {
//                        resultBigd.set(appleList.stream().map(Apple::getPrice).reduce(BigDecimal.ZERO,BigDecimal::add).add(resultBigd.get()));
//                    }
//                });
//            }
//        }, "123", new MergeHandlerImpl());
//        System.out.println(resultBigd.get());
//        System.out.println(System.currentTimeMillis());
    }
    class MergeContext<M extends MergeModel,H extends MergeHandler, R> {
        private Class<M> mClass;

        private H MergeHandler;

        private Class<R> rClass;

        MergeContext( Class<M> mClass ) {
            this.mClass = mClass;
        }
    }
}
