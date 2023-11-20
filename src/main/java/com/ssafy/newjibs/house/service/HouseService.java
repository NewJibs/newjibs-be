package com.ssafy.newjibs.house.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.newjibs.house.dto.Coordinate;
import com.ssafy.newjibs.house.dto.HouseDto;
import com.ssafy.newjibs.house.dto.HouseResultDto;
import com.ssafy.newjibs.house.repository.HouseRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class HouseService {
	private final HouseRepository houseRepository;

	public List<Coordinate> find2020CoordinatesWithMinMaxDealAmount() {
		return houseRepository.find2020CoordinatesWithMinMaxDealAmount();
	}

	public List<HouseDto> findHouseDtosByAptCode(Long aptCode) {
		return houseRepository.findHouseDtosByAptCodeFor2020(aptCode);
	}

	public Map<Long, HouseResultDto> findHouseResultDtoForGiven2020DealNo(List<Long> nos) {
		Map<Long, HouseResultDto> resultMap = new LinkedHashMap<>();
		for (Long no : nos) {
			HouseResultDto houseResultDto = houseRepository.findHouseResultDtoForGiven2020DealNo(no);
			houseResultDto.setResultGap(houseResultDto.getResultGap() - houseResultDto.getBeforeDealAmount());
			resultMap.put(no, houseResultDto);
		}
		return resultMap;
	}
}
