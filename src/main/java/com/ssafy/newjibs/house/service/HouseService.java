package com.ssafy.newjibs.house.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.newjibs.exception.BaseException;
import com.ssafy.newjibs.exception.ErrorCode;
import com.ssafy.newjibs.house.dto.Coordinate;
import com.ssafy.newjibs.house.dto.HouseDto;
import com.ssafy.newjibs.house.dto.HouseResultDto;
import com.ssafy.newjibs.house.repository.HouseRepository;
import com.ssafy.newjibs.member.domain.Member;
import com.ssafy.newjibs.member.repository.MemberRepository;
import com.ssafy.newjibs.member.util.SecurityUtil;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class HouseService {
	private final HouseRepository houseRepository;
	private final MemberRepository memberRepository;

	public List<Coordinate> find2020CoordinatesWithMinMaxDealAmount() {
		return houseRepository.find2020CoordinatesWithMinMaxDealAmount();
	}

	public List<HouseDto> findHouseDtosByAptCode(Long aptCode) {
		return houseRepository.findHouseDtosByAptCodeFor2020(aptCode);
	}

	public Map<Long, HouseResultDto> findHouseResultDtoForGiven2020DealNo(List<Long> nos) {
		Map<Long, HouseResultDto> resultMap = new LinkedHashMap<>();
		long point = 0L;
		for (Long no : nos) {
			HouseResultDto houseResultDto = houseRepository.findHouseResultDtoForGiven2020DealNo(no);
			long gap = houseResultDto.getResultGap() - houseResultDto.getBeforeDealAmount();
			point += gap;
			houseResultDto.setResultGap(gap);
			resultMap.put(no, houseResultDto);
		}

		updatePoint(point);

		return resultMap;
	}

	private void updatePoint(long point) {
		String email = SecurityUtil.getCurrentEmail().orElseThrow(() -> new BaseException(ErrorCode.MEMBER_NOT_FOUND));
		Member member = memberRepository.findByEmail(email).orElseThrow(() -> new BaseException(ErrorCode.MEMBER_NOT_FOUND));
		long updatedPoint = member.getPoint() + point;

		if(updatedPoint < 0) {// set point to zero when point is minus
			member.setPoint(0L);
		}
		member.setPoint(updatedPoint);
	}
}
