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
public class DealNoDto {
	@JsonProperty("2020")
	private Long no2020;

	@JsonProperty("2022")
	private Long no2022;
}
