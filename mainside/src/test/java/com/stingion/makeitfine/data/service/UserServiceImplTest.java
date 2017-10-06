package com.stingion.makeitfine.data.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Test
    public void findBySSO() throws Exception {
        assertThat(userService.findBySSO("admin").getPassword()).isEqualTo("nimda");
        assertThat(userService.findBySSO("admin").getPassword()).isNotEqualTo("nimda1");
    }

    @Test
    public void findById() throws Exception {
        assertThat(userService.findById(1).getSsoId()).isEqualTo("bill");
    }
}
