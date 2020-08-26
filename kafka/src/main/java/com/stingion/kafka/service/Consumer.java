/*
 * Created under not commercial project "Make it fine"
 *
 * Copyright 2017-2020
 */

package com.stingion.kafka.service;

import static com.stingion.kafka.Constants.GROUP_ID;
import static com.stingion.kafka.Constants.TOPIC;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Profile("!test")
public class Consumer {

    @KafkaListener(topics = TOPIC, groupId = GROUP_ID)
    public void consume(String message) {
        log.info(String.format("#### -> Consumed message -> %s", message));
    }
}
