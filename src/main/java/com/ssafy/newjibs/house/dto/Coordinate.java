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
public class Coordinate {
	@JsonProperty("aptCode")
	private Long aptCode;

	@JsonProperty("lat")
	private String lat;

	@JsonProperty("lng")
	private String lng;

	@JsonProperty("minDealAmount")
	private Long minDealAmount;

	@JsonProperty("maxDealAmount")
	private Long maxDealAmount;
}
