package com.springcloud.excle;

import com.baomidou.mybatisplus.extension.service.IService;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.apache.poi.ss.formula.functions.T;

/**
 * @author zhanglong
 * @description: context容器 （方法是线程不安全的，如果需要并行处理需要额外加锁）
 * @date 2019-08-31 22:54
 */
public final class EasyExcelExecutorContext {


    private DataHandler dataHandler;

    private EasyExcelExecutorContextBuilder easyExcelExecutorContextBuilder;

    public EasyExcelExecutorContext(){
        this.dataHandler = new DataHandler();
        this.easyExcelExecutorContextBuilder = new EasyExcelExecutorContextBuilder();
    }

    public DataHandler dataHandler() {
        return dataHandler;
    }

    public EasyExcelExecutorContextBuilder easyExcelExecutorContextBuilder() {
        return easyExcelExecutorContextBuilder;
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

        public EasyExcelExecutorContext.DataHandler dataAdd( M model ) {
            data.add(model);
            return dataHandler;
        }

        public EasyExcelExecutorContext.DataHandler errorMsgAdd( M model, String errMsg ) {
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

        private List<IService<?>> iServiceList;

        public EasyExcelExecutorContextBuilder builderEasyExcelHandler(
            final EasyExcelHandler easyExcelHandler ) {
            this.easyExcelHandler = easyExcelHandler;
            return this;
        }

        public EasyExcelHandler easyExcelHandler() {
            return easyExcelHandler;
        }

        public List<IService<?>> iServiceList(){
            return iServiceList;
        }

        public EasyExcelExecutorContextBuilder builderIservices(final List<IService<?>> iServicesList) {
            this.iServiceList = iServicesList;
            return this;
        }
    }


}
