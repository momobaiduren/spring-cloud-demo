package com.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class SpringCloudDemoConfigApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringCloudDemoConfigApplication.class, args);
  }

}
