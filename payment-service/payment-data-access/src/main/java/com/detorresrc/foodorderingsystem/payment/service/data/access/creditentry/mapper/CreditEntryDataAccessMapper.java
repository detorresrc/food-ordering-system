package com.detorresrc.foodorderingsystem.payment.service.data.access.creditentry.mapper;

import com.detorresrc.foodorderingsystem.payment.service.data.access.creditentry.entity.CreditEntryEntity;
import com.detorresrc.foodorderingsystem.payment.service.domain.entity.CreditEntry;
import com.detorresrc.foodorderingsystem.payment.service.domain.valueobject.CreditEntryId;
import com.detorresrc.foodorderingsystem.valueobject.CustomerId;
import com.detorresrc.foodorderingsystem.valueobject.Money;
import org.springframework.stereotype.Component;

@Component
public class CreditEntryDataAccessMapper {
    public CreditEntry creditEntryEntityToCreditEntry(CreditEntryEntity creditEntryEntity) {
        return CreditEntry.builder()
            .creditEntryId(new CreditEntryId(creditEntryEntity.getId()))
            .customerId(new CustomerId(creditEntryEntity.getCustomerId()))
            .totalCreditAmount(new Money(creditEntryEntity.getTotalCreditAmount()))
            .build();
    }

    public CreditEntryEntity creditEntryToCreditEntryEntity(CreditEntry creditEntry) {
        return CreditEntryEntity.builder()
            .id(creditEntry.getId().getValue())
            .customerId(creditEntry.getCustomerId().getValue())
            .totalCreditAmount(creditEntry.getTotalCreditAmount().getValue())
            .build();
    }
}
