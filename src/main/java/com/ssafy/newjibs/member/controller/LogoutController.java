package com.ssafy.newjibs.member.controller;

import static com.ssafy.newjibs.member.jwt.JwtFilter.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.newjibs.member.jwt.TokenProvider;
import com.ssafy.newjibs.member.service.RedisService;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/members")
public class LogoutController {
	private final RedisService redisService;
	private final TokenProvider tokenProvider;

	@ApiOperation(value = "로그아웃")
	@PostMapping("/logout")
	public ResponseEntity<Void> logout(HttpServletRequest request, HttpServletResponse response) {
		String authorizationHeader = request.getHeader(AUTHORIZATION_HEADER);
		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			String accessToken = authorizationHeader.substring("Bearer ".length());
			if (tokenProvider.validateToken(accessToken)) {
				redisService.addTokenToBlacklist(accessToken);
				Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
				if (authentication != null) {
					new SecurityContextLogoutHandler().logout(request, response, authentication);
				}
				redisService.delete(tokenProvider.getEmail(accessToken));
				SecurityContextHolder.clearContext(); // clear Security Context
			}
		}
		return ResponseEntity.ok().build();
	}
}
