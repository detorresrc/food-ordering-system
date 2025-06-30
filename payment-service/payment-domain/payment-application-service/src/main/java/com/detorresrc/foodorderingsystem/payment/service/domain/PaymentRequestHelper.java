package com.detorresrc.foodorderingsystem.payment.service.domain;

import com.detorresrc.foodorderingsystem.payment.service.domain.dto.PaymentRequest;
import com.detorresrc.foodorderingsystem.payment.service.domain.entity.CreditEntry;
import com.detorresrc.foodorderingsystem.payment.service.domain.entity.CreditHistory;
import com.detorresrc.foodorderingsystem.payment.service.domain.entity.Payment;
import com.detorresrc.foodorderingsystem.payment.service.domain.event.PaymentEvent;
import com.detorresrc.foodorderingsystem.payment.service.domain.exception.PaymentApplicationServiceException;
import com.detorresrc.foodorderingsystem.payment.service.domain.mapper.PaymentDataMapper;
import com.detorresrc.foodorderingsystem.payment.service.domain.ports.output.message.publisher.PaymentCancelledMessagePublisher;
import com.detorresrc.foodorderingsystem.payment.service.domain.ports.output.message.publisher.PaymentCompleteMessagePublisher;
import com.detorresrc.foodorderingsystem.payment.service.domain.ports.output.message.publisher.PaymentFailedMessagePublisher;
import com.detorresrc.foodorderingsystem.payment.service.domain.ports.output.repository.CreditEntryRepository;
import com.detorresrc.foodorderingsystem.payment.service.domain.ports.output.repository.CreditHistoryRepository;
import com.detorresrc.foodorderingsystem.payment.service.domain.ports.output.repository.PaymentRepository;
import com.detorresrc.foodorderingsystem.valueobject.CustomerId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class PaymentRequestHelper {
    private final PaymentDomainService paymentDomainService;
    private final PaymentDataMapper paymentDataMapper;
    private final PaymentRepository paymentRepository;
    private final CreditEntryRepository creditEntryRepository;
    private final CreditHistoryRepository creditHistoryRepository;
    private final PaymentCompleteMessagePublisher paymentCompleteMessagePublisher;
    private final PaymentCancelledMessagePublisher paymentCancelledMessagePublisher;
    private final PaymentFailedMessagePublisher paymentFailedMessagePublisher;

    private static enum PaymentProcessType {
        PAYMENT_INITIALIZATION,
        PAYMENT_CANCELLATION
    }

    @Transactional
    public PaymentEvent persistPayment(PaymentRequest request) {
        log.info("Received payment complete event for order id: {}", request.getOrderId());
        return processEvent(paymentDataMapper.paymentRequestToPayment(request), PaymentProcessType.PAYMENT_INITIALIZATION);
    }

    @Transactional
    public PaymentEvent persistCancelPayment(PaymentRequest request) {
        log.info("Received payment rollback event for order id: {}", request.getOrderId());

        Payment payment = paymentRepository.findByOrderId(UUID.fromString(request.getOrderId()))
            .orElseThrow(() -> {
                log.error("Could not find payment for order id: {}", request.getOrderId());
                return new PaymentApplicationServiceException("Could not find payment for order id: " + request.getOrderId());
            });

        return processEvent(paymentDataMapper.paymentRequestToPayment(request), PaymentProcessType.PAYMENT_CANCELLATION);
    }

    private List<CreditHistory> getCreditHistories(CustomerId customerId) {
        return creditHistoryRepository.findByCustomerId(customerId)
            .orElseThrow(() -> {
                log.error("Could not find credit history for customer id: {}", customerId.getValue());
                return new PaymentApplicationServiceException("Could not find credit history for customer id: " + customerId.getValue());
            });
    }

    private CreditEntry getCreditEntry(CustomerId customerId) {
        return creditEntryRepository.findByCustomerId(customerId)
            .orElseThrow(() -> {
                log.error("Could not find credit entry for customer id: {}", customerId.getValue());
                return new PaymentApplicationServiceException("Could not find credit entry for customer id: " + customerId.getValue());
            });
    }

    private void persistObject(Payment payment, List<String> failureMessages, CreditEntry creditEntry, List<CreditHistory> creditHistories) {
        paymentRepository.save(payment);
        if (failureMessages.isEmpty()) {
            creditEntryRepository.save(creditEntry);
            creditHistoryRepository.save(creditHistories.getLast());
        }
    }

    private PaymentEvent processEvent(Payment payment, PaymentProcessType processType) {
        CreditEntry creditEntry = getCreditEntry(payment.getCustomerId());
        List<CreditHistory> creditHistories = getCreditHistories(payment.getCustomerId());
        List<String> failureMessages = new ArrayList<>();
        PaymentEvent paymentEvent;

        if (processType == PaymentProcessType.PAYMENT_INITIALIZATION) {
            paymentEvent =
                paymentDomainService.validateAndInitializePayment(
                    payment,
                    creditEntry,
                    creditHistories,
                    failureMessages,
                    paymentCompleteMessagePublisher,
                    paymentFailedMessagePublisher
                );
        } else {
            paymentEvent =
                paymentDomainService.validateAndCancelPayment(
                    payment,
                    creditEntry,
                    creditHistories,
                    failureMessages,
                    paymentCancelledMessagePublisher,
                    paymentFailedMessagePublisher
                );
        }
        persistObject(payment, failureMessages, creditEntry, creditHistories);
        return paymentEvent;
    }

}
