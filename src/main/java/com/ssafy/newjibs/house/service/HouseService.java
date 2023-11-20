package com.ssafy.newjibs.house.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.newjibs.house.dto.Coordinate;
import com.ssafy.newjibs.house.dto.HouseDto;
import com.ssafy.newjibs.house.repository.DongCodeRepository;
import com.ssafy.newjibs.house.repository.HouseDealRepository;
import com.ssafy.newjibs.house.repository.HouseInfoRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class HouseService {
	private final DongCodeRepository dongcodeRepository;
	private final HouseInfoRepository houseInfoRepository;
	private final HouseDealRepository houseDealRepository;

	public List<Coordinate> find2020CoordinatesWithMinMaxDealAmount() {
		return houseDealRepository.find2020CoordinatesWithMinMaxDealAmount();
	}

	public List<HouseDto> findHouseDtosByAptCode(Long aptCode) {
		return houseDealRepository.findHouseDtosByAptCode(aptCode);
	}
}
