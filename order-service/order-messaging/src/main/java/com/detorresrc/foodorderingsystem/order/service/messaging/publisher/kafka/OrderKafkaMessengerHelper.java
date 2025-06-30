package com.detorresrc.foodorderingsystem.order.service.messaging.publisher.kafka;

import com.detorresrc.foodorderingsystem.kafka.producer.service.KafkaProducer;
import com.detorresrc.foodorderingsystem.order.service.domain.config.OrderServiceConfigData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.specific.SpecificRecordBase;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class OrderKafkaMessengerHelper {
    private final OrderServiceConfigData orderServiceConfigData;

    public <T> void callback(
        SendResult<String, T> result,
        Throwable throwable, String orderId) {

        String requestAvroModelName = result.getProducerRecord().value().getClass().getSimpleName();
        var payload = result.getProducerRecord().value();

        if (throwable != null) {
            log.error("Error while sending {} " +
                    "message: {} to topic: {}",
                requestAvroModelName,
                payload.toString(),
                result.getRecordMetadata().topic()
            );
        } else {
            log.info("Received successful response from Kafka for order id: {} " +
                    "Topic: {} Partition: {} Offset: {} Timestamp: {}",
                orderId,
                result.getRecordMetadata().topic(),
                result.getRecordMetadata().partition(),
                result.getRecordMetadata().offset(),
                result.getRecordMetadata().timestamp()
            );
        }

    }

    public <T extends SpecificRecordBase> void sendWithCallback(
        KafkaProducer<String, T> kafkaProducer,
        String orderId,
        T avroModel
    ) {
        String requestAvroModelName = avroModel.getClass().getSimpleName();
        try {
            kafkaProducer.send(orderServiceConfigData.getPaymentRequestTopicName(), orderId, avroModel)
                .whenComplete((res, ex) -> {
                    callback(res, ex, orderId);
                });
            log.info("{} sent to Kafka for order id: {}", requestAvroModelName, orderId);
        } catch (Exception e) {
            log.error("Error while sending {} message for order id: {}. Error: {}",
                requestAvroModelName,
                orderId,
                e.getMessage(),
                e);
        }
    }
}
