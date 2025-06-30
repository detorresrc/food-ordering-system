package com.detorresrc.foodorderingsystem.payment.service.messaging.publisher.kafka;

import com.detorresrc.foodorderingsystem.kafka.order.avro.model.PaymentResponseAvroModel;
import com.detorresrc.foodorderingsystem.kafka.producer.service.KafkaProducer;
import com.detorresrc.foodorderingsystem.payment.service.domain.event.PaymentCompletedEvent;
import com.detorresrc.foodorderingsystem.payment.service.domain.ports.output.message.publisher.PaymentCompleteMessagePublisher;
import com.detorresrc.foodorderingsystem.payment.service.messaging.mapper.PaymentMessagingDataMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PaymentCompletedKafkaMessagePublisher implements PaymentCompleteMessagePublisher {
    private final PaymentMessagingDataMapper paymentMessagingDataMapper;
    private final KafkaProducer<String, PaymentResponseAvroModel> kafkaProducer;
    private final PaymentKafkaMessengerHelper paymentKafkaMessengerHelper;

    @Override
    public void publish(PaymentCompletedEvent domainEvent) {
        String orderId = domainEvent.getPayment().getOrderId().getValue().toString();

        log.info("Received PaymentCompletedEvent for order id: {}", orderId);

        paymentKafkaMessengerHelper.sendWithCallback(
            kafkaProducer,
            orderId,
            paymentMessagingDataMapper.paymentCompletedEventToPaymentResponseAvroModel(domainEvent)
        );
    }
}
