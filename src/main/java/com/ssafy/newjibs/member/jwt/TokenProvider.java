package com.ssafy.newjibs.member.jwt;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import com.ssafy.newjibs.member.domain.Authority;
import com.ssafy.newjibs.member.domain.Member;
import com.ssafy.newjibs.member.jwt.dto.Tokens;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class TokenProvider {
	private final Logger logger = LoggerFactory.getLogger(TokenProvider.class);
	private static final String AUTHORITIES_KEY = "auth";

	@Value("${jwt.access-token-validity-in-seconds}")
	private long accessTokenExpireTime;

	@Value("${jwt.refresh-token-validity-in-seconds}")
	private long refreshTokenExpireTime;

	@Value("${jwt.secret}")
	private String secret;

	private Key key;

	@PostConstruct
	public void init() {
		byte[] keyBytes = Decoders.BASE64.decode(secret);
		this.key = Keys.hmacShaKeyFor(keyBytes);
	}

	public String createToken(Member member) {
		Claims claims = Jwts.claims().setSubject(member.getEmail()); // subject
		Date now = new Date();

		// authorities to string
		String authorities = member.getAuthorities().stream()
			.map(Authority::getAuthorityName) // get name from authority
			.collect(Collectors.joining(","));

		// create token
		return Jwts.builder()
			.setClaims(claims) // save info
			.claim(AUTHORITIES_KEY, authorities) // add authority
			.setIssuedAt(now) // token issue time
			.setExpiration(new Date(now.getTime() + accessTokenExpireTime * 1000)) // token expire time
			.signWith(key, SignatureAlgorithm.HS256) // signature
			.compact();
	}

	// create both of tokens(access, refresh)
	public Tokens createTokens(Member member) {
		long now = System.currentTimeMillis();
		Date accessTokenValidity = new Date(now + this.accessTokenExpireTime * 1000);

		// authorities to string
		String authorities = member.getAuthorities().stream()
			.map(Authority::getAuthorityName)
			.collect(Collectors.joining(","));

		// create access-token with authority
		String accessToken = Jwts.builder()
			.setSubject(member.getEmail())
			.claim(AUTHORITIES_KEY, authorities)
			.signWith(key, SignatureAlgorithm.HS256)
			.setExpiration(accessTokenValidity)
			.compact();

		// create refresh-token without authority)
		Date refreshTokenValidity = new Date(now + this.refreshTokenExpireTime * 1000);
		String refreshToken = Jwts.builder()
			.setSubject(member.getEmail())
			.signWith(key, SignatureAlgorithm.HS256)
			.setExpiration(refreshTokenValidity)
			.compact();

		return new Tokens(accessToken, refreshToken);
	}

	public Authentication getAuthentication(String token) {
		Claims claims = Jwts.parserBuilder()
			.setSigningKey(key)
			.build()
			.parseClaimsJws(token)
			.getBody();

		Collection<? extends GrantedAuthority> authorities =
			Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());

		User principal = new User(claims.getSubject(), "", authorities);

		return new UsernamePasswordAuthenticationToken(principal, token, authorities);
	}

	public String getEmail(String token) {
		// find email from token
		return Jwts.parserBuilder()
			.setSigningKey(key)
			.build()
			.parseClaimsJws(token)
			.getBody()
			.getSubject(); // username
	}

	public boolean validateToken(String token) {
		logger.info("TOKEN : " + token);
		try {
			Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
			return true;
		} catch (SecurityException | MalformedJwtException e) {
			logger.info("잘못된 JWT 서명입니다.");
			logger.info(e.getMessage());
		} catch (ExpiredJwtException e) {
			logger.info("만료된 JWT 토큰입니다.");
			logger.info(e.getMessage());
		} catch (UnsupportedJwtException e) {
			logger.info("지원되지 않는 JWT 토큰입니다.");
			logger.info(e.getMessage());
		} catch (IllegalArgumentException e) {
			logger.info("JWT 토큰이 잘못되었습니다.");
			logger.info(e.getMessage());
		}
		return false;
	}
}
