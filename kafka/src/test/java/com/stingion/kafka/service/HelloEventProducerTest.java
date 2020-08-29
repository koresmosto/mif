/*
 * Created under not commercial project "Make it fine"
 *
 * Copyright 2017-2020
 */

package com.stingion.kafka.service;

import com.stingion.kafka.event.HelloEvent;
import com.stingion.kafka.service.producer.Producer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public class HelloEventProducerTest extends KafkaProducerTestInitializer<HelloEvent> {

    @Autowired
    private Producer<HelloEvent> producer;

    public HelloEventProducerTest(@Value("${spring.kafka.topic.hello}") String topicName) {
        super(topicName);
    }

    @Test
    public void kafkaSetup_WithTopic_EnsureSendMessageIsReceived() throws InterruptedException {
        HelloEvent message = new HelloEvent("Hi, there!");
        producer.sendMessage(message);

        assertStringPolled(message);
    }
}
