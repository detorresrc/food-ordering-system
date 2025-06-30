package com.detorresrc.foodorderingsystem.payment.service.domain;

import com.detorresrc.foodorderingsystem.payment.service.domain.dto.PaymentRequest;
import com.detorresrc.foodorderingsystem.payment.service.domain.event.PaymentEvent;
import com.detorresrc.foodorderingsystem.payment.service.domain.ports.input.message.listener.PaymentRequestMessageListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PaymentRequestMessageListenerImpl implements PaymentRequestMessageListener {
    private final PaymentRequestHelper paymentRequestHelper;

    @Override
    public void completePayment(PaymentRequest paymentRequest) {
        var paymentEvent = paymentRequestHelper.persistPayment(paymentRequest);
        fireEvent(paymentEvent);
    }

    @Override
    public void cancelPayment(PaymentRequest paymentRequest) {
        var paymentEvent = paymentRequestHelper.persistCancelPayment(paymentRequest);
        fireEvent(paymentEvent);
    }

    private void fireEvent(PaymentEvent paymentEvent) {
        log.info("Publishing payment event with payment id: {} and order id: {}",
            paymentEvent.getPayment().getId().getValue(),
            paymentEvent.getPayment().getOrderId().getValue());

        paymentEvent.fire();
    }
}
