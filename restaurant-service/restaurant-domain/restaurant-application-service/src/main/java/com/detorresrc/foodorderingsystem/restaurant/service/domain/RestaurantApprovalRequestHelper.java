package com.detorresrc.foodorderingsystem.restaurant.service.domain;

import com.detorresrc.foodorderingsystem.restaurant.service.domain.dto.RestaurantApprovalRequest;
import com.detorresrc.foodorderingsystem.restaurant.service.domain.entity.Restaurant;
import com.detorresrc.foodorderingsystem.restaurant.service.domain.event.OrderApprovalEvent;
import com.detorresrc.foodorderingsystem.restaurant.service.domain.exception.RestaurantNotFoundException;
import com.detorresrc.foodorderingsystem.restaurant.service.domain.mapper.RestaurantDataMapper;
import com.detorresrc.foodorderingsystem.restaurant.service.domain.ports.output.message.publisher.OrderApprovedMessagePublisher;
import com.detorresrc.foodorderingsystem.restaurant.service.domain.ports.output.message.publisher.OrderRejectedMessagePublisher;
import com.detorresrc.foodorderingsystem.restaurant.service.domain.ports.output.repository.OrderApprovalRepository;
import com.detorresrc.foodorderingsystem.restaurant.service.domain.ports.output.repository.RestaurantRepository;
import com.detorresrc.foodorderingsystem.valueobject.OrderId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class RestaurantApprovalRequestHelper {
    private final RestaurantDomainService restaurantDomainService;
    private final RestaurantDataMapper restaurantDataMapper;
    private final RestaurantRepository restaurantRepository;
    private final OrderApprovalRepository orderApprovalRepository;
    private final OrderApprovedMessagePublisher orderApprovedMessagePublisher;
    private final OrderRejectedMessagePublisher orderRejectedMessagePublisher;

    @Transactional
    public OrderApprovalEvent persistOrderApproval(RestaurantApprovalRequest restaurantApprovalRequest) {
        log.info("Processing restaurant approval for order id: {}", restaurantApprovalRequest.getOrderId());
        List<String> failureMessages = new ArrayList<>();
        Restaurant restaurant = findRestaurant(restaurantApprovalRequest);
        OrderApprovalEvent orderApprovalEvent =
            restaurantDomainService.validateOrder(
                restaurant,
                failureMessages,
                orderApprovedMessagePublisher,
                orderRejectedMessagePublisher);
        orderApprovalRepository.save(restaurant.getOrderApproval());
        return orderApprovalEvent;
    }

    private Restaurant findRestaurant(RestaurantApprovalRequest restaurantApprovalRequest) {
        Restaurant restaurant = restaurantDataMapper
            .restaurantApprovalRequestToRestaurant(restaurantApprovalRequest);
        Optional<Restaurant> restaurantResult = restaurantRepository.findRestaurantInformation(restaurant);
        if (restaurantResult.isEmpty()) {
            log.error("Restaurant with id " + restaurant.getId().getValue() + " not found!");
            throw new RestaurantNotFoundException("Restaurant with id " + restaurant.getId().getValue() +
                " not found!");
        }

        Restaurant restaurantEntity = restaurantResult.get();
        restaurant.setActive(restaurantEntity.isActive());
        restaurant.getOrderDetail().getProducts().forEach(product ->
            restaurantEntity.getOrderDetail().getProducts().forEach(p -> {
                if (p.getId().equals(product.getId())) {
                    product.updateWithConfirmedNamePriceAndAvailability(p.getName(), p.getPrice(), p.isAvailable());
                }
            }));
        restaurant.getOrderDetail().setId(new OrderId(UUID.fromString(restaurantApprovalRequest.getOrderId())));

        return restaurant;
    }
}
