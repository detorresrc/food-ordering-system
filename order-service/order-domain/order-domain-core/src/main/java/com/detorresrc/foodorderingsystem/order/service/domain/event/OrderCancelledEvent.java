package com.detorresrc.foodorderingsystem.order.service.domain.event;

import com.detorresrc.foodorderingsystem.event.publisher.DomainEventPublisher;
import com.detorresrc.foodorderingsystem.order.service.domain.entity.Order;

import java.time.ZonedDateTime;


public class OrderCancelledEvent extends OrderEvent {
    private final DomainEventPublisher<OrderCancelledEvent> orderCancelledEventDomainEventPublisher;

    public OrderCancelledEvent(Order order, ZonedDateTime createdAt, DomainEventPublisher<OrderCancelledEvent> orderCancelledEventDomainEventPublisher) {
        super(order, createdAt);
        this.orderCancelledEventDomainEventPublisher = orderCancelledEventDomainEventPublisher;
    }

    @Override
    public void fire() {
        this.orderCancelledEventDomainEventPublisher.publish(this);
    }
}
