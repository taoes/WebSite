package com.mafour.api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.mafour.mapper")
@SpringBootApplication(scanBasePackages = "com")
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
