package com.demo.excel;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zhanglong
 * @description: 处理器接口
 * @date 2019-08-3120:52
 */
public interface EasyExcelHandler<T> {

    EasyExcelHandler DEFAULTEASYEXCELHANDLER = new DefaultEasyExcelHandler();

    void handlerData( List<T> data, Map<T, String> errorMsg,
        HttpServletResponse response );
}
