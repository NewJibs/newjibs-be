package com.ssafy.newjibs.member.config;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ssafy.newjibs.member.jwt.JwtFilter;
import com.ssafy.newjibs.member.jwt.TokenProvider;
import com.ssafy.newjibs.member.service.RedisService;

public class JwtSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
	private final TokenProvider tokenProvider;
	private final RedisService redisService;

	public JwtSecurityConfig(TokenProvider tokenProvider, RedisService redisService) {
		this.tokenProvider = tokenProvider;
		this.redisService = redisService;
	}

	@Override
	public void configure(HttpSecurity http) {
		http.addFilterBefore(
			new JwtFilter(tokenProvider, redisService),
			UsernamePasswordAuthenticationFilter.class
		);
	}
}
