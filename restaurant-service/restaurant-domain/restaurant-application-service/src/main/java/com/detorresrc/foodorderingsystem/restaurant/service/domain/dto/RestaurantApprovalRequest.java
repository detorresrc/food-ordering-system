package com.detorresrc.foodorderingsystem.restaurant.service.domain.dto;

import com.detorresrc.foodorderingsystem.restaurant.service.domain.entity.Product;
import com.detorresrc.foodorderingsystem.valueobject.RestaurantOrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class RestaurantApprovalRequest {
    private String id;
    private String sagaId;
    private String restaurantId;
    private String orderId;
    private RestaurantOrderStatus restaurantOrderStatus;
    private List<Product> products;
    private BigDecimal price;
    private Instant createdAt;
}
