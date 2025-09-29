package com.example.stocklab_service;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.stocklab_service.repository.mapper")
public class StockLabServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(StockLabServiceApplication.class, args);
    }

}
