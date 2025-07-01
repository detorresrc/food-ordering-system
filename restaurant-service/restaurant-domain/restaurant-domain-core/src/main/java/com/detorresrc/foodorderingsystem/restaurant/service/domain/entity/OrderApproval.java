package com.detorresrc.foodorderingsystem.restaurant.service.domain.entity;

import com.detorresrc.foodorderingsystem.entity.BaseEntity;
import com.detorresrc.foodorderingsystem.restaurant.service.domain.valueobject.OrderApprovalId;
import com.detorresrc.foodorderingsystem.valueobject.OrderApprovalStatus;
import com.detorresrc.foodorderingsystem.valueobject.OrderId;
import com.detorresrc.foodorderingsystem.valueobject.RestaurantId;
import lombok.Getter;

@Getter
public class OrderApproval extends BaseEntity<OrderApprovalId> {
    private final RestaurantId restaurantId;
    private final OrderId orderId;
    private final OrderApprovalStatus approvalStatus;

    private OrderApproval(Builder builder) {
        super.setId(builder.orderApprovalId);
        restaurantId = builder.restaurantId;
        orderId = builder.orderId;
        approvalStatus = builder.approvalStatus;
    }

    public static Builder builder() {
        return Builder.builder();
    }

    public static final class Builder {
        private OrderApprovalId orderApprovalId;
        private RestaurantId restaurantId;
        private OrderId orderId;
        private OrderApprovalStatus approvalStatus;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder orderApprovalId(OrderApprovalId val) {
            orderApprovalId = val;
            return this;
        }

        public Builder restaurantId(RestaurantId val) {
            restaurantId = val;
            return this;
        }

        public Builder orderId(OrderId val) {
            orderId = val;
            return this;
        }

        public Builder approvalStatus(OrderApprovalStatus val) {
            approvalStatus = val;
            return this;
        }

        public OrderApproval build() {
            return new OrderApproval(this);
        }
    }
}
