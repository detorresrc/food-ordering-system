package com.detorresrc.foodorderingsystem.payment.service.domain.event;

import com.detorresrc.foodorderingsystem.event.publisher.DomainEventPublisher;
import com.detorresrc.foodorderingsystem.payment.service.domain.entity.Payment;

import java.time.ZonedDateTime;
import java.util.Collections;

public class PaymentFailedEvent extends PaymentEvent {
    private final DomainEventPublisher<PaymentFailedEvent> paymentFailedEventDomainEventPublisher;

    public PaymentFailedEvent(
        Payment payment,
        ZonedDateTime createdAt,
        DomainEventPublisher<PaymentFailedEvent> paymentFailedEventDomainEventPublisher) {

        super(payment, createdAt, Collections.emptyList());
        this.paymentFailedEventDomainEventPublisher = paymentFailedEventDomainEventPublisher;
    }

    @Override
    public void fire() {
        paymentFailedEventDomainEventPublisher.publish(this);
    }
}
