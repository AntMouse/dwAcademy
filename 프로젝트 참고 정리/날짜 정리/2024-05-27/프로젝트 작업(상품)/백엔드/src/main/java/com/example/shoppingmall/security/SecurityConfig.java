package com.example.shoppingmall.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests.
        requestMatchers(new AntPathRequestMatcher("/**")).permitAll())
        // h2 콘솔 접속 및 리액트 연동 관련 코드 시작
        .csrf((csrf) -> csrf.ignoringRequestMatchers(new AntPathRequestMatcher("/h2-console/**"))
        .ignoringRequestMatchers(new AntPathRequestMatcher("/api/**"))) // API 요청에 대해 CSRF 무시
        .headers((headers) -> headers.addHeaderWriter(new XFrameOptionsHeaderWriter(XFrameOptionsHeaderWriter.
        XFrameOptionsMode.SAMEORIGIN)))
        // h2 콘솔 접속 및 리액트 연동 관련 코드 끝.
        ;

        return http.build();
    }
}