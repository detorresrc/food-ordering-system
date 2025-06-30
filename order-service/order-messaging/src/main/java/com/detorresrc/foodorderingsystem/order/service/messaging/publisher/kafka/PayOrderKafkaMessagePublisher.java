package com.detorresrc.foodorderingsystem.order.service.messaging.publisher.kafka;

import com.detorresrc.foodorderingsystem.kafka.order.avro.model.RestaurantApprovalRequestAvroModel;
import com.detorresrc.foodorderingsystem.kafka.producer.service.KafkaProducer;
import com.detorresrc.foodorderingsystem.order.service.domain.event.OrderPaidEvent;
import com.detorresrc.foodorderingsystem.order.service.domain.ports.output.message.publisher.restaurantapproval.OrderPaidRestaurantRequestMessagePublisher;
import com.detorresrc.foodorderingsystem.order.service.messaging.mapper.OrderMessagingDataMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PayOrderKafkaMessagePublisher implements OrderPaidRestaurantRequestMessagePublisher {
    private final OrderMessagingDataMapper orderMessagingDataMapper;
    private final KafkaProducer<String, RestaurantApprovalRequestAvroModel> kafkaProducer;
    private final OrderKafkaMessengerHelper orderKafkaMessengerHelper;

    @Override
    public void publish(OrderPaidEvent domainEvent) {
        var orderId = domainEvent.getOrder().getId().getValue().toString();

        var restaurantApprovalRequestAvroModel =
            orderMessagingDataMapper.orderPaidEventToRestaurantApprovalRequestAvroModel(domainEvent);

        orderKafkaMessengerHelper.sendWithCallback(
            kafkaProducer,
            orderId,
            restaurantApprovalRequestAvroModel
        );
    }
}
