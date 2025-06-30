package com.detorresrc.foodorderingsystem.payment.service.domain.ports.output.repository;

import com.detorresrc.foodorderingsystem.payment.service.domain.entity.CreditHistory;
import com.detorresrc.foodorderingsystem.valueobject.CustomerId;

import java.util.List;
import java.util.Optional;

public interface CreditHistoryRepository {
    CreditHistory save(CreditHistory creditHistory);

    Optional<List<CreditHistory>> findByCustomerId(CustomerId customerId);
}
