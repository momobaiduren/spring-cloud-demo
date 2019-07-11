package com.springcloud.manager;

/**
 * @author zhanglong
 * @title: RecoverConfig
 * @projectName spring-cloud-demo
 * @description: TODO
 * @date 2019-07-1123:02
 */


public interface RecoverConfig {

  /**
   * 获取最大重试次数
   */
   int getMaxRetryCount();

  /**
   * 获取需要执行事务恢复的持续时间.
   */
   int getRecoverDuration();

  /**
   * 获取定时任务规则表达式.
   */
   String getCronExpression();
}
