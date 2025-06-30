package com.detorresrc.foodorderingsystem.payment.service.domain;

import com.detorresrc.foodorderingsystem.event.publisher.DomainEventPublisher;
import com.detorresrc.foodorderingsystem.payment.service.domain.entity.CreditEntry;
import com.detorresrc.foodorderingsystem.payment.service.domain.entity.CreditHistory;
import com.detorresrc.foodorderingsystem.payment.service.domain.entity.Payment;
import com.detorresrc.foodorderingsystem.payment.service.domain.event.PaymentCancelledEvent;
import com.detorresrc.foodorderingsystem.payment.service.domain.event.PaymentCompletedEvent;
import com.detorresrc.foodorderingsystem.payment.service.domain.event.PaymentEvent;
import com.detorresrc.foodorderingsystem.payment.service.domain.event.PaymentFailedEvent;

import java.util.List;

public interface PaymentDomainService {
    PaymentEvent validateAndInitializePayment(
        Payment payment,
        CreditEntry creditEntry,
        List<CreditHistory> creditHistories,
        List<String> failureMessages,
        DomainEventPublisher<PaymentCompletedEvent> paymentCompletePublisher,
        DomainEventPublisher<PaymentFailedEvent> paymentFailedPublisher
    );

    PaymentEvent validateAndCancelPayment(
        Payment payment,
        CreditEntry creditEntry,
        List<CreditHistory> creditHistories,
        List<String> failureMessages,
        DomainEventPublisher<PaymentCancelledEvent> publisher,
        DomainEventPublisher<PaymentFailedEvent> paymentFailedPublisher
    );
}
