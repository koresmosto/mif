package com.stingion.makeitfine;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;

@TestConfiguration
@ImportResource("classpath:spring-test/context-test.xml")
public class ServiceTestConfiguration {

    @Bean
    public String itis() {
        return "Hello!";
    }
}
