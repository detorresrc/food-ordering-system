package com.detorresrc.foodorderingsystem.order.service.messaging.mapper;

import com.detorresrc.foodorderingsystem.kafka.order.avro.model.*;
import com.detorresrc.foodorderingsystem.order.service.domain.entity.Order;
import com.detorresrc.foodorderingsystem.order.service.domain.event.OrderCancelledEvent;
import com.detorresrc.foodorderingsystem.order.service.domain.event.OrderCreatedEvent;
import com.detorresrc.foodorderingsystem.order.service.domain.event.OrderPaidEvent;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class OrderMessagingDataMapper {
    public PaymentRequestAvroModel orderCreateEventToPaymentRequestAvroModel(OrderCreatedEvent event) {
        Order order = event.getOrder();
        return PaymentRequestAvroModel.newBuilder()
            .setId(UUID.randomUUID())
            .setSagaId(UUID.randomUUID())
            .setCustomerId(order.getCustomerId().getValue())
            .setOrderId(order.getId().getValue())
            .setPrice(order.getPrice().getValue())
            .setCreatedAt(event.getCreatedAt().toInstant())
            .setPaymentOrderStatus(PaymentOrderStatus.PENDING)
            .build();
    }

    public PaymentRequestAvroModel orderCancelledEventToPaymentRequestAvroModel(OrderCancelledEvent event) {
        Order order = event.getOrder();
        return PaymentRequestAvroModel.newBuilder()
            .setId(UUID.randomUUID())
            .setSagaId(UUID.randomUUID())
            .setCustomerId(order.getCustomerId().getValue())
            .setOrderId(order.getId().getValue())
            .setPrice(order.getPrice().getValue())
            .setCreatedAt(event.getCreatedAt().toInstant())
            .setPaymentOrderStatus(PaymentOrderStatus.CANCELLED)
            .build();
    }

    public RestaurantApprovalRequestAvroModel orderPaidEventToRestaurantApprovalRequestAvroModel(OrderPaidEvent event) {
        Order order = event.getOrder();

        return RestaurantApprovalRequestAvroModel.newBuilder()
            .setOrderId(UUID.randomUUID())
            .setSagaId(UUID.randomUUID())
            .setOrderId(order.getId().getValue())
            .setRestaurantId(order.getRestaurantId().getValue())
            .setOrderId(order.getId().getValue())
            .setRestaurantOrderStatus(RestaurantOrderStatus.valueOf(order.getOrderStatus().name()))
            .setProducts(order.getItems().stream().map(orderItem ->
                Product.newBuilder()
                    .setId(orderItem.getProduct().getId().getValue().toString())
                    .setQuantity(orderItem.getQuantity())
                    .build()
            ).toList())
            .setPrice(order.getPrice().getValue())
            .setCreatedAt(event.getCreatedAt().toInstant())
            .setRestaurantOrderStatus(RestaurantOrderStatus.PAID)
            .build();
    }
}
