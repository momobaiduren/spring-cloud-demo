package com.springcloud.security;

import java.util.Set;

/**
 * @author ZhangLong on 2019/10/26  10:36 上午
 * @version V1.0
 */
public interface SecurityUser {
    String fetchToken();

    Long fetchUserId();

    String fetchUserName();

    String fetchUserAccount();

    Set<String> fetchOrganizations();

    Set<String> fetchPurchaseGroups();

    Set<String> fetchLocations();

    default Set<String> fetchProperties(String key) {
        return null;
    }
}
