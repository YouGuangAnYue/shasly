package com.shasly.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.shasly.order.mapper")
public class StartApp {
    public static void main(String[] args) {
        SpringApplication.run(StartApp.class,args) ;
    }
}
