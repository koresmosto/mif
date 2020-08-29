/*
 * Created under not commercial project "Make it fine"
 *
 * Copyright 2017-2020
 */

package com.stingion.kafka.configuration.kafka;

import com.stingion.kafka.event.HelloEvent;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HelloEventKafkaConfig extends EventKafkaConfig<HelloEvent> {

    public HelloEventKafkaConfig() {
        super(HelloEvent.class);
    }
}
