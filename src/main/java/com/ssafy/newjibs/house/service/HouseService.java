package com.ssafy.newjibs.house.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.newjibs.exception.BaseException;
import com.ssafy.newjibs.exception.ErrorCode;
import com.ssafy.newjibs.house.dto.Coordinate;
import com.ssafy.newjibs.house.dto.FinalResultDto;
import com.ssafy.newjibs.house.dto.HouseDto;
import com.ssafy.newjibs.house.dto.HouseInfoDto;
import com.ssafy.newjibs.house.dto.PriceChangeDto;
import com.ssafy.newjibs.house.dto.ResultDto;
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

	public FinalResultDto findResultsByDealNo(List<Long> nos) {
		List<ResultDto> results = new ArrayList<>();
		long sumGapPrice = 0L;
		for (Long no : nos) {
			PriceChangeDto priceChangeDto = houseRepository.findPriceChangeByNo(no);
			HouseInfoDto houseInfoDto = houseRepository.findHouseInfo(no);
			ResultDto resultDto = new ResultDto(priceChangeDto, houseInfoDto);
			results.add(resultDto);

			sumGapPrice += priceChangeDto.getPriceGap();
		}

		updatePoint(sumGapPrice);

		return new FinalResultDto(sumGapPrice, results);
	}

	private void updatePoint(long point) {
		String email = SecurityUtil.getCurrentEmail().orElseThrow(() -> new BaseException(ErrorCode.MEMBER_NOT_FOUND));
		Member member = memberRepository.findByEmail(email)
			.orElseThrow(() -> new BaseException(ErrorCode.MEMBER_NOT_FOUND));
		long updatedPoint = member.getPoint() + point;

		if (updatedPoint < 0) {// set point to zero when point is minus
			member.setPoint(0L);
		}
		member.setPoint(updatedPoint);
	}
}
