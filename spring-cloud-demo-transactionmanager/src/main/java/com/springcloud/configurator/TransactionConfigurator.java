package com.springcloud.configurator;


import com.springcloud.manager.RecoverConfig;
import com.springcloud.manager.TransactionManager;
import com.springcloud.manager.TransactionRepository;

/**
 * @title: TransactionConfigurator
 * @projectName spring-cloud-demo
 * @description: TODO
 * @author zhanglong
 * @date 2019-07-1020:16
 */
public interface TransactionConfigurator {
  /**
   * 获取事务管理器.
   * @return
   */
   TransactionManager getTransactionManager();

  /**
   * 获取事务库.
   * @return
   */
  TransactionRepository getTransactionRepository();

  /**
   * 获取事务恢复配置.
   * @return
   */
  RecoverConfig getRecoverConfig();
}
