//package com.springcloud;
//
//import com.baomidou.mybatisplus.dts.EnableDtsRabbit;
//import org.springframework.amqp.rabbit.connection.ConnectionFactory;
//import org.springframework.amqp.rabbit.core.RabbitAdmin;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.Bean;
//
///**
// * @title: DtsRabbitApplication
// * @projectName spring-cloud-demo
// * @description: TODO
// * @author zhanglong
// * @date 2019-07-0817:17
// */
//
//@EnableDtsRabbit
//@SpringBootApplication
//public class DtsRabbitApplication {
//  @Bean
//  public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
//    return new RabbitAdmin(connectionFactory);
//  }
//
//  public static void main(String[] args) {
//    SpringApplication.run(DtsRabbitApplication.class, args);
//  }
//}
