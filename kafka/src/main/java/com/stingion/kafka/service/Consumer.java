/*
 * Created under not commercial project "Make it fine"
 *
 * Copyright 2017-2020
 */

package com.stingion.kafka.service;

import com.stingion.kafka.event.HelloEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Profile("!test")
@DependsOn("mifEventHelloTopic")
@Service
public class Consumer {

    @KafkaListener(topics = "${spring.kafka.topic.hello}", groupId = "${spring.kafka.group-id}")
    public void consume(HelloEvent message) {
        log.info("Consumed message -> {}", message);
    }
}
