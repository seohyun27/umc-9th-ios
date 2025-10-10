package com.example.umc9th.global;

import jakarta.persistence.Entity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.umc9th") // Controller, Service 등
@EntityScan(basePackages = "com.example.umc9th.domain") // Entity
public class Umc9thApplication {

  public static void main(String[] args) {
    SpringApplication.run(Umc9thApplication.class, args);
  }
}
