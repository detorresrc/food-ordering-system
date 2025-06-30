package com.detorresrc.foodorderingsystem.payment.service.data.access.payment.repository;

import com.detorresrc.foodorderingsystem.payment.service.data.access.payment.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PaymentJpaRepository extends JpaRepository<PaymentEntity, UUID> {
    Optional<PaymentEntity> findByOrderId(UUID orderId);
}
