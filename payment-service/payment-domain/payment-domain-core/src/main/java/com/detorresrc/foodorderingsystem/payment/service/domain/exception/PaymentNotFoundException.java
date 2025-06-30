package com.detorresrc.foodorderingsystem.payment.service.domain.exception;

import com.detorresrc.foodorderingsystem.exception.DomainException;

public class PaymentNotFoundException extends DomainException {
    public PaymentNotFoundException(String message) {
        super(message);
    }

    public PaymentNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
