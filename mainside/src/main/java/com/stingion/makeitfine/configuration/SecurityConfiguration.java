package com.stingion.makeitfine.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Value("${security.ignore:false}")
    private boolean securityIgnore;

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

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
        auth.authenticationProvider(authProvider());
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
