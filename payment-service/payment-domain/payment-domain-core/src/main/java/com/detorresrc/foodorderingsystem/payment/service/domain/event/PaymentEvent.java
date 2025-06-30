package com.detorresrc.foodorderingsystem.payment.service.domain.event;

import com.detorresrc.foodorderingsystem.event.DomainEvent;
import com.detorresrc.foodorderingsystem.payment.service.domain.entity.Payment;
import lombok.Builder;
import lombok.Getter;

import java.time.ZonedDateTime;
import java.util.List;

@Builder
@Getter
public abstract class PaymentEvent implements DomainEvent<Payment> {
    private final Payment payment;
    private final ZonedDateTime createdAt;
    private final List<String> failureMessages;
}