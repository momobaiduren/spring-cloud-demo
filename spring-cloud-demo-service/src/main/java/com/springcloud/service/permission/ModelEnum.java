package com.springcloud.service.permission;

/**
 * @author zhanglong
 * @description: 权限模块枚举
 * @date 2019-08-2121:52
 */
public enum ModelEnum {
    /**
     * 公司权限
     */
    companys("companys"),
    /**
     * 采购组
     */
    purchaseGroups("purchaseGroups"),
    /**
     * 采购组织
     */
    purchaseOrgs("purchaseOrgs"),
    ;
    private String model;

    ModelEnum( String model ) {
        this.model = model;
    }

    public String model() {
        return model;
    }
}
