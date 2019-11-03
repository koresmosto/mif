package com.stingion.makeitfine.controller;

import com.stingion.makeitfine.data.model.User;
import com.stingion.makeitfine.data.repository.UserRepository;
import com.stingion.makeitfine.data.service.UserService;
import com.stingion.makeitfine.data.service.impl.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.Optional;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class UserServiceImplWithMockTest {

    @TestConfiguration
    static class UserServiceTestContextConfiguration {
        @Bean
        public UserService userService() {
            return new UserServiceImpl();
        }
    }

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private EntityManager entityManager;

    @MockBean
    private EntityManagerFactory entityManagerFactory;

    @Before
    public void setUp() {
        User user = new User();
        when(userRepository.findById(any(Integer.class))).thenReturn(Optional.of(user));
    }

    @Test
    public void findById() throws Exception {
        assertThat(userService.findById(new Random().nextInt())).isNotNull();
    }
}