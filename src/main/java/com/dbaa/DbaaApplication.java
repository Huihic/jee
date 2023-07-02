package com.dbaa;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.dbaa.dao")
@EntityScan("com.dbaa.entity")
public class DbaaApplication {

    public static void main(String[] args) {
        SpringApplication.run(DbaaApplication.class, args);
    }

}
