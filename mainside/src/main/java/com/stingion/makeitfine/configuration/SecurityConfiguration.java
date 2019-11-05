package com.stingion.makeitfine.configuration;

import com.stingion.makeitfine.data.service.impl.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Value("${security.ignore:false}")
    private boolean securityIgnore;

    @Autowired
    private CustomUserDetailsService authProvider;

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        if (!securityIgnore) {
            configureGlobalSecurityIfSecurityON(auth);
        }
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        if (!securityIgnore) {
            configureIfSecurityIsON(http);
        }
    }

    private void configureGlobalSecurityIfSecurityON(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authProvider);
    }

    private void configureIfSecurityIsON(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/login**", "/public/**").permitAll();
        http.authorizeRequests().antMatchers("/**").access("hasRole('ADMIN') or hasRole('USER')").and()
                .csrf().disable()
                .formLogin()
                .loginPage("/login")
                .usernameParameter("ssoId")
                .passwordParameter("password")
                .failureUrl("/Access_Denied")
                .and().logout();
        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/Access_Denied");
        http.headers().frameOptions().disable();
    }
}
