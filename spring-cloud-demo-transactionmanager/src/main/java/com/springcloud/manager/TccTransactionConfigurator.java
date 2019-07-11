package com.springcloud.manager;

import com.springcloud.configurator.TransactionConfigurator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @title: TccTransactionConfigurator
 * @projectName spring-cloud-demo
 * @description: TODO
 * @author zhanglong
 * @date 2019-07-1122:53
 */

@Component
public class TccTransactionConfigurator implements TransactionConfigurator {
  /**
   * 事务库
   */
  @Autowired
  private TransactionRepository transactionRepository;

  /**
   * 事务恢复配置
   */
  @Autowired(required = false)
  private RecoverConfig recoverConfig = DefaultRecoverConfig.INSTANCE;

  /**
   * 根据事务配置器创建事务管理器.
   */
  private TransactionManager transactionManager = new TransactionManager(this);

  /**
   * 获取事务管理器.
   */
  @Override
  public TransactionManager getTransactionManager() {
    return transactionManager;
  }

  /**
   * 获取事务库.
   */
  @Override
  public TransactionRepository getTransactionRepository() {
    return transactionRepository;
  }

  /**
   * 获取事务恢复配置.
   * @return
   */
  @Override
  public RecoverConfig getRecoverConfig() {
    return recoverConfig;
  }
}
