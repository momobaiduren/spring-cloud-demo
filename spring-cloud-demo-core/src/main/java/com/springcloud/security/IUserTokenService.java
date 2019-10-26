package com.springcloud.security;

/**
 * @author ZhangLong on 2019/10/26  11:13 上午
 * @version V1.0
 */
public interface IUserTokenService {
   SecurityUser getUser(String token);
}
