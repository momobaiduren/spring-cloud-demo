//package com.demo.controller;
//
//import com.demo.excel.EasyExcelExecutor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
//import javax.servlet.http.HttpServletResponse;
//
///**
// * @author zhanglong
// * @title: ExcelDemoController
// * @projectName spring-cloud-demo
// * @description: excel Demo
// * @date 2019-07-3015:00
// */
//@RestController
//@RequestMapping(value = "/excel", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//public class ExcelDemoController {
//    @Autowired
//    private DemoEasyExcelHandler demoEasyExcelHandler;
//
//    @PostMapping("importData")
//    public String importExcel(MultipartFile file,  HttpServletResponse response) throws RuntimeException {
//        EasyExcelExecutor
//            .instance().bindingHandler(demoEasyExcelHandler)
//            .importExcel(file, DemoEntity.class,response,true);
//
//        //穿到后台进行处理 这里直接返回了
//        return "";
//    }
//}
