package com.detorresrc.foodorderingsystem.restaurant.service.domain;

import com.detorresrc.foodorderingsystem.DomainConstant;
import com.detorresrc.foodorderingsystem.event.publisher.DomainEventPublisher;
import com.detorresrc.foodorderingsystem.restaurant.service.domain.entity.Restaurant;
import com.detorresrc.foodorderingsystem.restaurant.service.domain.event.OrderApprovalEvent;
import com.detorresrc.foodorderingsystem.restaurant.service.domain.event.OrderApprovedEvent;
import com.detorresrc.foodorderingsystem.restaurant.service.domain.event.OrderRejectedEvent;
import com.detorresrc.foodorderingsystem.valueobject.OrderApprovalStatus;
import lombok.extern.slf4j.Slf4j;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@Slf4j
public class RestaurantDomainServiceImpl implements RestaurantDomainService {
    @Override
    public OrderApprovalEvent validateOrder(
        Restaurant restaurant,
        List<String> failureMessages,
        DomainEventPublisher<OrderApprovedEvent>
            orderApprovedEventDomainEventPublisher,
        DomainEventPublisher<OrderRejectedEvent>
            orderRejectedEventDomainEventPublisher) {

        restaurant.validateOrder(failureMessages);
        log.info("Validating order with id: {}", restaurant.getOrderDetail().getId().getValue());

        if (failureMessages.isEmpty()) {
            log.info("Order is approved for order id: {}", restaurant.getOrderDetail().getId().getValue());
            restaurant.constructOrderApproval(OrderApprovalStatus.APPROVED);
            return new OrderApprovedEvent(restaurant.getOrderApproval(),
                restaurant.getId(),
                failureMessages,
                ZonedDateTime.now(ZoneId.of(DomainConstant.DEFAULT_TIMEZONE)),
                orderApprovedEventDomainEventPublisher);
        } else {
            log.info("Order is rejected for order id: {}", restaurant.getOrderDetail().getId().getValue());
            restaurant.constructOrderApproval(OrderApprovalStatus.REJECTED);
            return new OrderRejectedEvent(restaurant.getOrderApproval(),
                restaurant.getId(),
                failureMessages,
                ZonedDateTime.now(ZoneId.of(DomainConstant.DEFAULT_TIMEZONE)),
                orderRejectedEventDomainEventPublisher);
        }
    }
}
