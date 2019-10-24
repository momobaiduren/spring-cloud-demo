package com.demo.controller;

import com.demo.excel.EasyExcelExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhanglong
 * @title: ExcelDemoController
 * @projectName spring-cloud-demo
 * @description: excel Demo
 * @date 2019-07-3015:00
 */
@RestController
@RequestMapping(value = "/excel", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ExcelDemoController {
    @Autowired
    private DemoEasyExcelHandler demoEasyExcelHandler;

    @PostMapping("importData")
    public String importExcel(MultipartFile file, HttpServletResponse response) throws RuntimeException {
        EasyExcelExecutor.importExcel(easyExcel -> {demoEasyExcelHandler.handlerData(easyExcel);},file, DemoEntity.class);
//        EasyExcelExecutor.importExcelAndExportErrorData(easyExcel -> {System.out.println("不做任何处理");},file,DemoEntity.class,response);
        //穿到后台进行处理 这里直接返回了
        return "";
    }
    static List<DemoEntity> data;
    static {
        data = new ArrayList<>();
        data.add(new DemoEntity("zhangsan",23));
        data.add(new DemoEntity("lisi",24));
        data.add(new DemoEntity("wangwu",25));
    }

    @PostMapping("exportData")
    public String exportData(HttpServletResponse response) throws RuntimeException {
//        EasyExcelExecutor.importExcel(demoEasyExcelHandler,file, DemoEntity.class);
//        EasyExcelExecutor.exportResponse(DemoEntity.class, "zhanglong","zhanglong",data,response);
        //穿到后台进行处理 这里直接返回了
        return "";
    }
}
