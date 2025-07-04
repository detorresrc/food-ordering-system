package com.detorresrc.foodorderingsystem.kafka.producer.service;

import org.apache.avro.specific.SpecificRecordBase;
import org.springframework.kafka.support.SendResult;

import java.io.Serializable;
import java.util.concurrent.CompletableFuture;

public interface KafkaProducer<K extends Serializable, V extends SpecificRecordBase> {
    void send(String topicName, K key, V message, CompletableFuture<SendResult<K, V>> callback);

    CompletableFuture<SendResult<K, V>> send(String topicName, K key, V message);
}
