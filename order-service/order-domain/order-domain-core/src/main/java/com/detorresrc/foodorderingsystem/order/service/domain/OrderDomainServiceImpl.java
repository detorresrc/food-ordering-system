package com.detorresrc.foodorderingsystem.order.service.domain;

import com.detorresrc.foodorderingsystem.order.service.domain.entity.Order;
import com.detorresrc.foodorderingsystem.order.service.domain.entity.Product;
import com.detorresrc.foodorderingsystem.order.service.domain.entity.Restaurant;
import com.detorresrc.foodorderingsystem.order.service.domain.event.OrderCancelledEvent;
import com.detorresrc.foodorderingsystem.order.service.domain.event.OrderCreatedEvent;
import com.detorresrc.foodorderingsystem.order.service.domain.event.OrderPaidEvent;
import com.detorresrc.foodorderingsystem.order.service.domain.exception.OrderDomainException;
import lombok.extern.slf4j.Slf4j;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@Slf4j
public class OrderDomainServiceImpl implements OrderDomainService {
    private static final String UTC_ZONE = "UTC";

    @Override
    public OrderCreatedEvent validateAndInitializeOrder(Order order, Restaurant restaurant) {
        validateRestaurant(restaurant);
        setOrderProductInformation(order, restaurant);

        order.validateOrder();
        order.initializeOrder();

        log.info("Order with id: {} is initialized with restaurant: {}",
            order.getId().getValue(), restaurant.getId().getValue());

        return new OrderCreatedEvent(order, ZonedDateTime.now(ZoneId.of(UTC_ZONE)));
    }

    @Override
    public OrderPaidEvent payOrder(Order order) {
        order.pay();
        log.info("Order with id: {} is paid", order.getId().getValue());
        return new OrderPaidEvent(order, ZonedDateTime.now(ZoneId.of(UTC_ZONE)));
    }

    @Override
    public void approveOrder(Order order) {
        order.approve();
        log.info("Order with id: {} is approved", order.getId().getValue());
    }

    @Override
    public OrderCancelledEvent cancelOrderPayment(Order order, List<String> failureMessages) {
        order.initCancel(failureMessages);
        log.info("Order payment is cancelling for order with id: {}", order.getId().getValue());
        return new OrderCancelledEvent(order, ZonedDateTime.now(ZoneId.of(UTC_ZONE)));
    }

    @Override
    public void cancelOrder(Order order, List<String> failureMessages) {
        order.cancel(failureMessages);
        log.info("Order with id: {} is cancelled", order.getId().getValue());
    }

    private void setOrderProductInformation(Order order, Restaurant restaurant) {
        order.getItems().forEach(item -> {
            restaurant.getProducts().forEach(restaurantProduct -> {
                Product currProduct = item.getProduct();
                if (currProduct.equals(restaurantProduct)) {
                    currProduct.updateWithConfirmedNameAndPrice(
                        restaurantProduct.getName(),
                        restaurantProduct.getPrice());
                }
            });
        });
    }

    private void validateRestaurant(Restaurant restaurant) {
        if (!restaurant.getActive())
            throw new OrderDomainException("Restaurant is not active: " + restaurant.getId().getValue());
    }
}
