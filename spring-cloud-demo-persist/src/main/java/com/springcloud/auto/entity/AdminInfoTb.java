package com.springcloud.auto.entity;

import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author zhanglong
 * @since 2019-07-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class AdminInfoTb implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * 管理员姓名
   */
  private String adminName;

  /**
   * 管理员用户名
   */
  private String adminUsername;

  /**
   * 管理员密码
   */
  private String adminPassword;

  /**
   * 创建时间
   */
  private LocalDateTime createTime;

  /**
   * 管理员类型
   */
  private String adminType;

  /**
   * 管理员状态
   */
  private String adminStatus;

  /**
   * 管理员联系方式
   */
  private String adminTelephone;

  /**
   * 管理员邮箱
   */
  private String adminEmial;

  /**
   * 身份证号
   */
  @TableField("admin_IDCard")
  private String adminIdcard;

  /**
   * 管理员头像
   */
  private String adminHeadUrl;


}
