package com.detorresrc.foodorderingsystem.restaurant.service.data.access.restaurant.adapter;

import com.detorresrc.foodorderingsystem.restaurant.service.data.access.restaurant.mapper.RestaurantDataAccessMapper;
import com.detorresrc.foodorderingsystem.restaurant.service.data.access.restaurant.repository.OrderApprovalJpaRepository;
import com.detorresrc.foodorderingsystem.restaurant.service.domain.entity.OrderApproval;
import com.detorresrc.foodorderingsystem.restaurant.service.domain.ports.output.repository.OrderApprovalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderApprovalRepositoryImpl implements OrderApprovalRepository {
    private final OrderApprovalJpaRepository orderApprovalJpaRepository;
    private final RestaurantDataAccessMapper restaurantDataAccessMapper;

    @Override
    public OrderApproval save(OrderApproval orderApproval) {
        return restaurantDataAccessMapper
            .orderApprovalEntityToOrderApproval(orderApprovalJpaRepository
                .save(restaurantDataAccessMapper.orderApprovalToOrderApprovalEntity(orderApproval)));
    }
}
