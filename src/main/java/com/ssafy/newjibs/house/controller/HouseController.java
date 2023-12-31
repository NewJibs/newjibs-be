package com.ssafy.newjibs.house.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.newjibs.house.dto.Coordinate;
import com.ssafy.newjibs.house.dto.FinalResultDto;
import com.ssafy.newjibs.house.dto.HouseDto;
import com.ssafy.newjibs.house.service.HouseService;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/houses")
public class HouseController {
	private final HouseService houseService;

	@ApiOperation(value = "전체 좌표 정보")
	@PreAuthorize("hasAnyRole('USER','ADMIN')")
	@GetMapping("/coordinates")
	public ResponseEntity<List<Coordinate>> getAllCoordinates() {
		return ResponseEntity.ok(houseService.find2020CoordinatesWithMinMaxDealAmount());
	}

	@ApiOperation(value = "특정 좌표의 집 정보 리스트")
	@PreAuthorize("hasAnyRole('USER','ADMIN')")
	@GetMapping("/{aptCode}")
	public ResponseEntity<List<HouseDto>> getHouses(@PathVariable Long aptCode) {
		return ResponseEntity.ok(houseService.findHouseDtosByAptCode(aptCode));
	}

	@ApiOperation(value = "구매한 부동산 목록에 대한 투자 결과")
	@PreAuthorize("hasAnyRole('USER','ADMIN')")
	@PostMapping("/results")
	public ResponseEntity<FinalResultDto> getResults(@RequestBody List<Long> nos) {
		return ResponseEntity.ok(houseService.findResultsByDealNo(nos));
	}
}
