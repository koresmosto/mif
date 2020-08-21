/*
 * Created under not commercial project "Make it fine"
 *
 * Copyright 2017-2020
 */

package com.stingion.kafka.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ProducerTest extends KafkaTestInitializer {

    @Autowired
    private Producer producer;

    @Test
    public void kafkaSetup_WithTopic_EnsureSendMessageIsReceived() throws InterruptedException {
        String sendMessage = "Hi, there!";
        producer.sendMessage(sendMessage);

        assertStringPolled(sendMessage);
    }
}
