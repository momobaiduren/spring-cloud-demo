package com.springcloud.provider.hystrix;

import com.springcloud.auto.entity.AccountInfo;
import com.springcloud.provider.IAccountInfoFeignClientApi;
import org.springframework.stereotype.Component;

/**
 * @title: AccountFeignHystrix
 * @projectName spring-cloud-demo
 * @description: TODO
 * @author zhanglong
 * @date 2019-07-1412:52
 */
@Component
public class AccountFeignHystrix implements IAccountInfoFeignClientApi {

  @Override
  public AccountInfo jianAccount(AccountInfo accountInfo) {
    System.out.println(accountInfo);
    return accountInfo;
  }
}
