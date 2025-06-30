package com.detorresrc.foodorderingsystem.order.service.data.access.customer.adapter;

import com.detorresrc.foodorderingsystem.order.service.data.access.customer.mapper.CustomerDataAccessMapper;
import com.detorresrc.foodorderingsystem.order.service.data.access.customer.repository.CustomerJpaRepository;
import com.detorresrc.foodorderingsystem.order.service.domain.entity.Customer;
import com.detorresrc.foodorderingsystem.order.service.domain.ports.output.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CustomerRepositoryImpl implements CustomerRepository {
    private final CustomerJpaRepository customerJpaRepository;
    private final CustomerDataAccessMapper customerDataAccessMapper;

    @Override
    public Optional<Customer> findCustomer(UUID customerId) {
        return customerJpaRepository.findById(customerId)
            .map(customerDataAccessMapper::customerEntityToCustomer);
    }
}
