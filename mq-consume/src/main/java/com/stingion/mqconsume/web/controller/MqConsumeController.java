/*
 * Created under not commercial project "Make it fine"
 *
 *  Copyright 2017-2020
 *  @author stingion
 */

package com.stingion.mqconsume.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/mqconsume")
public class MqConsumeController {

  @GetMapping
  public ResponseEntity<String> hello() {
    log.info("Consumer microservice (mq-consume module");//todo: can be removed: checking conn
    return ResponseEntity.ok("Hello from \"mq-consume\" module!");
  }
}
