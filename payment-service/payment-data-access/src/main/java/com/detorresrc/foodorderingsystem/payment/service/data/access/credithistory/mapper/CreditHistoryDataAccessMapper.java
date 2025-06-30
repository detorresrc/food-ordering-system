package com.detorresrc.foodorderingsystem.payment.service.data.access.credithistory.mapper;

import com.detorresrc.foodorderingsystem.payment.service.data.access.credithistory.entity.CreditHistoryEntity;
import com.detorresrc.foodorderingsystem.payment.service.domain.entity.CreditHistory;
import com.detorresrc.foodorderingsystem.payment.service.domain.valueobject.CreditHistoryId;
import com.detorresrc.foodorderingsystem.valueobject.CustomerId;
import com.detorresrc.foodorderingsystem.valueobject.Money;
import org.springframework.stereotype.Component;

@Component
public class CreditHistoryDataAccessMapper {
    public CreditHistory creditHistoryEntityToCreditHistory(CreditHistoryEntity creditHistoryEntity) {
        return CreditHistory.builder()
            .creditHistoryId(new CreditHistoryId(creditHistoryEntity.getId()))
            .customerId(new CustomerId(creditHistoryEntity.getCustomerId()))
            .amount(new Money(creditHistoryEntity.getAmount()))
            .transactionType(creditHistoryEntity.getType())
            .build();
    }

    public CreditHistoryEntity creditHistoryToCreditHistoryEntity(CreditHistory creditHistory) {
        return CreditHistoryEntity.builder()
            .id(creditHistory.getId().getValue())
            .customerId(creditHistory.getCustomerId().getValue())
            .amount(creditHistory.getAmount().getValue())
            .type(creditHistory.getTransactionType())
            .build();
    }
}
