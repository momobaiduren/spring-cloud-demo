package com.springcloud.datasource.config;

import com.baomidou.dynamic.datasource.DynamicDataSourceConfigure;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @title: DynamicConfiguration
 * @projectName spring-cloud-demo
 * @description: TODO
 * @author zhanglong
 * @date 2019-07-3023:26
 */

@Configuration
public class DynamicConfiguration {
  private static final String readMethodRegex = "@annotation(com.springcloud.datasource.annotation.ReadDS)";
  private static final String writeMehtodRegex = "@annotation(com.springcloud.datasource.annotation.WriteDS)";
  private static final String otherMethodRegex = "@annotation(com.springcloud.datasource.annotation.OtherDS)";
  @Value("${datasource.read.readSource}")
  private String readSource = "read";
  @Value("${datasource.write.writeSource}")
  private String writeSource = "write";
  @Value("${datasource.other.otherSource}")
  private String otherSource = "read";


  @Bean
  public DynamicDataSourceConfigure dynamicDataSourceConfigure() {
    return DynamicDataSourceConfigure.config()
        .expressionMatchers(readMethodRegex, readSource)
        .expressionMatchers(writeMehtodRegex, writeSource)
        .expressionMatchers(otherMethodRegex, otherSource);
  }
}