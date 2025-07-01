package com.detorresrc.foodorderingsystem.restaurant.service.domain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = {
    "com.detorresrc.foodorderingsystem.restaurant.service.data.access",
    "com.detorresrc.foodorderingsystem.system.data.access"
})
@EntityScan(basePackages = {
    "com.detorresrc.foodorderingsystem.restaurant.service.data.access",
    "com.detorresrc.foodorderingsystem.system.data.access"
})
@SpringBootApplication(scanBasePackages = "com.detorresrc.foodorderingsystem")
public class RestaurantServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(RestaurantServiceApplication.class, args);
    }
}
