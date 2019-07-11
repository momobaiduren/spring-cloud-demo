package com.springcloud.manager;

import com.springcloud.context.TransactionXid;
import java.util.Date;
import java.util.List;

/**
 * @title: TransactionRepository
 * @projectName spring-cloud-demo
 * @description: TODO
 * @author zhanglong
 * @date 2019-07-1122:54
 */

public interface TransactionRepository {
  /**
   * 创建事务日志记录.
   * @param transaction
   */
  int create(Transaction transaction);

  /**
   * 更新事务日志记录.
   * @param transaction
   */
  int update(Transaction transaction);

  /**
   * 删除事务日志记录.
   * @param transaction
   */
  int delete(Transaction transaction);

  /**
   * 根据xid查找事务日志记录.
   * @param xid
   * @return
   */
  Transaction findByXid(TransactionXid xid);

  /**
   * 找出所有未处理事务日志（从某一时间点开始）.
   * @return
   */
  List<Transaction> findAllUnmodifiedSince(Date date);
}
