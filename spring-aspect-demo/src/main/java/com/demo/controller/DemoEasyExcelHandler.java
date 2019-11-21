package com.demo.controller;

import com.springcloud.easyExcel.ExcleData;
import org.springframework.stereotype.Component;

/**
 * @author zhanglong
 * @description: 描述
 * @date 2019-08-3122:23
 */
@Component
public class DemoEasyExcelHandler {
    public void handlerData( ExcleData<DemoEntity> excleData) {
        System.out.println(excleData.get());
        System.out.println("---------------------------");
        System.out.println(excleData.errorData());
    }
}
