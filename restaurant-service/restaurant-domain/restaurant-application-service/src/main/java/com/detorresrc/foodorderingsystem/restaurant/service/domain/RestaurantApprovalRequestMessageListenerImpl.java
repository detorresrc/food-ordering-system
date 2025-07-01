package com.detorresrc.foodorderingsystem.restaurant.service.domain;

import com.detorresrc.foodorderingsystem.restaurant.service.domain.dto.RestaurantApprovalRequest;
import com.detorresrc.foodorderingsystem.restaurant.service.domain.event.OrderApprovalEvent;
import com.detorresrc.foodorderingsystem.restaurant.service.domain.ports.input.message.listener.RestaurantApprovalRequestMessageListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class RestaurantApprovalRequestMessageListenerImpl implements RestaurantApprovalRequestMessageListener {
    private final RestaurantApprovalRequestHelper restaurantApprovalRequestHelper;

    @Override
    public void approveOrder(RestaurantApprovalRequest restaurantApprovalRequest) {
        OrderApprovalEvent orderApprovalEvent =
            restaurantApprovalRequestHelper.persistOrderApproval(restaurantApprovalRequest);
        orderApprovalEvent.fire();
    }
}
