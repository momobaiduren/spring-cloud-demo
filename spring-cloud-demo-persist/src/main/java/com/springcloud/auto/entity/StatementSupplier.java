package com.springcloud.auto.entity;

    import java.time.LocalDateTime;
    import java.io.Serializable;
    import lombok.Data;
    import lombok.EqualsAndHashCode;
    import lombok.experimental.Accessors;

/**
* <p>
    * 
    * </p>
*
* @author zhanglong
* @since 2019-07-11
*/
    @Data
        @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    public class StatementSupplier implements Serializable {

    private static final long serialVersionUID = 1L;

            /**
            * 供应商编码
            */
    private String supplierCode
supplierCode;

            /**
            * 供应商名称
            */
    private String supplierName;

            /**
            * 供应商状态
            */
    private Boolean supplierStatus;

            /**
            * 采购组织编码
            */
    private String procurementOrganizationCode;

            /**
            * 采购组织名称
            */
    private String procurementOrganizationName;

            /**
            * 创建时间
            */
    private LocalDateTime createTime;

            /**
            * 创建者
            */
    private String createBy;

            /**
            * 修改时间
            */
    private LocalDateTime updateTime;

            /**
            * 修改者
            */
    private String updateBy;


}
