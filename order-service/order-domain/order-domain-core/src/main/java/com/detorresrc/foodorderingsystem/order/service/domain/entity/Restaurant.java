package com.detorresrc.foodorderingsystem.order.service.domain.entity;

import com.detorresrc.foodorderingsystem.entity.AggregateRoot;
import com.detorresrc.foodorderingsystem.valueobject.RestaurantId;
import lombok.Getter;

import java.util.List;

@Getter
public class Restaurant extends AggregateRoot<RestaurantId> {
    private final List<Product> products;
    private final Boolean active;

    private Restaurant(Builder builder) {
        super.setId(builder.restaurantId);
        products = builder.products;
        active = builder.active;
    }

    public static final class Builder {
        private RestaurantId restaurantId;
        private List<Product> products;
        private Boolean active;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder restaurantId(RestaurantId val) {
            restaurantId = val;
            return this;
        }

        public Builder products(List<Product> val) {
            products = val;
            return this;
        }

        public Builder active(Boolean val) {
            active = val;
            return this;
        }

        public Restaurant build() {
            return new Restaurant(this);
        }
    }
}
