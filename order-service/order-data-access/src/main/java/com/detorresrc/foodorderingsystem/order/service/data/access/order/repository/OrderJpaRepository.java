package com.detorresrc.foodorderingsystem.order.service.data.access.order.repository;

import com.detorresrc.foodorderingsystem.order.service.data.access.order.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface OrderJpaRepository extends JpaRepository<OrderEntity, UUID> {
    Optional<OrderEntity> findByTrackingId(UUID trackingId);
}
