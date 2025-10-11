package com.example.umc9thspringdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Umc9thSpringDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(Umc9thSpringDemoApplication.class, args);
    }

}
