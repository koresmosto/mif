/*
 *  Created under not commercial project "Make it fine"
 *
 *  Copyright 2017-2020
 *  @author stingion
 */

package com.stingion.makeitfine.controller;

import java.util.Optional;
import javax.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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

    private final RestTemplate restTemplate;

    @Value("${intro-service.base-url:#{null}}")
    private String introServiceBaseUrl;

    @Value("${mq-publish.base-url:#{null}}")
    private String mqPublishBaseUrl;

    @Value("${mq-consume.base-url:#{null}}")
    private String mqConsumeBaseUrl;

    @Value("${cache-service.base-url:#{null}}")
    private String cacheServiceBaseUrl;

    /**
     * Get response from {@code intro-service} module.
     *
     * @return response
     */
    @GetMapping(path = "intro/hello", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> introMS() {
        return getResponseEntity(introServiceBaseUrl, "intro");
    }

    /**
     * Get response from {@code mq-publish} module.
     *
     * @return response
     */
    @GetMapping(path = "mqpublish/hello", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> mqPublishMS() {
        return getResponseEntity(mqPublishBaseUrl, "mqpublish");
    }

    /**
     * Get response from {@code mq-consume} module.
     *
     * @return response
     */
    @GetMapping(path = "mqconsume/hello", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> mqConsumeMS() {
        return getResponseEntity(mqConsumeBaseUrl, "mqconsume");
    }

    /**
     * Get response from {@code cache-service} module.
     *
     * @return response
     */
    @GetMapping(path = "cache/hello", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> cacheServiceMS() {
        return getResponseEntity(cacheServiceBaseUrl, "cache");
    }

    /**
     * Get author of project.
     *
     * @return response
     */
    @GetMapping(path = "intro/author", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> author() {
        return getResponseEntity(introServiceBaseUrl, "info/details/author");
    }

    @NotNull
    private ResponseEntity<String> getResponseEntity(String serviceBaseUrl, String path) {
        String url = UriComponentsBuilder.fromHttpUrl(serviceBaseUrl)
                .path(path)
                .toUriString();
        return ResponseEntity.ok(getResponseBodyOrEmpty(url));
    }

    @NotNull
    private String getResponseBodyOrEmpty(String url) {
        return Optional.ofNullable(restTemplate.getForEntity(url, String.class).getBody()).orElse(StringUtils.EMPTY);
    }
}
