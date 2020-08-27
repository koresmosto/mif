/*
 * Created under not commercial project "Make it fine"
 *
 * Copyright 2017-2020
 */

package com.stingion.kafka.configuration;

import static com.stingion.kafka.Constants.TOPIC;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Profile;

@Configuration
@ImportResource("classpath:spring-context.xml")
public class ContextConfig {

    @Bean
    public String topic() {
        return TOPIC;
    }

    @Profile("!test")
    @Bean
    public NewTopic topic1() {
        return new NewTopic(TOPIC, 1, (short) 1);
    }
}
