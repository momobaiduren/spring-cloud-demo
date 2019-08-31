package com.demo.excel;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zhanglong
 * @description: 默认导入实现处理器
 * @date 2019-08-3122:07
 */
public class DefaultEasyExcelHandler<T> implements EasyExcelHandler<T>{

    @Override
    public void handlerData( List<T> data, Map<T, String> errorMsg,
        HttpServletResponse response ) {
        throw new RuntimeException("导入未实现{@EasyExcelHandler}接口");
    }
}
