package com.demo.excel;

import java.util.List;

/**
 * @author zhanglong
 * @title: Converter
 * @projectName spring-cloud-demo
 * @description: 数据转换
 * @date 2019-07-3015:35
 */

public interface Converter<T, R> {

    List<T> convert( List<R> source );
}
