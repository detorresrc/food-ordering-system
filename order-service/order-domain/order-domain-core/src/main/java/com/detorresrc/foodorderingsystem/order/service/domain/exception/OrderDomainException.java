package com.detorresrc.foodorderingsystem.order.service.domain.exception;

import com.detorresrc.foodorderingsystem.exception.DomainException;

public class OrderDomainException extends DomainException {
    public OrderDomainException(String message) {
        super(message);
    }

    public OrderDomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
