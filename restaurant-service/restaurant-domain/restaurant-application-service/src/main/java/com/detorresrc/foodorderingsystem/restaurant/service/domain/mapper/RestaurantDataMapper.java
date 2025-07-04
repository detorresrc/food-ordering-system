package com.detorresrc.foodorderingsystem.restaurant.service.domain.mapper;

import com.detorresrc.foodorderingsystem.restaurant.service.domain.dto.RestaurantApprovalRequest;
import com.detorresrc.foodorderingsystem.restaurant.service.domain.entity.OrderDetail;
import com.detorresrc.foodorderingsystem.restaurant.service.domain.entity.Product;
import com.detorresrc.foodorderingsystem.restaurant.service.domain.entity.Restaurant;
import com.detorresrc.foodorderingsystem.valueobject.Money;
import com.detorresrc.foodorderingsystem.valueobject.OrderId;
import com.detorresrc.foodorderingsystem.valueobject.OrderStatus;
import com.detorresrc.foodorderingsystem.valueobject.RestaurantId;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class RestaurantDataMapper {
    public Restaurant restaurantApprovalRequestToRestaurant(RestaurantApprovalRequest
                                                                restaurantApprovalRequest) {
        return Restaurant.builder()
            .restaurantId(new RestaurantId(UUID.fromString(restaurantApprovalRequest.getRestaurantId())))
            .orderDetail(OrderDetail.builder()
                .orderId(new OrderId(UUID.fromString(restaurantApprovalRequest.getOrderId())))
                .products(restaurantApprovalRequest.getProducts().stream().map(
                        product -> Product.builder()
                            .productId(product.getId())
                            .quantity(product.getQuantity())
                            .build())
                    .collect(Collectors.toList()))
                .totalAmount(new Money(restaurantApprovalRequest.getPrice()))
                .orderStatus(OrderStatus.valueOf(restaurantApprovalRequest.getRestaurantOrderStatus().name()))
                .build())
            .build();
    }
}
