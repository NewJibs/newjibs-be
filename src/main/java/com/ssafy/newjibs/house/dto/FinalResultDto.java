package com.ssafy.newjibs.house.dto;

import static com.ssafy.newjibs.house.constant.InvestmentConstant.*;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FinalResultDto {
	@JsonProperty("sumPriceGap")
	private Long sumPriceGap;

	@JsonProperty("sumPercentageGap")
	private Double sumPercentageGap;

	@JsonProperty("results")
	private List<ResultDto> results;

	public FinalResultDto(Long sumPriceGap, List<ResultDto> results) {
		this.sumPriceGap = sumPriceGap;
		if (sumPriceGap != 0) {
			double percentageCalc = (double)sumPriceGap / SUM_SEED_AMOUNT.getValue() * 100;
			this.sumPercentageGap = Math.round(percentageCalc * 100) / 100.0;// rounding from the second decimal place
		} else {
			this.sumPercentageGap = 0.0;
		}
		this.results = results;
	}
}
