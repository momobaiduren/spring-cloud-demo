//package com.springcloud;
//
//import com.baomidou.mybatisplus.dts.DtsMeta;
//import com.baomidou.mybatisplus.dts.listener.IDtsListener;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.PlatformTransactionManager;
//
///**
// * @title: RabbitReceiver
// * @projectName spring-cloud-demo
// * @description: TODO
// * @author zhanglong
// * @date 2019-07-0817:28
// */
//
//@Component
//public class RabbitReceiver implements IDtsListener {
//  public static final Logger logger = LoggerFactory.getLogger(RabbitReceiver.class);
//
//  @Autowired
//  protected PlatformTransactionManager transactionManager;
//
//  @Override
//  public void process(DtsMeta dtsMeta) {
//    logger.info("Receiving message: {} with transaction manager: {}",
//        dtsMeta.getPayload(), transactionManager.getClass().getSimpleName());
//    /**
//     * 根据 event 处理不同业务逻辑
//     */
//    if (dtsMeta.getEvent().startsWith("Error")) {
//      throw new RuntimeException("Test receiver exception");
//    }
//  }
//}
//package com.springcloud;
//
//import com.baomidou.mybatisplus.dts.DtsMeta;
//import com.baomidou.mybatisplus.dts.listener.IDtsListener;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.PlatformTransactionManager;
//
///**
// * @title: RabbitReceiver
// * @projectName spring-cloud-demo
// * @description: TODO
// * @author zhanglong
// * @date 2019-07-0817:28
// */
//
//@Component
//public class RabbitReceiver implements IDtsListener {
//  public static final Logger logger = LoggerFactory.getLogger(RabbitReceiver.class);
//
//  @Autowired
//  protected PlatformTransactionManager transactionManager;
//
//  @Override
//  public void process(DtsMeta dtsMeta) {
//    logger.info("Receiving message: {} with transaction manager: {}",
//        dtsMeta.getPayload(), transactionManager.getClass().getSimpleName());
//    /**
//     * 根据 event 处理不同业务逻辑
//     */
//    if (dtsMeta.getEvent().startsWith("Error")) {
//      throw new RuntimeException("Test receiver exception");
//    }
//  }
//}
