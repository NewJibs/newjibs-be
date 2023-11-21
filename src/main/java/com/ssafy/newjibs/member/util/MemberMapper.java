package com.ssafy.newjibs.member.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.ssafy.newjibs.member.domain.Authority;
import com.ssafy.newjibs.member.domain.Member;
import com.ssafy.newjibs.member.dto.AuthorityDto;
import com.ssafy.newjibs.member.dto.MemberWithAuthDto;
import com.ssafy.newjibs.member.dto.MemberSelfInfoDto;
import com.ssafy.newjibs.member.dto.RankDto;
import com.ssafy.newjibs.member.dto.RegisterDto;

@Component
public class MemberMapper {
	public Member toEntity(RegisterDto registerDto, String encPwd, Authority authority) {
		return Member.builder()
			.email(registerDto.getEmail())
			.password(encPwd)
			.name(registerDto.getName())
			.birth(LocalDate.parse(registerDto.getBirth()))
			.joinDate(LocalDateTime.now())
			// skip image
			.point(0L)// set init point to zero
			.authorities(Collections.singleton(authority))
			.activated(true)
			.build();
	}

	public MemberWithAuthDto toDto(Member member) {
		return MemberWithAuthDto.builder()
			.email(member.getEmail())
			.name(member.getName())
			.birth(member.getBirth())
			.joinDate(member.getJoinDate())
			.imageUrl(member.getImageUrl())
			.point(member.getPoint())
			.authorityDtoSet(member.getAuthorities().stream()
				.map(authority -> AuthorityDto.builder().authorityName(authority.getAuthorityName()).build())
				.collect(Collectors.toSet()))
			.build();
	}

	public MemberSelfInfoDto toInfoDto(Member member) {
		return MemberSelfInfoDto.builder()
			.email(member.getEmail())
			.name(member.getName())
			.birth(member.getBirth())
			.joinDate(member.getJoinDate())
			.imageUrl(member.getImageUrl())
			.point(member.getPoint())
			.build();
	}

	public RankDto toRankDto(Member member) {
		return RankDto.builder()
			.email(member.getEmail())
			.name(member.getName())
			.point(member.getPoint())
			.build();
	}
}
