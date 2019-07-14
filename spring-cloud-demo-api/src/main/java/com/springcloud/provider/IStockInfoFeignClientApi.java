package com.springcloud.provider;

import com.springcloud.auto.entity.StockInfo;
import com.springcloud.provider.hystrix.AccountFeignHystrix;
import com.springcloud.provider.hystrix.StockFeignHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @title: IStockInfoFeignClientApi
 * @projectName spring-cloud-demo
 * @description: TODO
 * @author zhanglong
 * @date 2019-07-1412:53
 */

@FeignClient(value = "spring-cloud-demo-provider-rpc", fallback = StockFeignHystrix.class)
public interface IStockInfoFeignClientApi {
  @PostMapping("/api/stock/jianStock")
  StockInfo jianStock(StockInfo stockInfo);
}
