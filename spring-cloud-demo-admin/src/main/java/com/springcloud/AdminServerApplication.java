package com.springcloud;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zhanglong
 * @title: AdminServerApplication
 * @projectName spring-cloud-demo
 * @description: TODO
 * @date 2019-08-0323:22
 */

@EnableAdminServer
@SpringBootApplication
public class AdminServerApplication {

  public static void main(String[] args) {
    SpringApplication.run(AdminServerApplication.class, args);
  }
}
