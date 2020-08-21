/*
 * Created under not commercial project "Make it fine"
 *
 * Copyright 2017-2020
 */

package com.stingion.kafka.testconfiguration;

import static com.stingion.kafka.Constants.TEST_TOPIC;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@TestConfiguration
public class TestContextConfig {

    @Bean
    @Primary
    public String testTopic() {
        return TEST_TOPIC;
    }
}
