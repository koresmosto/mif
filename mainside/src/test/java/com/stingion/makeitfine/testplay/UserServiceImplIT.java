package com.stingion.makeitfine.testplay;

import com.stingion.makeitfine.data.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("integration_test")
@SpringBootTest
public class UserServiceImplIT {

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
