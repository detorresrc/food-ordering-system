package com.detorresrc.foodorderingsystem.kafka.producer.service.impl;

import com.detorresrc.foodorderingsystem.kafka.producer.service.KafkaProducer;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.specific.SpecificRecordBase;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaProducerImpl<K extends Serializable, V extends SpecificRecordBase> implements KafkaProducer<K, V> {
    private final KafkaTemplate<K, V> kafkaTemplate;

    @Override
    public void send(String topicName, K key, V message, CompletableFuture<SendResult<K, V>> callback) {
        log.info("Sending message:{} to topic: {}", message, topicName);
        kafkaTemplate.send(topicName, key, message)
            .whenComplete((result, ex) -> {
                if (ex != null) {
                    log.error("Error sending message: {} to topic: {}. Exception: {}", message, topicName, ex.getMessage());
                    callback.completeExceptionally(ex);
                } else {
                    log.info("Message sent successfully to topic: {} with result: {}", topicName, result);
                    callback.complete(result);
                }
            });
    }

    @PreDestroy
    public void close() {
        if (kafkaTemplate != null) {
            log.info("Closing kafka producer!");
            kafkaTemplate.destroy();
        }
    }
}
