package com.demo.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**

 * @projectName spring-cloud-demo
 * @description: 参数绑定的Model
 * @author zhanglong
 * @date 2019-07-3015:14
 */
@Data
public class DemoImportModel extends ReadModel{

  @ExcelProperty(value = "客户经理号", index = 0)
  private String managerNo;

  @ExcelProperty(value = "客户经理", index = 1)
  private String managerName;

  @ExcelProperty(value = "储蓄存款日均-基数", index = 2)
  private String averageDailyDeposit_jishu;

  @ExcelProperty(value = "储蓄存款日均-第一季度", index = 3)
  private String averageDailyDeposit_one;

  @ExcelProperty(value = "储蓄存款日均-第二季度", index = 4)
  private String averageDailyDeposit_two;

  @ExcelProperty(value = "储蓄存款日均-第三季度", index = 5)
  private String averageDailyDeposit_three;

  @ExcelProperty(value = "储蓄存款日均-第四季度", index = 6)
  private String averageDailyDeposit_four;

  @ExcelProperty(value = "零售营业收入-第一季度", index = 7)
  private String businessIncome_one;

  @ExcelProperty(value = "零售营业收入-第二季度", index = 8)
  private String businessIncome_two;

  @ExcelProperty(value = "零售营业收入-第三季度", index = 9)
  private String businessIncome_three;

  @ExcelProperty(value = "零售营业收入-第四季度", index = 10)
  private String businessIncome_four;

  @ExcelProperty(value = "零售中间业务收入-第一季度", index = 7)
  private String intermediateBusinessIncome_one;

  @ExcelProperty(value = "零售中间业务收入-第二季度", index = 8)
  private String intermediateBusinessIncome_two;

  @ExcelProperty(value = "零售中间业务收入-第三季度", index = 9)
  private String intermediateBusinessIncome_three;

  @ExcelProperty(value = "零售中间业务收入-第四季度", index = 10)
  private String intermediateBusinessIncome_four;

  @ExcelProperty(value = "个人金融资产-基数", index = 11)
  private String personalFinancialAssets_jishu;

  @ExcelProperty(value = "个人金融资产-第一季度", index = 12)
  private String personalFinancialAssets_one;

  @ExcelProperty(value = "个人金融资产-第二季度", index = 13)
  private String personalFinancialAssets_two;

  @ExcelProperty(value = "个人金融资产-第三季度", index = 14)
  private String personalFinancialAssets_three;

  @ExcelProperty(value = "个人金融资产-第四季度", index = 15)
  private String personalFinancialAssets_four;

  @ExcelProperty(value = "新增优质客户-基数", index = 16)
  private String newHighQualityCustomers_jishu;

  @ExcelProperty(value = "新增优质客户-第一季度", index = 17)
  private String newHighQualityCustomers_one;

  @ExcelProperty(value = "新增优质客户-第二季度", index = 18)
  private String newHighQualityCustomers_two;

  @ExcelProperty(value = "新增优质客户-第三季度", index = 19)
  private String newHighQualityCustomers_three;

  @ExcelProperty(value = "新增优质客户-第四季度", index = 20)
  private String newHighQualityCustomers_four;

  @ExcelProperty(value = "新增贵宾客户-基数", index = 21)
  private String newVIPClients_jishu;

  @ExcelProperty(value = "新增贵宾客户-第一季度", index = 22)
  private String newVIPClients_one;

  @ExcelProperty(value = "新增贵宾客户-第二季度", index = 23)
  private String newVIPClients_two;

  @ExcelProperty(value = "新增贵宾客户-第三季度", index = 24)
  private String newVIPClients_three;

  @ExcelProperty(value = "新增贵宾客户-第四季度", index = 25)
  private String newVIPClients_four;

  @ExcelProperty(value = "信用卡新增开卡量-第一季度", index = 26)
  private String creditCardOpeningQuantity_one;

  @ExcelProperty(value = "信用卡新增开卡量-第二季度", index = 27)
  private String creditCardOpeningQuantity_two;

  @ExcelProperty(value = "信用卡新增开卡量-第三季度", index = 28)
  private String creditCardOpeningQuantity_three;

  @ExcelProperty(value = "信用卡新增开卡量-第四季度", index = 29)
  private String creditCardOpeningQuantity_four;

  //这里进行校验操作
  @Override
  public String check() {
    return null;
  }
}
