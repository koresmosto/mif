package com.stingion.makeitfine.configuration;

import com.stingion.makeitfine.data.service.impl.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService authProvider;

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/login**", "/public/**").permitAll();
        http.authorizeRequests().antMatchers("/**").access("hasRole('ADMIN') or hasRole('USER')").and()
                .csrf().disable()
                .formLogin()
                .loginPage("/login")
                .usernameParameter("ssoId")
                .passwordParameter("password")
                .failureUrl("/Access_Denied");
        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/Access_Denied");
        http.headers().frameOptions().disable();
//        .and()
//                .authorizeRequests()
//                .antMatchers(HttpMethod.POST, "/user**").hasRole("ADMIN")
//                .antMatchers(HttpMethod.PUT, "/user**").hasRole("ADMIN")
//                .antMatchers(HttpMethod.DELETE, "/user**").hasRole("ADMIN");
    }
}
