package com.demo.excel;

import java.util.List;

/**
 * @title: Converter
 * @projectName spring-cloud-demo
 * @description: 数据转换
 * @author zhanglong
 * @date 2019-07-3015:35
 */

public interface Converter<T, R> {

  List<T> convert(List<R> source);
}
