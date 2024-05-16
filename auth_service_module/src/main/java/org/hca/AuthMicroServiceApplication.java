package org.hca;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AuthMicroServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthMicroServiceApplication.class);
    }
}