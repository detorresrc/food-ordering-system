package com.detorresrc.foodorderingsystem.order.service.data.access.order.adapter;

import com.detorresrc.foodorderingsystem.order.service.data.access.order.mapper.OrderDataAccessMapper;
import com.detorresrc.foodorderingsystem.order.service.data.access.order.repository.OrderJpaRepository;
import com.detorresrc.foodorderingsystem.order.service.domain.entity.Order;
import com.detorresrc.foodorderingsystem.order.service.domain.ports.output.repository.OrderRepository;
import com.detorresrc.foodorderingsystem.order.service.domain.valueobject.TrackingId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepository {
    private final OrderJpaRepository orderJpaRepository;
    private final OrderDataAccessMapper orderDataAccessMapper;

    @Override
    public Order save(Order order) {
        return orderDataAccessMapper.orderEntityToOrder(
            orderJpaRepository.save(orderDataAccessMapper.orderToOrderEntity(order))
        );
    }

    @Override
    public Optional<Order> findByTrackingId(TrackingId trackingId) {
        return orderJpaRepository.findByTrackingId(trackingId.getValue())
            .map(orderDataAccessMapper::orderEntityToOrder);
    }
}
