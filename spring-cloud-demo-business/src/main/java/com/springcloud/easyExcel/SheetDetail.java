package com.springcloud.easyExcel;

import java.util.List;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ZhangLong on 2019/11/4  10:25 上午
 * @version V1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SheetDetail<M extends ExcelModel> {
    @NotNull(message = "model类类型不能为空")
    private Class<M> mClass;
    @NotEmpty(message = "导出数据不能为空")
    private List<M> data;

    private String sheetName;

}
