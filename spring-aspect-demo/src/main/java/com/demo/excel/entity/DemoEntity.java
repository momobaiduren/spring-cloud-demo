package com.demo.excel.entity;

import com.demo.excel.entity.AverageDailyDeposit;
import lombok.Data;

/**
 * @title: DemoEntity
 * @projectName spring-cloud-demo
 * @description: 对应业务的实体类
 * @author zhanglong
 * @date 2019-07-3015:32
 */

@Data
public class DemoEntity {
//  客户经理号
  private String managerNo;
//  客户经理
  private String managerName;
  //  储蓄存款日均 基数	第一季度	第二季度	第三季度	第四季度
  private AverageDailyDeposit averageDailyDeposit;
//  零售营业收入		第一季度	第二季度	第三季度	第四季度
  private BusinessIncome businessIncome;
//  零售中间业务收入	第一季度	第二季度	第三季度	第四季度
  private IntermediateBusinessIncome intermediateBusinessIncome;
//  个人金融资产				基数	第一季度	第二季度	第三季度	第四季度
  private PersonalFinancialAssets personalFinancialAssets;
//  新增优质客户					基数	第一季度	第二季度	第三季度	第四季度
  private NewHighQualityCustomers newHighQualityCustomers;
//  新增贵宾客户					基数	第一季度	第二季度	第三季度	第四季度
  private NewVIPClients newVIPClients;
//   信用卡新增开卡量	第一季度	第二季度	第三季度	第四季度
  private CreditCardOpeningQuantity creditCardOpeningQuantity;
}
