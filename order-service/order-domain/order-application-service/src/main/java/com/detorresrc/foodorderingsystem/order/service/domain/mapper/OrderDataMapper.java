package com.detorresrc.foodorderingsystem.order.service.domain.mapper;

import com.detorresrc.foodorderingsystem.order.service.domain.dto.create.CreateOrderCommand;
import com.detorresrc.foodorderingsystem.order.service.domain.dto.create.CreateOrderResponse;
import com.detorresrc.foodorderingsystem.order.service.domain.dto.create.OrderAddress;
import com.detorresrc.foodorderingsystem.order.service.domain.entity.Order;
import com.detorresrc.foodorderingsystem.order.service.domain.entity.OrderItem;
import com.detorresrc.foodorderingsystem.order.service.domain.entity.Product;
import com.detorresrc.foodorderingsystem.order.service.domain.entity.Restaurant;
import com.detorresrc.foodorderingsystem.order.service.domain.valueobject.StreetAddress;
import com.detorresrc.foodorderingsystem.valueobject.CustomerId;
import com.detorresrc.foodorderingsystem.valueobject.Money;
import com.detorresrc.foodorderingsystem.valueobject.RestaurantId;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class OrderDataMapper {
    public Restaurant createOrderCommandToRestaurant(CreateOrderCommand command) {
        return Restaurant.Builder.builder()
            .restaurantId(new RestaurantId(command.getRestaurantId()))
            .products(command.getItems().stream().map(orderItem ->
                new Product(orderItem.getProductId())
            ).collect(Collectors.toList()))
            .build();
    }

    public Order createOrderCommandToOrder(CreateOrderCommand command) {
        return Order.Builder.newBuilder()
            .customerId(new CustomerId(command.getCustomerId()))
            .restaurantId(new RestaurantId(command.getRestaurantId()))
            .deliveryAddress(orderAddressToDeliveryAddress(command.getAddress()))
            .price(new Money(command.getPrice()))
            .items(orderItemsToOrderItemEntities(command.getItems()))
            .build();
    }

    private List<OrderItem> orderItemsToOrderItemEntities(@NotNull List<com.detorresrc.foodorderingsystem.order.service.domain.dto.create.OrderItem> items) {
        return items.stream()
            .map(
                i ->
                    OrderItem.Builder.newBuilder()
                        .product(new Product(i.getProductId()))
                        .price(new Money(i.getPrice()))
                        .quantity(i.getQuantity())
                        .subTotal(new Money(i.getSubTotal()))
                        .build()
            ).collect(Collectors.toList());
    }

    private StreetAddress orderAddressToDeliveryAddress(@NotNull OrderAddress address) {
        return StreetAddress.builder()
            .id(UUID.randomUUID())
            .street(address.getStreet())
            .postalCode(address.getPostalCode())
            .city(address.getCity())
            .build();
    }

    public CreateOrderResponse orderToCreateOrderResponse(Order order) {
        return CreateOrderResponse.builder()
            .orderTrackingId(order.getTrackingId().getValue())
            .orderStatus(order.getOrderStatus())
            .build();
    }
}
