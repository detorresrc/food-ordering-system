package com.detorresrc.foodorderingsystem.restaurant.service.domain.entity;

import com.detorresrc.foodorderingsystem.entity.BaseEntity;
import com.detorresrc.foodorderingsystem.valueobject.Money;
import com.detorresrc.foodorderingsystem.valueobject.ProductId;
import lombok.Getter;

@Getter
public class Product extends BaseEntity<ProductId> {
    private String name;
    private Money price;
    private final Integer quantity;
    private boolean available;

    private Product(Builder builder) {
        super.setId(builder.productId);
        name = builder.name;
        price = builder.price;
        quantity = builder.quantity;
        available = builder.available;
    }

    public void updateWithConfirmedNamePriceAndAvailability(String name, Money price, boolean available) {
        this.name = name;
        this.price = price;
        this.available = available;
    }

    public static Builder builder() {
        return Builder.builder();
    }

    public static final class Builder {
        private ProductId productId;
        private String name;
        private Money price;
        private Integer quantity;
        private boolean available;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder productId(ProductId val) {
            productId = val;
            return this;
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder price(Money val) {
            price = val;
            return this;
        }

        public Builder quantity(Integer val) {
            quantity = val;
            return this;
        }

        public Builder available(boolean val) {
            available = val;
            return this;
        }

        public Product build() {
            return new Product(this);
        }
    }
}
