package com.detorresrc.foodorderingsystem.rest;

import com.detorresrc.foodorderingsystem.order.service.domain.dto.create.CreateOrderCommand;
import com.detorresrc.foodorderingsystem.order.service.domain.dto.create.CreateOrderResponse;
import com.detorresrc.foodorderingsystem.order.service.domain.dto.track.TrackOrderQuery;
import com.detorresrc.foodorderingsystem.order.service.domain.dto.track.TrackOrderResponse;
import com.detorresrc.foodorderingsystem.order.service.domain.ports.input.service.OrderApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/orders", produces = "application/vnd.api.v1+json")
@RequiredArgsConstructor
public class OrderController {
    private final OrderApplicationService orderApplicationService;

    @PostMapping
    public ResponseEntity<CreateOrderResponse> createOrder(@Valid @RequestBody CreateOrderCommand command) {
        log.info("Received create order request: {}", command);

        var response = orderApplicationService.createOrder(command);
        log.info("Order created successfully with tracking id {}", response.getOrderTrackingId());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(value = "/{trackingId}")
    public ResponseEntity<TrackOrderResponse> trackOrder(@PathVariable UUID trackingId) {
        return ResponseEntity.ok(orderApplicationService.trackOrder(
            TrackOrderQuery.builder()
                .orderTrackingId(trackingId)
                .build()
        ));
    }
}
