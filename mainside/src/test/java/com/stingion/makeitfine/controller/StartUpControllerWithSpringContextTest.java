package com.stingion.makeitfine.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class StartUpControllerWithSpringContextTest {

    @Autowired
    private StartUpController startUpController;

    @Test
    public void testContextLoads() {
        assertThat(startUpController).isNotNull();
    }
}
