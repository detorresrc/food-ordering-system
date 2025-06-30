package com.detorresrc.foodorderingsystem.payment.service.domain.ports.output.repository;

import com.detorresrc.foodorderingsystem.payment.service.domain.entity.CreditEntry;
import com.detorresrc.foodorderingsystem.valueobject.CustomerId;

import java.util.Optional;

public interface CreditEntryRepository {
    CreditEntry save(CreditEntry creditEntry);

    Optional<CreditEntry> findByCustomerId(CustomerId customerId);
}
