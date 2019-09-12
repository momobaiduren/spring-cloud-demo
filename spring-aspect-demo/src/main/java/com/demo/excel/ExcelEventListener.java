//package com.demo.excel;
//
//import com.alibaba.excel.context.AnalysisContext;
//import com.alibaba.excel.event.AnalysisEventListener;
//import com.demo.validation.ValidationEntityResult;
//import com.demo.validation.ValidationHandler;
//import com.demo.validation.ValidationManager;
//import java.util.Objects;
//import lombok.Data;
//import lombok.extern.slf4j.Slf4j;
//
///**
// * @author zhanglong
// * @describ 事件解析监听处理器
// * @date 2019-06-01  13:09
// */
//@Slf4j
//@Data
//public class ExcelEventListener<T extends ExcelModel> extends AnalysisEventListener<T> {
//
//    private EasyExcelExecutorContext easyExcelExecutorContext;
//
//    private int count = 0;
//
//    private int dataSize = 0;
//
//    private int errorSize = 0;
//
//    public ExcelEventListener( final EasyExcelExecutorContext easyExcelExecutorContext ) {
//        this.easyExcelExecutorContext = easyExcelExecutorContext;
//    }
//
//    @Override
//    public void invoke( T model, AnalysisContext analysisContext ) {
//        count++;
//        ValidationEntityResult<T> validationEntityResult = ValidationManager.context()
//            .validationHandler(ValidationHandler.DEFULTVALIDATIONHANDLER).validateEntity(model);
//        if (!validationEntityResult.hasError()) {
//            dataSize++;
//            easyExcelExecutorContext.dataHandler().dataAdd(model);
//        } else {
//            errorSize++;
//            easyExcelExecutorContext.dataHandler()
//                .errorMsgAdd(model, String.format("检查错误：%s", validationEntityResult.errorMsgs()));
//        }
//    }
//
//    @Override
//    public void doAfterAllAnalysed( AnalysisContext analysisContext ) {
//        String logMsg = String
//            .format("excle表格导入数据数量(条)：%d条,解析成功数量(条)：%d条,校验失败数量(条)：%d条", count, dataSize,
//                errorSize);
//        log.warn(logMsg);
//        long startTime = System.currentTimeMillis();
//        if (Objects.nonNull(easyExcelExecutorContext)) {
//            if (Objects.nonNull(easyExcelExecutorContext.easyExcelExecutorContextBind()) && Objects
//                .nonNull(easyExcelExecutorContext.easyExcelExecutorContextBind().easyExcelHandler())) {
//                easyExcelExecutorContext.easyExcelExecutorContextBind().easyExcelHandler()
//                    .handlerData(easyExcelExecutorContext.dataHandler(), easyExcelExecutorContext.easyExcelExecutorContextBind());
//            }
//        }
//        long endTime = System.currentTimeMillis();
//        log.warn(String.format("表格数据处理时长(ms):%d", endTime - startTime));
//    }
//
//}
