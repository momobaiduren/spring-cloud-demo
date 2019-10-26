package com.springcloud.permission;


import com.springcloud.security.SecurityUser;
import com.springcloud.security.UserContext;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ZhangLong on 2019/10/22  5:39 下午
 * @version V1.0 获取用户权限信息
 */
public class PermissionAuthorityContext {

    private static final ThreadLocal<Map<PermissionAuthorityEnum, Set<String>>> currentPermissionAuthorities = new ThreadLocal<>();

    static {
        currentPermissionAuthorities.set(new ConcurrentHashMap<>());
    }

    public static Set<String> getCurrentAuthoritySet(PermissionAuthorityEnum permissionKey, Set<String> paramSet) {
        Set<String> currentAuthoritySet = currentPermissionAuthorities.get().get(permissionKey);
        if (CollectionUtils.isNotEmpty(currentAuthoritySet)) {
            currentAuthoritySet.addAll(paramSet);
        }
        return currentAuthoritySet;
    }

    public static Map<PermissionAuthorityEnum, Set<String>> getCurrentPermissionAuthorities() {
        return currentPermissionAuthorities.get();
    }

    public static void set(PermissionAuthorityEnum permissionKey) {
        SecurityUser user = UserContext.getUser();
        Set<String> val = null;
        switch (permissionKey) {
            case COMPANYCODE:
            case OPTIONAL_COMPANYCODE:
            case SUPPLIERCODE:
            case OPTIONAL_SUPPLIERCODE:
                val = user.fetchProperties(permissionKey.name());
                break;
        }
        currentPermissionAuthorities.get().put(permissionKey, val);
    }

    public static void clear() {
        currentPermissionAuthorities.get().clear();
    }

}
