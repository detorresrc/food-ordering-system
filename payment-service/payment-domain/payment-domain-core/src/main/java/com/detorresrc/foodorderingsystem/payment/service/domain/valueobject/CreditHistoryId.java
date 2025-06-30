package com.detorresrc.foodorderingsystem.payment.service.domain.valueobject;

import com.detorresrc.foodorderingsystem.valueobject.BaseId;

import java.util.UUID;

public class CreditHistoryId extends BaseId<UUID> {
    public CreditHistoryId(UUID value) {
        super(value);
    }
}
