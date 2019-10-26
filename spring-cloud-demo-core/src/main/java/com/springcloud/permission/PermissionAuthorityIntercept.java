package com.springcloud.permission;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ZhangLong on 2019/10/22  5:39 下午
 * @version V1.0 获取用户权限信息
 */
public class PermissionAuthorityIntercept {

    public PermissionAuthorityIntercept() {
        permissionAuthorities.set(new ConcurrentHashMap<>());
    }

    ThreadLocal<Map<String, Set<String>>> permissionAuthorities = new ThreadLocal<>();

    public Set<String> getAuthoritySet(String permissionKey) {
        return permissionAuthorities.get().get(permissionKey);
    }

    public void cachepermissionAuthorities() {
        //  TODO 获取用户权限信息
    }
}
