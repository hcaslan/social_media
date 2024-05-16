package org.hca;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class UserProfileMicroServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserProfileMicroServiceApplication.class);
    }
}