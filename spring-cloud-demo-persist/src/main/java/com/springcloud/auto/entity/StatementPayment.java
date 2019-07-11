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
    public class StatementPayment implements Serializable {

    private static final long serialVersionUID = 1L;

            /**
            * 付款单号
            */
    private String paymentNo;

            /**
            * 公司编码
            */
    private String companyCode;

            /**
            * 公司名称
            */
    private String companyName;

            /**
            * 供应商编码
            */
    private String supplierCode;

            /**
            * 供应商名称
            */
    private String supplierName;

            /**
            * 应付金额（本期）
            */
    private BigDecimal payableAmount;

            /**
            * 预付金额（可扣）
            */
    private String advanceAmountNo;

            /**
            * 预付金额（可扣）
            */
    private BigDecimal availableAdvanceAmount;

            /**
            * 应扣信控
            */
    private BigDecimal deductibleCreditControl;

            /**
            * 固定信控
            */
    private BigDecimal fixedCreditControl;

            /**
            * 未满足付款条件的金额
            */
    private BigDecimal unsatisfactoryPayableAmount;

            /**
            * 实扣信控
            */
    private BigDecimal actualDeductionCreditControl;

            /**
            * 实付金额（含手续费）
            */
    private BigDecimal ipfPayableAmount;

            /**
            * 电汇手续费
            */
    private BigDecimal teleTransferServiceFee;

            /**
            * 本期实付金额
            */
    private BigDecimal unipfPayableAmount;

    private LocalDateTime createTime;

    private String createBy;

    private LocalDateTime updateTime;

    private String updateBy;

    private Boolean paymentStatus;


}
