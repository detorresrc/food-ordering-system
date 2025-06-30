package com.detorresrc.foodorderingsystem.payment.service.data.access.creditentry.repository;

import com.detorresrc.foodorderingsystem.payment.service.data.access.creditentry.entity.CreditEntryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CreditEntryJpaRepository extends JpaRepository<CreditEntryEntity, UUID> {
    Optional<CreditEntryEntity> findByCustomerId(UUID customerId);
}
