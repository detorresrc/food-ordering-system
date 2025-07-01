package com.detorresrc.foodorderingsystem.restaurant.service.data.access.restaurant.adapter;

import com.detorresrc.foodorderingsystem.restaurant.service.data.access.restaurant.mapper.RestaurantDataAccessMapper;
import com.detorresrc.foodorderingsystem.restaurant.service.domain.entity.Restaurant;
import com.detorresrc.foodorderingsystem.restaurant.service.domain.ports.output.repository.RestaurantRepository;
import com.detorresrc.foodorderingsystem.system.data.access.restaurant.entity.RestaurantEntity;
import com.detorresrc.foodorderingsystem.system.data.access.restaurant.repository.RestaurantJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class RestaurantRepositoryImpl implements RestaurantRepository {
    private final RestaurantJpaRepository restaurantJpaRepository;
    private final RestaurantDataAccessMapper restaurantDataAccessMapper;

    @Override
    public Optional<Restaurant> findRestaurantInformation(Restaurant restaurant) {
        List<UUID> restaurantProducts =
            restaurantDataAccessMapper.restaurantToRestaurantProducts(restaurant);
        Optional<List<RestaurantEntity>> restaurantEntities = restaurantJpaRepository
            .findByRestaurantIdAndProductIdIn(restaurant.getId().getValue(),
                restaurantProducts);
        return restaurantEntities.map(restaurantDataAccessMapper::restaurantEntityToRestaurant);
    }
}
