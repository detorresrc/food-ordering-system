package com.detorresrc.foodorderingsystem.restaurant.service.domain.exception;

import com.detorresrc.foodorderingsystem.exception.DomainException;

public class RestaurantNotFoundException extends DomainException {
    public RestaurantNotFoundException(String message) {
        super(message);
    }

    public RestaurantNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}