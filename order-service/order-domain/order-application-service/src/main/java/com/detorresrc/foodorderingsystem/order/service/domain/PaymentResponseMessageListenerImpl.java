package com.detorresrc.foodorderingsystem.order.service.domain;

import com.detorresrc.foodorderingsystem.order.service.domain.dto.message.PaymentResponse;
import com.detorresrc.foodorderingsystem.order.service.domain.ports.input.message.listener.payment.PaymentResponseMessageListener;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Valid
@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentResponseMessageListenerImpl implements PaymentResponseMessageListener {
    @Override
    public void paymentCompleted(PaymentResponse response) {

    }

    @Override
    public void paymentCancelled(PaymentResponse response) {

    }
}
