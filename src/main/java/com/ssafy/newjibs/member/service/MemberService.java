package com.ssafy.newjibs.member.service;

import static com.ssafy.newjibs.member.options.AuthorityConstant.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.newjibs.exception.BaseException;
import com.ssafy.newjibs.exception.ErrorCode;
import com.ssafy.newjibs.member.domain.Authority;
import com.ssafy.newjibs.member.domain.Member;
import com.ssafy.newjibs.member.dto.MemberInfoDto;
import com.ssafy.newjibs.member.dto.MemberSelfInfoDto;
import com.ssafy.newjibs.member.dto.MemberWithAuthDto;
import com.ssafy.newjibs.member.dto.RankDto;
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

	public MemberWithAuthDto register(RegisterDto registerDto) {
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

	public Member getMemberByEmail(String email) {
		return memberRepository.findByEmail(email).orElseThrow(() -> new BaseException(ErrorCode.MEMBER_NOT_FOUND));
	}

	public MemberWithAuthDto getMemberWithAuthorities(String email) {
		return memberMapper.toDto(
			memberRepository.findByEmail(email).orElseThrow(() -> new BaseException(ErrorCode.MEMBER_NOT_FOUND)));
	}

	public MemberSelfInfoDto getMyMemberInfo() {
		return memberMapper.toSelfInfoDto(SecurityUtil.getCurrentEmail()
			.flatMap(memberRepository::findByEmail)
			.orElseThrow(() -> new BaseException(ErrorCode.MEMBER_NOT_FOUND))
		);
	}

	public MemberInfoDto getMemberInfo(String email) {
		Member member = memberRepository.findByEmail(email)
			.orElseThrow(() -> new BaseException(ErrorCode.MEMBER_NOT_FOUND));

		if (member.getAuthorities().stream()
			.anyMatch(auth -> ADMIN.getRole()
				.equals(auth.getAuthorityName()))) {// forbidden if trying to see admin's info
			throw new BaseException(ErrorCode.ADMIN_NOT_ALLOWED);
		}

		return memberMapper.toInfoDto(member);
	}

	public void saveImageUrl(Long memberId, String url) {
		Member member = memberRepository.findById(memberId)
			.orElseThrow(() -> new BaseException(ErrorCode.MEMBER_NOT_FOUND));
		if (member.getImageUrl() != null) {// if image already uploaded, delete image from s3
			s3Service.deleteImageFromS3(member.getImageUrl());
		}
		member.setImageUrl(url);
	}

	public void deleteImageUrl(Long memberId) {
		Member member = memberRepository.findById(memberId)
			.orElseThrow(() -> new BaseException(ErrorCode.MEMBER_NOT_FOUND));
		String url = member.getImageUrl();
		if (url == null) {
			throw new BaseException(ErrorCode.IMAGE_DELETE_ERROR);
		}
		s3Service.deleteImageFromS3(url);
		member.setImageUrl(null);
	}

	public void withdraw() {
		String email = SecurityUtil.getCurrentEmail().orElseThrow(() -> new BaseException(ErrorCode.MEMBER_NOT_FOUND));
		memberRepository.findByEmail(email)
			.orElseThrow(() -> new BaseException(ErrorCode.MEMBER_NOT_FOUND))
			.setActivated(false);
	}

	public Map<Long, RankDto> getRanks() {
		List<Member> topTenMembers = memberRepository.findTop10MembersByPoint();
		Map<Long, RankDto> ranks = new LinkedHashMap<>();
		long rank = 1;
		for (Member member : topTenMembers) {
			ranks.put(rank++, memberMapper.toRankDto(member));
		}
		return ranks;
	}
}
