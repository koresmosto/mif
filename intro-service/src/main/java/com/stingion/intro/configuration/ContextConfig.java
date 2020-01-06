/*
 * Created under not commercial project "Make it fine"
 *
 * Copyright 2017-2019
 * @author stingion
 */

package com.stingion.intro.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

@Configuration
@ImportResource("classpath:spring-context.xml")
@RequiredArgsConstructor
public class ContextConfig {

  private final MongoDbFactory mongoDbFactory;

  @Bean
  public MongoTemplate mongoTemplate() { //remove _class
    MappingMongoConverter converter = new MappingMongoConverter(mongoDbFactory,
        new MongoMappingContext());
    converter.setTypeMapper(new DefaultMongoTypeMapper(null));
    MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory, converter);
    return mongoTemplate;
  }
}
