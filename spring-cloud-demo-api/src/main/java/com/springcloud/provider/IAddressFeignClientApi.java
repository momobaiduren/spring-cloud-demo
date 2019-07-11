package com.springcloud.provider;

import com.springcloud.auto.entity.AddressInfoTb;
import com.springcloud.provider.hystrix.AddressFeignHystrix;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author zhanglong
 * @title: IAddressFeignClientApi
 * @projectName spring-cloud-demo
 * @description: TODO
 * @date 2019-07-0616:16
 */
@FeignClient(value = "spring-cloud-demo-provider-rpc", fallback = AddressFeignHystrix.class)
public interface IAddressFeignClientApi {

  @GetMapping("api/address/{addressCode}")
  List<AddressInfoTb> getAddressList(@PathVariable(value = "addressCode") String addressCode);

//  @GetMapping("/getAddress/{addressCode}")
//  List<AddressInfoTb> getAddress(@PathVariable(value = "addressCode") String addressCode);

}
