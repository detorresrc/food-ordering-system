package com.detorresrc.foodorderingsystem.restaurant.service.domain.entity;

import com.detorresrc.foodorderingsystem.entity.AggregateRoot;
import com.detorresrc.foodorderingsystem.restaurant.service.domain.valueobject.OrderApprovalId;
import com.detorresrc.foodorderingsystem.valueobject.Money;
import com.detorresrc.foodorderingsystem.valueobject.OrderApprovalStatus;
import com.detorresrc.foodorderingsystem.valueobject.OrderStatus;
import com.detorresrc.foodorderingsystem.valueobject.RestaurantId;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
public class Restaurant extends AggregateRoot<RestaurantId> {
    private OrderDetail orderDetail;
    private OrderApproval orderApproval;
    private boolean active;

    private Restaurant(Builder builder) {
        super.setId(builder.restaurantId);
        orderApproval = builder.orderApproval;
        setActive(builder.active);
        orderDetail = builder.orderDetail;
    }

    public void validateOrder(List<String> failureMessages) {
        if (orderDetail.getOrderStatus() != OrderStatus.PAID) {
            failureMessages.add("Payment is not completed for order: " + orderDetail.getId());
        }
        Money totalAmount = orderDetail.getProducts().stream().map(product -> {
            if (!product.isAvailable()) {
                failureMessages.add("Product with id: " + product.getId().getValue()
                    + " is not available");
            }
            return product.getPrice().multiply(product.getQuantity());
        }).reduce(Money.ZERO, Money::add);

        if (!totalAmount.equals(orderDetail.getTotalAmount())) {
            failureMessages.add("Price total is not correct for order: " + orderDetail.getId());
        }
    }

    public void constructOrderApproval(OrderApprovalStatus orderApprovalStatus) {
        this.orderApproval = OrderApproval.builder()
            .orderApprovalId(new OrderApprovalId(UUID.randomUUID()))
            .restaurantId(this.getId())
            .orderId(this.getOrderDetail().getId())
            .approvalStatus(orderApprovalStatus)
            .build();
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public static Builder builder() {
        return Builder.builder();
    }

    public static final class Builder {
        private RestaurantId restaurantId;
        private OrderApproval orderApproval;
        private boolean active;
        private OrderDetail orderDetail;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder restaurantId(RestaurantId val) {
            restaurantId = val;
            return this;
        }

        public Builder orderApproval(OrderApproval val) {
            orderApproval = val;
            return this;
        }

        public Builder active(boolean val) {
            active = val;
            return this;
        }

        public Builder orderDetail(OrderDetail val) {
            orderDetail = val;
            return this;
        }

        public Restaurant build() {
            return new Restaurant(this);
        }
    }
}
