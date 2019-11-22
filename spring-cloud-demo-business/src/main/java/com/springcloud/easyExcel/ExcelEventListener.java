package com.springcloud.easyExcel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.springcloud.validation.ValidationEntityResult;
import com.springcloud.validation.ValidationManager;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author zhanglong
 * @describ 事件解析监听处理器
 * @date 2019-06-01  13:09
 */
@Slf4j
@Data
public class ExcelEventListener<M extends ReadModel> extends AnalysisEventListener<M> {


    private ExcleData<M> excleData;

    private Consumer<ExcleData<M>> excleDataConsumer;

    private Map<M, String> modelAndErrMsg;

    private boolean isResposeError;

    private Runnable runnable;

    ExcelEventListener( final Consumer<ExcleData<M>> excleDataConsumer, ExcleData<M> excleData,
        boolean isResposeError, Runnable runnable ) {
        this.excleDataConsumer = excleDataConsumer;
        this.excleData = excleData;
        this.isResposeError = isResposeError;
        this.runnable = runnable;
        modelAndErrMsg = new HashMap<>();
    }

    @Override
    public void invoke( M model, AnalysisContext analysisContext ) {
        //检查每一个需要校验的数据
        ValidationEntityResult<M> validationEntityResult = ValidationManager
            .validation(null).validateEntity(model);
        if (!validationEntityResult.hasError()) {
            excleData.dataAdd(model);
        } else {
            modelAndErrMsg.put(model, String.format("检查错误：%s", validationEntityResult.errorMsgs()));
        }
    }

    @Override
    public void doAfterAllAnalysed( AnalysisContext analysisContext ) {
        String logMsg = String
            .format("excle表格导入数据数量(条)：%d条,解析成功数量(条)：%d条,校验失败数量(条)：%d条",
                analysisContext.getTotalCount(),
                excleData.get().size(),
                excleData.errorData().size());
        log.warn(logMsg);
        long startTime = System.currentTimeMillis();
        excleData.errorModelAdd(modelAndErrMsg);
        if (Objects.nonNull(excleDataConsumer)) {
            excleDataConsumer.accept(excleData);
        }
        if (isResposeError && Objects.nonNull(runnable)) {
            runnable.run();
        }

        long endTime = System.currentTimeMillis();
        log.warn(String.format("表格数据处理时长(ms):%d", endTime - startTime));
    }

}
