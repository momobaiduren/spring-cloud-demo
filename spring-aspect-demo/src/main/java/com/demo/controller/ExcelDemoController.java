package com.demo.controller;

import com.demo.excel.EasyExcelExecutor;
import javax.servlet.http.HttpServletResponse;
import javax.validation.groups.Default;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author zhanglong
 * @title: ExcelDemoController
 * @projectName spring-cloud-demo
 * @description: excel Demo
 * @date 2019-07-3015:00
 */
@RestController
@RequestMapping("/excel")
public class ExcelDemoController {
    @Autowired
    private DemoEasyExcelHandler<DemoEntity> demoEasyExcelHandler;

    @PostMapping("importData")
    public String importExcel(@Validated({Default.class}) MultipartFile file, HttpServletResponse response) throws RuntimeException {
        EasyExcelExecutor
            .bind(demoEasyExcelHandler,response)
            .importExcel(file, DemoEntity.class);



        //穿到后台进行处理 这里直接返回了
        return "";
    }
}
