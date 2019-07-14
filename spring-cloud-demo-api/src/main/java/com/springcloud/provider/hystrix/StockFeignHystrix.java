package com.springcloud.provider.hystrix;

import com.springcloud.auto.entity.StockInfo;
import com.springcloud.provider.IStockInfoFeignClientApi;
import org.springframework.stereotype.Component;

/**
 * @title: StockFeignHystrix
 * @projectName spring-cloud-demo
 * @description: TODO
 * @author zhanglong
 * @date 2019-07-1412:54
 */

@Component
public class StockFeignHystrix implements IStockInfoFeignClientApi {

  @Override
  public StockInfo jianStock(StockInfo stockInfo) {
    return null;
  }
}
