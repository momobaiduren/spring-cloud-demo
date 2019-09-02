package com.springcloud.excle;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.springcloud.validation.ValidationEntityResult;
import com.springcloud.validation.ValidationHandler;
import com.springcloud.validation.ValidationManager;
import java.util.Objects;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author zhanglong
 * @describ 事件解析监听处理器
 * @date 2019-06-01  13:09
 */
@Slf4j
@Data
public class ExcelEventListener<M extends ExcelModel> extends AnalysisEventListener<M> {

    private EasyExcelExecutorContext easyExcelExecutorContext;


    public ExcelEventListener( final EasyExcelExecutorContext easyExcelExecutorContext ) {
        this.easyExcelExecutorContext = easyExcelExecutorContext;
    }

    @Override
    public void invoke( M model, AnalysisContext analysisContext ) {
        ValidationEntityResult<M> validationEntityResult = ValidationManager.context()
            .validationHandler(ValidationHandler.DEFULTVALIDATIONHANDLER).validateEntity(model);
        if (!validationEntityResult.hasError()) {
            easyExcelExecutorContext.dataHandler().dataAdd(model);
        } else {
            easyExcelExecutorContext.dataHandler()
                .errorMsgAdd(model, String.format("检查错误：%s", validationEntityResult.errorMsgs()));
        }
    }

    @Override
    public void doAfterAllAnalysed( AnalysisContext analysisContext ) {
        String logMsg = String
            .format("excle表格导入数据数量(条)：%d条,解析成功数量(条)：%d条,校验失败数量(条)：%d条",
                analysisContext.getTotalCount(),
                easyExcelExecutorContext.dataHandler().get().size(),
                easyExcelExecutorContext.dataHandler().errorData().size());
        log.warn(logMsg);
        long startTime = System.currentTimeMillis();
        if (Objects.nonNull(easyExcelExecutorContext)) {
            if (Objects.nonNull(easyExcelExecutorContext.easyExcelExecutorContextBuilder())
                && Objects
                .nonNull(easyExcelExecutorContext.easyExcelExecutorContextBuilder()
                    .easyExcelHandler())) {
                easyExcelExecutorContext.easyExcelExecutorContextBuilder().easyExcelHandler()
                    .handlerData(easyExcelExecutorContext.dataHandler());
            }
        }
        long endTime = System.currentTimeMillis();
        log.warn(String.format("表格数据处理时长(ms):%d", endTime - startTime));
    }

}
