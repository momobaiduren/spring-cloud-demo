package com.demo.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @Author zhanglong
 * @Date 2019-09-12  23:17
 * @Version V1.0
 */
@Data
public abstract class ReadModel extends ExcelModel{

    @ExcelProperty("错误信息")
    public String errorMsg;
}
