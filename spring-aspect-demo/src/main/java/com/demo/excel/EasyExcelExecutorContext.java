package com.demo.excel;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhanglong
 * @description: context容器 （方法是线程不安全的，如果需要并行处理需要额外加锁）
 * @date 2019-08-31 22:54
 */
public final class EasyExcelExecutorContext {


    private DataHandler dataHandler;

    private EasyExcelHandler easyExcelHandler;


    public EasyExcelExecutorContext() {
        this.dataHandler = new DataHandler();
    }

    public DataHandler dataHandler() {
        return dataHandler;
    }


    public class DataHandler<M extends ExcelModel> {

        private List<M> data = new ArrayList<>();

        private List<M> errorData = new ArrayList<>();

        public List<M> get() {
            return data;
        }

        public List<M> errorData() {
            return errorData;
        }

        public EasyExcelExecutorContext.DataHandler dataAdd(M model) {
            data.add(model);
            return dataHandler;
        }

        public EasyExcelExecutorContext.DataHandler errorMsgAdd(M model, String errMsg) {
            if (StringUtils.isNotBlank(errMsg)){
                if (data.contains(model)) {
                    data.remove(model);
                }
                ReadModel readModel = (ReadModel) model;
                readModel.setErrorMsg(errMsg);
                errorData.add((M) readModel);
            }
            return dataHandler;
        }
    }

    public EasyExcelExecutorContext setEasyExcelHandler(
            final EasyExcelHandler easyExcelHandler) {
        this.easyExcelHandler = easyExcelHandler;
        return this;
    }


    public EasyExcelHandler easyExcelHandler() {
        return easyExcelHandler;
    }


}
