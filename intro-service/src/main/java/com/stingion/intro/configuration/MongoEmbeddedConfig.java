/*
 * Created under not commercial project "Make it fine"
 *
 *  Copyright 2017-2020
 *  @author stingion
 */

package com.stingion.intro.configuration;

import com.stingion.intro.domain.Info;
import com.stingion.intro.service.InfoService;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile({"docker"})
@RequiredArgsConstructor
public class MongoEmbeddedConfig {

  private final InfoService infoService;

  /**
   * Fulfill db after creation.
   */
  @PostConstruct
  public void init() {
    infoService.insert(new Info("author", "koresmosto@gmail.com (docker)"));
    infoService.insert(new Info("purpose", "Social Network for workers (docker)"));
    infoService.insert(new Info("stage", "Development stage (docker)"));
  }
}
