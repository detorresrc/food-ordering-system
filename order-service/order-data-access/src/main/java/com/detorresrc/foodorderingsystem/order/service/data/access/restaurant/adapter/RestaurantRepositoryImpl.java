package com.detorresrc.foodorderingsystem.order.service.data.access.restaurant.adapter;

import com.detorresrc.foodorderingsystem.order.service.data.access.restaurant.mapper.RestaurantDataAccessMapper;
import com.detorresrc.foodorderingsystem.order.service.data.access.restaurant.repository.RestaurantJpaRepository;
import com.detorresrc.foodorderingsystem.order.service.domain.entity.Restaurant;
import com.detorresrc.foodorderingsystem.order.service.domain.ports.output.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RestaurantRepositoryImpl implements RestaurantRepository {
    private final RestaurantJpaRepository restaurantJpaRepository;
    private final RestaurantDataAccessMapper restaurantDataAccessMapper;

    @Override
    public Optional<Restaurant> findRestaurantInformation(Restaurant restaurant) {
        return restaurantJpaRepository.findByRestaurantIdAndProductIdIn(
                restaurant.getId().getValue(),
                restaurant.getProducts().stream().map(p -> p.getId().getValue()).toList())
            .map(restaurantDataAccessMapper::restaurantEntityToRestaurant);
    }
}
