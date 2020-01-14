/*
 * Created under not commercial project "Make it fine"
 *
 *  Copyright 2017-2020
 *  @author stingion
 */

package com.stingion.makeitfine.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class OtherServicesController {

  @Value("${intro-service.base-url:#{null}}")
  private String introServiceBaseUrl;

  @Value("${mq-publish.base-url:#{null}}")
  private String mqPublishBaseUrl;

  private final RestTemplate restTemplate;

  /**
   * Get response from {@code intro-service} module.
   */
  @GetMapping(path = "intro/hello", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> introMS() {
    String url = UriComponentsBuilder.fromHttpUrl(introServiceBaseUrl)
        .path("intro")
        .toUriString();
    ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
    return ResponseEntity.ok(response.getBody());
  }

  /**
   * Get response from {@code mq-publish} module.
   */
  @GetMapping(path = "mqpublish/hello", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> mqPublishMS() {
    String url = UriComponentsBuilder.fromHttpUrl(mqPublishBaseUrl)
        .path("mqpublish")
        .toUriString();
    ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
    return ResponseEntity.ok(response.getBody());
  }

  /**
   * Get author of project.
   */
  @GetMapping(path = "intro/author", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> author() {
    String url = UriComponentsBuilder.fromHttpUrl(introServiceBaseUrl)
        .path("info/details/author")
        .toUriString();
    String responseBody = restTemplate.getForEntity(url, String.class).getBody();
    log.info("Get info about author ({}) from intro-service", responseBody); //todo: can be removed
    return ResponseEntity.ok(responseBody);
  }
}
