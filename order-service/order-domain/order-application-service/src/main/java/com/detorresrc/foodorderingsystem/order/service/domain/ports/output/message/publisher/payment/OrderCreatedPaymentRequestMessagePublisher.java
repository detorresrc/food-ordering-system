package com.detorresrc.foodorderingsystem.order.service.domain.ports.output.message.publisher.payment;

import com.detorresrc.foodorderingsystem.event.DomainEvent;
import com.detorresrc.foodorderingsystem.event.publisher.DomainEventPublisher;
import com.detorresrc.foodorderingsystem.order.service.domain.event.OrderCreatedEvent;

public interface OrderCreatedPaymentRequestMessagePublisher extends DomainEventPublisher<OrderCreatedEvent> {
}
