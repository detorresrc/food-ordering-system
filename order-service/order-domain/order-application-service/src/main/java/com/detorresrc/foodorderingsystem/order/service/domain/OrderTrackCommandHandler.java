package com.detorresrc.foodorderingsystem.order.service.domain;

import com.detorresrc.foodorderingsystem.order.service.domain.dto.create.CreateOrderCommand;
import com.detorresrc.foodorderingsystem.order.service.domain.dto.create.CreateOrderResponse;
import com.detorresrc.foodorderingsystem.order.service.domain.dto.track.TrackOrderQuery;
import com.detorresrc.foodorderingsystem.order.service.domain.dto.track.TrackOrderResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OrderTrackCommandHandler {
    public TrackOrderResponse trackOrder(TrackOrderQuery query){
        return null;
    }
}
