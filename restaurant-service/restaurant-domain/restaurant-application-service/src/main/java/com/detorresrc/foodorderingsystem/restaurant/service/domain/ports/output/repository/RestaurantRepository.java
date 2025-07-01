package com.detorresrc.foodorderingsystem.restaurant.service.domain.ports.output.repository;

import com.detorresrc.foodorderingsystem.restaurant.service.domain.entity.Restaurant;

import java.util.Optional;

public interface RestaurantRepository {
    Optional<Restaurant> findRestaurantInformation(Restaurant restaurant);
}
