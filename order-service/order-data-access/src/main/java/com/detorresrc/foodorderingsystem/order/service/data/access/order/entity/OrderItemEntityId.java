package com.detorresrc.foodorderingsystem.order.service.data.access.order.entity;

import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderItemEntityId implements Serializable {
    private Long id;
    private OrderEntity order;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        OrderItemEntityId that = (OrderItemEntityId) o;
        return Objects.equals(id, that.id) && Objects.equals(order, that.order);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, order);
    }
}
