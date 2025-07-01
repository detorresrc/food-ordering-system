package com.detorresrc.foodorderingsystem.restaurant.service.domain.ports.output.message.publisher;

import com.detorresrc.foodorderingsystem.event.publisher.DomainEventPublisher;
import com.detorresrc.foodorderingsystem.restaurant.service.domain.event.OrderRejectedEvent;

public interface OrderRejectedMessagePublisher extends DomainEventPublisher<OrderRejectedEvent> {
}
