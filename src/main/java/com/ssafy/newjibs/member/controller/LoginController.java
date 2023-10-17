package com.ssafy.newjibs.member.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.newjibs.member.dto.LoginDto;
import com.ssafy.newjibs.member.service.MemberService;
import com.ssafy.newjibs.response.BaseResponse;
import com.ssafy.newjibs.response.ResponseStatus;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/users")
public class LoginController {
	private final MemberService memberService;

	@PostMapping("/login")
	public BaseResponse<String> login(@RequestBody LoginDto loginDto) {
		String token = memberService.login(loginDto);
		return new BaseResponse<>(ResponseStatus.SUCCESS, token);
	}
}
