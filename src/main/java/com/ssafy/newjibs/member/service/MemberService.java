package com.ssafy.newjibs.member.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.newjibs.member.repository.MemberRepository;
import com.ssafy.newjibs.member.util.MemberMapper;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
	private final MemberRepository memberRepository;
	private final MemberMapper memberMapper;

}
