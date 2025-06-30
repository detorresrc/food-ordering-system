package com.detorresrc.foodorderingsystem.order.service.domain.dto.create;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderCommand {
    @NotNull
    private UUID customerId;
    @NotNull
    private UUID restaurantId;
    @NotNull
    private BigDecimal price;
    @NotNull
    private List<OrderItem> items;
    @NotNull
    private OrderAddress address;
}