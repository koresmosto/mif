/*
 * Created under not commercial project "Make it fine"
 *
 * Copyright 2017-2020
 */

package com.stingion.kafka.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Profile;

@Configuration
@ImportResource("classpath:spring-context.xml")
public class ContextConfig {

    @Profile("!test")
    @Bean
    public NewTopic mifEventHelloTopic(@Value("${spring.kafka.topic.hello}") String topicName) {
        return new NewTopic(topicName, 2, (short) 2);
    }
}
