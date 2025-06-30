package com.detorresrc.foodorderingsystem.order.service.data.access.restaurant.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "order_restaurant_m_view", schema = "restaurants")
@Entity
@IdClass(RestaurantEntity.class)
public class RestaurantEntity {
    @Id
    private UUID restaurantId;
    @Id
    private UUID productId;

    private String name;
    private Boolean active;
    private String productName;
    private BigDecimal productPrice;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        RestaurantEntity that = (RestaurantEntity) o;
        return Objects.equals(restaurantId, that.restaurantId) && Objects.equals(productId, that.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(restaurantId, productId);
    }
}
