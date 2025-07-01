package com.detorresrc.foodorderingsystem.restaurant.service.messaging.publisher.kafka;

import com.detorresrc.foodorderingsystem.kafka.order.avro.model.RestaurantApprovalResponseAvroModel;
import com.detorresrc.foodorderingsystem.kafka.producer.service.KafkaProducer;
import com.detorresrc.foodorderingsystem.kafka.producer.service.KafkaProducerMessengerHelper;
import com.detorresrc.foodorderingsystem.restaurant.service.domain.config.RestaurantServiceConfigData;
import com.detorresrc.foodorderingsystem.restaurant.service.domain.event.OrderRejectedEvent;
import com.detorresrc.foodorderingsystem.restaurant.service.domain.ports.output.message.publisher.OrderRejectedMessagePublisher;
import com.detorresrc.foodorderingsystem.restaurant.service.messaging.mapper.RestaurantMessagingDataMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderRejectedKafkaMessagePublisher implements OrderRejectedMessagePublisher {
    private final RestaurantMessagingDataMapper restaurantMessagingDataMapper;
    private final KafkaProducer<String, RestaurantApprovalResponseAvroModel> kafkaProducer;
    private final KafkaProducerMessengerHelper kafkaProducerMessengerHelper;
    private final RestaurantServiceConfigData restaurantServiceConfigData;

    @Override
    public void publish(OrderRejectedEvent orderRejectedEvent) {
        String orderId = orderRejectedEvent.getOrderApproval().getOrderId().getValue().toString();

        log.info("Received OrderRejectedEvent for order id: {}", orderId);

        kafkaProducerMessengerHelper.sendWithCallback(
            kafkaProducer,
            restaurantServiceConfigData.getRestaurantApprovalResponseTopicName(),
            orderId,
            restaurantMessagingDataMapper
                .orderRejectedEventToRestaurantApprovalResponseAvroModel(orderRejectedEvent)
        );
    }

}
