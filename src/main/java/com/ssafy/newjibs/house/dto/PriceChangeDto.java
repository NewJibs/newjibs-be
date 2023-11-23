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
public class PriceChangeDto {
	@JsonProperty("beforeDealAmount")
	private Long beforeDealAmount;

	@JsonProperty("beforeDealYear")
	private Integer beforeDealYear;

	@JsonProperty("beforeDealMonth")
	private Integer beforeDealMonth;

	@JsonProperty("beforeDealDay")
	private Integer beforeDealDay;

	@JsonProperty("afterDealAmount")
	private Long afterDealAmount;

	@JsonProperty("afterDealYear")
	private Integer afterDealYear;

	@JsonProperty("afterDealMonth")
	private Integer afterDealMonth;

	@JsonProperty("afterDealDay")
	private Integer afterDealDay;

	@JsonProperty("priceGap")
	private Long priceGap;// afterDealAmount - beforeDealAmount

	@JsonProperty("percentageGap")
	private Double percentageGap;// priceGap / beforeDealAmount * 100

	public PriceChangeDto(Long beforeDealAmount, Integer beforeDealYear, Integer beforeDealMonth, Integer beforeDealDay,
		Long afterDealAmount, Integer afterDealYear, Integer afterDealMonth, Integer afterDealDay) {
		this.beforeDealAmount = beforeDealAmount;
		this.beforeDealYear = beforeDealYear;
		this.beforeDealMonth = beforeDealMonth;
		this.beforeDealDay = beforeDealDay;
		this.afterDealAmount = afterDealAmount;
		this.afterDealYear = afterDealYear;
		this.afterDealMonth = afterDealMonth;
		this.afterDealDay = afterDealDay;
		this.priceGap = afterDealAmount - beforeDealAmount;
		if (beforeDealAmount != 0) {
			double percentageCalc = (double)priceGap / beforeDealAmount * 100;
			this.percentageGap = Math.round(percentageCalc * 100) / 100.0;// rounding from the second decimal place
		} else {
			this.percentageGap = 0.0;
		}
	}
}
