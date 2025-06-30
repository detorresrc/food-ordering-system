package com.detorresrc.foodorderingsystem.payment.service.domain.valueobject;

import com.detorresrc.foodorderingsystem.valueobject.BaseId;

import java.util.UUID;

public class PaymentId extends BaseId<UUID> {
    public PaymentId(UUID value) {
        super(value);
    }
}
