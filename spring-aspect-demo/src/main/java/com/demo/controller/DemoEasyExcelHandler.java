package com.demo.controller;

import com.demo.excel.EasyExcelExecutorContext;
import com.demo.excel.EasyExcelHandler;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

/**
 * @author zhanglong
 * @description: 描述
 * @date 2019-08-3122:23
 */
@Component
public class DemoEasyExcelHandler implements EasyExcelHandler<DemoEntity> {

    @Override
    public void handlerData( List<DemoEntity> data, Map<DemoEntity, String> errorMsg,
        EasyExcelExecutorContext easyExcelExecutorContext ) {
        System.out.println(data);
        easyExcelExecutorContext.easyExcelExecutor()
            .exportResponse(DemoEntity.class, "zhangsan", "zhangsan", errorMsg.keySet().stream().collect(
                Collectors.toList()));
    }

//    @Override
//    public void handlerData( List<? extends ExcelModel> data,
//        Map<? extends ExcelModel, String> errorMsg, EasyExcelExecutorContext response ) {
//
//    }
}
