/*
 * Created under not commercial project "Make it fine"
 *
 *  Copyright 2017-2020
 *  @author stingion
 */

package com.stingion.cache.controller;

import com.stingion.cache.model.Record;
import com.stingion.cache.model.RecordRepository;
import com.stingion.util.mq.Message;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@RestController
@RequestMapping("/records")
@RequiredArgsConstructor
public class RecordController {

  private final RestTemplate restTemplate;

  private final List<Message> queueMessages;

  private final RecordRepository recordRepository;

  @Value("${mq-consume.base-url:#{null}}")
  private String mqConsumeBaseUrl;

  @Cacheable(value = "records", key = "#recordId")
  @RequestMapping(value = "/get/{recordId}", method = RequestMethod.GET)
  public Record getRecord(@PathVariable String recordId) {
    log.info("Getting record with ID {}.", recordId);
    return recordRepository.findById(Long.valueOf(recordId)).orElse(null);
  }

  /**
   * Refresh all messages by getting them from queue.
   *
   * @return messages contained response
   */
  @GetMapping("refresh")
  public ResponseEntity<List<Message>> hello() {
    String url =
        UriComponentsBuilder.fromHttpUrl(mqConsumeBaseUrl).path("mqconsume/queue").toUriString();

    queueMessages.clear();
    queueMessages.addAll(Arrays.asList(restTemplate.getForEntity(url, Message[].class).getBody()));
    log.info("Get {} messages from queue", queueMessages.size());

    recordRepository.deleteAll();
    queueMessages.stream().forEach(m -> recordRepository.save(new Record(m.getMsg())));

    return ResponseEntity.ok(queueMessages);
  }
}
