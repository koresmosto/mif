/*
 *  Created under not commercial project "Make it fine"
 *
 *  Copyright 2017-2020
 *  @author stingion
 */

package com.stingion.kafka.web.controller;

import com.stingion.kafka.service.Producer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/kafka")
@RequiredArgsConstructor
public class KafkaController {

    private final Producer producer;

    @GetMapping
    public ResponseEntity<String> hello() {
        log.info("Kafka microservice (kafka module)"); //todo: can be removed: checking conn
        return ResponseEntity.ok("Hello from \"kafka\" module!");
    }

    @PostMapping(value = "/publish")
    public void sendMessageToKafkaTopicWithHeader(@RequestParam("message") String message) {
        this.producer.sendMessage(message);
    }

    @PostMapping(value = "/body")
    public void sendMessageToKafkaTopicWithBody(@RequestBody String message) {
        this.producer.sendMessage(message);
    }
}
