package com.detorresrc.foodorderingsystem.order.service.domain.valueobject;

import com.detorresrc.foodorderingsystem.valueobject.BaseId;

import java.util.UUID;

public class TrackingId extends BaseId<UUID> {
    public TrackingId(UUID value) {
        super(value);
    }
}
