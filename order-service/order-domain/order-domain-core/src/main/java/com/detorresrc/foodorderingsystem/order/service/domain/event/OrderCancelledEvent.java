package com.detorresrc.foodorderingsystem.order.service.domain.event;

import com.detorresrc.foodorderingsystem.order.service.domain.entity.Order;

import java.time.ZonedDateTime;


public class OrderCancelledEvent extends OrderEvent {
    public OrderCancelledEvent(Order order, ZonedDateTime createdAt) {
        super(order, createdAt);
    }
}
