package com.springcloud.service.permission;//package com.yh.csx.settle.service.request;
//
///**
// * @author zhanglong
// * @title: AuthorityEnum
// * @projectName csx-b2b-settle
// * @description: 权限枚举
// * @date 2019-08-1712:42
// */
//public enum UserManagerAuthorityEnum {
//    /**
//     * 用户公司编码权限
//     */
//    CompanyCodeAuthority("company_code",
//            new FieldNameEnum[]{FieldNameEnum.CompanyCode}, ModelEnum.companys),
//    /**
//     * 采购组织编码
//     */
//    PurchaseOrgcCodeAuthority("purchase_org_code", new FieldNameEnum[]{FieldNameEnum.PurchaseOrgCode},
//            ModelEnum.purchaseOrgs),
//    /**
//     * 采购组编码
//     */
//    PurchaseGroupCodeAuthority("purchase_code", new FieldNameEnum[]{FieldNameEnum.PurchaseGroupCode},
//            ModelEnum.purchaseGroups),
//    ;
//    /**
//     * 数据库列名
//     */
//    private String columnName;
//    /**
//     * 属性列名
//     */
//    private FieldNameEnum[] fieldNames;
//    /**
//     * usermanager权限名
//     */
//    private ModelEnum modelEnum;
//
//    UserManagerAuthorityEnum(String columnName, FieldNameEnum[] fieldNames, ModelEnum modelEnum) {
//        this.columnName = columnName;
//        this.fieldNames = fieldNames;
//        this.modelEnum = modelEnum;
//    }
//
//    public String columnName() {
//        return this.columnName;
//    }
//
//    public FieldNameEnum[] fieldNames() {
//        return this.fieldNames;
//    }
//
//    public ModelEnum modelEnum() {
//        return this.modelEnum;
//    }
//
//
//    public enum FieldNameEnum {
//        /**
//         * 公司编码
//         */
//        CompanyCode("companyCode", true),
//        /**
//         * 采购组织编码
//         */
//        PurchaseOrgCode("purchaseOrgCode", true),
//        /**
//         * 采购组编码
//         */
//        PurchaseGroupCode("purchaseGroupCode", true),
//
//        /**
//         * 公司编码s
//         */
//        CompanyCodes("companyCodes", false);
//
//        private boolean isMustFull;
//        private String fileName;
//
//        FieldNameEnum(String fileName, boolean isMustFull) {
//            this.fileName = fileName;
//            this.isMustFull = isMustFull;
//        }
//
//        public boolean isMustFull() {
//            return this.isMustFull;
//        }
//
//        public String fileName() {
//            return this.fileName;
//        }
//    }
//
//    public enum ModelEnum {
//        /**
//         * 公司权限
//         */
//        companys("companys"),
//        /**
//         * 采购组
//         */
//        purchaseGroups("purchaseGroups"),
//        /**
//         * 采购组织
//         */
//        purchaseOrgs("purchaseOrgs"),
//        ;
//        private String model;
//
//        ModelEnum(String model) {
//            this.model = model;
//        }
//
//        public String model() {
//            return model;
//        }
//    }
//}
