package com.detorresrc.foodorderingsystem.payment.service.domain.valueobject;

import com.detorresrc.foodorderingsystem.valueobject.BaseId;

import java.util.UUID;

public class CreditEntryId extends BaseId<UUID> {
    public CreditEntryId(UUID value) {
        super(value);
    }
}
