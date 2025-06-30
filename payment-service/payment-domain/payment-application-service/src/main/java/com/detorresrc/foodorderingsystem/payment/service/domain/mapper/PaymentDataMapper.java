package com.detorresrc.foodorderingsystem.payment.service.domain.mapper;

import com.detorresrc.foodorderingsystem.payment.service.domain.dto.PaymentRequest;
import com.detorresrc.foodorderingsystem.payment.service.domain.entity.Payment;
import com.detorresrc.foodorderingsystem.valueobject.CustomerId;
import com.detorresrc.foodorderingsystem.valueobject.Money;
import com.detorresrc.foodorderingsystem.valueobject.OrderId;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PaymentDataMapper {
    public Payment paymentRequestToPayment(PaymentRequest request) {
        return Payment.builder()
            .orderId(new OrderId(UUID.fromString(request.getOrderId())))
            .customerId(new CustomerId(UUID.fromString(request.getCustomerId())))
            .price(new Money(request.getPrice()))
            .build();
    }
}
