package com.detorresrc.foodorderingsystem.payment.service.domain;

import com.detorresrc.foodorderingsystem.DomainConstant;
import com.detorresrc.foodorderingsystem.event.publisher.DomainEventPublisher;
import com.detorresrc.foodorderingsystem.payment.service.domain.entity.CreditEntry;
import com.detorresrc.foodorderingsystem.payment.service.domain.entity.CreditHistory;
import com.detorresrc.foodorderingsystem.payment.service.domain.entity.Payment;
import com.detorresrc.foodorderingsystem.payment.service.domain.event.PaymentCancelledEvent;
import com.detorresrc.foodorderingsystem.payment.service.domain.event.PaymentCompletedEvent;
import com.detorresrc.foodorderingsystem.payment.service.domain.event.PaymentEvent;
import com.detorresrc.foodorderingsystem.payment.service.domain.event.PaymentFailedEvent;
import com.detorresrc.foodorderingsystem.payment.service.domain.valueobject.CreditHistoryId;
import com.detorresrc.foodorderingsystem.payment.service.domain.valueobject.TransactionType;
import com.detorresrc.foodorderingsystem.valueobject.Money;
import com.detorresrc.foodorderingsystem.valueobject.PaymentStatus;
import lombok.extern.slf4j.Slf4j;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Slf4j
public class PaymentDomainServiceImpl implements PaymentDomainService {
    private static Money getTotalHistoryAmount(List<CreditHistory> creditHistories, TransactionType type) {
        return creditHistories.stream().filter(v -> type == v.getTransactionType()).map(CreditHistory::getAmount).reduce(Money.ZERO, Money::add);
    }

    @Override
    public PaymentEvent validateAndInitializePayment(Payment payment, CreditEntry creditEntry, List<CreditHistory> creditHistories, List<String> failureMessages, DomainEventPublisher<PaymentCompletedEvent> paymentCompletePublisher, DomainEventPublisher<PaymentFailedEvent> paymentFailedPublisher) {

        payment.validatePayment(failureMessages);
        payment.initializePayment();
        validateCreditEntry(payment, creditEntry, failureMessages);
        subtractCreditAmount(payment, creditEntry);
        updateCreditHistory(payment, creditHistories, TransactionType.DEBIT);
        validateCreditHistory(creditEntry, creditHistories, failureMessages);

        if (failureMessages.isEmpty()) {
            log.info("Payment is initiated for order id: {}", payment.getOrderId().getValue());
            payment.updateStatus(PaymentStatus.COMPLETED);
            return new PaymentCompletedEvent(payment, ZonedDateTime.now(ZoneId.of(DomainConstant.DEFAULT_TIMEZONE)), paymentCompletePublisher);
        } else {
            log.info("Payment initiation is failed for order id: {}", payment.getOrderId().getValue());
            payment.updateStatus(PaymentStatus.FAILED);
            return new PaymentFailedEvent(payment, ZonedDateTime.now(ZoneId.of(DomainConstant.DEFAULT_TIMEZONE)), paymentFailedPublisher);
        }
    }

    @Override
    public PaymentEvent validateAndCancelPayment(Payment payment, CreditEntry creditEntry, List<CreditHistory> creditHistories, List<String> failureMessages, DomainEventPublisher<PaymentCancelledEvent> paymentCancelledPublisher, DomainEventPublisher<PaymentFailedEvent> paymentFailedPublisher) {

        payment.validatePayment(failureMessages);
        addCreditEntry(payment, creditEntry);
        updateCreditHistory(payment, creditHistories, TransactionType.CREDIT);

        if (failureMessages.isEmpty()) {
            log.info("Payment is cancelled for order id: {}", payment.getOrderId().getValue());
            payment.updateStatus(PaymentStatus.CANCELLED);
            return new PaymentCancelledEvent(payment, ZonedDateTime.now(ZoneId.of(DomainConstant.DEFAULT_TIMEZONE)), paymentCancelledPublisher);
        } else {
            log.info("Payment cancellation is failed for order id: {}", payment.getOrderId().getValue());
            payment.updateStatus(PaymentStatus.FAILED);
            return new PaymentFailedEvent(payment, ZonedDateTime.now(ZoneId.of(DomainConstant.DEFAULT_TIMEZONE)), paymentFailedPublisher);
        }
    }

    private void subtractCreditAmount(Payment payment, CreditEntry creditEntry) {
        creditEntry.subtractCreditAmount(payment.getPrice());
    }

    private void validateCreditEntry(Payment payment, CreditEntry creditEntry, List<String> failureMessages) {
        if (payment.getPrice().isGreaterThan(creditEntry.getTotalCreditAmount())) {
            var message = String.format("Customer with id: %s does not have enough credit for payment!", payment.getCustomerId());
            log.error(message);
            failureMessages.add(message);
        }
    }

    private void updateCreditHistory(Payment payment, List<CreditHistory> creditHistories, TransactionType transactionType) {
        creditHistories.add(CreditHistory.Builder.builder().creditHistoryId(new CreditHistoryId(UUID.randomUUID())).customerId(payment.getCustomerId()).amount(payment.getPrice()).transactionType(transactionType).build());
    }

    private void validateCreditHistory(CreditEntry creditEntry, List<CreditHistory> creditHistories, List<String> failureMessages) {
        Money totalCreditHistory = getTotalHistoryAmount(creditHistories, TransactionType.CREDIT);
        Money totalDebitHistory = getTotalHistoryAmount(creditHistories, TransactionType.DEBIT);

        if (totalDebitHistory.isGreaterThan(totalCreditHistory)) {
            var message = String.format("Customer with id: %s does not have enough credit according to credit history!", creditEntry.getCustomerId().getValue());
            log.error(message);
            failureMessages.add(message);
        }
        if (!creditEntry.getTotalCreditAmount().equals(totalCreditHistory.subtract(totalDebitHistory))) {
            var message = String.format("Credit history total is not equal to current credit for customer id: %s", creditEntry.getCustomerId().getValue());
            log.error(message);
            failureMessages.add(message);
        }
    }

    private void addCreditEntry(Payment payment, CreditEntry creditEntry) {
        creditEntry.addCreditAmount(payment.getPrice());
    }

}