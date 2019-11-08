package com.stingion.makeitfine.testplay;

import com.stingion.makeitfine.controller.StartUpController;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("it")
@Tag("excluded")
public class StartUpControllerWithSpringContextIT {

    @Autowired
    private StartUpController startUpController;

    @Test
    public void testContextLoads() {
        assertThat(startUpController).isNotNull();
    }
}
