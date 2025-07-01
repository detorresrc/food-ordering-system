package com.detorresrc.foodorderingsystem.restaurant.service.domain.valueobject;

import com.detorresrc.foodorderingsystem.valueobject.BaseId;

import java.util.UUID;

public class OrderApprovalId extends BaseId<UUID> {
    public OrderApprovalId(UUID value) {
        super(value);
    }
}
