package com.springcloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
@EnableEurekaClient
@SpringBootApplication
@MapperScan("com.springcloud.auto.mapper")
public class SpringCloudDemoProviderRpcApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringCloudDemoProviderRpcApplication.class, args);
  }

}
