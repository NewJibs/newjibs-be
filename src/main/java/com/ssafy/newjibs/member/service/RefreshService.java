package com.ssafy.newjibs.member.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ssafy.newjibs.exception.BaseException;
import com.ssafy.newjibs.exception.ErrorCode;
import com.ssafy.newjibs.member.domain.Member;
import com.ssafy.newjibs.member.jwt.TokenProvider;
import com.ssafy.newjibs.member.jwt.dto.RefreshToken;
import com.ssafy.newjibs.member.jwt.dto.Tokens;
import com.ssafy.newjibs.member.repository.MemberRepository;
import com.ssafy.newjibs.member.repository.TokenRedisRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class RefreshService {
	private final TokenProvider tokenProvider;

	private final TokenRedisRepository tokenRedisRepository;

	private final MemberRepository memberRepository;

	@Value("${jwt.secret}")
	private String secret;

	public Tokens refresh(String refreshToken) {
		try {
			JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret)).build();
			DecodedJWT decodedJWT = verifier.verify(refreshToken);

			long now = System.currentTimeMillis();
			String email = decodedJWT.getSubject();

			RefreshToken redisData = tokenRedisRepository.findByEmail(email)
				.orElseThrow(() -> new BaseException(ErrorCode.TOKEN_FORBIDDEN));
			String redisRefreshToken = redisData.getRefreshToken();

			if (!redisRefreshToken.equals(refreshToken)) {
				throw new BaseException(ErrorCode.TOKEN_FORBIDDEN);
			}

			long refreshExpireTime = decodedJWT.getClaim("exp").asLong() * 1000;

			long diffDays = (refreshExpireTime - now) / 1000 / (24 * 3600);
			// long diffMin = (refreshExpireTime - now) / 1000 / 60;
			Member member = memberRepository.findByEmail(email)
				.orElseThrow(() -> new BaseException(ErrorCode.MEMBER_NOT_FOUND));
			Tokens tokens;

			if (diffDays < 3) {
				log.info("refresh token reissued.");
				tokens = tokenProvider.createTokens(member);
				tokenRedisRepository.save(
					new RefreshToken(email, tokens.getRefreshToken(), tokens.getAccessToken()));
			} else {
				tokens = new Tokens(tokenProvider.createToken(member), refreshToken);
			}
			return tokens;
		} catch (JWTDecodeException | TokenExpiredException e) {
			throw new BaseException(ErrorCode.MEMBER_NOT_FOUND);
		}
	}

}
