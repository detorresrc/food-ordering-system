package com.detorresrc.foodorderingsystem.order.service.domain.event;

import com.detorresrc.foodorderingsystem.event.DomainEvent;
import com.detorresrc.foodorderingsystem.order.service.domain.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.ZonedDateTime;

@AllArgsConstructor
@Getter
public abstract class OrderEvent implements DomainEvent<Order> {
    private final Order order;
    private final ZonedDateTime createdAt;
}
