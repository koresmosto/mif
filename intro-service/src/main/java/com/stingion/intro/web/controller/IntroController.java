package com.stingion.intro.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/intro")
public class IntroController {

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> sayHi() {
    log.info("Access to IntroController.sayHi method");//todo: can be removed: checking connection
    return ResponseEntity.ok("Hi!");
  }
}
