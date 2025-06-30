package com.detorresrc.foodorderingsystem.payment.service.domain.exception;

import com.detorresrc.foodorderingsystem.exception.DomainException;

public class PaymentDomainException extends DomainException {
    public PaymentDomainException(String message) {
        super(message);
    }

    public PaymentDomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
