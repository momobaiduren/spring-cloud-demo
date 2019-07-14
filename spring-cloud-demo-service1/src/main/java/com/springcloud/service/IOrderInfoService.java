package com.springcloud.service;

import com.springcloud.auto.entity.OrderInfo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhanglong
 * @since 2019-07-14
 */
public interface IOrderInfoService {

  OrderInfo createAndPayOrder(OrderInfo orderInfo);
}
