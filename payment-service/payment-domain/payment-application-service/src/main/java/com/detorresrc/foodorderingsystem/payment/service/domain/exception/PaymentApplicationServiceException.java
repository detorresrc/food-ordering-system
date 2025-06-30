package com.detorresrc.foodorderingsystem.payment.service.domain.exception;

import com.detorresrc.foodorderingsystem.exception.DomainException;

public class PaymentApplicationServiceException extends DomainException {
    public PaymentApplicationServiceException(String message) {
        super(message);
    }

    public PaymentApplicationServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
