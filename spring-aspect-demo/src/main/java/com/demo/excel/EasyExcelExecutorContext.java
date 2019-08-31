package com.demo.excel;

import javax.servlet.http.HttpServletResponse;

/**
 * @author zhanglong
 * @description: context容器
 * @date 2019-08-3122:54
 */
public class EasyExcelExecutorContext {
    private EasyExcelHandler easyExcelHandler;
    private HttpServletResponse response;

    public void setEasyExcelHandler(final EasyExcelHandler easyExcelHandler ) {
        this.easyExcelHandler = easyExcelHandler;
    }

    public void setResponse(final HttpServletResponse response ) {
        this.response = response;
    }

    public EasyExcelHandler easyExcelHandler() {
        return easyExcelHandler;
    }

    public HttpServletResponse response() {
        return response;
    }
}
