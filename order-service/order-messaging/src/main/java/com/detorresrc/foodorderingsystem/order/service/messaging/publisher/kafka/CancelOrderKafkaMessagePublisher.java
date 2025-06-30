package com.detorresrc.foodorderingsystem.order.service.messaging.publisher.kafka;

import com.detorresrc.foodorderingsystem.kafka.order.avro.model.PaymentRequestAvroModel;
import com.detorresrc.foodorderingsystem.kafka.producer.service.KafkaProducer;
import com.detorresrc.foodorderingsystem.order.service.domain.event.OrderCancelledEvent;
import com.detorresrc.foodorderingsystem.order.service.domain.ports.output.message.publisher.payment.OrderCancelledPaymentRequestMessagePublisher;
import com.detorresrc.foodorderingsystem.order.service.messaging.mapper.OrderMessagingDataMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CancelOrderKafkaMessagePublisher implements OrderCancelledPaymentRequestMessagePublisher {
    private final OrderMessagingDataMapper orderMessagingDataMapper;
    private final KafkaProducer<String, PaymentRequestAvroModel> kafkaProducer;
    private final OrderKafkaMessengerHelper orderKafkaMessengerHelper;

    @Override
    public void publish(OrderCancelledEvent domainEvent) {
        var orderId = domainEvent.getOrder().getId().getValue().toString();
        log.error("Received OrderCancelledEvent for order id: {}", orderId);

        var paymentRequestAvroModel =
            orderMessagingDataMapper.orderCancelledEventToPaymentRequestAvroModel(domainEvent);

        orderKafkaMessengerHelper.sendWithCallback(
            kafkaProducer,
            orderId,
            paymentRequestAvroModel
        );
    }
}
