package com.mysite.sbb;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		// 모든 페이지에 접속할 수 있도록 허용
		http.authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests.
		requestMatchers(new AntPathRequestMatcher("/**")).permitAll()).
		// h2-console에 접속할 수 있도록 허용
		csrf((csrf) -> csrf.ignoringRequestMatchers(new AntPathRequestMatcher
		("/h2-console/**"))).
		// h2-console 액박 뜨는 현상 방지 코드
		headers((headers) -> headers.addHeaderWriter(new XFrameOptionsHeaderWriter
		(XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN)))
		.formLogin((formLogin) -> formLogin.loginPage("/user/login")
		.defaultSuccessUrl("/"))
		;
		return http.build();
	}
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
