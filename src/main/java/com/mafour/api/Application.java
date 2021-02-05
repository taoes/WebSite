package com.mafour.api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAsync
@EnableScheduling
@MapperScan("com.mafour.api.dao.mapper")
@SpringBootApplication(scanBasePackages = "com")
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
