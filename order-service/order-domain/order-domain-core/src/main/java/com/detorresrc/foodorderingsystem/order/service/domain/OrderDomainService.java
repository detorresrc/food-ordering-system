package com.detorresrc.foodorderingsystem.order.service.domain;

import com.detorresrc.foodorderingsystem.event.publisher.DomainEventPublisher;
import com.detorresrc.foodorderingsystem.order.service.domain.entity.Order;
import com.detorresrc.foodorderingsystem.order.service.domain.entity.Restaurant;
import com.detorresrc.foodorderingsystem.order.service.domain.event.OrderCancelledEvent;
import com.detorresrc.foodorderingsystem.order.service.domain.event.OrderCreatedEvent;
import com.detorresrc.foodorderingsystem.order.service.domain.event.OrderPaidEvent;

import java.util.List;

public interface OrderDomainService {
    OrderCreatedEvent validateAndInitializeOrder(
        Order order,
        Restaurant restaurant,
        DomainEventPublisher<OrderCreatedEvent> orderCreatedEventDomainEventPublisher);

    OrderPaidEvent payOrder(
        Order order,
        DomainEventPublisher<OrderPaidEvent> orderPaidEventDomainEventPublisher);

    void approveOrder(Order order);

    OrderCancelledEvent cancelOrderPayment(
        Order order,
        List<String> failureMessages,
        DomainEventPublisher<OrderCancelledEvent> orderCancelledEventDomainEventPublisher);

    void cancelOrder(Order order, List<String> failureMessages);
}
