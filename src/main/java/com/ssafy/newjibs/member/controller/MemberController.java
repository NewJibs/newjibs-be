package com.ssafy.newjibs.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.newjibs.member.dto.MemberDto;
import com.ssafy.newjibs.member.dto.RegisterDto;
import com.ssafy.newjibs.member.service.MemberService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/members")
public class MemberController {
	private final MemberService memberService;

	@ApiOperation(value = "회원가입")
	@PostMapping("/register")
	public ResponseEntity<MemberDto> register(@Valid @RequestBody RegisterDto registerDto) {
		return ResponseEntity.ok(memberService.register(registerDto));
	}

	@ApiOperation(value = "본인의 회원 정보를 가져온다.")
	@GetMapping("/member")
	@PreAuthorize("hasAnyRole('USER','ADMIN')")
	public ResponseEntity<MemberDto> getMyUserInfo(HttpServletRequest request) {
		return ResponseEntity.ok(memberService.getMyMemberWithAuthorities());
	}

	@ApiOperation(value = "특정 유저 정보를 가져온다.")
	@GetMapping("/member/{email}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<MemberDto> getUserInfo(@PathVariable String email) {
		return ResponseEntity.ok(memberService.getMemberWithAuthorities(email));
	}
}
