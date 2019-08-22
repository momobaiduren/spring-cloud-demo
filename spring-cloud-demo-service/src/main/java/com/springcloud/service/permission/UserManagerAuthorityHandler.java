package com.springcloud.service.permission;

import java.util.List;
import javax.validation.constraints.NotNull;
import org.springframework.stereotype.Component;

/**
 * @author zhanglong
 * @description: 用户权限处理器
 * @date 2019-08-2209:03
 */
@Component
public class UserManagerAuthorityHandler implements AuthorityHandler{

    @Override
    public List<String> fetchAuthorityCodeList( @NotNull() ModelEnum authorityModelName) {
        List<String> authorityCodeList = null;
        //TODO 从用户中心获取数据
//        UserManager userManager = UserManager.init();
//        if (authorityModelName.equals(ModelEnum.companys.toString())) {
//            authorityCodeList = userManager.getUser()
//                .fetchProperties(authorityModelName.toString());
//        } else if (authorityModelName.equals(ModelEnum.purchaseGroups.toString())) {
//            authorityCodeList = userManager.getPurchaseGroupList();
//        } else if(authorityModelName.equals(ModelEnum.purchaseOrgs.toString())) {
//            authorityCodeList = userManager.getPurchaseOrgList();
//        }
        return authorityCodeList;
    }
}
