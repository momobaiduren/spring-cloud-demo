package com.demo.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.util.StringUtils;
import com.demo.validation.ValidationEntityResult;
import com.demo.validation.ValidationHandler;
import com.demo.validation.ValidationManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.servlet.http.HttpServletResponse;
import lombok.Data;

/**
 *
 * @describ 事件解析监听处理器
 * @author zhanglong
 * @date 2019-06-01  13:09
 *
 */
@Data
public class ExcelEventListener<T> extends AnalysisEventListener<T> {

    private List<T> data = new ArrayList<>();
    private Map<T, String> errorMsg = new HashMap<>();

    private EasyExcelExecutorContext easyExcelExecutorContext;

    public ExcelEventListener(final EasyExcelExecutorContext easyExcelExecutorContext){
        this.easyExcelExecutorContext = easyExcelExecutorContext;
    }

    @Override
    public void invoke( T model, AnalysisContext analysisContext ) {
        ValidationEntityResult<T> validationEntityResult = ValidationManager.context()
            .validationHandler(ValidationHandler.DEFULTVALIDATIONHANDLER).validateEntity(model);
        if(!validationEntityResult.hasError()) {
            data.add(model);
        }else {
            errorMsg.put(model,String.format("检查错误：%s", validationEntityResult.errorMsgs()));
        }
    }

    @Override
    public void doAfterAllAnalysed( AnalysisContext analysisContext ) {
        if(Objects.nonNull(easyExcelExecutorContext)) {
            if(Objects.nonNull(easyExcelExecutorContext.easyExcelHandler())) {
                easyExcelExecutorContext.easyExcelHandler().handlerData(data, errorMsg, easyExcelExecutorContext.response());
            }
        }
    }

}
