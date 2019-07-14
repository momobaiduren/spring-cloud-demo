package com.springcloud.provider;

import com.springcloud.auto.entity.AccountInfo;
import com.springcloud.provider.hystrix.AccountFeignHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @title: IAccountInfoFeignClientApi
 * @projectName spring-cloud-demo
 * @description: TODO
 * @author zhanglong
 * @date 2019-07-1412:52
 */
@FeignClient(value = "spring-cloud-demo-provider-rpc", fallback = AccountFeignHystrix.class)
public interface IAccountInfoFeignClientApi {
  @PostMapping("api/account/jianAccount")
  AccountInfo jianAccount(AccountInfo accountInfo);
}
