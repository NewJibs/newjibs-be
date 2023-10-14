package com.ssafy.newjibs.member.service;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.newjibs.config.jwt.JwtTokenProvider;
import com.ssafy.newjibs.member.domain.Member;
import com.ssafy.newjibs.member.dto.LoginDto;
import com.ssafy.newjibs.member.dto.RegisterDto;
import com.ssafy.newjibs.member.repository.MemberRepository;
import com.ssafy.newjibs.member.util.MemberMapper;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
	private final MemberRepository memberRepository;
	private final MemberMapper memberMapper;
	private final BCryptPasswordEncoder encoder;
	private final AuthenticationManagerBuilder authenticationManagerBuilder;
	private final JwtTokenProvider jwtTokenProvider;

	public Long register(RegisterDto registerDto) {
		boolean isExist = memberRepository.existsByEmail(registerDto.getEmail());
		if (isExist) {
			// throw 409
		}
		String encPwd = encoder.encode(registerDto.getPassword());
		Member member = memberRepository.save(memberMapper.toEntity(registerDto, encPwd));

		return member.getMemberId();
	}

	public String login(LoginDto loginDto) {
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
			loginDto.getEmail(), loginDto.getPassword());

		// verification
		Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

		// generate jwt token and return
		return jwtTokenProvider.generateToken(authentication);
	}
}
