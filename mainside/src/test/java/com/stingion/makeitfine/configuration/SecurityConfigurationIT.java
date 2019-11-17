/*
 * Created under not commercial project "Make it fine"
 *
 *  Copyright 2017-2019
 *  @author stingion
 */

package com.stingion.makeitfine.configuration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import com.stingion.makeitfine.data.model.user.User;
import com.stingion.makeitfine.data.model.utils.State;
import java.util.Optional;
import javax.annotation.PostConstruct;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

@SuppressWarnings("ConfigurationProperties")
@SpringBootTest(webEnvironment = RANDOM_PORT)
@ActiveProfiles("security_on_integration_test")
@TestPropertySource("classpath:values-test.yml")
@ConfigurationProperties(prefix = "test.integration", ignoreInvalidFields = true)
class SecurityConfigurationIT {

  @LocalServerPort
  private int port;

  @Value("${protocolHost}")
  private String protocolHost;

  @Value("${basicAuthUser.username}")
  private String username;

  @Value("${basicAuthUser.password}")
  private String password;

  private String hostPort;

  @Autowired
  protected TestRestTemplate restTemplate;

  @PostConstruct
  private void init() {
    hostPort = protocolHost + ":" + port;
    restTemplate = restTemplate.withBasicAuth(username, password);
  }

  @Test
  void indexPageTest() {
    String[] responseBody = new String[]{getResponseBody("/index"), getResponseBody("/")};

    for (String r : responseBody) {
      assertTrue(r.contains("English"));
      assertTrue(r.contains(" | "));
      assertTrue(r.contains("Русский"));
      assertTrue(r.contains("Make it fine"));
      assertTrue(r.contains("Домашняя страница") || r.contains("Home page"));
    }
  }

  @Test
  void getUserWithFirstId() {
    User expectedUser = new User();
    expectedUser.setId(1);
    expectedUser.setSsoId("bill");
    expectedUser.setEmail("bill@xyz.com");
    expectedUser.setState(State.ACTIVE);

    User actualUser = getResponseBody("/user/1", User.class);

    assertEquals(expectedUser.getId(), actualUser.getId());
    assertEquals(expectedUser.getSsoId(), actualUser.getSsoId());
    assertEquals(expectedUser.getEmail(), actualUser.getEmail());
    assertEquals(expectedUser.getState(), actualUser.getState());
  }

  private <T> T getResponseBody(String relativePath, Class<T>... clasz) {
    Class<T> type =
        Optional.ofNullable(clasz).map(classes -> classes.length).orElse(0) > 0 ? clasz[0]
            : (Class<T>) String.class;
    return restTemplate.getForEntity(hostPort + relativePath, type).getBody();
  }
}