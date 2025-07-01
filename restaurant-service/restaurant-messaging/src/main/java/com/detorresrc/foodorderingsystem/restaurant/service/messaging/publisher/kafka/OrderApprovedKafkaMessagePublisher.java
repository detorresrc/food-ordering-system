package com.detorresrc.foodorderingsystem.restaurant.service.messaging.publisher.kafka;

import com.detorresrc.foodorderingsystem.kafka.order.avro.model.RestaurantApprovalResponseAvroModel;
import com.detorresrc.foodorderingsystem.kafka.producer.service.KafkaProducer;
import com.detorresrc.foodorderingsystem.kafka.producer.service.KafkaProducerMessengerHelper;
import com.detorresrc.foodorderingsystem.restaurant.service.domain.config.RestaurantServiceConfigData;
import com.detorresrc.foodorderingsystem.restaurant.service.domain.event.OrderApprovedEvent;
import com.detorresrc.foodorderingsystem.restaurant.service.domain.ports.output.message.publisher.OrderApprovedMessagePublisher;
import com.detorresrc.foodorderingsystem.restaurant.service.messaging.mapper.RestaurantMessagingDataMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderApprovedKafkaMessagePublisher implements OrderApprovedMessagePublisher {
    private final RestaurantMessagingDataMapper restaurantMessagingDataMapper;
    private final KafkaProducer<String, RestaurantApprovalResponseAvroModel> kafkaProducer;
    private final KafkaProducerMessengerHelper kafkaProducerMessengerHelper;
    private final RestaurantServiceConfigData restaurantServiceConfigData;

    @Override
    public void publish(OrderApprovedEvent orderApprovedEvent) {
        String orderId = orderApprovedEvent.getOrderApproval().getOrderId().getValue().toString();

        log.info("Received OrderApprovedEvent for order id: {}", orderId);

        kafkaProducerMessengerHelper.sendWithCallback(
            kafkaProducer,
            restaurantServiceConfigData.getRestaurantApprovalResponseTopicName(),
            orderId,
            restaurantMessagingDataMapper
                .orderApprovedEventToRestaurantApprovalResponseAvroModel(orderApprovedEvent)
        );
    }

}
