package com.detorresrc.foodorderingsystem.order.service.domain;

import com.detorresrc.foodorderingsystem.order.service.domain.dto.track.TrackOrderQuery;
import com.detorresrc.foodorderingsystem.order.service.domain.dto.track.TrackOrderResponse;
import com.detorresrc.foodorderingsystem.order.service.domain.exception.OrderNotFoundException;
import com.detorresrc.foodorderingsystem.order.service.domain.mapper.OrderDataMapper;
import com.detorresrc.foodorderingsystem.order.service.domain.ports.output.repository.OrderRepository;
import com.detorresrc.foodorderingsystem.order.service.domain.valueobject.TrackingId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderTrackCommandHandler {
    private final OrderDataMapper orderDataMapper;
    private final OrderRepository orderRepository;

    @Transactional(readOnly = true)
    public TrackOrderResponse trackOrder(TrackOrderQuery query) {
        return orderRepository.findByTrackingId(new TrackingId(query.getOrderTrackingId()))
            .map(orderDataMapper::orderToTrackOrderResponse)
            .orElseThrow(() -> {
                var message = String.format("Order with tracking id %s not found", query.getOrderTrackingId());
                log.error(message);
                return new OrderNotFoundException(message);
            });
    }
}