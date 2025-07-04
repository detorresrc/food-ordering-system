package com.detorresrc.foodorderingsystem.payment.service.domain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.detorresrc.foodorderingsystem.payment.service.data.access")
@EntityScan(basePackages = "com.detorresrc.foodorderingsystem.payment.service.data.access")
@SpringBootApplication(scanBasePackages = "com.detorresrc.foodorderingsystem")
public class PaymentServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(PaymentServiceApplication.class, args);
    }
}
