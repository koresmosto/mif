package com.stingion.makeitfine.controller.it;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

import javax.annotation.PostConstruct;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ActiveProfiles("it")
@Tag("excluded")
class StartUpControllerIT {

    @LocalServerPort
    private int port;

    private String hostPort;

    @PostConstruct
    private void init() {
        hostPort = "http://localhost:" + port;
    }

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void index() {
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
    void greeting() {
        assertEquals("Make it fine \"makeitfine\"", getResponseBody("/info"));
        assertEquals("Make it fine \"makeitfine\"", getResponseBody("/info?details=any"));
        assertEquals("Stingion : stingion@gmail.com", getResponseBody("/info?details=author"));
        assertEquals("Social Network for workers", getResponseBody("/info?details=Purpose"));
        assertEquals("Development stage : development milestone", getResponseBody("/info?details=stage"));
    }

    private String getResponseBody(String relativePath) {
        return restTemplate.getForEntity(hostPort + relativePath, String.class).getBody();
    }
}