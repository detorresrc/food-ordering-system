package com.detorresrc.foodorderingsystem.order.service.domain.entity;

import com.detorresrc.foodorderingsystem.entity.AggregateRoot;
import com.detorresrc.foodorderingsystem.order.service.domain.exception.OrderDomainException;
import com.detorresrc.foodorderingsystem.order.service.domain.valueobject.OrderItemId;
import com.detorresrc.foodorderingsystem.order.service.domain.valueobject.StreetAddress;
import com.detorresrc.foodorderingsystem.order.service.domain.valueobject.TrackingId;
import com.detorresrc.foodorderingsystem.valueobject.*;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
public class Order extends AggregateRoot<OrderId> {
    private final CustomerId customerId;
    private final RestaurantId restaurantId;
    private final StreetAddress deliveryAddress;
    private final Money price;
    private final List<OrderItem> items;

    private TrackingId trackingId;
    private OrderStatus orderStatus;
    private List<String> failureMessages;

    public void initializeOrder() {
        setId(new OrderId(UUID.randomUUID()));
        trackingId = new TrackingId(UUID.randomUUID());
        orderStatus = OrderStatus.PENDING;
        initializeOrderItems();
    }

    public void validateOrder() {
        validateInitialOrder();
        validateTotalPrice();
        validateItemsPrice();
    }

    public void pay() {
        if(orderStatus != OrderStatus.PENDING)
            throw new OrderDomainException("Order is not in a valid state for payment: " + orderStatus);

        orderStatus = OrderStatus.PAID;
    }

    public void approve() {
        if(orderStatus != OrderStatus.PAID)
            throw new OrderDomainException("Order is not in a valid state for approval: " + orderStatus);

        orderStatus = OrderStatus.APPROVED;
    }

    public void initCancel(List<String> failureMessages) {
        if(orderStatus != OrderStatus.PAID)
            throw new OrderDomainException("Order is not in a valid state for cancellation: " + orderStatus);

        orderStatus = OrderStatus.CANCELLING;
        updateFailureMessages(failureMessages);
    }

    public void cancel(List<String> failureMessages) {
        if(!(orderStatus == OrderStatus.CANCELLING || orderStatus == OrderStatus.PENDING))
            throw new OrderDomainException("Order is not in a valid state for cancellation: " + orderStatus);

        orderStatus = OrderStatus.CANCELLED;
        updateFailureMessages(failureMessages);
    }

    private void updateFailureMessages(List<String> failureMessages) {
        if(this.failureMessages!=null && failureMessages!=null){
            this.failureMessages.addAll(failureMessages.stream().filter(m -> !m.isEmpty()).toList());
        }
        if(this.failureMessages == null)
            this.failureMessages = failureMessages;
    }

    private void validateItemsPrice() {
        var orderItemsTotal = items.stream()
            .map(this::validateItemPrice)
            .reduce(Money.ZERO, Money::add);

        if(!price.equals(orderItemsTotal))
            throw new OrderDomainException("Order total price must be equal to the sum of item prices");
    }

    private Money validateItemPrice(OrderItem item) {
        if(!item.isPriceValid())
            throw new OrderDomainException("Order item price is invalid: " + item.getId());

        return item.getSubTotal();
    }

    private void validateTotalPrice() {
        if(price == null || !price.isGreaterThanZero())
            throw new OrderDomainException("Order total price must be greater than zero");
    }

    private void validateInitialOrder() {
        if(orderStatus!=null || getId()!=null)
            throw new OrderDomainException("Order is already initialized");
    }

    private void initializeOrderItems() {
        long itemId = 1;
        for(OrderItem orderItem: items) {
            orderItem.initializeOrderItem(super.getId(), new OrderItemId(itemId++));
        }
    }

    private Order(Builder builder) {
        super.setId(builder.orderId);
        customerId = builder.customerId;
        restaurantId = builder.restaurantId;
        deliveryAddress = builder.deliveryAddress;
        price = builder.price;
        items = builder.items;
        trackingId = builder.trackingId;
        orderStatus = builder.orderStatus;
        failureMessages = builder.failureMessages;
    }

    public static final class Builder {
        private OrderId orderId;
        private CustomerId customerId;
        private RestaurantId restaurantId;
        private StreetAddress deliveryAddress;
        private Money price;
        private List<OrderItem> items;
        private TrackingId trackingId;
        private OrderStatus orderStatus;
        private List<String> failureMessages;

        private Builder() {
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public Builder orderId(OrderId val) {
            orderId = val;
            return this;
        }

        public Builder customerId(CustomerId val) {
            customerId = val;
            return this;
        }

        public Builder restaurantId(RestaurantId val) {
            restaurantId = val;
            return this;
        }

        public Builder deliveryAddress(StreetAddress val) {
            deliveryAddress = val;
            return this;
        }

        public Builder price(Money val) {
            price = val;
            return this;
        }

        public Builder items(List<OrderItem> val) {
            items = val;
            return this;
        }

        public Builder trackingId(TrackingId val) {
            trackingId = val;
            return this;
        }

        public Builder orderStatus(OrderStatus val) {
            orderStatus = val;
            return this;
        }

        public Builder failureMessages(List<String> val) {
            failureMessages = val;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }
}
