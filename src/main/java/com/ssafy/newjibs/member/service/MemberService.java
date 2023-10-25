package com.ssafy.newjibs.member.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.newjibs.exception.BaseException;
import com.ssafy.newjibs.exception.ErrorCode;
import com.ssafy.newjibs.member.domain.Authority;
import com.ssafy.newjibs.member.domain.Member;
import com.ssafy.newjibs.member.dto.MemberDto;
import com.ssafy.newjibs.member.dto.RegisterDto;
import com.ssafy.newjibs.member.repository.MemberRepository;
import com.ssafy.newjibs.member.util.MemberMapper;
import com.ssafy.newjibs.member.util.SecurityUtil;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
	private final MemberRepository memberRepository;
	private final PasswordEncoder passwordEncoder;
	private final MemberMapper memberMapper;

	public MemberDto register(RegisterDto registerDto) {
		if (memberRepository.existsByEmail(registerDto.getEmail())) {
			throw new BaseException(ErrorCode.DUPLICATED_EMAIL);
		}

		Authority authority = Authority.builder()
			.authorityName("ROLE_USER")
			.build();

		Member member = memberMapper.toEntity(registerDto, passwordEncoder.encode(registerDto.getPassword()), authority);

		return memberMapper.toDto(memberRepository.save(member));
	}

	public MemberDto getMemberWithAuthorities(String email) {
		return memberMapper.toDto(
			memberRepository.findByEmail(email).orElseThrow(() -> new BaseException(ErrorCode.MEMBER_NOT_FOUND)));
	}

	public MemberDto getMyMemberWithAuthorities() {
		return memberMapper.toDto(SecurityUtil.getCurrentEmail()
			.flatMap(memberRepository::findOneWithAuthoritiesByEmail)
			.orElseThrow(() -> new BaseException(ErrorCode.MEMBER_NOT_FOUND))
		);
	}
}
