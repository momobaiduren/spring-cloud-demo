package com.springcloud.easyExcel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author ZhangLong on 2019/10/16  5:56 下午
 * @version V1.0
 */
public class ExcleData<M extends ReadModel> {

    private List<M> data = new ArrayList<>();

    private List<M> errorData = new ArrayList<>();

    public List<M> get() {
        return data;
    }

    public List<M> errorData() {
        return errorData;
    }

    public void dataAdd(M model ) {
        data.add(model);
    }
    /**
     * create by ZhangLong on 2019/10/16
     * description 不要数据data/errorData循环中直接使用调用该方法,循环中进行remove可能会导致 {@link java.util.ConcurrentModificationException}
     */
    public void errorModelAdd(Map<M, String> modelAndErrMsg) {
        modelAndErrMsg.forEach((m, errMsg) -> {
            data.remove(m);
            m.setErrorMsg(errMsg);
            errorData.add(m);
        });
    }
}
