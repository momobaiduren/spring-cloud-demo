//package com.springcloud.datasource.config;
//
//import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
//import com.springcloud.datasource.enums.DataSourceEnum;
//import java.util.HashMap;
//import java.util.Map;
//import javax.sql.DataSource;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.stereotype.Component;
//
///**
// * @title: DataSourceConfig
// * @projectName spring-cloud-demo
// * @description: TODO
// * @author zhanglong
// * @date 2019-07-0723:44
// */
//
//@Configuration
//public class DataSourceConfig {
//  @Bean("readDataSource")
//  @ConfigurationProperties("spring.datasource.druid.read")
//  public DataSource readDataSource(){
//    return DruidDataSourceBuilder.create().build();
//  }
//
//  @Bean("writeDataSource")
//  @ConfigurationProperties("spring.datasource.druid.write")
//  public DataSource writeDataSource(){
//    return DruidDataSourceBuilder.create().build();
//  }
//
//  @Bean("dynamicDs")
//  public DataSource dynamicDs(@Qualifier("readDataSource")DataSource readDataSource, @Qualifier("writeDataSource")DataSource writeDataSource) {
//    Map<Object, Object> targetDataSources = new HashMap<>();
//    targetDataSources.put(DataSourceEnum.read, readDataSource);
//    targetDataSources.put(DataSourceEnum.write, writeDataSource);
//    return new DynamicDataSource(readDataSource, targetDataSources);
//  }
//}
