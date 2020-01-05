/*
 * Created under not commercial project "Make it fine"
 *
 *  Copyright 2017-2020
 *  @author stingion
 */

package com.stingion.intro.web.controller;

import com.stingion.intro.domain.Info;
import com.stingion.intro.service.InfoService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/intro")
public class IntroController {

  private final InfoService infoService;

  @GetMapping
  public ResponseEntity<String> hello() {
    log.info("Intro microservice (intro-service module");//todo: can be removed: checking connection
    return ResponseEntity.ok("Hi!");
  }

  @GetMapping(path = "info")
  public List<Info> getInfo() {
    return infoService.findAll();
  }
}
