package com.demo.controller;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author zhanglong
 * @description: 描述
 * @date 2019-08-3122:25
 */
@Data
public class DemoEntity extends ReadModel {
    @ExcelProperty("姓名")
    private String username;
    @NotNull(message = "年龄不能为空")
    @ExcelProperty("年龄")
    private int age;

    public DemoEntity() {
    }

    public DemoEntity(String username, @NotNull(message = "年龄不能为空") int age) {
        this.username = username;
        this.age = age;
    }
}
