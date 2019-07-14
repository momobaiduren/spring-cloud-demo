package com.springcloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@MapperScan("com.springcloud.auto.mapper")
public class SpringCloudDemoProviderBaApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringCloudDemoProviderBaApplication.class, args);
  }

}
