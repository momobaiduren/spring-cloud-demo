package com.springcloud.permission;

/**
 * @author ZhangLong on 2019/10/22  6:10 下午
 * @version V1.0
 */
public enum PermissionAuthorityEnum{
    COMPANYCODE("companyCode", "company_code", true),
    OPTIONAL_COMPANYCODE("companyCode", "company_code", false),
    SUPPLIERCODE("supplierCode", "company_code", true),
    OPTIONAL_SUPPLIERCODE("supplierCode", "company_code", false),
    ;

    private String premissionCode;

    private String sqlColumn;

    private boolean ismust;
    /**
     * create by ZhangLong on 2019/10/22
     * description 权限属性名称和实体类名称保持一致
     */
    PermissionAuthorityEnum(String premissionCode, String sqlColumn, boolean ismust){
        this.premissionCode = premissionCode;
        this.sqlColumn = sqlColumn;
        this.ismust = ismust;
    }

    public String getPremissionCode(){
        return premissionCode;
    }

    public String sqlColumn() {
        return sqlColumn;
    }

    public boolean ismust() {
        return ismust;
    }
}
