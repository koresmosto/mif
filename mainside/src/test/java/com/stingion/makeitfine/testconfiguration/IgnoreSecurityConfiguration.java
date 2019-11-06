package com.stingion.makeitfine.testconfiguration;

import com.stingion.makeitfine.data.model.User;
import com.stingion.makeitfine.data.service.UserService;
import com.stingion.makeitfine.data.service.impl.CustomUserDetailsService;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.List;

@TestConfiguration
public class IgnoreSecurityConfiguration {

    @Bean
    public CustomUserDetailsService authProvider() {
        return new CustomUserDetailsService();
    }

    @Bean
    public UserService userService() {
        return new UserService() {
            @Override
            public User findBySSO(String sso) {
                return null;
            }

            @Override
            public List<User> findAll() {
                return null;
            }

            @Override
            public User findById(Integer id) {
                return null;
            }

            @Override
            public User save(User entity) {
                return null;
            }

            @Override
            public User update(User entity) {
                return null;
            }

            @Override
            public void delete(User entity) {

            }
        };
    }
}
