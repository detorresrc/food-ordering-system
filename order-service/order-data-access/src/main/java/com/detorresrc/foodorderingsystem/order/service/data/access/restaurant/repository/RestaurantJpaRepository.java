package com.detorresrc.foodorderingsystem.order.service.data.access.restaurant.repository;

import com.detorresrc.foodorderingsystem.order.service.data.access.restaurant.entity.RestaurantEntity;
import com.detorresrc.foodorderingsystem.order.service.data.access.restaurant.entity.RestaurantEntityId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RestaurantJpaRepository extends JpaRepository<RestaurantEntity, RestaurantEntityId> {
    Optional<List<RestaurantEntity>> findByRestaurantIdAndProductIdIn(UUID restaurantId, List<UUID> productIds);
}
