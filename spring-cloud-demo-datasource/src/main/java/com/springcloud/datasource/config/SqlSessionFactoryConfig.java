//package com.springcloud.datasource.config;
//
//import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
//import com.baomidou.mybatisplus.core.MybatisConfiguration;
//import com.baomidou.mybatisplus.core.config.GlobalConfig;
//import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
//import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
//import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
//import com.springcloud.datasource.enums.DataSourceEnum;
//import java.util.HashMap;
//import java.util.Map;
//import javax.annotation.Resource;
//import javax.sql.DataSource;
//import org.apache.ibatis.plugin.Interceptor;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.apache.ibatis.type.JdbcType;
//import org.mybatis.spring.annotation.MapperScan;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
///**
// * @title: SqlSessionFactoryConfig
// * @projectName spring-cloud-demo
// * @description: TODO
// * @author zhanglong
// * @date 2019-07-0613:18
// */
////@EnableTransactionManagement
//@Configuration
//@EnableTransactionManagement
//@MapperScan("com.springcloud.auto.mapper")
//public class SqlSessionFactoryConfig {
//  private Logger logger = LoggerFactory.getLogger(SqlSessionFactoryConfig.class);
//
//  @Resource(name = "dynamicDs")
//  private DataSource dynamicDataSource;
//
////  @Autowired
////  private DataSource writeDataSource;
//
//  @Bean("sqlSessionFactory")
//  public SqlSessionFactory createSqlSessionFactory() throws Exception {
//
//    MybatisSqlSessionFactoryBean sqlSessionFactory  = null;
//
//
//
//    try {
//      // 实例SessionFactory
//      sqlSessionFactory = new MybatisSqlSessionFactoryBean();
//      // 配置数据源
//      sqlSessionFactory.setDataSource(dynamicDataSource);
//      // 加载MyBatis配置文件
//      PathMatchingResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
//      MybatisConfiguration configuration = new MybatisConfiguration();
//    //configuration.setDefaultScriptingLanguage(MybatisXMLLanguageDriver.class);
//    configuration.setJdbcTypeForNull(JdbcType.NULL);
//    configuration.setMapUnderscoreToCamelCase(true);
//    configuration.setCacheEnabled(false);
//    sqlSessionFactory.setConfiguration(configuration);
//      sqlSessionFactory.setGlobalConfig(new GlobalConfig().setDbConfig().setMe);
//    sqlSessionFactory.setPlugins(new Interceptor[]{ new PaginationInterceptor(),new OptimisticLockerInterceptor(),
////        paginationInterceptor() //添加分页功能
//    });
////    sqlSessionFactory.setGlobalConfig(new GlobalConfiguration());
//    } catch (Exception e) {
//
//      logger.error("创建SqlSession连接工厂错误：{}", e);
//
//    }
//    return sqlSessionFactory.getObject();
//  }
//
//  @Bean
//  public PlatformTransactionManager platformTransactionManager() {
//    return new DataSourceTransactionManager(dynamicDataSource);
//  }
////  @Bean("sqlSessionFactory")
////  public SqlSessionFactory sqlSessionFactory() throws Exception {
////    MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
////    sqlSessionFactory.setDataSource(multipleDataSource(db1(),db2()));
////    //sqlSessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:/mapper/*/*Mapper.xml"));
////
////    MybatisConfiguration configuration = new MybatisConfiguration();
////    //configuration.setDefaultScriptingLanguage(MybatisXMLLanguageDriver.class);
////    configuration.setJdbcTypeForNull(JdbcType.NULL);
////    configuration.setMapUnderscoreToCamelCase(true);
////    configuration.setCacheEnabled(false);
////    sqlSessionFactory.setConfiguration(configuration);
////    sqlSessionFactory.setPlugins(new Interceptor[]{ //PerformanceInterceptor(),OptimisticLockerInterceptor()
////        paginationInterceptor() //添加分页功能
////    });
////    //sqlSessionFactory.setGlobalConfig(globalConfiguration());
////    return sqlSessionFactory.getObject();
////  }
//}
