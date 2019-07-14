package com.springcloud.provider;

import com.springcloud.auto.entity.AccountInfo;
import com.springcloud.provider.hystrix.AccountFeignHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @title: IAccountInfoFeignClientApi
 * @projectName spring-cloud-demo
 * @description: TODO
 * @author zhanglong
 * @date 2019-07-1412:52
 */
@FeignClient(value = "spring-cloud-demo-provider-rpc",url = "http://localhost:7200", fallback = AccountFeignHystrix.class)
public interface IAccountInfoFeignClientApi {
  @PostMapping("api/account/jianAccount")
  AccountInfo jianAccount(@RequestBody AccountInfo accountInfo);
}
