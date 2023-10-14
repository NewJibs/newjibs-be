package com.ssafy.newjibs.config.oauth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ssafy.newjibs.config.oauth.dto.SessionUser;
import com.ssafy.newjibs.member.domain.Member;
import com.ssafy.newjibs.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PrincipalDetailService implements UserDetailsService {

	private final MemberRepository memberRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Member principal = memberRepository.findByEmail(username)
			.orElseThrow(() -> {
				return new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다");
			});
		return new SessionUser(principal);
	}
}
