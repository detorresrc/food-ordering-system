package com.detorresrc.foodorderingsystem.order.service.data.access.restaurant.mapper;

import com.detorresrc.foodorderingsystem.order.service.data.access.restaurant.entity.RestaurantEntity;
import com.detorresrc.foodorderingsystem.order.service.data.access.restaurant.exception.RestaurantDataAccessException;
import com.detorresrc.foodorderingsystem.order.service.domain.entity.Product;
import com.detorresrc.foodorderingsystem.order.service.domain.entity.Restaurant;
import com.detorresrc.foodorderingsystem.valueobject.Money;
import com.detorresrc.foodorderingsystem.valueobject.ProductId;
import com.detorresrc.foodorderingsystem.valueobject.RestaurantId;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class RestaurantDataAccessMapper {
    public List<UUID> restaurantToRestaurantProducts(Restaurant restaurant) {
        return restaurant.getProducts().stream()
            .map(p -> p.getId().getValue())
            .toList();
    }

    public Restaurant restaurantEntityToRestaurant(List<RestaurantEntity> restaurantEntities) {
        RestaurantEntity restaurantEntity =
            restaurantEntities.stream().findFirst().orElseThrow(() ->
                new RestaurantDataAccessException("Restaurant cloud not be found"));

        List<Product> restaurantProducts = restaurantEntities.stream()
            .map(e -> new Product(new ProductId(e.getProductId()), e.getProductName(), new Money(e.getProductPrice()))).toList();

        return Restaurant.Builder.builder()
            .restaurantId(new RestaurantId(restaurantEntity.getRestaurantId()))
            .products(restaurantProducts)
            .active(restaurantEntity.getRestaurantActive())
            .build();
    }
}
