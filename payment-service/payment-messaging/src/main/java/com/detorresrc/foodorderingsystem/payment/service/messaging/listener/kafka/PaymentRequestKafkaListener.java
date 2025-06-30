package com.detorresrc.foodorderingsystem.payment.service.messaging.listener.kafka;

import com.detorresrc.foodorderingsystem.kafka.consumer.KafkaConsumer;
import com.detorresrc.foodorderingsystem.kafka.order.avro.model.PaymentOrderStatus;
import com.detorresrc.foodorderingsystem.kafka.order.avro.model.PaymentRequestAvroModel;
import com.detorresrc.foodorderingsystem.payment.service.domain.ports.input.message.listener.PaymentRequestMessageListener;
import com.detorresrc.foodorderingsystem.payment.service.messaging.mapper.PaymentMessagingDataMapper;
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
public class PaymentRequestKafkaListener implements KafkaConsumer<PaymentRequestAvroModel> {
    private final PaymentRequestMessageListener paymentRequestMessageListener;
    private final PaymentMessagingDataMapper paymentMessagingDataMapper;

    @Override
    @KafkaListener(
        id = "${kafka-consumer-config.payment-consumer-group-id}",
        topics = "${payment-service.payment-request-topic-name}")
    public void receive(
        @Payload List<PaymentRequestAvroModel> messages,
        @Header(KafkaHeaders.RECEIVED_KEY) List<String> keys,
        @Header(KafkaHeaders.RECEIVED_PARTITION) List<Integer> partitions,
        @Header(KafkaHeaders.OFFSET) List<Long> offsets) {

        log.info("{} number of payment response received with keys: {}, partitions: {}, offsets: {}",
            messages.size(),
            keys,
            partitions,
            offsets);

        messages.forEach(avroModel -> {
            if (avroModel.getPaymentOrderStatus() == PaymentOrderStatus.PENDING) {
                log.info("Processing payment for order id: {}",
                    avroModel.getOrderId());

                paymentRequestMessageListener.completePayment(paymentMessagingDataMapper.paymentRequestAvroModelToPaymentRequest(avroModel));
            } else if (avroModel.getPaymentOrderStatus() == PaymentOrderStatus.CANCELLED) {
                log.info("Cancelling payment for order id: {}",
                    avroModel.getOrderId());

                paymentRequestMessageListener.cancelPayment(paymentMessagingDataMapper.paymentRequestAvroModelToPaymentRequest(avroModel));
            }
        });
    }
}
