package com.springcloud.provider.rpc;


import com.springcloud.auto.entity.AccountInfo;
import com.springcloud.provider.IAccountInfoFeignClientApi;
import com.springcloud.service.IAccountInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
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
@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class AccountInfoFeignClient implements IAccountInfoFeignClientApi {
  @Autowired
  private IAccountInfoService iAccountInfoService;
  @Override
  public AccountInfo jianAccount(@RequestBody AccountInfo accountInfo) {
    return iAccountInfoService.jianAccount(accountInfo);
  }
}
