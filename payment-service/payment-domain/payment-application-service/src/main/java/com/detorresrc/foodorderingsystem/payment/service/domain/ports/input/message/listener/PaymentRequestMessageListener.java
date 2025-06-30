package com.detorresrc.foodorderingsystem.payment.service.domain.ports.input.message.listener;

import com.detorresrc.foodorderingsystem.payment.service.domain.dto.PaymentRequest;

public interface PaymentRequestMessageListener {
    void completePayment(PaymentRequest paymentRequest);

    void cancelPayment(PaymentRequest paymentRequest);
}
