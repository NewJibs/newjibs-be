package com.ssafy.newjibs.member.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.newjibs.member.jwt.dto.RefreshToken;

@Repository
public interface TokenRedisRepository extends CrudRepository<RefreshToken, String> {
	Optional<RefreshToken> findByEmail(String email);
	Optional<RefreshToken> findByAccessToken(String accessToken);
}
