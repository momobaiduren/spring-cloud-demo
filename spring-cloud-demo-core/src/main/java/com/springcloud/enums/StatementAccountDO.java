package com.springcloud.enums;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author zhanglong
 * @since 2019-07-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class StatementAccountDO {

    //主键
    private Long id;


    /**
     * 对账单号
     */
    private String statementNo;

    /**
     * 勾票单号
     */
    private String checkTicketNo;

    /**
     * 对账时间
     */
    private Date statementTime;

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
     * 入库金额
     */
    private BigDecimal inWarehouseAmount;

    /**
     * 退货金额
     */
    private BigDecimal outWarehouseAmount;

    /**
     * 返利配送
     */
    private BigDecimal rebateDeliveryAmount;

    /**
     * 促销扣款
     */
    private BigDecimal promotionDeductAmount;

    /**
     * 补差
     */
    private BigDecimal compensation;

    /**
     * 账扣通道费
     */
    private BigDecimal accountPassagewayAmount;

    /**
     * 代扣通道费
     */
    private BigDecimal withholdPassagewayAmount;

    /**
     * 账扣费用合计
     */
    private BigDecimal accountPassagewayTotal;

    /**
     * 对账金额
     */
    private BigDecimal statementAmount;

    /**
     * 期末累计未收账扣费用
     */
    private BigDecimal uncollectedAccountPassageway;

    /**
     * 建议开票总金额
     */
    private BigDecimal suggestiNvoiceAmount;

    /**
     * 入库金额
     */
    private BigDecimal inWarehouseTaxAmount;

    /**
     * 退货金额
     */
    private BigDecimal outWarehouseTaxAmount;

    /**
     * 返利配送
     */
    private BigDecimal rebateDeliveryTaxAmount;

    /**
     * 促销扣款
     */
    private BigDecimal promotionDeductTaxAmount;

    /**
     * 补差
     */
    private BigDecimal compensationTax;

    /**
     * 账扣通道费
     */
    private BigDecimal accountPassagewayTaxAmount;

    /**
     * 代扣通道费
     */
    private BigDecimal withholdPassagewayTaxAmount;

    /**
     * 账扣费用合计
     */
    private BigDecimal accountPassagewayTotalTax;

    /**
     * 期末累计未收账扣费用
     */
    private BigDecimal uncollectedAccountPassagewayTax;

    /**
     * 建议开票总金额
     */
    private BigDecimal suggestiNvoiceTaxAmount;

    /**
     * 蓝0点金额
     */
    private BigDecimal blueJ0Tax00;

    /**
     * 蓝13点金额
     */
    private BigDecimal blueJiTax13;

    /**
     * 蓝9点金额
     */
    private BigDecimal blueJfTax09;

    /**
     * 蓝9.574点金额
     */
    private BigDecimal blueJjTax09574;

    /**
     * 蓝9.89点金额
     */
    private BigDecimal blueJhTax0989;

    /**
     * 蓝13点金额
     */
    private BigDecimal blueJ2Tax13;

    /**
     * 蓝14.942点金额
     */
    private BigDecimal blueJ3Tax14942;

    /**
     * 红0点金额
     */
    private BigDecimal redJ0Tax00;

    /**
     * 红13点金额
     */
    private BigDecimal redJiTax13;

    /**
     * 红9点金额
     */
    private BigDecimal redJfTax09;

    /**
     * 红9.574点金额
     */
    private BigDecimal redJjTax09574;

    /**
     * 红9.89点金额
     */
    private BigDecimal redJhTax0989;

    /**
     * 红13点金额
     */
    private BigDecimal redJ2Tax13;

    /**
     * 红14.942点金额
     */
    private BigDecimal redJ3Tax14942;

    /**
     * 蓝0点金额
     */
    private BigDecimal blueJ0TaxAmount;

    /**
     * 蓝13点金额
     */
    private BigDecimal blueJiTaxAmount;

    /**
     * 蓝9点金额
     */
    private BigDecimal blueJfTaxAmount;

    /**
     * 蓝9.574点金额
     */
    private BigDecimal blueJjTaxAmount;

    /**
     * 蓝9.89点金额
     */
    private BigDecimal blueJhTaxAmount;

    /**
     * 蓝13点金额
     */
    private BigDecimal blueJ2TaxAmount;

    /**
     * 蓝14.942点金额
     */
    private BigDecimal blueJ3TaxAmount;

    /**
     * 红0点金额
     */
    private BigDecimal redJ0TaxAmount;

    /**
     * 红13点金额
     */
    private BigDecimal redJiTaxAmount;

    /**
     * 红9点金额
     */
    private BigDecimal redJfTaxAmount;

    /**
     * 红9.574点金额
     */
    private BigDecimal redJjTaxAmount;

    /**
     * 红9.89点金额
     */
    private BigDecimal redJhTaxAmount;

    /**
     * 红13点金额
     */
    private BigDecimal redJ2TaxAmount;

    /**
     * 红14.942点金额
     */
    private BigDecimal redJ3TaxAmount;

    /**
     * 对账状态0.生成对账单；1.财务确认；
     */
    private Integer statementStatus;

    /**
     * 财务对账日期
     */
    private Date financeStatementTime;

    /**
     * 实际付款单号
     */
    private String paymentNo;

    /**
     * 合并后的单号
     */
    private String mergeAccountNo;

    /**
     * 票核状态 0-发票未录入 1-发票已录入未审核 2-票核通过 3-票核拒绝
     */
    private Integer checkTicketStatus;

    /**
     * 总税额
     */
    private BigDecimal taxTotal;

    private Date createTime;

    private String createBy;

    private Date updateTime;

    private String updateBy;

    /**
     * 票核时间
     */
    private Date auditDate;

    /**
     * 账期时间
     */
    private Date accountPeriod;
    /**
     * 签章凭证
     */
    private String signatureUrl;
    /**
     * 合同编号
     */
    private String contractId;
    /**
     * 签章日期
     */
    private Date signDate;
    /**
     * 是否签章 0：未签章 1：已签章
     */
    private Integer signFlag;

    public static StatementAccountDO moneyInitStatementAccountDO() {
        StatementAccountDO statementAccount = new StatementAccountDO();
        statementAccount.redJ0Tax00 = BigDecimal.ZERO;
        statementAccount.redJ2Tax13 = BigDecimal.ZERO;
        statementAccount.redJ3Tax14942 = BigDecimal.ZERO;
        statementAccount.redJfTax09 = BigDecimal.ZERO;
        statementAccount.redJhTax0989 = BigDecimal.ZERO;
        statementAccount.redJiTax13 = BigDecimal.ZERO;
        statementAccount.redJjTax09574 = BigDecimal.ZERO;

        statementAccount.blueJ0Tax00 = BigDecimal.ZERO;
        statementAccount.blueJ2Tax13 = BigDecimal.ZERO;
        statementAccount.blueJ3Tax14942 = BigDecimal.ZERO;
        statementAccount.blueJfTax09 = BigDecimal.ZERO;
        statementAccount.blueJhTax0989 = BigDecimal.ZERO;
        statementAccount.blueJiTax13 = BigDecimal.ZERO;
        statementAccount.blueJjTax09574 = BigDecimal.ZERO;

        statementAccount.redJ0TaxAmount = BigDecimal.ZERO;
        statementAccount.redJ2TaxAmount = BigDecimal.ZERO;
        statementAccount.redJ3TaxAmount = BigDecimal.ZERO;
        statementAccount.redJfTaxAmount = BigDecimal.ZERO;
        statementAccount.redJhTaxAmount = BigDecimal.ZERO;
        statementAccount.redJiTaxAmount = BigDecimal.ZERO;
        statementAccount.redJjTaxAmount = BigDecimal.ZERO;

        statementAccount.blueJ0TaxAmount = BigDecimal.ZERO;
        statementAccount.blueJ2TaxAmount = BigDecimal.ZERO;
        statementAccount.blueJ3TaxAmount = BigDecimal.ZERO;
        statementAccount.blueJfTaxAmount = BigDecimal.ZERO;
        statementAccount.blueJhTaxAmount = BigDecimal.ZERO;
        statementAccount.blueJiTaxAmount = BigDecimal.ZERO;
        statementAccount.blueJjTaxAmount = BigDecimal.ZERO;

        statementAccount.promotionDeductAmount = BigDecimal.ZERO;
        statementAccount.statementAmount = BigDecimal.ZERO;
        statementAccount.accountPassagewayAmount = BigDecimal.ZERO;
        statementAccount.accountPassagewayTotal = BigDecimal.ZERO;
        statementAccount.compensation = BigDecimal.ZERO;
        statementAccount.inWarehouseAmount = BigDecimal.ZERO;
        statementAccount.rebateDeliveryAmount = BigDecimal.ZERO;
        statementAccount.outWarehouseAmount = BigDecimal.ZERO;
        statementAccount.withholdPassagewayAmount = BigDecimal.ZERO;
        statementAccount.uncollectedAccountPassageway = BigDecimal.ZERO;
        statementAccount.taxTotal = BigDecimal.ZERO;
        statementAccount.suggestiNvoiceAmount = BigDecimal.ZERO;

        statementAccount.inWarehouseTaxAmount = BigDecimal.ZERO;
        statementAccount.outWarehouseTaxAmount = BigDecimal.ZERO;
        statementAccount.rebateDeliveryTaxAmount = BigDecimal.ZERO;
        statementAccount.promotionDeductTaxAmount = BigDecimal.ZERO;
        statementAccount.compensationTax = BigDecimal.ZERO;
        statementAccount.accountPassagewayTaxAmount = BigDecimal.ZERO;
        statementAccount.withholdPassagewayTaxAmount = BigDecimal.ZERO;
        statementAccount.accountPassagewayTotalTax = BigDecimal.ZERO;
        statementAccount.uncollectedAccountPassagewayTax = BigDecimal.ZERO;
        statementAccount.suggestiNvoiceTaxAmount = BigDecimal.ZERO;
        return statementAccount;
    }

}
