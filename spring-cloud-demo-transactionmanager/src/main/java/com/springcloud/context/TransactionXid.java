package com.springcloud.context;

import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.UUID;
import javax.transaction.xa.Xid;

/**
 * @title: TransactionXid
 * @projectName spring-cloud-demo
 * @description:
 * XId是 X/Open 事务标识符 XID 结构的 Java 映射。
 * 此接口指定三个访问器方法，以检索全局事务格式 ID、全局事务 ID 和分支限定符。
 * Xid 接口供事务管理器和资源管理器使用。此接口对应用程序不可见。
 * @author zhanglong
 * @date 2019-07-1020:20
 */

public class TransactionXid implements Xid, Serializable {

  /**
   * XID 的格式标识符
   */
  private int formatId = 1;

  /**
   * 全局事务ID.
   */
  private byte[] globalTransactionId;

  /**
   * 分支限定符.
   */
  private byte[] branchQualifier;

  public TransactionXid() {
    globalTransactionId = uuidToByteArray(UUID.randomUUID());
    branchQualifier = uuidToByteArray(UUID.randomUUID());
  }

  public TransactionXid(byte[] globalTransactionId) {
    this.globalTransactionId = globalTransactionId;
    branchQualifier = uuidToByteArray(UUID.randomUUID());
  }

  public TransactionXid(byte[] globalTransactionId, byte[] branchQualifier) {
    this.globalTransactionId = globalTransactionId;
    this.branchQualifier = branchQualifier;
  }
  /**
   * 克隆事务ID.
   */
  @Override
  public TransactionXid clone() {

    byte[] cloneGlobalTransactionId = new byte[globalTransactionId.length];
    byte[] cloneBranchQualifier = new byte[branchQualifier.length];

    System.arraycopy(globalTransactionId, 0, cloneGlobalTransactionId, 0, globalTransactionId.length);
    System.arraycopy(branchQualifier, 0, cloneBranchQualifier, 0, branchQualifier.length);

    TransactionXid clone = new TransactionXid(cloneGlobalTransactionId, cloneBranchQualifier);
    return clone;
  }
  @Override
  public int getFormatId() {
    return formatId;
  }

  @Override
  public byte[] getGlobalTransactionId() {
    return globalTransactionId;
  }

  @Override
  public byte[] getBranchQualifier() {
    return branchQualifier;
  }

  public static byte[] uuidToByteArray(UUID uuid) {
    ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
    bb.putLong(uuid.getMostSignificantBits());
    bb.putLong(uuid.getLeastSignificantBits());
    return bb.array();
  }

  public static UUID byteArrayToUUID(byte[] bytes) {
    ByteBuffer bb = ByteBuffer.wrap(bytes);
    long firstLong = bb.getLong();
    long secondLong = bb.getLong();
    return new UUID(firstLong, secondLong);
  }

}
