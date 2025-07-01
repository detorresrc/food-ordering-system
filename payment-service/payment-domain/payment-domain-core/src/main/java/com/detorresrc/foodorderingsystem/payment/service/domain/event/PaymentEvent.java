package com.detorresrc.foodorderingsystem.payment.service.domain.event;

import com.detorresrc.foodorderingsystem.event.DomainEvent;
import com.detorresrc.foodorderingsystem.payment.service.domain.entity.Payment;
import lombok.Getter;

import java.time.ZonedDateTime;
import java.util.List;

@Getter
public abstract class PaymentEvent implements DomainEvent<Payment> {
    private final Payment payment;
    private final ZonedDateTime createdAt;
    private final List<String> failureMessages;

    protected PaymentEvent(Payment payment, ZonedDateTime createdAt, List<String> failureMessages) {
        this.payment = payment;
        this.createdAt = createdAt;
        this.failureMessages = failureMessages;
    }
}