package com.ssafy.newjibs.house.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResultDto {
	@JsonProperty("priceChange")
	private PriceChangeDto priceChangeDto;

	@JsonProperty("houseInfo")
	private HouseInfoDto houseInfoDto;
}
