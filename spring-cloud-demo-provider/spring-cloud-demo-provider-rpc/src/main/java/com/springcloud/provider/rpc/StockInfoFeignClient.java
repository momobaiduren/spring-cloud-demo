package com.springcloud.provider.rpc;


import com.springcloud.auto.entity.StockInfo;
import com.springcloud.provider.IStockInfoFeignClientApi;
import com.springcloud.service.IStockInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhanglong
 * @since 2019-07-14
 */
@RestController
@RequestMapping("/api/stock")
public class StockInfoFeignClient implements IStockInfoFeignClientApi {
  @Autowired
  private IStockInfoService iStockInfoService;
  @Override
  public StockInfo jianStock(StockInfo stockInfo) {
    return iStockInfoService.jianStock(stockInfo);
  }
}
