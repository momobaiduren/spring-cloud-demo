package com.springcloud.manager;

import com.springcloud.context.TransactionXid;
import java.io.Serializable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @title: Participant
 * @projectName spring-cloud-demo
 * @description: TODO
 * @author zhanglong
 * @date 2019-07-1122:43
 */

public class Participant implements Serializable {
  static final Logger LOG = LoggerFactory.getLogger(Participant.class.getSimpleName());

  private static final long serialVersionUID = 4127729421281425247L;

  private TransactionXid xid;

  private Terminator terminator;

  public Participant() {

  }

  public Participant(TransactionXid xid, Terminator terminator) {
    this.xid = xid;
    this.terminator = terminator;
  }

  /**
   * 回滚参与者事务（在Transaction中被调用）
   */
  public void rollback() {
    LOG.debug("==>Participant.rollback()");
    terminator.rollback();
  }

  /**
   * 提交参与者事务（在Transaction中被调用）.
   */
  public void commit() {
    LOG.debug("==>Participant.rollback()");
    terminator.commit();
  }
}
