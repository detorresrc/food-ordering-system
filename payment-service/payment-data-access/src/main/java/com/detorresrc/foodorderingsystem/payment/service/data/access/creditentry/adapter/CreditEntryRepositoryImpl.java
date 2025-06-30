package com.detorresrc.foodorderingsystem.payment.service.data.access.creditentry.adapter;

import com.detorresrc.foodorderingsystem.payment.service.data.access.creditentry.mapper.CreditEntryDataAccessMapper;
import com.detorresrc.foodorderingsystem.payment.service.data.access.creditentry.repository.CreditEntryJpaRepository;
import com.detorresrc.foodorderingsystem.payment.service.domain.entity.CreditEntry;
import com.detorresrc.foodorderingsystem.payment.service.domain.ports.output.repository.CreditEntryRepository;
import com.detorresrc.foodorderingsystem.valueobject.CustomerId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CreditEntryRepositoryImpl implements CreditEntryRepository {
    private final CreditEntryJpaRepository creditEntryJpaRepository;
    private final CreditEntryDataAccessMapper creditEntryDataAccessMapper;

    @Override
    public CreditEntry save(CreditEntry creditEntry) {
        return creditEntryDataAccessMapper
            .creditEntryEntityToCreditEntry(creditEntryJpaRepository
                .save(creditEntryDataAccessMapper.creditEntryToCreditEntryEntity(creditEntry)));
    }

    @Override
    public Optional<CreditEntry> findByCustomerId(CustomerId customerId) {
        return creditEntryJpaRepository
            .findByCustomerId(customerId.getValue())
            .map(creditEntryDataAccessMapper::creditEntryEntityToCreditEntry);
    }
}
