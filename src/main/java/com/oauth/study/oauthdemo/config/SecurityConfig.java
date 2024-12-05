package com.oauth.study.oauthdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import com.oauth.study.oauthdemo.config.handler.FailureHandler;
import com.oauth.study.oauthdemo.config.handler.SuccessHandler;
import com.oauth.study.oauthdemo.service.UserService;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    
    private final SuccessHandler successHandler;
    private final FailureHandler failureHandler;
    private final UserService userService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        System.out.println("debug >>> SecurityConfig");
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .formLogin(form -> form.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                  .requestMatchers("/login/form","/WEB-INF/**").permitAll()
                  .anyRequest().permitAll())
                  .oauth2Login(oauth -> oauth
                              .loginPage("/login")
                              .successHandler(successHandler)
                              .failureHandler(failureHandler)
                              .userInfoEndpoint(user -> user.userService(userService)))
                .build();
    }
}
