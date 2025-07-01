package com.detorresrc.foodorderingsystem.payment.service.messaging.publisher.kafka;

import com.detorresrc.foodorderingsystem.kafka.order.avro.model.PaymentResponseAvroModel;
import com.detorresrc.foodorderingsystem.kafka.producer.service.KafkaProducer;
import com.detorresrc.foodorderingsystem.kafka.producer.service.KafkaProducerMessengerHelper;
import com.detorresrc.foodorderingsystem.payment.service.domain.config.PaymentServiceConfigData;
import com.detorresrc.foodorderingsystem.payment.service.domain.event.PaymentCancelledEvent;
import com.detorresrc.foodorderingsystem.payment.service.domain.ports.output.message.publisher.PaymentCancelledMessagePublisher;
import com.detorresrc.foodorderingsystem.payment.service.messaging.mapper.PaymentMessagingDataMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PaymentCancelledKafkaMessagePublisher implements PaymentCancelledMessagePublisher {
    private final PaymentMessagingDataMapper paymentMessagingDataMapper;
    private final KafkaProducer<String, PaymentResponseAvroModel> kafkaProducer;
    private final PaymentServiceConfigData paymentServiceConfigData;
    private final KafkaProducerMessengerHelper kafkaProducerMessengerHelper;

    @Override
    public void publish(PaymentCancelledEvent domainEvent) {
        String orderId = domainEvent.getPayment().getOrderId().getValue().toString();

        log.info("Received PaymentCancelledEvent for order id: {}", orderId);

        kafkaProducerMessengerHelper.sendWithCallback(
            kafkaProducer,
            paymentServiceConfigData.getPaymentResponseTopicName(),
            orderId,
            paymentMessagingDataMapper.paymentCancelledEventToPaymentResponseAvroModel(domainEvent));
    }
}
