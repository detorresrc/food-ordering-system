package com.detorresrc.foodorderingsystem.order.service.domain.entity;

import com.detorresrc.foodorderingsystem.entity.AggregateRoot;
import com.detorresrc.foodorderingsystem.valueobject.CustomerId;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Customer extends AggregateRoot<CustomerId> {
    public Customer(CustomerId customerId) {
        super.setId(customerId);
    }
}
