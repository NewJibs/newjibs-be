package com.ssafy.newjibs.member.service;

import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.ssafy.newjibs.member.jwt.dto.RefreshToken;
import com.ssafy.newjibs.member.repository.TokenRedisRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class RedisService {

	private final TokenRedisRepository tokenRedisRepository;
	private final StringRedisTemplate stringRedisTemplate;

	public void save(String email, String refreshToken, String accessToken) {
		tokenRedisRepository.save(new RefreshToken(email, refreshToken, accessToken));
	}

	public void delete(String email) {
		tokenRedisRepository.deleteById(email);
	}

	public void addTokenToBlacklist(String token) {
		ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
		ops.set("blacklist:" + token, "true", 1, TimeUnit.HOURS);// expire after an hour
	}

	public boolean isTokenBlacklisted(String token) {
		ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
		return ops.get("blacklist:" + token) != null;
	}
}
