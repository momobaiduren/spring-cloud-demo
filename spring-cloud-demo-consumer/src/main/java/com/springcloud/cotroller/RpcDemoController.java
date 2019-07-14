package com.springcloud.cotroller;

import com.springcloud.auto.entity.AccountInfo;
import com.springcloud.provider.IAccountInfoFeignClientApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @title: RpcDemoController
 * @projectName spring-cloud-demo
 * @description: TODO
 * @author zhanglong
 * @date 2019-07-0621:57
 */
@RestController
public class RpcDemoController {
//  @Autowired
//  private IStockInfoFeignClientApi iStockInfoFeignClientApi;
  @Autowired
  private IAccountInfoFeignClientApi iAccountInfoFeignClientApi;

  @GetMapping("/addressList/{addressCode}")
  public Object addressList(@PathVariable(value = "addressCode") String addressCode){
//    List<AddressInfoTb> addressInfoTbList = iAddressFeignClientApi.getAddressList(addressCode);
//    StockInfo stockInfo = new StockInfo();
//    stockInfo.setStockNum(10);
//    stockInfo.setProductId(1L);
//    stockInfo = iStockInfoFeignClientApi.jianStock(stockInfo);
    AccountInfo accountInfo = new AccountInfo();
    accountInfo.setUserName("zhanglong");
    accountInfo.setAccountTotal(1100);
    iAccountInfoFeignClientApi.jianAccount(accountInfo);
    return accountInfo;
  }
}
