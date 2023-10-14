package com.ssafy.newjibs.member.util;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.ssafy.newjibs.member.domain.Member;
import com.ssafy.newjibs.member.dto.RegisterDto;
import com.ssafy.newjibs.member.options.Role;

@Component
public class MemberMapper {
	public Member toEntity(RegisterDto registerDto, String encPwd) {
		return Member.builder()
			.email(registerDto.getEmail())
			.password(encPwd)
			.name(registerDto.getName())
			.birth(LocalDate.parse(registerDto.getBirth()))
			.joinDate(LocalDateTime.now())
			.memberImage(registerDto.getMemberImage())
			.role(Role.ROLE_USER)
			.build();
	}
}
