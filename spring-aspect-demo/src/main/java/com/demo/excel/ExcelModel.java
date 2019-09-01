package com.demo.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author zhanglong
 * @description: 对于表格实体的一个父的实现，可以在这里提取公共部分
 * @date 2019-06-01 12:25
 */
@Data
public abstract class ExcelModel {
    @ExcelProperty("错误信息")
    String errorMsg;
}
