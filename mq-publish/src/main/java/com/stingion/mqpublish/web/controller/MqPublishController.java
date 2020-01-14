/*
 * Created under not commercial project "Make it fine"
 *
 *  Copyright 2017-2020
 *  @author stingion
 */

package com.stingion.mqpublish.web.controller;

import com.stingion.mqpublish.configuration.Publisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/mqpublish")
@RequiredArgsConstructor
public class MqPublishController {

  private final Publisher publisher;

  @GetMapping
  public ResponseEntity<String> hello() {
    log.info("Publisher microservice (mq-publish module");//todo: can be removed: checking conn
    return ResponseEntity.ok("Hello from \"mq-publish\" module!");
  }

  @GetMapping("/secretUrl")
  public String secretUrl(@RequestParam("msg") String msg) {
    publisher.produceMsg(msg);
    return "Access to secret Url and send message: \"" + msg + "\"";
  }
}
