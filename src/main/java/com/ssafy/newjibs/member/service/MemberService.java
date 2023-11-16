package com.ssafy.newjibs.member.service;

import static com.ssafy.newjibs.member.options.AuthorityConstant.*;

import java.util.Optional;

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
	private final S3Service s3Service;

	public MemberDto register(RegisterDto registerDto) {
		if (memberRepository.existsByEmail(registerDto.getEmail())) {
			throw new BaseException(ErrorCode.DUPLICATED_EMAIL);
		}

		Authority authority = Authority.builder()
			.authorityName(USER.getRole())
			.build();

		Member member = memberMapper.toEntity(registerDto, passwordEncoder.encode(registerDto.getPassword()),
			authority);



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

	private void saveImageUrl(Long memberId, String url) {
		Member member = memberRepository.findById(memberId)
			.orElseThrow(() -> new BaseException(ErrorCode.MEMBER_NOT_FOUND));
		if (member.getImageUrl() != null) {// if image already uploaded, delete image from s3
			s3Service.deleteImageFromS3(member.getImageUrl());
		}
		member.setImageUrl(url);
		memberRepository.save(member);// todo: dirty checking possibility
	}

	private Optional<String> deleteImageUrl(Long memberId) {
		Member member = memberRepository.findById(memberId)
			.orElseThrow(() -> new BaseException(ErrorCode.MEMBER_NOT_FOUND));
		String url = member.getImageUrl();
		if (url == null) {
			throw new BaseException(ErrorCode.IMAGE_DELETE_ERROR);
		}
		member.setImageUrl(null);
		memberRepository.save(member);// todo: dirty checking possibility
		return Optional.of(url);
	}
}
