package com.detorresrc.foodorderingsystem.restaurant.service.domain.exception;

import com.detorresrc.foodorderingsystem.exception.DomainException;

public class RestaurantApplicationServiceException extends DomainException {
    public RestaurantApplicationServiceException(String message) {
        super(message);
    }

    public RestaurantApplicationServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
