package com.springcloud.context;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @title: SpringTransactionContext
 * @projectName spring-cloud-demo
 * @description: TODO
 * @author zhanglong
 * @date 2019-07-1122:12
 */

public class SpringTransactionContext implements Serializable {
  private static final long serialVersionUID = -8199390103169700387L;

  private TransactionXid xid;

  private int status;

  /**
   * 附加属性.
   */
  private Map<String, String> attachments = new ConcurrentHashMap<String, String>();

  public SpringTransactionContext() {

  }
  /**
   * 构建事务上下文对像.
   * @param xid
   * @param status
   */
  public SpringTransactionContext(TransactionXid xid, int status) {
    this.xid = xid;
    this.status = status;
  }

  public void setXid(TransactionXid xid) {
    this.xid = xid;
  }

  public TransactionXid getXid() {
    return xid.clone();
  }

  public void setAttachments(Map<String, String> attachments) {
    this.attachments = attachments;
  }

  public Map<String, String> getAttachments() {
    return attachments;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public int getStatus() {
    return status;
  }
}
