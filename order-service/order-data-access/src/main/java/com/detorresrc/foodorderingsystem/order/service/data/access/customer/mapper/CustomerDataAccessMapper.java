package com.detorresrc.foodorderingsystem.order.service.data.access.customer.mapper;

import com.detorresrc.foodorderingsystem.order.service.data.access.customer.entity.CustomerEntity;
import com.detorresrc.foodorderingsystem.order.service.domain.entity.Customer;
import com.detorresrc.foodorderingsystem.valueobject.CustomerId;
import org.springframework.stereotype.Component;

@Component
public class CustomerDataAccessMapper {
    public Customer customerEntityToCustomer(CustomerEntity customer) {
        return new Customer(new CustomerId(customer.getId()));
    }
}
