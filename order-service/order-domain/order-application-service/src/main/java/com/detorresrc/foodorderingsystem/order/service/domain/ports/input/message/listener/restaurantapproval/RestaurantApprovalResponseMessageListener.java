package com.detorresrc.foodorderingsystem.order.service.domain.ports.input.message.listener.restaurantapproval;

import com.detorresrc.foodorderingsystem.order.service.domain.dto.message.RestaurantApprovalResponse;

public interface RestaurantApprovalResponseMessageListener {
    void orderApproved(RestaurantApprovalResponse response);
    void orderRejected(RestaurantApprovalResponse response);
}
