package com.springcloud.cotroller;

import com.springcloud.provider.IStockInfoFeignClientApi;
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
  @Autowired
  private IStockInfoFeignClientApi iStockInfoFeignClientApi;

  @GetMapping("/addressList/{addressCode}")
  public Object addressList(@PathVariable(value = "addressCode") String addressCode){
//    List<AddressInfoTb> addressInfoTbList = iAddressFeignClientApi.getAddressList(addressCode);
    return iStockInfoFeignClientApi;
  }
}
