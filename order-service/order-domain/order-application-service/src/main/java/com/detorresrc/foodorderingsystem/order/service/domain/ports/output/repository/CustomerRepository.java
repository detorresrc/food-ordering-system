package com.detorresrc.foodorderingsystem.order.service.domain.ports.output.repository;

import com.detorresrc.foodorderingsystem.order.service.domain.entity.Customer;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository {
    Optional<Customer> findCustomer(UUID customerId);
}
