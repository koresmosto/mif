/*
 * Created under not commercial project "Make it fine"
 *
 *  Copyright 2017-2020
 *  @author stingion
 */

package com.stingion.makeitfine.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Controller
@RequestMapping("/microservice")
@RequiredArgsConstructor
public class OtherServicesController {

  @Value("${intro-service.base-url:#{null}}")
  private String introServiceBaseUrl;

  private final RestTemplate restTemplate;

  /**
   * Get response from {@code intro-service} module.
   */
  @GetMapping(path = "intro", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> introMS() {
    String url = UriComponentsBuilder.fromHttpUrl(introServiceBaseUrl)
        .path("intro")
        .toUriString();

    ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

    return ResponseEntity.ok(response.getBody());
  }
}
