/*
 * ====================================================================
 * 龙果学院： www.roncoo.com （微信公众号：RonCoo_com）
 * 超级教程系列：《微服务架构的分布式事务解决方案》视频教程
 * 讲师：吴水成（水到渠成），840765167@qq.com
 * 课程地址：http://www.roncoo.com/course/view/7ae3d7eddc4742f78b0548aa8bd9ccdb
 * ====================================================================
 */
package com.springcloud.enums;

/**
 * 方法类型
 * Created by changmingxie on 11/11/15.
 */
public enum MethodType {
	
	/**
   * 根
   */
  ROOT,

  /**
   * 消费者
   */
  CONSUMER,

  /**
   * 提供者
   */
  PROVIDER,

  /**
   * 常规
   */
  NORMAL;
}
