package com.ssafy.newjibs.member.controller;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.newjibs.member.dto.RegisterDto;
import com.ssafy.newjibs.member.service.MemberService;
import com.ssafy.newjibs.response.BaseResponse;
import com.ssafy.newjibs.response.ResponseStatus;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {
	private final MemberService memberService;

	@PostMapping("/signup")
	public BaseResponse<Long> saveMember(@Valid @RequestBody RegisterDto registerDto) {
		return new BaseResponse<>(ResponseStatus.SUCCESS, memberService.register(registerDto));
	}
}
