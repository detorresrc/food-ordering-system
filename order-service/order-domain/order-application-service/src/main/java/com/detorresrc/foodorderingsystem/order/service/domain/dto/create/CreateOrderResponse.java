package com.detorresrc.foodorderingsystem.order.service.domain.dto.create;

import com.detorresrc.foodorderingsystem.valueobject.OrderStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateOrderResponse {
    @NotNull
    private UUID orderTrackingId;
    @NotNull
    private OrderStatus orderStatus;
    @NotNull
    private String message;
}
