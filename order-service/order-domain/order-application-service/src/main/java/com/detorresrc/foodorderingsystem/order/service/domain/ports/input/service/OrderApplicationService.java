package com.detorresrc.foodorderingsystem.order.service.domain.ports.input.service;

import com.detorresrc.foodorderingsystem.order.service.domain.dto.create.CreateOrderCommand;
import com.detorresrc.foodorderingsystem.order.service.domain.dto.create.CreateOrderResponse;
import com.detorresrc.foodorderingsystem.order.service.domain.dto.track.TrackOrderQuery;
import com.detorresrc.foodorderingsystem.order.service.domain.dto.track.TrackOrderResponse;
import jakarta.validation.Valid;

public interface OrderApplicationService {
    CreateOrderResponse createOrder(@Valid CreateOrderCommand command);
    TrackOrderResponse trackOrder(@Valid TrackOrderQuery query);
}
