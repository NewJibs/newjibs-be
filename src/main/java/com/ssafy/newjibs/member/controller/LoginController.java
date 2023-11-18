package com.ssafy.newjibs.member.controller;

import javax.validation.Valid;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.newjibs.exception.BaseException;
import com.ssafy.newjibs.exception.ErrorCode;
import com.ssafy.newjibs.member.domain.Member;
import com.ssafy.newjibs.member.dto.LoginDto;
import com.ssafy.newjibs.member.jwt.JwtFilter;
import com.ssafy.newjibs.member.jwt.TokenProvider;
import com.ssafy.newjibs.member.jwt.dto.AccessToken;
import com.ssafy.newjibs.member.jwt.dto.Tokens;
import com.ssafy.newjibs.member.service.MemberService;
import com.ssafy.newjibs.member.service.RedisService;
import com.ssafy.newjibs.member.service.RefreshService;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/members")
public class LoginController {
	private final TokenProvider tokenProvider;
	private final AuthenticationManagerBuilder authenticationManagerBuilder;
	private final RedisService redisService;
	private final RefreshService refreshService;
	private final MemberService memberService;

	@ApiOperation(value = "로그인")
	@PostMapping("/login")
	public ResponseEntity<AccessToken> login(@Valid @RequestBody LoginDto loginDto) {
		UsernamePasswordAuthenticationToken authenticationToken =
			new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword());

		Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
		SecurityContextHolder.getContext().setAuthentication(authentication);

		Member member = memberService.getMemberByEmail(loginDto.getEmail());

		// token creation
		Tokens tokens = tokenProvider.createTokens(member);
		String accessToken = tokens.getAccessToken();
		String refreshToken = tokens.getRefreshToken();

		redisService.save(loginDto.getEmail(), refreshToken, accessToken);

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + accessToken);

		return new ResponseEntity<>(new AccessToken(accessToken), httpHeaders, HttpStatus.OK);
	}

	@ApiOperation(value = "토큰 리프레시")
	@PostMapping("/refresh")
	public ResponseEntity<Tokens> refresh(@RequestBody Tokens tokens) {

		String refreshToken = tokens.getRefreshToken();

		if (refreshToken == null || !refreshToken.startsWith("Bearer ")) {
			throw new BaseException(ErrorCode.TOKEN_FORBIDDEN);
		}

		refreshToken = refreshToken.substring("Bearer ".length());
		Tokens refreshed = refreshService.refresh(refreshToken);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + refreshed.getAccessToken());

		return new ResponseEntity<>(refreshed, httpHeaders, HttpStatus.OK);
	}
}
