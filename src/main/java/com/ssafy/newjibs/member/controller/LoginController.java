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

import com.ssafy.newjibs.member.dto.LoginDto;
import com.ssafy.newjibs.member.jwt.JwtFilter;
import com.ssafy.newjibs.member.jwt.TokenProvider;
import com.ssafy.newjibs.member.jwt.dto.TokenDto;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/members")
public class LoginController {
	private final TokenProvider tokenProvider;
	private final AuthenticationManagerBuilder authenticationManagerBuilder;

	@ApiOperation(value = "로그인")
	@PostMapping("/login")
	public ResponseEntity<TokenDto> login(@Valid @RequestBody LoginDto loginDto) {
		UsernamePasswordAuthenticationToken authenticationToken =
			new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword());

		Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
		SecurityContextHolder.getContext().setAuthentication(authentication);

		String accessToken = tokenProvider.createToken(authentication);
		String refreshToken = tokenProvider.createRefreshToken(authentication.getName());

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + accessToken);

		return new ResponseEntity<>(new TokenDto(accessToken, refreshToken), httpHeaders, HttpStatus.OK);
	}
}
