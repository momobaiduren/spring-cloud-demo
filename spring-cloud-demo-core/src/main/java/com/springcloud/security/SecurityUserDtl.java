package com.springcloud.security;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

/**
 * @author ZhangLong on 2019/10/26  10:36 上午
 * @version V1.0
 */
@Data
@Builder
public class SecurityUserDtl implements SecurityUser {

    private String token;

    private Long userId;

    private String userName;

    private String userAccount;

    private Set<String> organizations;

    private Set<String> purchaseGroups;

    private Set<String> locations;

    @Override
    public String fetchToken() {
        return token;
    }

    @Override
    public Long fetchUserId() {
        return userId;
    }

    @Override
    public String fetchUserName() {
        return userName;
    }

    @Override
    public String fetchUserAccount() {
        return userAccount;
    }

    @Override
    public Set<String> fetchOrganizations() {
        return organizations;
    }

    @Override
    public Set<String> fetchPurchaseGroups() {
        return purchaseGroups;
    }

    @Override
    public Set<String> fetchLocations() {
        return locations;
    }
}