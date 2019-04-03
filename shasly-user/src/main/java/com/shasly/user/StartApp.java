package com.shasly.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.shasly.user.mapper")
public class StartApp {
    public static void main(String[] args) {
        SpringApplication.run(StartApp.class,args) ;
    }
}
