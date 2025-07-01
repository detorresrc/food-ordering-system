package com.detorresrc.foodorderingsystem.restaurant.service.domain;

import com.detorresrc.foodorderingsystem.event.publisher.DomainEventPublisher;
import com.detorresrc.foodorderingsystem.restaurant.service.domain.entity.Restaurant;
import com.detorresrc.foodorderingsystem.restaurant.service.domain.event.OrderApprovalEvent;
import com.detorresrc.foodorderingsystem.restaurant.service.domain.event.OrderApprovedEvent;
import com.detorresrc.foodorderingsystem.restaurant.service.domain.event.OrderRejectedEvent;

import java.util.List;

public interface RestaurantDomainService {
    OrderApprovalEvent validateOrder(
        Restaurant restaurant,
        List<String> failureMessages,
        DomainEventPublisher<OrderApprovedEvent> orderApprovedEventDomainEventPublisher,
        DomainEventPublisher<OrderRejectedEvent> orderRejectedEventDomainEventPublisher);
}
