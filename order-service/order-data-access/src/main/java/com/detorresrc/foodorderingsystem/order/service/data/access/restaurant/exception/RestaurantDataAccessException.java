package com.detorresrc.foodorderingsystem.order.service.data.access.restaurant.exception;

public class RestaurantDataAccessException extends RuntimeException {
    public RestaurantDataAccessException(String message) {
        super(message);
    }

    public RestaurantDataAccessException(String message, Throwable cause) {
        super(message, cause);
    }

    public RestaurantDataAccessException(Throwable cause) {
        super(cause);
    }
}
