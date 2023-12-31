package com.ssafy.newjibs.member.controller;

import java.io.IOException;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.newjibs.member.dto.MemberInfoDto;
import com.ssafy.newjibs.member.dto.MemberSelfInfoDto;
import com.ssafy.newjibs.member.dto.MemberWithAuthDto;
import com.ssafy.newjibs.member.dto.RankDto;
import com.ssafy.newjibs.member.dto.RegisterDto;
import com.ssafy.newjibs.member.service.MemberService;
import com.ssafy.newjibs.member.service.S3Service;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/members")
public class MemberController {
	private final MemberService memberService;
	private final S3Service s3Service;

	@ApiOperation(value = "회원가입")
	@PostMapping("/register")
	public ResponseEntity<MemberWithAuthDto> register(@Valid @RequestBody RegisterDto registerDto) {
		return ResponseEntity.ok(memberService.register(registerDto));
	}

	@ApiOperation(value = "프로필 사진 업로드")
	@PostMapping("/profile")
	@PreAuthorize("hasAnyRole('USER','ADMIN')")
	public ResponseEntity<Void> uploadImage(@RequestPart(value = "image", required = false) MultipartFile multipartFile) throws IOException {
		memberService.saveImageUrl(s3Service.uploadImage(multipartFile));
		return ResponseEntity.ok().build();
	}

	@ApiOperation(value = "프로필 사진 삭제")
	@DeleteMapping("/profile")
	@PreAuthorize("hasAnyRole('USER','ADMIN')")
	public ResponseEntity<Void> deleteImage() {
		memberService.deleteImageUrl();
		return ResponseEntity.ok().build();
	}

	@ApiOperation(value = "본인의 회원 정보 조회")
	@GetMapping("/member")
	@PreAuthorize("hasAnyRole('USER','ADMIN')")
	public ResponseEntity<MemberSelfInfoDto> getMyUserInfo() {
		return ResponseEntity.ok(memberService.getMyMemberInfo());
	}

	@ApiOperation(value = "다른 유저의 회원 정보 조회")
	@GetMapping("/{email}")
	@PreAuthorize("hasAnyRole('USER','ADMIN')")
	public ResponseEntity<MemberInfoDto> getUserInfo(@PathVariable String email) {
		return ResponseEntity.ok(memberService.getMemberInfo(email));
	}

	@ApiOperation(value = "관리자가 특정 유저의 회원 정보를 조회")
	@GetMapping("/member/{email}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<MemberWithAuthDto> getUserInfoByAdmin(@PathVariable String email) {
		return ResponseEntity.ok(memberService.getMemberWithAuthorities(email));
	}

	@ApiOperation(value = "회원 탈퇴")
	@DeleteMapping("/member/withdrawal")
	@PreAuthorize("hasAnyRole('USER')")
	public ResponseEntity<Void> withdraw() {
		memberService.withdraw();
		return ResponseEntity.ok().build();
	}

	@ApiOperation(value = "1 ~ 10위 랭킹 조회")
	@GetMapping("/ranks")
	public ResponseEntity<Map<Long, RankDto>> getRanks() {
		return ResponseEntity.ok(memberService.getRanks());
	}
}
