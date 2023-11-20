package com.ssafy.newjibs.house.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.newjibs.house.dto.Coordinate;
import com.ssafy.newjibs.house.dto.HouseDto;
import com.ssafy.newjibs.house.dto.HouseResultDto;
import com.ssafy.newjibs.house.service.HouseService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/houses")
public class HouseController {
	private final HouseService houseService;

	@GetMapping("/coordinates")
	public ResponseEntity<List<Coordinate>> getAllCoordinates() {
		return ResponseEntity.ok(houseService.find2020CoordinatesWithMinMaxDealAmount());
	}

	@GetMapping("/{aptCode}")
	public ResponseEntity<List<HouseDto>> getHouses(@PathVariable Long aptCode) {
		return ResponseEntity.ok(houseService.findHouseDtosByAptCode(aptCode));
	}

	@GetMapping("/results")
	public ResponseEntity<Map<Long, HouseResultDto>> getResults(@RequestBody List<Long> nos) {
		return ResponseEntity.ok(houseService.findHouseResultDtoForGiven2020DealNo(nos));
	}
}
