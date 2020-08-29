/*
 * Created under not commercial project "Make it fine"
 *
 * Copyright 2017-2020
 */

package com.stingion.kafka.service.producer;

import com.stingion.kafka.event.MessageEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MessageEventProducer extends Producer<MessageEvent> {

    public MessageEventProducer(@Value("${spring.kafka.topic.message}") String topicName) {
        super(topicName);
    }
}
