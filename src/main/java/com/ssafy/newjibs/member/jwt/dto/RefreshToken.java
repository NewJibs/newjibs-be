package com.ssafy.newjibs.member.jwt.dto;

import org.springframework.data.annotation.Id;

import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
@RedisHash(value = "jwtToken", timeToLive = 60 * 60 * 24 * 3)// 3 days
public class RefreshToken {
	@Id
	private String email;

	private String refreshToken;

	@Indexed
	private String accessToken;
}
