package com.detorresrc.foodorderingsystem.order.service.messaging.listener.kafka;

import com.detorresrc.foodorderingsystem.kafka.consumer.KafkaConsumer;
import com.detorresrc.foodorderingsystem.kafka.order.avro.model.PaymentResponseAvroModel;
import com.detorresrc.foodorderingsystem.kafka.order.avro.model.PaymentStatus;
import com.detorresrc.foodorderingsystem.order.service.domain.ports.input.message.listener.payment.PaymentResponseMessageListener;
import com.detorresrc.foodorderingsystem.order.service.messaging.mapper.OrderMessagingDataMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class PaymentResponseKafkaListener implements KafkaConsumer<PaymentResponseAvroModel> {
    private final PaymentResponseMessageListener paymentResponseMessageListener;
    private final OrderMessagingDataMapper orderMessagingDataMapper;

    @Override
    @KafkaListener(id = "${kafka-consumer-config.payment-consumer-group-id}", topics = "${order-service.payment-response-topic-name}")
    public void receive(
        @Payload List<PaymentResponseAvroModel> messages,
        @Header(KafkaHeaders.RECEIVED_KEY) List<String> keys,
        @Header(KafkaHeaders.RECEIVED_PARTITION) List<Integer> partitions,
        @Header(KafkaHeaders.OFFSET) List<Long> offsets) {

        log.info("{} number of payment response received with keys: {}, partitions: {}, offsets: {}",
            messages.size(),
            keys,
            partitions,
            offsets);

        messages.forEach(avroModel -> {
            if (PaymentStatus.COMPLETED == avroModel.getPaymentStatus()) {
                log.info("Processing successful payment for order id: {}",
                    avroModel.getOrderId());
                paymentResponseMessageListener.paymentCompleted(orderMessagingDataMapper.paymentResponseAvroModelToPaymentResponse(avroModel));
            } else if (PaymentStatus.CANCELLED == avroModel.getPaymentStatus()) {
                log.info("Processing unsuccessful payment for order id: {}",
                    avroModel.getOrderId());
                paymentResponseMessageListener.paymentCancelled(orderMessagingDataMapper.paymentResponseAvroModelToPaymentResponse(avroModel));
            }
        });
    }
}
