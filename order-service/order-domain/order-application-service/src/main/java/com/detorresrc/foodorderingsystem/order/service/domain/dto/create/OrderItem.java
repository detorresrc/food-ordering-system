package com.detorresrc.foodorderingsystem.order.service.domain.dto.create;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class OrderItem {
    @NotNull
    private UUID productId;
    @NotNull
    private Integer quantity;
    @NotNull
    private BigDecimal price;
    @NotNull
    private BigDecimal subTotal;
}
