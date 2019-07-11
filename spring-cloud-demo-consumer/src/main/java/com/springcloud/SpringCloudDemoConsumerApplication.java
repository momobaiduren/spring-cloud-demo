package com.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SpringCloudDemoConsumerApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringCloudDemoConsumerApplication.class, args);
  }

}
