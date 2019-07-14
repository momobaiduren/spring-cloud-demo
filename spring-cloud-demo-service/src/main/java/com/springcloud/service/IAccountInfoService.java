package com.springcloud.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.springcloud.auto.entity.AccountInfo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhanglong
 * @since 2019-07-14
 */

public interface IAccountInfoService {

  AccountInfo jianAccount(AccountInfo accountInfo);
}
