package com.detorresrc.foodorderingsystem.payment.service.messaging.mapper;

import com.detorresrc.foodorderingsystem.kafka.order.avro.model.PaymentRequestAvroModel;
import com.detorresrc.foodorderingsystem.kafka.order.avro.model.PaymentResponseAvroModel;
import com.detorresrc.foodorderingsystem.kafka.order.avro.model.PaymentStatus;
import com.detorresrc.foodorderingsystem.payment.service.domain.dto.PaymentRequest;
import com.detorresrc.foodorderingsystem.payment.service.domain.event.PaymentCancelledEvent;
import com.detorresrc.foodorderingsystem.payment.service.domain.event.PaymentCompletedEvent;
import com.detorresrc.foodorderingsystem.payment.service.domain.event.PaymentEvent;
import com.detorresrc.foodorderingsystem.payment.service.domain.event.PaymentFailedEvent;
import com.detorresrc.foodorderingsystem.valueobject.PaymentOrderStatus;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PaymentMessagingDataMapper {
    public PaymentResponseAvroModel paymentCompletedEventToPaymentResponseAvroModel(PaymentCompletedEvent event) {
        return buildPaymentResponseAvroModel(event);
    }

    public PaymentResponseAvroModel paymentCancelledEventToPaymentResponseAvroModel(PaymentCancelledEvent event) {
        return buildPaymentResponseAvroModel(event);
    }

    public PaymentResponseAvroModel paymentFailedEventToPaymentResponseAvroModel(PaymentFailedEvent event) {
        return buildPaymentResponseAvroModel(event);
    }

    public PaymentRequest paymentRequestAvroModelToPaymentRequest(PaymentRequestAvroModel model) {
        return PaymentRequest.builder()
            .id(model.getId().toString())
            .sagaId(model.getSagaId().toString())
            .customerId(model.getCustomerId().toString())
            .orderId(model.getOrderId().toString())
            .price(model.getPrice())
            .createdAt(model.getCreatedAt())
            .paymentOrderStatus(PaymentOrderStatus.valueOf(model.getPaymentOrderStatus().name()))
            .build();
    }

    private <T extends PaymentEvent> PaymentResponseAvroModel buildPaymentResponseAvroModel(T event) {
        return PaymentResponseAvroModel.newBuilder()
            .setId(UUID.randomUUID())
            .setSagaId(UUID.randomUUID())
            .setPaymentId(event.getPayment().getId().getValue())
            .setCustomerId(event.getPayment().getCustomerId().getValue())
            .setOrderId(event.getPayment().getOrderId().getValue())
            .setPrice(event.getPayment().getPrice().getValue())
            .setCreatedAt(event.getCreatedAt().toInstant())
            .setPaymentStatus(PaymentStatus.valueOf(event.getPayment().getPaymentStatus().name()))
            .setFailureMessages(event.getFailureMessages())
            .build();
    }
}
