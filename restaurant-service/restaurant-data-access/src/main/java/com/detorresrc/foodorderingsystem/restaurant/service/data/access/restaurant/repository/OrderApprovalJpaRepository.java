package com.detorresrc.foodorderingsystem.restaurant.service.data.access.restaurant.repository;

import com.detorresrc.foodorderingsystem.restaurant.service.data.access.restaurant.entity.OrderApprovalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderApprovalJpaRepository extends JpaRepository<OrderApprovalEntity, UUID> {
}
