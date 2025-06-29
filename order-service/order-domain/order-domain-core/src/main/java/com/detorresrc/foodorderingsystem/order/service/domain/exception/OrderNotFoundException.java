package com.detorresrc.foodorderingsystem.order.service.domain.exception;

import com.detorresrc.foodorderingsystem.exception.DomainException;

public class OrderNotFoundException extends DomainException {
    public OrderNotFoundException(String message) {
        super(message);
    }

    public OrderNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
