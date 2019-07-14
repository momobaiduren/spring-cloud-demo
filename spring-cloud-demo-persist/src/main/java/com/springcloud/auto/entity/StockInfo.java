package com.springcloud.auto.entity;

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
 * @since 2019-07-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class StockInfo implements Serializable {

  private static final long serialVersionUID = 1L;

  private Integer stockNum;

  private Long productId;


}
