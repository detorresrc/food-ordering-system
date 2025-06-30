package com.detorresrc.foodorderingsystem.order.service.messaging.listener.kafka;

import com.detorresrc.foodorderingsystem.kafka.consumer.KafkaConsumer;
import com.detorresrc.foodorderingsystem.kafka.order.avro.model.OrderApprovalStatus;
import com.detorresrc.foodorderingsystem.kafka.order.avro.model.RestaurantApprovalResponseAvroModel;
import com.detorresrc.foodorderingsystem.order.service.domain.ports.input.message.listener.restaurantapproval.RestaurantApprovalResponseMessageListener;
import com.detorresrc.foodorderingsystem.order.service.messaging.mapper.OrderMessagingDataMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.detorresrc.foodorderingsystem.order.service.domain.entity.Order.FAILURE_MESSAGE_DELIMITER;

@Slf4j
@Component
@RequiredArgsConstructor
public class RestaurantApprovalResponseKafkaListener implements KafkaConsumer<RestaurantApprovalResponseAvroModel> {
    private final RestaurantApprovalResponseMessageListener restaurantApprovalResponseKafkaListener;
    private final OrderMessagingDataMapper orderMessagingDataMapper;

    @Override
    @KafkaListener(id = "${kafka-consumer-config.restaurant-approval-consumer-group-id}", topics = "${restaurant-service.restaurant-approval-response-topic-name}")
    public void receive(
        @Payload List<RestaurantApprovalResponseAvroModel> messages,
        @Header(KafkaHeaders.RECEIVED_KEY) List<String> keys,
        @Header(KafkaHeaders.RECEIVED_PARTITION) List<Integer> partitions,
        @Header(KafkaHeaders.OFFSET) List<Long> offsets) {

        messages.forEach(avroModel -> {
            if (avroModel.getOrderApprovalStatus() == OrderApprovalStatus.APPROVED) {
                log.info("Processing approved order for order id: {}",
                    avroModel.getOrderId());

                restaurantApprovalResponseKafkaListener.orderApproved(orderMessagingDataMapper.approvalResponseAvroModelToApprovalResponse(avroModel));
            } else if (avroModel.getOrderApprovalStatus() == OrderApprovalStatus.REJECTED) {
                log.info("Processing rejected order for order id: {}, with failure messages: {}",
                    avroModel.getOrderId(),
                    String.join(FAILURE_MESSAGE_DELIMITER, avroModel.getFailureMessages()));

                restaurantApprovalResponseKafkaListener.orderRejected(orderMessagingDataMapper.approvalResponseAvroModelToApprovalResponse(avroModel));
            }
        });
    }
}
