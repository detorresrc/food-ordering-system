package com.detorresrc.foodorderingsystem.order.service.domain.entity;

import com.detorresrc.foodorderingsystem.entity.BaseEntity;
import com.detorresrc.foodorderingsystem.order.service.domain.valueobject.OrderItemId;
import com.detorresrc.foodorderingsystem.valueobject.Money;
import com.detorresrc.foodorderingsystem.valueobject.OrderId;
import lombok.Getter;

@Getter
public class OrderItem extends BaseEntity<OrderItemId> {
    private final Product product;
    private final Integer quantity;
    private final Money price;
    private final Money subTotal;
    private OrderId orderId;

    private OrderItem(Builder builder) {
        super.setId(builder.orderItemId);
        product = builder.product;
        quantity = builder.quantity;
        price = builder.price;
        subTotal = builder.subTotal;
    }

    void initializeOrderItem(OrderId orderId, OrderItemId orderItemId) {
        this.orderId = orderId;
        super.setId(orderItemId);
    }

    boolean isPriceValid() {
        return price.isGreaterThanZero() && price.equals(product.getPrice()) && price.multiply(quantity).equals(subTotal);
    }

    public static final class Builder {
        private OrderItemId orderItemId;
        private Product product;
        private Integer quantity;
        private Money price;
        private Money subTotal;

        private Builder() {
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public Builder orderItemId(OrderItemId val) {
            orderItemId = val;
            return this;
        }

        public Builder product(Product val) {
            product = val;
            return this;
        }

        public Builder quantity(Integer val) {
            quantity = val;
            return this;
        }

        public Builder price(Money val) {
            price = val;
            return this;
        }

        public Builder subTotal(Money val) {
            subTotal = val;
            return this;
        }

        public OrderItem build() {
            return new OrderItem(this);
        }
    }
}
