/*
 * Created under not commercial project "Make it fine"
 *
 * Copyright 2017-2019
 * @author stingion
 */

package com.stingion.intro.configuration;

import com.mongodb.Mongo;
import lombok.RequiredArgsConstructor;
import org.mongeez.MongeezRunner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

@Configuration
@Profile({"dev", "docker"})
@RequiredArgsConstructor
public class MongoConfig {

  private final MongoDbFactory mongoDbFactory;

  private final Mongo mongo;

  @Value("${mongoMigration.isEnabled:false}")
  private boolean executeMigrationEnabled;

  @Value("${spring.data.mongodb.authentication-database}")
  private String authDb;

  @Value("${spring.data.mongodb.database}")
  private String dbName;

  @Value("${mongoMigration.allPrivilegesUser:#{null}}")
  private String username;

  @Value("${mongoMigration.allPrivilegesPass:#{null}}")
  private String password;

  /**
   * Migration mongo db with 'mongeez' framework.
   *
   * @return spring bean
   */
  @Bean
  public MongeezRunner mongeez() {
    MongeezRunner mongeezRunner = new MongeezRunner();
    mongeezRunner.setExecuteEnabled(executeMigrationEnabled);
    mongeezRunner.setMongo(mongo);
    mongeezRunner.setAuthDb(authDb);
    mongeezRunner.setDbName(dbName);
    mongeezRunner.setUserName(username);
    mongeezRunner.setPassWord(password);
    mongeezRunner.setFile(new ClassPathResource("db/migration/mongo/migration-changesets.xml"));

    return mongeezRunner;
  }

  /**
   * Disable including/considering '_class' filed in mongo db document while working through Java.
   *
   * @return spring bean
   */
  @Bean
  public MongoTemplate mongoTemplate() {
    MappingMongoConverter converter = new MappingMongoConverter(mongoDbFactory,
        new MongoMappingContext());
    converter.setTypeMapper(new DefaultMongoTypeMapper(null));
    MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory, converter);
    return mongoTemplate;
  }
}
