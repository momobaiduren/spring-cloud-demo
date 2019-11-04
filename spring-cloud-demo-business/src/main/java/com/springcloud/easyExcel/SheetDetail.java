package com.springcloud.easyExcel;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author ZhangLong on 2019/11/4  10:25 上午
 * @version V1.0
 */
@Data
@AllArgsConstructor
public class SheetDetail<M extends ExcelModel> {
    @NotNull(message = "model类类型不能为空")
    private Class<M> mClass;
    @NotEmpty(message = "导出数据不能为空")
    private List<M> data;
    @NotBlank(message = "未定义sheet名称")
    private String sheetName;

}
