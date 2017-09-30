package com.stingion.makeitfine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    public final String USER_ROLE="USER";
    public final String ADMIN_ROLE="ADMIN";
    public final String ANY_ROLE="ANY";

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("admin").password("adminpass").roles(ADMIN_ROLE, USER_ROLE, ANY_ROLE);
        auth.inMemoryAuthentication().withUser("user").password("userpass").roles(USER_ROLE, ANY_ROLE);
        auth.inMemoryAuthentication().withUser("any").password("anypass").roles(ANY_ROLE);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/login**", "/public/**").permitAll();
        http.authorizeRequests().antMatchers("/**").access("hasRole('ADMIN') or hasRole('USER')").and()
                .formLogin()
                .loginPage("/login")
                .usernameParameter("ssoId")
                .passwordParameter("password")
                .failureUrl("/Access_Denied");
        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/Access_Denied");
    }
}
