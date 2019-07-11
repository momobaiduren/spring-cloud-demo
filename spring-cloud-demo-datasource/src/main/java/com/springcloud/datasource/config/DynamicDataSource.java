//package com.springcloud.datasource.config;
//
//import com.springcloud.datasource.holder.DataSourceContextHolder;
//import java.util.Map;
//import javax.sql.DataSource;
//import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
//import org.springframework.lang.Nullable;
//
///**
// * @title: DynamicDataSource
// * @projectName spring-cloud-demo
// * @description: TODO
// * @author zhanglong
// * @date 2019-07-0523:20
// */
//
//
//public class DynamicDataSource extends AbstractRoutingDataSource {
//
//  public DynamicDataSource(DataSource readDataSource, Map<Object, Object> targetDataSources) {
//    super.setTargetDataSources(targetDataSources);
//  }
//  @Nullable
//  @Override
//  protected Object determineCurrentLookupKey() {
//    return DataSourceContextHolder.getDatasourceType();
//  }
//
//}
