package com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class SpringDemoApplication {

    public static void main( String[] args ) {
        SpringApplication.run(SpringDemoApplication.class, args);
    }

}
