package com.springcloud.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
    QueryWrapper<AccountInfo> qw = new QueryWrapper<>();
    qw.eq("user_Name",accountInfo.getUserName());
    AccountInfo userAccountInfo = accountInfoMapper.selectOne(qw);
    userAccountInfo.setAccountTotal(userAccountInfo.getAccountTotal() - accountInfo.getAccountTotal());
    accountInfoMapper.update(userAccountInfo,qw);
    return userAccountInfo;
  }
}
