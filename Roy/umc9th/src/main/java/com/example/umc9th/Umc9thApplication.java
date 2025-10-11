package com.example.umc9th;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan(basePackages = "com.example.umc9th.domain") // Entity
public class Umc9thApplication {

  public static void main(String[] args) {
    SpringApplication.run(Umc9thApplication.class, args);
  }
}
