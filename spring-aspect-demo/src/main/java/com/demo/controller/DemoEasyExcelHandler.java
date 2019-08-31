package com.demo.controller;

import com.demo.excel.EasyExcelHandler;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

/**
 * @author zhanglong
 * @description: 描述
 * @date 2019-08-3122:23
 */
@Component
public class DemoEasyExcelHandler<DemoEntity> implements EasyExcelHandler<DemoEntity> {

    @Override
    public void handlerData( List<DemoEntity> data, Map<DemoEntity, String> errorMsg,
        HttpServletResponse response ) {
        System.out.println(data);
        System.out.println(errorMsg);
    }
}
