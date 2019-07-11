package com.springcloud.auto.entity;

import com.baomidou.mybatisplus.annotation.TableId;
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
public class AddressInfoTb implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * id
   */
  @TableId("Id")
  private Integer Id;

  /**
   * 名字
   */
  @TableField("Name")
  private String Name;

  /**
   * 上级id
   */
  @TableField("Pid")
  private Integer Pid;


}
