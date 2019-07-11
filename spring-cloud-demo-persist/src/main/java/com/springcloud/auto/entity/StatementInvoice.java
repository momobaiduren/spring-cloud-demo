package com.springcloud.auto.entity;

    import java.math.BigDecimal;
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
    public class StatementInvoice implements Serializable {

    private static final long serialVersionUID = 1L;

            /**
            * 发票单号
            */
    private String invoiceNo;

            /**
            * 系统税率
            */
    private BigDecimal sysTaxRate;

            /**
            * 系统价额
            */
    private BigDecimal sysAmount;

            /**
            * 供应商价额
            */
    private BigDecimal supplierAmount;

            /**
            * 价差
            */
    private BigDecimal spread;

            /**
            * 系统税额
            */
    private BigDecimal sysTaxAmount;

            /**
            * 供应商税额
            */
    private BigDecimal supplierTaxAmount;

            /**
            * 税差
            */
    private BigDecimal taxSpread;

            /**
            * 价税总额
            */
    private BigDecimal valueTaxTotal;

            /**
            * 发票票据单号
            */
    private String invoiceBillNo;

            /**
            * 发票票据编码
            */
    private String invoiceBillCode;

            /**
            * 发票票据时间
            */
    private LocalDateTime invoiceBillTime;

            /**
            * 票据税率
            */
    private BigDecimal invoiceBillTax;

            /**
            * 备注
            */
    private String invoiceRemark;

    private LocalDateTime createTime;

    private String createBy;

    private LocalDateTime updateTime;

    private String updateBy;

            /**
            * 审核状态
            */
    private Boolean invoiceStatus;


}
