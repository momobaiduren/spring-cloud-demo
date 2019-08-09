package com.springcloud.service.impl;

import com.springcloud.auto.entity.AccountInfo;
import com.springcloud.auto.entity.OrderInfo;
import com.springcloud.auto.entity.StockInfo;
import com.springcloud.auto.mapper.OrderInfoMapper;
import com.springcloud.provider.IAccountInfoFeignClientApi;
import com.springcloud.provider.IStockInfoFeignClientApi;
import com.springcloud.service.IOrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhanglong
 * @since 2019-07-14
 */
@Service
public class OrderInfoServiceImpl implements IOrderInfoService {
  @Autowired
  private OrderInfoMapper orderInfoMapper;
  @Autowired
  private IAccountInfoFeignClientApi iAccountInfoFeignClientApi;
  @Autowired
  private IStockInfoFeignClientApi iStockInfoFeignClientApi;

  @Override
  public OrderInfo createAndPayOrder(OrderInfo orderInfo) {
    AccountInfo accountInfo = new AccountInfo();
    accountInfo.setAccountTotal(orderInfo.getProductTotal());
    accountInfo.setUserName(orderInfo.getUserName());
    iAccountInfoFeignClientApi.jianAccount(accountInfo);
    StockInfo stockInfo = new StockInfo();
    stockInfo.setProductId(orderInfo.getProductId());
    stockInfo.setStockNum(orderInfo.getProductNum());
    iStockInfoFeignClientApi.jianStock(stockInfo);
    orderInfoMapper.insert(orderInfo);
    return orderInfo;
  }
}
