package com.springcloud.security;

import com.springcloud.cache.Cache;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author ZhangLong on 2019/10/26  10:38 上午
 * @version V1.0
 */
public final class UserContext {
    private static final ThreadLocal<SecurityUser> localUserContext = new ThreadLocal<>();

    private static final Cache<String, SecurityUser> tokenUserCache = new UserPermissionCache();

    private UserContext() {}

    /**
     * create by ZhangLong on 2019/10/26
     * description 本地缓存数据（线程内）
     */
    public static SecurityUser getUser() {
        return localUserContext.get();
    }
    /**
     * create by ZhangLong on 2019/10/26
     * description 清除缓存
     */
    public static void clear() {
        localUserContext.remove();
        tokenUserCache.clear();
    }
    /**
     * create by ZhangLong on 2019/10/26
     * description 缓存数据（服务内）
     */
    public static void saveUserOrElseCache(String token, IUserTokenService userTokenService) {
        if (Objects.isNull(tokenUserCache.get(token))){
            synchronized (UserContext.class) {
                if (Objects.isNull(tokenUserCache.get(token))){
                    SecurityUser securityUser = userTokenService.getUser(token);
                    tokenUserCache.cache(token, securityUser, 30L, TimeUnit.MINUTES);
                    localUserContext.set(securityUser);
                }
            }
        }else {
            localUserContext.set(tokenUserCache.get(token));
        }
    }
}
