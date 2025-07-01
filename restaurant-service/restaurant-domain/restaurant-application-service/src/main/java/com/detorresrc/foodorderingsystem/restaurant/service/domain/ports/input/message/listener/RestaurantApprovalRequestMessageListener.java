package com.detorresrc.foodorderingsystem.restaurant.service.domain.ports.input.message.listener;

import com.detorresrc.foodorderingsystem.restaurant.service.domain.dto.RestaurantApprovalRequest;

public interface RestaurantApprovalRequestMessageListener {
    void approveOrder(RestaurantApprovalRequest restaurantApprovalRequest);
}
