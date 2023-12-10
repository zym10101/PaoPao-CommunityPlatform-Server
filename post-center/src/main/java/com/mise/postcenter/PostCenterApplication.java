package com.mise.postcenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PostCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(PostCenterApplication.class, args);
    }

}
