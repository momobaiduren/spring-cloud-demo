package com.springcloud.provider.rpc;


import com.springcloud.auto.entity.AccountInfo;
import com.springcloud.provider.IAccountInfoFeignClientApi;
import com.springcloud.service.IAccountInfoService;
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
@RequestMapping("/api/account")
public class AccountInfoFeignClient implements IAccountInfoFeignClientApi {
  @Autowired
  private IAccountInfoService iAccountInfoService;
  @Override
  public AccountInfo jianAccount(AccountInfo accountInfo) {
    return iAccountInfoService.jianAccount(accountInfo);
  }
}
