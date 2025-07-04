package com.detorresrc.foodorderingsystem.payment.service.data.access.payment.mapper;

import com.detorresrc.foodorderingsystem.payment.service.data.access.payment.entity.PaymentEntity;
import com.detorresrc.foodorderingsystem.payment.service.domain.entity.Payment;
import com.detorresrc.foodorderingsystem.payment.service.domain.valueobject.PaymentId;
import com.detorresrc.foodorderingsystem.valueobject.CustomerId;
import com.detorresrc.foodorderingsystem.valueobject.Money;
import com.detorresrc.foodorderingsystem.valueobject.OrderId;
import org.springframework.stereotype.Component;

@Component
public class PaymentDataAccessMapper {
    public PaymentEntity paymentToPaymentEntity(Payment payment) {
        return PaymentEntity.builder()
            .id(payment.getId().getValue())
            .customerId(payment.getCustomerId().getValue())
            .orderId(payment.getOrderId().getValue())
            .price(payment.getPrice().getAmount())
            .status(payment.getPaymentStatus())
            .createdAt(payment.getCreatedAt())
            .build();
    }

    public Payment paymentEntityToPayment(PaymentEntity paymentEntity) {
        return Payment.builder()
            .paymentId(new PaymentId(paymentEntity.getId()))
            .customerId(new CustomerId(paymentEntity.getCustomerId()))
            .orderId(new OrderId(paymentEntity.getOrderId()))
            .price(new Money(paymentEntity.getPrice()))
            .createdAt(paymentEntity.getCreatedAt())
            .build();
    }
}
