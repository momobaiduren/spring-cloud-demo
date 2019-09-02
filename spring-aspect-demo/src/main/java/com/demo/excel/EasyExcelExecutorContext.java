package com.demo.excel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author zhanglong
 * @description: context容器
 * @date 2019-08-3122:54
 */
public final class EasyExcelExecutorContext {


    private DataHandler dataHandler;

    private EasyExcelExecutorContextBuilder easyExcelExecutorContextBuilder;

    public EasyExcelExecutorContext( EasyExcelHandler easyExcelHandler ) {
        this.dataHandler = new DataHandler();
        this.easyExcelExecutorContextBuilder = new EasyExcelExecutorContextBuilder();
        if (Objects.isNull(easyExcelHandler)) {
            easyExcelHandler = EasyExcelHandler.DEFAULTEASYEXCELHANDLER;
        }
        easyExcelExecutorContextBuilder.bindingasyExcelHandler(easyExcelHandler);
    }


    public DataHandler dataHandler() {
        return dataHandler;
    }

    public EasyExcelExecutorContextBuilder easyExcelExecutorContextBuilder() {
        return easyExcelExecutorContextBuilder;
    }

    public class DataHandler<T extends ExcelModel> {

        private List<T> data = new ArrayList<>();

        private List<T> errorData = new ArrayList<>();

        public List<T> get() {
            return data;
        }

        public List<T> errorData() {
            return errorData;
        }

        public EasyExcelExecutorContext.DataHandler dataAdd( T model ) {
            data.add(model);
            return dataHandler;
        }

        public EasyExcelExecutorContext.DataHandler errorMsgAdd( T model, String errMsg ) {
            if (data.contains(model)) {
                data.remove(model);
            }
            model.setErrorMsg(errMsg);
            errorData.add(model);
            return dataHandler;
        }
    }

    public class EasyExcelExecutorContextBuilder {

        private EasyExcelHandler easyExcelHandler;

        public EasyExcelExecutorContextBuilder bindingasyExcelHandler(
            final EasyExcelHandler easyExcelHandler ) {
            this.easyExcelHandler = easyExcelHandler;
            return easyExcelExecutorContextBuilder;
        }

        public EasyExcelHandler easyExcelHandler() {
            return easyExcelHandler;
        }

    }


}
