package com.detorresrc.foodorderingsystem.restaurant.service.domain.event;

import com.detorresrc.foodorderingsystem.event.DomainEvent;
import com.detorresrc.foodorderingsystem.restaurant.service.domain.entity.OrderApproval;
import com.detorresrc.foodorderingsystem.valueobject.RestaurantId;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.ZonedDateTime;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class OrderApprovalEvent implements DomainEvent<OrderApproval> {
    private final OrderApproval orderApproval;
    private final RestaurantId restaurantId;
    private final List<String> failureMessages;
    private final ZonedDateTime createdAt;

    @Override
    public void fire() {
    }
}
