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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/info")
public class InfoController {

  private final InfoService infoService;

  @GetMapping
  public List<Info> getInfo() {
    return infoService.findAll();
  }

  @GetMapping(value = "{key}")
  public Info getInfo(@PathVariable("key") String key) {
    return infoService.findByKey(key);
  }
}
