package com.detorresrc.foodorderingsystem.order.service.domain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = {
    "com.detorresrc.foodorderingsystem.order.service.data.access"
})
@EntityScan(basePackages = "com.detorresrc.foodorderingsystem.order.service.data.access")
@SpringBootApplication(scanBasePackages = "com.detorresrc.foodorderingsystem")
public class OrderServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }
}
