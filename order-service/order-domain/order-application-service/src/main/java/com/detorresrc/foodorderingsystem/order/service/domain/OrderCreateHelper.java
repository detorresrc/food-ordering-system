package com.detorresrc.foodorderingsystem.order.service.domain;

import com.detorresrc.foodorderingsystem.order.service.domain.dto.create.CreateOrderCommand;
import com.detorresrc.foodorderingsystem.order.service.domain.entity.Customer;
import com.detorresrc.foodorderingsystem.order.service.domain.entity.Order;
import com.detorresrc.foodorderingsystem.order.service.domain.entity.Restaurant;
import com.detorresrc.foodorderingsystem.order.service.domain.event.OrderCreatedEvent;
import com.detorresrc.foodorderingsystem.order.service.domain.exception.OrderDomainException;
import com.detorresrc.foodorderingsystem.order.service.domain.mapper.OrderDataMapper;
import com.detorresrc.foodorderingsystem.order.service.domain.ports.output.repository.CustomerRepository;
import com.detorresrc.foodorderingsystem.order.service.domain.ports.output.repository.OrderRepository;
import com.detorresrc.foodorderingsystem.order.service.domain.ports.output.repository.RestaurantRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderCreateHelper {
    private final OrderDomainService orderDomainService;
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final RestaurantRepository restaurantRepository;
    private final OrderDataMapper orderDataMapper;

    @Transactional
    public OrderCreatedEvent persistOrder(CreateOrderCommand command) {
        checkCustomer(command.getCustomerId());
        var order = orderDataMapper.createOrderCommandToOrder(command);
        var orderCreateEvent = orderDomainService.validateAndInitializeOrder(
            order,
            checkRestaurant(command));
        saveOrder(order);
        log.info("Order with id {} created successfully", order.getId().getValue());
        return orderCreateEvent;
    }

    private Restaurant checkRestaurant(CreateOrderCommand command) {
        var restaurant = restaurantRepository.findRestaurantInformation(orderDataMapper.createOrderCommandToRestaurant(command));
        if (restaurant.isEmpty()) {
            log.warn("Restaurant with id {} not found", command.getRestaurantId());
            throw new OrderDomainException("Restaurant with id " + command.getRestaurantId() + " not found");
        }

        return restaurant.get();
    }

    private void checkCustomer(@NotNull UUID customerId) {
        Optional<Customer> customer = customerRepository.findCustomer(customerId);
        if (customer.isEmpty()) {
            log.warn("Customer with id {} not found", customerId);
            throw new OrderDomainException("Customer with id " + customerId + " not found");
        }
    }

    private Order saveOrder(Order order) {
        var savedOrder = orderRepository.save(order);
        if (savedOrder == null) {
            log.error("Could not save order with id {}", order.getId().getValue());
            throw new OrderDomainException("Could not save order with id " + order.getId().getValue());
        }
        log.info("Order with id {} saved successfully", savedOrder.getId().getValue());
        return savedOrder;
    }
}
