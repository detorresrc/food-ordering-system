package com.detorresrc.foodorderingsystem.order.service.data.access.order.mapper;

import com.detorresrc.foodorderingsystem.order.service.data.access.order.entity.OrderAddressEntity;
import com.detorresrc.foodorderingsystem.order.service.data.access.order.entity.OrderEntity;
import com.detorresrc.foodorderingsystem.order.service.data.access.order.entity.OrderItemEntity;
import com.detorresrc.foodorderingsystem.order.service.domain.entity.Order;
import com.detorresrc.foodorderingsystem.order.service.domain.entity.OrderItem;
import com.detorresrc.foodorderingsystem.order.service.domain.entity.Product;
import com.detorresrc.foodorderingsystem.order.service.domain.valueobject.OrderItemId;
import com.detorresrc.foodorderingsystem.order.service.domain.valueobject.StreetAddress;
import com.detorresrc.foodorderingsystem.order.service.domain.valueobject.TrackingId;
import com.detorresrc.foodorderingsystem.valueobject.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.detorresrc.foodorderingsystem.order.service.domain.entity.Order.FAILURE_MESSAGE_DELIMITER;

@Component
public class OrderDataAccessMapper {
    public OrderEntity orderToOrderEntity(Order order) {
        var orderEntity = OrderEntity.builder()
            .id(order.getId().getValue())
            .customerId(order.getCustomerId().getValue())
            .restaurantId(order.getRestaurantId().getValue())
            .trackingId(order.getTrackingId().getValue())
            .address(deliveryAddressToOrderAddressEntity(order.getDeliveryAddress()))
            .price(order.getPrice().getValue())
            .items(orderItemsToOrderItemEntities(order.getItems()))
            .failureMessages(order.getFailureMessages() != null ? String.join(FAILURE_MESSAGE_DELIMITER, order.getFailureMessages()) : null)
            .build();

        orderEntity.getAddress().setOrder(orderEntity);
        orderEntity.getItems().forEach(o -> o.setOrder(orderEntity));

        return orderEntity;
    }

    public Order orderEntityToOrder(OrderEntity orderEntity) {
        return Order.Builder.newBuilder()
            .orderId(new OrderId(orderEntity.getId()))
            .customerId(new CustomerId(orderEntity.getCustomerId()))
            .restaurantId(new RestaurantId(orderEntity.getRestaurantId()))
            .trackingId(new TrackingId(orderEntity.getTrackingId()))
            .deliveryAddress(addressEntityToDeliveryAddress(orderEntity.getAddress()))
            .price(new Money(orderEntity.getPrice()))
            .items(orderItemEntityToOrderItems(orderEntity.getItems()))
            .orderStatus(orderEntity.getOrderStatus())
            .failureMessages(
                orderEntity.getFailureMessages().isEmpty()
                    ? new ArrayList<>()
                    : new ArrayList<>(
                    List.of(orderEntity.getFailureMessages().split(FAILURE_MESSAGE_DELIMITER))))
            .build();
    }

    private List<OrderItem> orderItemEntityToOrderItems(List<OrderItemEntity> items) {
        return items.stream()
            .map(item -> OrderItem.Builder.newBuilder()
                .orderItemId(new OrderItemId(item.getId()))
                .product(new Product(new ProductId(item.getProductId())))
                .price(new Money(item.getPrice()))
                .quantity(item.getQuantity())
                .subTotal(new Money(item.getSubTotal()))
                .build()
            ).toList();
    }

    private StreetAddress addressEntityToDeliveryAddress(OrderAddressEntity address) {
        return StreetAddress.builder()
            .id(address.getId())
            .street(address.getStreet())
            .postalCode(address.getPostalCode())
            .city(address.getCity())
            .build();
    }

    private List<OrderItemEntity> orderItemsToOrderItemEntities(List<OrderItem> items) {
        return items.stream()
            .map(orderItem -> OrderItemEntity.builder()
                .id(orderItem.getId().getValue())
                .productId(orderItem.getProduct().getId().getValue())
                .price(orderItem.getProduct().getPrice().getValue())
                .quantity(orderItem.getQuantity())
                .subTotal(orderItem.getProduct().getPrice().getValue())
                .build())
            .toList();
    }

    public OrderAddressEntity deliveryAddressToOrderAddressEntity(StreetAddress address) {
        return OrderAddressEntity.builder()
            .street(address.getStreet())
            .postalCode(address.getPostalCode())
            .city(address.getCity())
            .build();
    }
}
