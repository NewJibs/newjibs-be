package com.ssafy.newjibs.member.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.ssafy.newjibs.member.domain.Authority;
import com.ssafy.newjibs.member.domain.Member;
import com.ssafy.newjibs.member.dto.AuthorityDto;
import com.ssafy.newjibs.member.dto.MemberDto;
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
			.memberImage(registerDto.getMemberImage())
			.authorities(Collections.singleton(authority))
			.activated(true)
			.build();
	}

	public MemberDto toDto(Member member) {
		return MemberDto.builder()
			.email(member.getEmail())
			.name(member.getName())
			.birth(member.getBirth())
			.joinDate(member.getJoinDate())
			.memberImage(member.getMemberImage())
			.authorityDtoSet(member.getAuthorities().stream()
				.map(authority -> AuthorityDto.builder().authorityName(authority.getAuthorityName()).build())
				.collect(Collectors.toSet()))
			.build();
	}
}
