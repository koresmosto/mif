/*
 * Created under not commercial project "Make it fine"
 *
 *  Copyright 2017-2020
 *  @author stingion
 */

package com.stingion.intro.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("integration_test")
class InfoServiceIT {

  @Autowired
  private InfoService infoService;

  @Test
  public void findAll() {
    assertThat(infoService.findAll().size()).isEqualTo(3);
  }

  @Test
  public void findByKey() {
    assertThat(infoService.findByKey("any").getDetails()).isEqualTo("Any text is present.");
  }
}