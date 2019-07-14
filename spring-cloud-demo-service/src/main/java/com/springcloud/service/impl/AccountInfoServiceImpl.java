package com.springcloud.service.impl;

import com.springcloud.auto.entity.AccountInfo;
import com.springcloud.auto.mapper.AccountInfoMapper;
import com.springcloud.service.IAccountInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhanglong
 * @since 2019-07-14
 */
@Service
public class AccountInfoServiceImpl implements IAccountInfoService {
  @Autowired
  private AccountInfoMapper accountInfoMapper;
  @Override
  public AccountInfo jianAccount(AccountInfo accountInfo) {
    AccountInfo userAccountInfo = accountInfoMapper.selectById(accountInfo.getUserName());
    userAccountInfo.setAccountTotal(userAccountInfo.getAccountTotal() - accountInfo.getAccountTotal());
    accountInfoMapper.updateById(userAccountInfo);
    return userAccountInfo;
  }
}
