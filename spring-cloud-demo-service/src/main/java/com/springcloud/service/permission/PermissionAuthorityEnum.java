package com.springcloud.service.permission;

/**
 * @author ZhangLong on 2019/10/22  6:10 下午
 * @version V1.0
 */
public enum PermissionAuthorityEnum{
    COMPANYCODE("companyCode", "company_code"),
    SUPPLIERCODE("supplierCode", "company_code");

    private String  premissionCode;

    private String sqlColumn;
    /**
     * create by ZhangLong on 2019/10/22
     * description 权限属性名称和实体类名称保持一致
     */
    PermissionAuthorityEnum(String premissionCode, String sqlColumn){
        this.premissionCode = premissionCode;
        this.sqlColumn = sqlColumn;
    }

    public String getPremissionCode(){
        return premissionCode;
    }

    public String sqlColumn() {
        return sqlColumn;
    }

}
