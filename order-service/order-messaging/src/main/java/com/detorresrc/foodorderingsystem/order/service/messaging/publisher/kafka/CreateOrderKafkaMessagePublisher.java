package com.detorresrc.foodorderingsystem.order.service.messaging.publisher.kafka;

import com.detorresrc.foodorderingsystem.kafka.order.avro.model.PaymentRequestAvroModel;
import com.detorresrc.foodorderingsystem.kafka.producer.service.KafkaProducer;
import com.detorresrc.foodorderingsystem.order.service.domain.event.OrderCreatedEvent;
import com.detorresrc.foodorderingsystem.order.service.domain.ports.output.message.publisher.payment.OrderCreatedPaymentRequestMessagePublisher;
import com.detorresrc.foodorderingsystem.order.service.messaging.mapper.OrderMessagingDataMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CreateOrderKafkaMessagePublisher implements OrderCreatedPaymentRequestMessagePublisher {
    private final OrderMessagingDataMapper orderMessagingDataMapper;
    private final KafkaProducer<String, PaymentRequestAvroModel> kafkaProducer;
    private final OrderKafkaMessengerHelper orderKafkaMessengerHelper;

    @Override
    public void publish(OrderCreatedEvent domainEvent) {
        var orderId = domainEvent.getOrder().getId().getValue().toString();
        log.error("Received OrderCreatedEvent for order id: {}", orderId);

        var paymentRequestAvroModel =
            orderMessagingDataMapper.orderCreateEventToPaymentRequestAvroModel(domainEvent);

        orderKafkaMessengerHelper.sendWithCallback(
            kafkaProducer,
            orderId,
            paymentRequestAvroModel
        );
    }
}
