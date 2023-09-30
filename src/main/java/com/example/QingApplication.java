package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.example.mapper")
//开启事务的
@EnableTransactionManagement
//@EnableConfigurationProperties(CallbackAddressConfig.class)
public class QingApplication {

    public static void main(String[] args) {
        SpringApplication.run(QingApplication.class, args);
    }

}
